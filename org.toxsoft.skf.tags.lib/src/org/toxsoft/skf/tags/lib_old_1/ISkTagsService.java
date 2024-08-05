package org.toxsoft.skf.tags.lib_old_1;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.bricks.events.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.bricks.validator.impl.*;
import org.toxsoft.core.tslib.coll.primtypes.*;
import org.toxsoft.core.tslib.gw.gwid.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.uskat.core.*;
import org.toxsoft.uskat.core.api.*;

/**
 * Tags service.
 * <p>
 * Tags service introduces the <b>tag</b> concept. Any entity may be marked by the one or many tags.
 * <p>
 * TODO more about tags service
 *
 * @author hazard157
 */
public interface ISkTagsService
    extends ISkService {

  /**
   * The service ID.
   */
  String SERVICE_ID = ISkHardConstants.SK_SYSEXT_SERVICE_ID_PREFIX + ".Tags"; //$NON-NLS-1$

  /**
   * Returns the IDs of the tags that marks the specified GWID.
   *
   * @param aGwid {@link Gwid} - the asked GWID
   * @return {@link IStringList} - IDs of the marking tags
   */
  IStringList getMarkTagIds( Gwid aGwid );

  /**
   * Returns the root tags.
   *
   * @return {@link IStridablesList}&lt;{@link ISkRootTag}&gt; - the list of root tags
   */
  IStridablesList<ISkRootTag> listRoots();

  /**
   * Foinds the root tag by the ID of any tag.
   *
   * @param aTagFullId String - the full ID of any tag
   * @return {@link ISkRootTag} - the found root or <code>null</code> if no such tag
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ISkRootTag findHierarchyRoot( String aTagFullId );

  /**
   * Finds the tag by the full ID.
   *
   * @param aTagFullId String - the full ID of any tag
   * @return {@link ISkRootTag} - the tag or <code>null</code> if no such tag
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ISkTag findTag( String aTagFullId );

  /**
   * Creates the child tag.
   *
   * @param aParent {@link ISkTag} - the parent tag
   * @param aLocalId String - local ID of the tag (an IDname)
   * @param aName String - tag name
   * @param aDescription String - tag description
   * @param aUnmarkable boolean - flags that tag can not be used for marking, used as group of tags
   * @param aParams {@link IOptionSet} - values oth {@link ISkTag#params()}
   * @return {@link ISkTag} - created tag
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException validation failed
   */
  ISkTag createChildTag( ISkTag aParent, String aLocalId, String aName, String aDescription, boolean aUnmarkable,
      IOptionSet aParams );

  /**
   * Creates the root tag.
   *
   * @param aRootId String - root tag ID (an IDname)
   * @param aName String - tag name
   * @param aDescription String - tag description
   * @param aUnmarkable boolean - flags that tag can not be used for marking, used as group of tags
   * @param aParams {@link IOptionSet} - values oth {@link ISkTag#params()}
   * @return {@link ISkTag} - created tag
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException validation failed
   */
  ISkTag createRootTag( String aRootId, String aName, String aDescription, boolean aUnmarkable, IOptionSet aParams );

  /**
   * Changes the tag ID.
   * <p>
   * Changing tag ID means to change tags tree structure, event move the tag from one root hierarchy to another.
   *
   * @param <T> - editeg tag type, may be {@link ISkTag} or {@link ISkRootTag}
   * @param aFullId String - the tag full ID
   * @param aNewFullId String - new ID for thye tag
   * @return {@link ISkTag} - edited tags
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException validation failed
   */
  <T extends ISkTag> T editTagId( String aFullId, String aNewFullId );

  /**
   * Removes the tag with the childs (if any).
   *
   * @param aFullId String - the full ID of the tag
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException validation failed
   */
  void removeTag( String aFullId );

  // ------------------------------------------------------------------------------------
  // Service support

  /**
   * Returns the service validator.
   *
   * @return {@link ITsValidationSupport}&lt;{@link ISkTagsServiceValidator}&gt; - the service validator
   */
  ITsValidationSupport<ISkTagsServiceValidator> svs();

  /**
   * Returns the service eventer.
   *
   * @return {@link ITsEventer}&lt;{@link ISkTagsServiceListener}&gt; - the service eventer
   */
  ITsEventer<ISkTagsServiceListener> eventer();

}
