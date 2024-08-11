package org.toxsoft.skf.tags.lib;

import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.validator.impl.*;
import org.toxsoft.core.tslib.utils.errors.*;

/**
 * Manages the groups of the tags, the different hiarachical representation of the same tags.
 *
 * @author hazard157
 */
public interface ISkTagGroupsManager {

  /**
   * Returns the bundle if it exists.
   * <p>
   * If requested group exists but it is not a bundle (not a root group) then method returns <code>null</code>.
   *
   * @param aBundleId String - the bundle ID
   * @return {@link ISkTagGroup} - the found bundle or <code>null</code>
   */
  ISkTagGroup findBundle( String aBundleId );

  /**
   * Returns the group if it exists in any bundles.
   * <p>
   * Bundle is a group so this method returns the bundle if ID of the bundle is specified.
   *
   * @param aGroupId String - the group ID
   * @return {@link ISkTagGroup} - the found group or <code>null</code>
   */
  ISkTagGroup findGroup( String aGroupId );

  /**
   * Returns all bundles of tags.
   *
   * @return {@link IStridablesList}&lt;{@link ISkTagGroup}&gt; - list of bundles
   */
  IStridablesList<ISkTagGroup> listBundles();

  /**
   * Defines (creates new or updates an existing) bundle.
   * <p>
   * When editing, <code>aParams</code> may contain only subset of the options.
   *
   * @param aBundleInfo {@link IDtoTagInfo} - the ID and properties of the bundle to create/edit
   * @return {@link ISkTagGroup} - created/edited bundle
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException validation by {@link ISkTagService#svs()} failed
   */
  ISkTagGroup defineBundle( IDtoTagInfo aBundleInfo );

  /**
   * Removes the bundle.
   *
   * @param aBundleId String - ID of bundle to remove
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException validation by {@link ISkTagService#svs()} failed
   */
  void removeBundle( String aBundleId );

  /**
   * Defines (creates new or edits an existing) group properties.
   * <p>
   * When editing, <code>aParams</code> may contain only subset of the options. Editing of properties does not affects
   * child groups and tags.
   *
   * @param aParent {@link ISkTagGroup} - the parent group
   * @param aGroupInfo {@link IDtoTagInfo} - the ID and properties of the group to create/edit
   * @return {@link ISkTagGroup} - created/edited group
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException validation by {@link ISkTagService#svs()} failed
   */
  ISkTagGroup defineGroup( ISkTagGroup aParent, IDtoTagInfo aGroupInfo );

  /**
   * Permanently removes group with all child groups and tags.
   * <p>
   * Does nothing if <code>aGroupId</code> is not a child of the <code>aParent</code>.
   *
   * @param aParent {@link ISkTagGroup} - the parent group
   * @param aGroupId String - ID of group to remove
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException validation by {@link ISkTagService#svs()} failed
   */
  void removeGroup( ISkTagGroup aParent, String aGroupId );

  /**
   * Adds the tag to the group.
   * <p>
   * Does nothing if tag is already in group.
   *
   * @param aGroup {@link ISkTagGroup} - the group
   * @param aTagId String - the ID of tag to add
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException validation by {@link ISkTagService#svs()} failed
   */
  void addTag( ISkTagGroup aGroup, String aTagId );

  /**
   * Removes tag from the group.
   * <p>
   * Does nothing if tag is not in group.
   * <p>
   * Note: the tag remains in {@link ISkTagService#listTags()} it is only removed from the group.
   *
   * @param aGroup {@link ISkTagGroup} - the group
   * @param aTagId String - the ID of tag to remove
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException validation by {@link ISkTagService#svs()} failed
   */
  void removeTag( ISkTagGroup aGroup, String aTagId );

}
