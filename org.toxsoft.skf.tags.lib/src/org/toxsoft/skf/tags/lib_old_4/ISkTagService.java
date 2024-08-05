package org.toxsoft.skf.tags.lib_old_4;

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

  /**
   * Finds the section by the ID.
   *
   * @param aSectionId String - the section ID
   * @return {@link ISkTagSection} - found section or <code>null</code>
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ISkTagSection findSection( String aSectionId );

  /**
   * Returns all section.
   *
   * @return {@link IStridablesList}&lt;{@link ISkTagSection}&gt; - the tag sections list
   */
  IStridablesList<ISkTagSection> sections();

  /**
   * Creates the section.
   *
   * @param aSectionId String - the section ID
   * @param aAttrs {@link IOptionSet} - attributes values
   * @return {@link ISkTagSection} - created section
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException validation failed
   */
  ISkTagSection createSection( String aSectionId, IOptionSet aAttrs );

  /**
   * Changes the section attributes.
   *
   * @param aSectionId String - the section ID
   * @param aAttrs {@link IOptionSet} - set of the attributes to be changed, may be empty
   * @return {@link ISkTagSection} - the changed section
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException validation failed
   */
  ISkTagSection editSection( String aSectionId, IOptionSet aAttrs );

  /**
   * Removes the section.
   *
   * @param aSectionId String - the section ID
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException validation failed
   */
  void removeSection( String aSectionId );

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
