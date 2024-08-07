package org.toxsoft.skf.tags.lib;

import org.toxsoft.core.tslib.coll.helpers.*;
import org.toxsoft.core.tslib.coll.primtypes.*;

/**
 * Listener to the {@link ISkTagService} changes.
 *
 * @author hazard157
 */
public interface ISkTagServiceListener {

  /**
   * Called when changes in the bundles list occurs.
   * <p>
   * Informs when bundle is created and removed, or when bundle parameters {@link ISkTagBundle#params()} has been
   * changed. Does <b>not</b> informs about changes in the bundle subtree of groups and tags.
   *
   * @param aSource {@link ISkTagService} - the event source manager
   * @param aOp {@link ECrudOp} - what happened
   * @param aBundleId String - affected bundleID or <code>null</code> for {@link ECrudOp#LIST}
   */
  void onBundleChanged( ISkTagService aSource, ECrudOp aOp, String aBundleId );

  /**
   * Called when some changes in groups list occurs.
   * <p>
   * Informs when group is created and removed, or when group parameters {@link ISkTagGroup#params()} has been changed.
   * Does <b>not</b> informs about changes in the child tags of the group.
   *
   * @param aSource {@link ISkTagService} - the event source manager
   * @param aParent {@link ISkTagGroup} - the parent group (may be the bundle {@link ISkTagBundle})
   * @param aOp {@link ECrudOp} - what happened
   * @param aGroupId String - affected group ID or <code>null</code> for {@link ECrudOp#LIST}
   */
  void onGroupChanged( ISkTagService aSource, ISkTagGroup aParent, ECrudOp aOp, String aGroupId );

  /**
   * Called when some tags list of group has bee changed.
   * <p>
   * Method informs about changes in {@link ISkTagGroup#childTags()} list. The list of old tags IDs are supplied as
   * <code>aOldTagIds</code> arguments.
   *
   * @param aSource {@link ISkTagService} - the event source manager
   * @param aGroup {@link ISkTagGroup} - the group with changed tags list
   * @param aOldTagIds {@link IStringList} - old (before change) list of child tag ID of group
   */
  void onGroupTagsChanged( ISkTagService aSource, ISkTagGroup aGroup, IStringList aOldTagIds );

  /**
   * Called when some changes in tags list occurs.
   * <p>
   * Informs about changes in {@link ISkTagService#listTags()} list.
   *
   * @param aSource {@link ISkTagService} - the event source manager
   * @param aGroup {@link ISkTagGroup} - the event source group
   * @param aOp {@link ECrudOp} - what happened
   * @param aTagId String - affected tag ID or <code>null</code> for {@link ECrudOp#LIST}
   */
  void onTagChanged( ISkTagService aSource, ISkTagGroup aGroup, ECrudOp aOp, String aTagId );

}
