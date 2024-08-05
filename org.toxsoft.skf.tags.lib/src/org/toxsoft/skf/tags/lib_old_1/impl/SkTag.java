package org.toxsoft.skf.tags.lib_old_1.impl;

import static org.toxsoft.core.tslib.av.metainfo.IAvMetaConstants.*;
import static org.toxsoft.skf.tags.lib.impl.ISkTagServiceConstants.*;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.av.opset.impl.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.strid.coll.impl.*;
import org.toxsoft.core.tslib.bricks.strid.impl.*;
import org.toxsoft.core.tslib.coll.*;
import org.toxsoft.core.tslib.gw.gwid.*;
import org.toxsoft.core.tslib.gw.skid.*;
import org.toxsoft.skf.tags.lib.*;
import org.toxsoft.uskat.core.api.objserv.*;
import org.toxsoft.uskat.core.impl.*;

class SkTag
    extends SkObject
    implements ISkTag {

  // TODO on creation set params() NAME and DESCRIPRION to nmName() and description()

  static final ISkObjectCreator<SkTag> CREATOR = SkTag::new;

  SkTag( Skid aSkid ) {
    super( aSkid );
  }

  // ------------------------------------------------------------------------------------
  // ISkTag
  //

  @Override
  public void markGwids( IGwidList aGwids ) {
    Gwid gwid = Gwid.createClob( CLSID_TAG, strid(), CLBID_PARAMS );
    String markedGwidsStr = coreApi().clobService().readClob( gwid );
    GwidList ll = (GwidList)GwidList.KEEPER.str2ent( markedGwidsStr );
    if( !ll.hasElem( gwid ) ) {
      ll.add( gwid );
    }
    coreApi().clobService().writeClob( gwid, GwidList.KEEPER.ent2str( ll ) );
  }

  @Override
  public IGwidList getMarkedGwids() {
    Gwid gwid = Gwid.createClob( CLSID_TAG, strid(), CLBID_PARAMS );
    String markedGwidsStr = coreApi().clobService().readClob( gwid );
    return GwidList.KEEPER.str2ent( markedGwidsStr );
  }

  @Override
  public String localId() {
    return StridUtils.getLast( id() );
  }

  @Override
  public IOptionSet params() {
    Gwid gwid = Gwid.createClob( CLSID_TAG, strid(), CLBID_PARAMS );
    String paramsStr = coreApi().clobService().readClob( gwid );
    return OptionSetKeeper.KEEPER.str2ent( paramsStr );
  }

  @Override
  public void setParams( IOptionSet aParams ) {
    IOptionSetEdit pp = new OptionSet( aParams );
    pp.setStr( TSID_NAME, nmName() );
    pp.setStr( TSID_DESCRIPTION, description() );
    Gwid gwid = Gwid.createClob( CLSID_TAG, strid(), CLBID_PARAMS );
    String paramsStr = OptionSetKeeper.KEEPER_INDENTED.ent2str( pp );
    coreApi().clobService().writeClob( gwid, paramsStr );
  }

  @Override
  public boolean isUnmarkable() {
    return attrs().getBool( ATRID_IS_UNMARKABLE );
  }

  @Override
  public ISkRootTag root() {
    // Note: SkRootTag overrides this method
    String rootId = StridUtils.getFirst( id() );
    return coreApi().objService().get( new Skid( CLSID_ROOT_TAG, rootId ) );
  }

  @Override
  public ISkTag parent() {
    // Note: SkRootTag overrides this method
    String parentId = StridUtils.removeTailingIdNames( id(), 1 );
    return coreApi().objService().get( new Skid( CLSID_TAG, parentId ) );
  }

  @Override
  public IStridablesList<ISkTag> childTags() {
    IList<ISkTag> ll = getLinkObjs( LNKID_CHILD_TAGS );
    return new StridablesList<>( ll );
  }

}
