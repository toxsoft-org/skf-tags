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
   * Checks that the tag can be created.
   *
   * @param aSource {@link ISkTagService} - the tag service
   * @param aTagId String - the tag ID
   * @param aParams {@link IOptionSet} - the tag parameter values
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canCreateTag( ISkTagService aSource, String aTagId, IOptionSet aParams );

  /**
   * Checks that the tag can be edited.
   *
   * @param aSource {@link ISkTagService} - the tag service
   * @param aTagId String - the tag ID
   * @param aParams {@link IOptionSet} - the new tag properties
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canEditTag( ISkTagService aSource, String aTagId, IOptionSet aParams );

  /**
   * Checks that the tag can be removed.
   *
   * @param aSource {@link ISkTagService} - the tag service
   * @param aTagId String - the tag ID
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canRemoveTag( ISkTagService aSource, String aTagId );

  /**
   * Checks if the bundle can be created.
   *
   * @param aSource {@link ISkTagService} - the tag service
   * @param aBundleId String - the bundle ID
   * @param aParams {@link IOptionSet} - parameters values
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canCreateBundle( ISkTagService aSource, String aBundleId, IOptionSet aParams );

  /**
   * Checks if the can be edited.
   *
   * @param aSource {@link ISkTagService} - the tag service
   * @param aBundle {@link ISkTagBundle} - the bundle to edit
   * @param aParams {@link IOptionSet} - set of the parameters to be changed, may be empty
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canEditBundle( ISkTagService aSource, ISkTagBundle aBundle, IOptionSet aParams );

  /**
   * Checks if the bundle can be removed.
   *
   * @param aSource {@link ISkTagService} - the tag service
   * @param aBundleId String - the ID of the bundle to remove
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canRemoveBundle( ISkTagService aSource, String aBundleId );

  /**
   * Checks if the group can be created.
   *
   * @param aSource {@link ISkTagService} - the tag service
   * @param aParent {@link ISkTagGroup} - the parent group
   * @param aGroupId String - the group ID
   * @param aParams {@link IOptionSet} - parameters values
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canCreateGroup( ISkTagService aSource, ISkTagGroup aParent, String aGroupId, IOptionSet aParams );

  /**
   * Checks if the group can be edited.
   *
   * @param aSource {@link ISkTagService} - the tag service
   * @param aGroup {@link ISkTagGroup} - the group to edit
   * @param aParams {@link IOptionSet} - set of the parameters to be changed, may be empty
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canEditGroup( ISkTagService aSource, ISkTagGroup aGroup, IOptionSet aParams );

  /**
   * Checks if the group can be removed.
   *
   * @param aSource {@link ISkTagService} - the tag service
   * @param aParent {@link ISkTagGroup} - the parent group
   * @param aGroupId String - the group ID
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canRemoveGroup( ISkTagService aSource, ISkTagGroup aParent, String aGroupId );

  /**
   * Checks if tag can be added to the group.
   *
   * @param aSource {@link ISkTagService} - the tag service
   * @param aGroup {@link ISkTagGroup} - the group
   * @param aTagId String - the ID of tag to add
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canAddTagToGroup( ISkTagService aSource, ISkTagGroup aGroup, String aTagId );

  /**
   * Checks if tag can be removed from the group.
   *
   * @param aSource {@link ISkTagService} - the tag service
   * @param aGroup {@link ISkTagGroup} - the group
   * @param aTagId String - the ID of tag to remove
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canRemoveTagFromGroup( ISkTagService aSource, ISkTagGroup aGroup, String aTagId );

}
