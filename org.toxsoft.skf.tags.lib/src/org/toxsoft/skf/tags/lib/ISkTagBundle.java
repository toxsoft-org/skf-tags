package org.toxsoft.skf.tags.lib;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.bricks.validator.impl.*;
import org.toxsoft.core.tslib.coll.basis.*;
import org.toxsoft.core.tslib.utils.errors.*;

/**
 * The bundle of tags is a way to organize subset of tags as a hierarchical tree.
 * <p>
 * Actually the bundle is a tags group {@link ISkTagGroup}.
 *
 * @author hazard157
 */
public interface ISkTagBundle
    extends ISkTagGroup, ITsClearable {

  /**
   * Defines (creates new or edits an existing) group properties.
   * <p>
   * When editing, <code>aParams</code> may contain only subset of the options. Editing of properties does not affects
   * child groups and tags.
   *
   * @param aParent {@link ISkTagGroup} - the parent group
   * @param aGroupId String - the ID of group to be created/edtied as a child of the specified parent
   * @param aParams {@link IOptionSet} - {@link ISkTagGroup#params()} values
   * @return {@link ISkTagGroup} - created/edited group
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException validation by {@link ISkTagService#svs()} failed
   */
  ISkTagGroup defineGroup( ISkTagGroup aParent, String aGroupId, IOptionSet aParams );

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
