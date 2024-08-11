package org.toxsoft.skf.tags.lib.impl;

import static org.toxsoft.skf.tags.lib.impl.ISkTagServiceInternalHardConstants.*;

import org.toxsoft.core.tslib.bricks.ctx.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.strid.coll.impl.*;
import org.toxsoft.core.tslib.bricks.validator.impl.*;
import org.toxsoft.core.tslib.coll.*;
import org.toxsoft.core.tslib.coll.helpers.*;
import org.toxsoft.core.tslib.gw.skid.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.tags.lib.*;
import org.toxsoft.uskat.core.*;
import org.toxsoft.uskat.core.devapi.*;
import org.toxsoft.uskat.core.impl.*;
import org.toxsoft.uskat.core.impl.dto.*;

/**
 * {@link ISkTagService} implementation.
 *
 * @author hazard157
 */
public class SkTagService
    extends AbstractSkService
    implements ISkTagService {

  /**
   * Service creator singleton.
   */
  public static final ISkServiceCreator<AbstractSkService> CREATOR = SkTagService::new;

  private final ClassClaimingCoreValidator claimingValidator = new ClassClaimingCoreValidator();
  private final InternalValidationSupport  validationSupport = new InternalValidationSupport();

  private final InternalEventer    eventer;
  private final SkTagMarkManager   markManager;
  private final SkTagGroupsManager groupsManager;

  /**
   * Constructor.
   *
   * @param aCoreApi {@link IDevCoreApi} - owner core API implementation
   */
  SkTagService( IDevCoreApi aCoreApi ) {
    super( SERVICE_ID, aCoreApi );
    eventer = new InternalEventer( this );
    validationSupport.addValidator( new InternalBuiltinValidator( this ) );
    markManager = new SkTagMarkManager( this );
    groupsManager = new SkTagGroupsManager( this );
  }

  // ------------------------------------------------------------------------------------
  // AbstractSkService
  //

  @Override
  protected void doInit( ITsContextRo aArgs ) {
    // class TAG
    sysdescr().defineClass( CLSINF_TAG );
    objServ().registerObjectCreator( CLSID_TAG, SkTag.CREATOR );
    // class TAG_GROUP
    sysdescr().defineClass( CLSINF_TAG_GROUP );
    objServ().registerObjectCreator( CLSID_TAG_GROUP, SkTagGroup.CREATOR );
    // claim on classes
    sysdescr().svs().addValidator( claimingValidator );
    objServ().svs().addValidator( claimingValidator );
    linkService().svs().addValidator( claimingValidator );
    clobService().svs().addValidator( claimingValidator );
  }

  @Override
  protected void doClose() {
    // nop
  }

  @Override
  protected boolean doIsClassClaimedByService( String aClassId ) {
    return aClassId.startsWith( CLSID_PREFIX );
  }

  // ------------------------------------------------------------------------------------
  // package API
  //

  void pauseCoreValidationAndEvents() {
    sysdescr().svs().pauseValidator( claimingValidator );
    objServ().svs().pauseValidator( claimingValidator );
    linkService().svs().pauseValidator( claimingValidator );
    clobService().svs().pauseValidator( claimingValidator );
    sysdescr().eventer().pauseFiring();
    objServ().eventer().pauseFiring();
    linkService().eventer().pauseFiring();
    clobService().eventer().pauseFiring();
  }

  void resumeCoreValidationAndEvents() {
    sysdescr().svs().resumeValidator( claimingValidator );
    objServ().svs().resumeValidator( claimingValidator );
    linkService().svs().resumeValidator( claimingValidator );
    clobService().svs().resumeValidator( claimingValidator );
    sysdescr().eventer().resumeFiring( true );
    objServ().eventer().resumeFiring( true );
    linkService().eventer().resumeFiring( true );
    clobService().eventer().resumeFiring( true );
  }

  // ------------------------------------------------------------------------------------
  // ISkTagService
  //

  @Override
  public ISkTag findTag( String aTagId ) {
    Skid skid = new Skid( CLSID_TAG, aTagId );
    return coreApi().objService().find( skid );
  }

  @SuppressWarnings( { "unchecked", "rawtypes" } )
  @Override
  public IStridablesList<ISkTag> listTags() {
    IList<SkTag> ll = objServ().listObjs( CLSID_TAG, false );
    return new StridablesList<>( (IList)ll );
  }

  @Override
  public ISkTag defineTag( IDtoTagInfo aTagInfo ) {
    TsNullArgumentRtException.checkNull( aTagInfo );
    // validation
    Skid skid = new Skid( CLSID_TAG, aTagInfo.id() );
    ISkTag oldTag = objServ().find( skid );
    if( oldTag == null ) { //
      TsValidationFailedRtException.checkError( svs().validator().canCreateTag( this, aTagInfo ) );
    }
    else {
      TsValidationFailedRtException.checkError( svs().validator().canEditTag( this, oldTag, aTagInfo ) );
    }
    // create tag Sk-object
    pauseCoreValidationAndEvents();
    try {
      DtoObject tagObjDto = DtoObject.createDtoObject( skid, coreApi() );
      DtoTagInfo.updateObjDtoFromTag( tagObjDto, aTagInfo );
      ISkTag tag = objServ().defineObject( tagObjDto );
      ECrudOp op = oldTag != null ? ECrudOp.EDIT : ECrudOp.CREATE;
      eventer.fireTagChanged( op, tag.id() );
      return tag;
    }
    finally {
      resumeCoreValidationAndEvents();
    }
  }

  @Override
  public void removeTag( String aTagId ) {
    TsValidationFailedRtException.checkError( svs().validator().canRemoveTag( this, aTagId ) );
    Skid skid = new Skid( CLSID_TAG, aTagId );
    if( objServ().find( skid ) != null ) {
      pauseCoreValidationAndEvents();
      try {
        objServ().removeObject( skid );
        eventer.fireTagChanged( ECrudOp.REMOVE, aTagId );
      }
      finally {
        resumeCoreValidationAndEvents();
      }
    }
  }

  @Override
  public ISkTagMarkManager markMan() {
    return markManager;
  }

  @Override
  public ISkTagGroupsManager groupMan() {
    return groupsManager;
  }

  @Override
  public InternalValidationSupport svs() {
    return validationSupport;
  }

  @Override
  public InternalEventer eventer() {
    return eventer;
  }

}
