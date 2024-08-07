package org.toxsoft.skf.tags.lib;

import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.coll.primtypes.*;
import org.toxsoft.core.tslib.gw.gwid.*;
import org.toxsoft.core.tslib.gw.skid.*;
import org.toxsoft.core.tslib.gw.ugwi.*;

/**
 * Methods to mark/unmark entities with tags.
 *
 * @author hazard157
 */
@SuppressWarnings( "javadoc" )
public interface ISkTagMarkManager {

  IStridablesList<ISkTag> listTags();

  boolean isMarked( Ugwi aUgwi, String aTagId );

  boolean isMarked( Gwid aGwid, String aTagId );

  boolean isMarked( Skid aSkid, String aTagId );

  boolean isMarked( Ugwi aUgwi, IStringList aTagIds );

  boolean isMarked( Gwid aGwid, IStringList aTagIds );

  boolean isMarked( Skid aSkid, IStringList aTagIds );

  void setMarkState( Ugwi aUgwi, String aTagId, boolean aState );

  void setMarkState( Gwid aGwid, String aTagId, boolean aState );

  void setMarkState( Skid aSkid, String aTagId, boolean aState );

  void setMarkState( IUgwiList aUgwiList, String aTagId, boolean aState );

  void setMarkState( IGwidList aGwidList, String aTagId, boolean aState );

  void setMarkState( ISkidList aSkidList, String aTagId, boolean aState );

  IUgwiList listMarkedUgwies( String aTagId );

  IGwidList listMarkedGwids( String aTagId );

  ISkidList listMarkedSkids( String aTagId );

  void removeAllMarks( Ugwi aUgwi );

  void removeAllMarks( Gwid aGwid );

  void removeAllMarks( Skid aSkid );

  void removeAllMarks( IUgwiList aUgwiList );

  void removeAllMarks( IGwidList aGwidList );

  void removeAllMarks( ISkidList aSkidList );

}
