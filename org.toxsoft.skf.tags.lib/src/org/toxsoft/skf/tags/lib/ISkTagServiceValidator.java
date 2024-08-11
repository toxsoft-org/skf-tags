package org.toxsoft.skf.tags.lib;

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
   * @param aTagInfo {@link IDtoTagInfo} - the ID and properties of the tag to create
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canCreateTag( ISkTagService aSource, IDtoTagInfo aTagInfo );

  /**
   * Checks that the tag can be edited.
   *
   * @param aSource {@link ISkTagService} - the tag service
   * @param aTag {@link ISkTag} - the tag to edit
   * @param aTagInfo {@link IDtoTagInfo} - the ID and properties of the tag to edit
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canEditTag( ISkTagService aSource, ISkTag aTag, IDtoTagInfo aTagInfo );

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
   * @param aBundleInfo {@link IDtoTagInfo} - the ID and properties of the tag to create
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canCreateBundle( ISkTagService aSource, IDtoTagInfo aBundleInfo );

  /**
   * Checks if the can be edited.
   *
   * @param aSource {@link ISkTagService} - the tag service
   * @param aBundle {@link ISkTagGroup} - the bundle to edit
   * @param aBundleInfo {@link IDtoTagInfo} - the ID and properties of the tag to edit
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canEditBundle( ISkTagService aSource, ISkTagGroup aBundle, IDtoTagInfo aBundleInfo );

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
   * @param aGroupInfo {@link IDtoTagInfo} - the ID and properties of the tag to create
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canCreateGroup( ISkTagService aSource, ISkTagGroup aParent, IDtoTagInfo aGroupInfo );

  /**
   * Checks if the group can be edited.
   *
   * @param aSource {@link ISkTagService} - the tag service
   * @param aGroup {@link ISkTagGroup} - the group to edit
   * @param aGroupInfo {@link IDtoTagInfo} - the ID and properties of the tag to edit
   * @return {@link ValidationResult} - the check result
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ValidationResult canEditGroup( ISkTagService aSource, ISkTagGroup aGroup, IDtoTagInfo aGroupInfo );

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
