package org.toxsoft.skf.tags.lib_old.impl;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.bricks.ctx.*;
import org.toxsoft.core.tslib.bricks.events.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.bricks.validator.impl.*;
import org.toxsoft.core.tslib.coll.helpers.*;
import org.toxsoft.core.tslib.coll.primtypes.*;
import org.toxsoft.core.tslib.gw.gwid.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.tags.lib.*;
import org.toxsoft.uskat.core.*;
import org.toxsoft.uskat.core.devapi.*;
import org.toxsoft.uskat.core.impl.*;

/**
 * {@link ISkTagsService} implementation.
 *
 * @author hazard157
 */
public class SkTagsService
    extends AbstractSkService
    implements ISkTagsService {

  /**
   * Service creator singleton.
   */
  public static final ISkServiceCreator<AbstractSkService> CREATOR = SkTagsService::new;

  /**
   * {@link ISkTagsService#svs()} implementation.
   *
   * @author hazard157
   */
  static class Svs
      extends AbstractTsValidationSupport<ISkTagsServiceValidator>
      implements ISkTagsServiceValidator {

    @Override
    public ISkTagsServiceValidator validator() {
      return this;
    }

    @Override
    public ValidationResult canCreateChildTag( ISkTag aParent, String aLocalId, String aName, String aDescription,
        boolean aUnmarkable, IOptionSet aParams ) {
      TsNullArgumentRtException.checkNulls( aParent, aLocalId, aName, aDescription, aParams );
      ValidationResult vr = ValidationResult.SUCCESS;
      for( ISkTagsServiceValidator v : validatorsList() ) {
        vr = ValidationResult.firstNonOk( vr,
            v.canCreateChildTag( aParent, aLocalId, aName, aDescription, aUnmarkable, aParams ) );
        if( vr.isError() ) {
          break;
        }
      }
      return vr;
    }

    @Override
    public ValidationResult canCreateRootTag( String aRootId, String aName, String aDescription, boolean aUnmarkable,
        IOptionSet aParams ) {
      TsNullArgumentRtException.checkNulls( aRootId, aName, aDescription, aParams );
      ValidationResult vr = ValidationResult.SUCCESS;
      for( ISkTagsServiceValidator v : validatorsList() ) {
        vr = ValidationResult.firstNonOk( vr,
            v.canCreateRootTag( aRootId, aName, aDescription, aUnmarkable, aParams ) );
        if( vr.isError() ) {
          break;
        }
      }
      return vr;
    }

    @Override
    public ValidationResult canEditTagId( String aFullId, String aNewFullId ) {
      TsNullArgumentRtException.checkNulls( aFullId, aNewFullId );
      ValidationResult vr = ValidationResult.SUCCESS;
      for( ISkTagsServiceValidator v : validatorsList() ) {
        vr = ValidationResult.firstNonOk( vr, v.canEditTagId( aFullId, aNewFullId ) );
        if( vr.isError() ) {
          break;
        }
      }
      return vr;
    }

    @Override
    public ValidationResult canRemoveTag( String aFullId ) {
      TsNullArgumentRtException.checkNull( aFullId );
      ValidationResult vr = ValidationResult.SUCCESS;
      for( ISkTagsServiceValidator v : validatorsList() ) {
        vr = ValidationResult.firstNonOk( vr, v.canRemoveTag( aFullId ) );
        if( vr.isError() ) {
          break;
        }
      }
      return vr;
    }

  }

  /**
   * {@link ISkTagsService#eventer()} implementation.
   *
   * @author hazard157
   */
  static class Eventer
      extends AbstractTsEventer<ISkTagsServiceListener> {

    @Override
    protected boolean doIsPendingEvents() {
      // TODO Auto-generated method stub
      return false;
    }

    @Override
    protected void doFirePendingEvents() {
      // TODO Auto-generated method stub
    }

    @Override
    protected void doClearPendingEvents() {
      // TODO Auto-generated method stub
    }

    void fireTagsTreeChanged( ISkRootTag aChangesRoot ) {
      // TODO SkTagsService.Eventer.fireTagsTreeChanged()
    }

    void fireTagPropsChanged( ISkTag aChangedTag ) {
      // TODO SkTagsService.Eventer.fireTagPropsChanged()
    }

    void onTagsRootsChanged( ISkTagsService aSource, ECrudOp aOp, String aRootId ) {
      // TODO SkTagsService.Eventer.onTagsRootsChanged()
    }

  }

  private final ISkTagsServiceValidator builtinValidator = new ISkTagsServiceValidator() {

    @Override
    public ValidationResult canCreateChildTag( ISkTag aParent, String aLocalId, String aName, String aDescription,
        boolean aUnmarkable, IOptionSet aParams ) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public ValidationResult canCreateRootTag( String aRootId, String aName, String aDescription, boolean aUnmarkable,
        IOptionSet aParams ) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public ValidationResult canEditTagId( String aFullId, String aNewFullId ) {
      // TODO Auto-generated method stub
      return null;
    }

    @Override
    public ValidationResult canRemoveTag( String aFullId ) {
      // TODO Auto-generated method stub
      return null;
    }

  };

  private static final Svs     svs     = new Svs();
  private static final Eventer eventer = new Eventer();

  /**
   * Constructor.
   *
   * @param aCoreApi {@link IDevCoreApi} - owner core API implementation
   */
  public SkTagsService( IDevCoreApi aCoreApi ) {
    super( SERVICE_ID, aCoreApi );
    svs.addValidator( builtinValidator );
  }

  // ------------------------------------------------------------------------------------
  // AbstractSkService
  //

  @Override
  protected void doInit( ITsContextRo aArgs ) {
    // TODO Auto-generated method stub
  }

  @Override
  protected void doClose() {
    // TODO Auto-generated method stub
  }

  // ------------------------------------------------------------------------------------
  // ISkTagsService
  //

  @Override
  public IStringList getMarkTagIds( Gwid aGwid ) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IStridablesList<ISkRootTag> listRoots() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ISkRootTag findHierarchyRoot( String aTagFullId ) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ISkTag findTag( String aTagFullId ) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ISkTag createChildTag( ISkTag aParent, String aLocalId, String aName, String aDescription, boolean aUnmarkable,
      IOptionSet aParams ) {
    TsValidationFailedRtException
        .checkError( svs.canCreateChildTag( aParent, aLocalId, aName, aDescription, aUnmarkable, aParams ) );
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ISkTag createRootTag( String aRootId, String aName, String aDescription, boolean aUnmarkable,
      IOptionSet aParams ) {
    TsValidationFailedRtException
        .checkError( svs.canCreateRootTag( aRootId, aName, aDescription, aUnmarkable, aParams ) );
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public <T extends ISkTag> T editTagId( String aFullId, String aNewFullId ) {
    TsValidationFailedRtException.checkError( svs.canEditTagId( aFullId, aNewFullId ) );
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void removeTag( String aFullId ) {
    TsValidationFailedRtException.checkError( svs.canRemoveTag( aFullId ) );
    // TODO Auto-generated method stub
  }

  @Override
  public ITsValidationSupport<ISkTagsServiceValidator> svs() {
    return svs;
  }

  @Override
  public ITsEventer<ISkTagsServiceListener> eventer() {
    return eventer;
  }

}
