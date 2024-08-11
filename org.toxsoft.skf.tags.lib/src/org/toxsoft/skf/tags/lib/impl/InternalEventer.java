package org.toxsoft.skf.tags.lib.impl;

import org.toxsoft.core.tslib.bricks.events.*;
import org.toxsoft.core.tslib.coll.helpers.*;
import org.toxsoft.core.tslib.coll.primtypes.*;
import org.toxsoft.skf.tags.lib.*;

/**
 * {@link ISkTagService#eventer()} implementation.
 *
 * @author hazard157
 */
class InternalEventer
    extends AbstractTsEventer<ISkTagServiceListener> {

  private final SkTagService owner;

  private ECrudOp tagChangeOp = null;
  private String  tagChangeId = null;

  public InternalEventer( SkTagService aOwner ) {
    owner = aOwner;
  }

  @Override
  protected void doClearPendingEvents() {
    tagChangeOp = null;
    // TODO SkTagService.Eventer.doClearPendingEvents()
  }

  @Override
  protected void doFirePendingEvents() {
    if( tagChangeOp != null ) {
      reallyFireTagChanged( tagChangeOp, tagChangeId );
    }
    // TODO SkTagService.Eventer.doFirePendingEvents()
  }

  @Override
  protected boolean doIsPendingEvents() {
    if( tagChangeOp != null ) {
      return true;
    }
    // TODO SkTagService.Eventer.doIsPendingEvents()
    return false;
  }

  private void reallyFireTagChanged( ECrudOp aOp, String aTagId ) {
    for( ISkTagServiceListener l : listeners() ) {
      l.onTagChanged( owner, aOp, aTagId );
    }
  }

  public void fireTagChanged( ECrudOp aOp, String aTagId ) {
    // TODO SkTagService.Eventer.fireTagChanged()
  }

  void fireBundleChanged( ECrudOp aOp, String aBundleId ) {
    // TODO InternalEventer.fireBundleChanged()
  }

  void fireGroupChanged( ISkTagGroup aParent, ECrudOp aOp, String aGroupId ) {
    // TODO InternalEventer.fireGroupChanged()
  }

  void fireGroupTagsChanged( ISkTagGroup aGroup, IStringList aOldTagIds ) {
    // TODO InternalEventer.fireGroupTagsChanged()
  }

}
