package org.toxsoft.skf.tags.lib_old_4;

import static org.toxsoft.skf.tags.lib_old_4.ISkTagServiceSharedResources.*;

import org.toxsoft.core.tslib.bricks.keeper.*;
import org.toxsoft.core.tslib.bricks.keeper.std.*;
import org.toxsoft.core.tslib.bricks.strid.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.strid.coll.impl.*;
import org.toxsoft.core.tslib.utils.errors.*;

/**
 * The kind of the tags.
 * <p>
 * Regular tags can be both the parent of other tags and be used for entities marking. Dedicated group tag can have
 * childs but can not be used for marking. Leaf tag is used for marking but can not have childs.
 *
 * @author hazard157
 */
@SuppressWarnings( "javadoc" )
public enum ESkTagKind
    implements IStridable {

  REGULAR( "regular", STR_ETK_TAG_REGULAR, STR_ETK_TAG_REGULAR_D ), //$NON-NLS-1$

  GROUP( "group", STR_ETK_TAG_GROUP, STR_ETK_TAG_GROUP_D ), //$NON-NLS-1$

  LEAF( "leaf", STR_ETK_TAG_LEAF, STR_ETK_TAG_LEAF_D ), //$NON-NLS-1$

  ;

  /**
   * The keeper ID.
   */
  public static final String KEEPER_ID = "ESkTagKind"; //$NON-NLS-1$

  /**
   * Keeper singleton.
   */
  public static final IEntityKeeper<ESkTagKind> KEEPER = new StridableEnumKeeper<>( ESkTagKind.class );

  private static IStridablesListEdit<ESkTagKind> list = null;

  private final String id;
  private final String name;
  private final String description;

  ESkTagKind( String aId, String aName, String aDescription ) {
    id = aId;
    name = aName;
    description = aDescription;
  }

  // --------------------------------------------------------------------------
  // IStridable
  //

  @Override
  public String id() {
    return id;
  }

  @Override
  public String nmName() {
    return name;
  }

  @Override
  public String description() {
    return description;
  }

  // ----------------------------------------------------------------------------------
  // API
  //

  /**
   * Determines if tag can have child tags.
   *
   * @return boolean - childable tag sign<br>
   *         <b>true</b> - tag can have childs;<br>
   *         <b>false</b> - tag can <b>not</b> have childs.
   */
  public boolean isChildable() {
    return switch( this ) {
      case REGULAR -> true;
      case GROUP -> true;
      case LEAF -> false;
    };
  }

  /**
   * Determines if tag can be used for entities marking.
   *
   * @return boolean - markable tag sign<br>
   *         <b>true</b> - tag can mark entities;<br>
   *         <b>false</b> - tag can <b>not</b> mark entities.
   */
  public boolean isMarkable() {
    return switch( this ) {
      case REGULAR -> true;
      case GROUP -> false;
      case LEAF -> true;
    };
  }

  /**
   * Returns all constants in single list.
   *
   * @return {@link IStridablesList}&lt; {@link ESkTagKind} &gt; - list of constants in order of declaraion
   */
  public static IStridablesList<ESkTagKind> asList() {
    if( list == null ) {
      list = new StridablesList<>( values() );
    }
    return list;
  }

  /**
   * Returns the constant by the ID.
   *
   * @param aId String - the ID
   * @return {@link ESkTagKind} - found constant
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsItemNotFoundRtException no constant found by specified ID
   */
  public static ESkTagKind getById( String aId ) {
    return asList().getByKey( aId );
  }

  /**
   * Finds the constant by the name.
   *
   * @param aName String - the name
   * @return {@link ESkTagKind} - found constant or <code>null</code>
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  public static ESkTagKind findByName( String aName ) {
    TsNullArgumentRtException.checkNull( aName );
    for( ESkTagKind item : values() ) {
      if( item.name.equals( aName ) ) {
        return item;
      }
    }
    return null;
  }

  /**
   * Returns the constant by the name.
   *
   * @param aName String - the name
   * @return {@link ESkTagKind} - found constant
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsItemNotFoundRtException no constant found by specified name
   */
  public static ESkTagKind getByName( String aName ) {
    return TsItemNotFoundRtException.checkNull( findByName( aName ) );
  }

}
