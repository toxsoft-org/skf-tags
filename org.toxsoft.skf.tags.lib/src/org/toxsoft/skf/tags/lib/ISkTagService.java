package org.toxsoft.skf.tags.lib;

import org.toxsoft.core.tslib.bricks.events.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.bricks.validator.impl.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.uskat.core.*;
import org.toxsoft.uskat.core.api.*;

/**
 * Manages the tree-like hierarchy of a tags - something that can be used for any entities marking.
 *
 * @author hazard157
 */
public interface ISkTagService
    extends ISkService {

  /**
   * The service ID.
   */
  String SERVICE_ID = ISkHardConstants.SK_SYSEXT_SERVICE_ID_PREFIX + ".Tags"; //$NON-NLS-1$

  /**
   * Returns tag by ID if any found.
   * <p>
   * This is the same as to check ID existence in the keys of the list {@link #listTags()}.
   *
   * @param aTagId String - the tag ID
   * @return {@link ISkTagGroup} - the tag or <code>null</code> if tag with the specified ID
   */
  ISkTag findTag( String aTagId );

  /**
   * Returns all tags.
   *
   * @return {@link IStridablesList}&lt;{@link ISkTag}&gt; - list of tags
   */
  IStridablesList<ISkTag> listTags();

  /**
   * Defines (creates new or updates an existing) tags.
   * <p>
   * When editing, <code>aParams</code> may contain only subset of the options.
   *
   * @param aTagInfo {@link IDtoTagInfo} - the ID and properties of the tag to create/edit
   * @return {@link ISkTag} - created/edited tag
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException validation by {@link #svs()} failed
   */
  ISkTag defineTag( IDtoTagInfo aTagInfo );

  /**
   * Removes the tag.
   *
   * @param aTagId String - the ID of tag to remove
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException validation by {@link #svs()} failed
   */
  void removeTag( String aTagId );

  /**
   * Returns the entities marking manager.
   *
   * @return {@link ISkTagMarkManager} - entities marking means
   */
  ISkTagMarkManager markMan();

  /**
   * Returns the tags hierarchical representation means.
   *
   * @return {@link ISkTagGroupsManager} - the tags groups manager
   */
  ISkTagGroupsManager groupMan();

  // ------------------------------------------------------------------------------------
  // Service support

  /**
   * Returns the service validator.
   *
   * @return {@link ITsValidationSupport}&lt;{@link ISkTagServiceValidator}&gt; - the service validator
   */
  ITsValidationSupport<ISkTagServiceValidator> svs();

  /**
   * Returns the service eventer.
   *
   * @return {@link ITsEventer}&lt;{@link ISkTagServiceListener}&gt; - the service eventer
   */
  ITsEventer<ISkTagServiceListener> eventer();

}
