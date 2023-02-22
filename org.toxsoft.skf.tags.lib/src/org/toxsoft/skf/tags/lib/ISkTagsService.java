package org.toxsoft.skf.tags.lib;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.bricks.events.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.bricks.validator.impl.*;
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
   * Edits the tag.
   *
   * @param <T> - editeg tag type, may be {@link ISkTag} or {@link ISkRootTag}
   * @param aFullId String - the tag full ID
   * @param aName String - new name
   * @param aDescription String - new description
   * @param aParams {@link IOptionSet} - values oth {@link ISkTag#params()}
   * @return {@link ISkTag} - edited tags
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException validation failed
   */
  <T extends ISkTag> T editTag( String aFullId, String aName, String aDescription, IOptionSet aParams );

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
