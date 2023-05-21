package org.toxsoft.skf.tags.lib;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.utils.errors.*;

/**
 * {@link ISkTagService} editing validator.
 *
 * @author hazard157
 */
public interface ISkTagServiceValidator {

  /**
   * Checks if the section can be created.
   *
   * @param aSectionId String - the section ID
   * @param aParams {@link IOptionSet} - parameters values
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult createSection( String aSectionId, IOptionSet aParams );

  /**
   * Checks if the section can be edited.
   *
   * @param aSectionId String - the section ID
   * @param aParams {@link IOptionSet} - set of the parameters to be changed, may be empty
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult editSection( String aSectionId, IOptionSet aParams );

  /**
   * Checks if the section can be removed.
   *
   * @param aSectionId String - the section ID
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult removeSection( String aSectionId );

  /**
   * Checks that the tag can be created.
   *
   * @param aSection {@link ISkTagSection} - the tags section of an operation
   * @param aTagId String - the tag ID
   * @param aKind {@link ESkTagKind} - the kind of the tag to be created
   * @param aParams {@link IOptionSet} - the tag parameter values
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult createTag( ISkTagSection aSection, String aTagId, ESkTagKind aKind, IOptionSet aParams );

  /**
   * Checks that the tag can be edited.
   * <p>
   * Note: <code>aParams</code> will replace previous set in {@link ISkTag#params()}.
   *
   * @param aSection {@link ISkTagSection} - the tags section of an operation
   * @param aTagId String - the tag ID
   * @param aNewKind {@link ESkTagKind} - the new kind of the tag
   * @param aParams {@link IOptionSet} - the new values for {@link ISkTag#params()}
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult editTag( ISkTagSection aSection, String aTagId, ESkTagKind aNewKind, IOptionSet aParams );

  /**
   * Checks that the tag can be removed.
   *
   * @param aSection {@link ISkTagSection} - the tags section of an operation
   * @param aTagId String - the tag ID
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult removeTag( ISkTagSection aSection, String aTagId );

}
