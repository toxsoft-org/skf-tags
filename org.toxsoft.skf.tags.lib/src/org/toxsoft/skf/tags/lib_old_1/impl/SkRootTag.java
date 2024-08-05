package org.toxsoft.skf.tags.lib_old_1.impl;

import org.toxsoft.core.tslib.gw.skid.*;
import org.toxsoft.skf.tags.lib_old_1.*;
import org.toxsoft.uskat.core.api.objserv.*;

class SkRootTag
    extends SkTag
    implements ISkRootTag {

  static final ISkObjectCreator<SkTag> CREATOR = SkRootTag::new;

  SkRootTag( Skid aSkid ) {
    super( aSkid );
    // TODO Auto-generated constructor stub
  }

  // ------------------------------------------------------------------------------------
  // ISkTag
  //

  @Override
  public ISkRootTag root() {
    return this;
  }

  @Override
  public ISkTag parent() {
    return null;
  }

  // ------------------------------------------------------------------------------------
  // ISkRootTag
  //

  @Override
  public void clear() {
    // TODO Auto-generated method stub

  }

}
