package org.toxsoft.skf.tags.lib.impl;

import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.coll.primtypes.*;
import org.toxsoft.core.tslib.gw.gwid.*;
import org.toxsoft.core.tslib.gw.skid.*;
import org.toxsoft.core.tslib.gw.ugwi.*;
import org.toxsoft.skf.tags.lib.*;

/**
 * {@link ISkTagMarkManager} implementation
 *
 * @author hazard157
 */
class SkTagMarkManager
    implements ISkTagMarkManager {

  private final SkTagService owner;

  SkTagMarkManager( SkTagService aOwner ) {
    owner = aOwner;
  }

  // ------------------------------------------------------------------------------------
  // ISkTagMarkManager
  //

  @Override
  public IStridablesList<ISkTag> listTags() {
    return owner.listTags();
  }

  @Override
  public boolean isMarked( Ugwi aUgwi, String aTagId ) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isMarked( Gwid aGwid, String aTagId ) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isMarked( Skid aSkid, String aTagId ) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isMarked( Ugwi aUgwi, IStringList aTagIds ) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isMarked( Gwid aGwid, IStringList aTagIds ) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public boolean isMarked( Skid aSkid, IStringList aTagIds ) {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public void setMarkState( Ugwi aUgwi, String aTagId, boolean aState ) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setMarkState( Gwid aGwid, String aTagId, boolean aState ) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setMarkState( Skid aSkid, String aTagId, boolean aState ) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setMarkState( IUgwiList aUgwiList, String aTagId, boolean aState ) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setMarkState( IGwidList aGwidList, String aTagId, boolean aState ) {
    // TODO Auto-generated method stub

  }

  @Override
  public void setMarkState( ISkidList aSkidList, String aTagId, boolean aState ) {
    // TODO Auto-generated method stub

  }

  @Override
  public IUgwiList listMarkedUgwies( String aTagId ) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public IGwidList listMarkedGwids( String aTagId ) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public ISkidList listMarkedSkids( String aTagId ) {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void removeAllMarks( Ugwi aUgwi ) {
    // TODO Auto-generated method stub

  }

  @Override
  public void removeAllMarks( Gwid aGwid ) {
    // TODO Auto-generated method stub

  }

  @Override
  public void removeAllMarks( Skid aSkid ) {
    // TODO Auto-generated method stub

  }

  @Override
  public void removeAllMarks( IUgwiList aUgwiList ) {
    // TODO Auto-generated method stub

  }

  @Override
  public void removeAllMarks( IGwidList aGwidList ) {
    // TODO Auto-generated method stub

  }

  @Override
  public void removeAllMarks( ISkidList aSkidList ) {
    // TODO Auto-generated method stub

  }

}
