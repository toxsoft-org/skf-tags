package org.toxsoft.skf.tags.lib.impl;

import static org.toxsoft.skf.tags.lib.impl.ISkTagServiceInternalHardConstants.*;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.av.opset.impl.*;
import org.toxsoft.core.tslib.gw.skid.*;
import org.toxsoft.skf.tags.lib.*;
import org.toxsoft.uskat.core.api.objserv.*;
import org.toxsoft.uskat.core.impl.*;

/**
 * {@link ISkTag} implementation.
 *
 * @author hazard157
 */
class SkTag
    extends SkObject
    implements ISkTag {

  static final ISkObjectCreator<SkTag> CREATOR = SkTag::new;

  SkTag( Skid aSkid ) {
    super( aSkid );
  }

  // ------------------------------------------------------------------------------------
  // ISkTag
  //

  @Override
  public IOptionSet params() {
    String s = getClob( ATRID_STRIDABLES_PARAMS, null );
    if( s != null ) {
      return OptionSetKeeper.KEEPER.str2ent( s );
    }
    return IOptionSet.NULL;
  }

}
