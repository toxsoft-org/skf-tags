package org.toxsoft.skf.tags.lib_old_1;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.utils.errors.*;

/**
 * The service validator.
 *
 * @author hazard157
 */
public interface ISkTagsServiceValidator {

  /**
   * Checks if the child tag can be created.
   *
   * @param aParent {@link ISkTag} - the parent tag
   * @param aLocalId String - local ID of the tag (an IDname)
   * @param aName String - tag name
   * @param aDescription String - tag description
   * @param aUnmarkable boolean - flags that tag can not be used for marking, used as group of tags
   * @param aParams {@link IOptionSet} - values oth {@link ISkTag#params()}
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canCreateChildTag( ISkTag aParent, String aLocalId, String aName, String aDescription,
      boolean aUnmarkable, IOptionSet aParams );

  /**
   * Checks if the root tag can be created.
   *
   * @param aRootId String - root tag ID (an IDname)
   * @param aName String - tag name
   * @param aDescription String - tag description
   * @param aUnmarkable boolean - flags that tag can not be used for marking, used as group of tags
   * @param aParams {@link IOptionSet} - values oth {@link ISkTag#params()}
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canCreateRootTag( String aRootId, String aName, String aDescription, boolean aUnmarkable,
      IOptionSet aParams );

  /**
   * Checks if tag ID can be changed.
   *
   * @param aFullId String - the tag full ID
   * @param aNewFullId String - new ID for thye tag
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canEditTagId( String aFullId, String aNewFullId );

  /**
   * Checks if the tag can be removed.
   *
   * @param aFullId String - the full ID of the tag
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canRemoveTag( String aFullId );

}
