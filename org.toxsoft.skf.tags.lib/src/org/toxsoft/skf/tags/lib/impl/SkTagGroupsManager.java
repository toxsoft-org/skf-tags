package org.toxsoft.skf.tags.lib.impl;

import static org.toxsoft.skf.tags.lib.impl.ISkTagServiceInternalHardConstants.*;

import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.strid.coll.impl.*;
import org.toxsoft.core.tslib.bricks.validator.impl.*;
import org.toxsoft.core.tslib.gw.skid.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.tags.lib.*;
import org.toxsoft.uskat.core.api.objserv.*;

/**
 * {@link ISkTagGroupsManager} implementation
 *
 * @author hazard157
 */
class SkTagGroupsManager
    implements ISkTagGroupsManager {

  private final SkTagService owner;

  SkTagGroupsManager( SkTagService aOwner ) {
    owner = aOwner;
  }

  // ------------------------------------------------------------------------------------
  // implementation
  //

  // ------------------------------------------------------------------------------------
  // ISkTagGroupsManager
  //

  @Override
  public SkTagGroup findBundle( String aBundleId ) {
    Skid skid = new Skid( CLSID_TAG_GROUP, aBundleId );
    SkTagGroup g = owner.objServ().find( skid );
    if( g != null ) {
      if( g.isRootGroup() ) {
        return g;
      }
    }
    return null;
  }

  @Override
  public SkTagGroup findGroup( String aGroupId ) {
    Skid skid = new Skid( CLSID_TAG_GROUP, aGroupId );
    return owner.objServ().find( skid );
  }

  @Override
  public IStridablesList<ISkTagGroup> listBundles() {
    IStridablesListEdit<ISkTagGroup> llBundles = new StridablesList<>();
    for( ISkObject o : owner.objServ().listObjs( CLSID_TAG_GROUP, false ) ) {
      SkTagGroup g = (SkTagGroup)o;
      if( g.isRootGroup() ) {
        llBundles.add( g );
      }
    }
    return llBundles;
  }

  @Override
  public ISkTagGroup defineBundle( IDtoTagInfo aBundleInfo ) {
    TsNullArgumentRtException.checkNull( aBundleInfo );
    SkTagGroup bundle = findBundle( aBundleInfo.id() );
    if( bundle == null ) {
      TsValidationFailedRtException.checkError( owner.svs().validator().canCreateBundle( owner, aBundleInfo ) );
    }
    else {
      TsValidationFailedRtException.checkError( owner.svs().validator().canEditBundle( owner, bundle, aBundleInfo ) );
    }

    // TODO Auto-generated method stub
    // TODO реализовать SkTagGroupsManager.defineBundle()
    throw new TsUnderDevelopmentRtException( "SkTagGroupsManager.defineBundle()" );

  }

  @Override
  public void removeBundle( String aBundleId ) {
    // TODO Auto-generated method stub

  }

  @Override
  public ISkTagGroup defineGroup( ISkTagGroup aParent, IDtoTagInfo aGroupInfo ) {
    // TODO Auto-generated method stub

    // TODO реализовать SkTagGroupsManager.defineGroup()
    throw new TsUnderDevelopmentRtException( "SkTagGroupsManager.defineGroup()" );

  }

  @Override
  public void removeGroup( ISkTagGroup aParent, String aGroupId ) {
    // TODO Auto-generated method stub

  }

  @Override
  public void addTag( ISkTagGroup aGroup, String aTagId ) {
    // TODO Auto-generated method stub

  }

  @Override
  public void removeTag( ISkTagGroup aGroup, String aTagId ) {
    // TODO Auto-generated method stub

  }

}
