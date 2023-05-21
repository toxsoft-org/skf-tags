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
 * <p>
 * The tree view of tags is provided by the format of IDpaths - identifiers {@link ISkTag#id()}. Child tags add the
 * local IDname identifier {@link ISkTag#localId()} to the IDpath identifier of the parent. The root tag is a special
 * tag {@link ISkTagSection#root()}, whose identifier is an empty string.
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
   * Returns all section.
   *
   * @return {@link IStridablesList}&lt;{@link ISkTagSection}&gt; - the tag sections list
   */
  IStridablesList<ISkTagSection> sections();

  /**
   * Creates the section.
   *
   * @param aSectionId String - the section ID
   * @param aParams {@link IOptionSet} - parameters values
   * @return {@link ISkTagSection} - created section
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException validation failed
   */
  ISkTagSection createSection( String aSectionId, IOptionSet aParams );

  /**
   * Changes the section parameters.
   *
   * @param aSectionId String - the section ID
   * @param aParams {@link IOptionSet} - set of the parameters to be changed, may be empty
   * @return {@link ISkTagSection} - the changed section
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException validation failed
   */
  ISkTagSection editSection( String aSectionId, IOptionSet aParams );

  /**
   * Removes the section.
   *
   * @param aSectionId String - the section ID
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException validation failed
   */
  void removeSection( String aSectionId );

  /**
   * Removes all sections.
   * <p>
   * Warning: clearing the whole tag manager is the special operation that does <b>not</b> performs any check!
   */
  void clear();

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
