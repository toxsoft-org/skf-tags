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

  // ------------------------------------------------------------------------------------
  // Tags

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
   * @param aTagId String - the tag ID (IDpath)
   * @param aParams {@link IOptionSet} - {@link ISkTag#params()} values
   * @return {@link ISkTag} - created/edited tag
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException validation by {@link #svs()} failed
   */
  ISkTag defineTag( String aTagId, IOptionSet aParams );

  /**
   * Removes the tag.
   *
   * @param aTagId String - the ID of tag to remove
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException validation by {@link #svs()} failed
   */
  void removeTag( String aTagId );

  // ------------------------------------------------------------------------------------
  // Marking by tags

  /**
   * Returns the entities marking manager.
   *
   * @return {@link ISkTagMarkManager} - entities marking means
   */
  ISkTagMarkManager markMan();

  // ------------------------------------------------------------------------------------
  // Bundles

  /**
   * Returns all bundles of tags.
   *
   * @return {@link IStridablesList}&lt;{@link ISkTagBundle}&gt; - list of bundles
   */
  IStridablesList<ISkTagBundle> listBundles();

  /**
   * Defines (creates new or updates an existing) bundle.
   * <p>
   * When editing, <code>aParams</code> may contain only subset of the options.
   *
   * @param aBundleId String - the bundle ID (IDpath)
   * @param aParams {@link IOptionSet} - {@link ISkTagBundle#params()} values
   * @return {@link ISkTagBundle} - created/edited bundle
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException validation by {@link #svs()} failed
   */
  ISkTagBundle defineBundle( String aBundleId, IOptionSet aParams );

  /**
   * Removes the bundle.
   *
   * @param aBundleId String - ID of bundle to remove
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException validation by {@link #svs()} failed
   */
  void removeBundle( String aBundleId );

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
