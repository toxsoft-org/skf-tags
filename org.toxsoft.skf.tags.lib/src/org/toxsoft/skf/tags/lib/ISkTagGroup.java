package org.toxsoft.skf.tags.lib;

import org.toxsoft.core.tslib.bricks.strid.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;

/**
 * The group is a node of the tags tree in some bundle.
 * <p>
 * The bundle is a root group owned by {@link ISkTagService}, not by other group.
 *
 * @author hazard157
 */
public interface ISkTagGroup
    extends IStridableParameterized {

  /**
   * Returns the bundle this group belongs to.
   * <p>
   * Method returns <code><b>this</b></code> reference when called for the bundle {@link ISkTagGroup}.
   *
   * @return {@link ISkTagGroup} - the owner bundle
   */
  ISkTagGroup bundle();

  // ------------------------------------------------------------------------------------
  // hierarchy

  /**
   * Returns child group by ID if any found.
   *
   * @param aGroupId String - the child group ID
   * @return {@link ISkTagGroup} - the child group or <code>null</code> if no child with the specified ID
   */
  ISkTagGroup findGroup( String aGroupId );

  /**
   * Returns parent group of this group.
   * <p>
   * Method returns <code>null</code> when called for the bundle, one of {@link ISkTagGroupsManager#listBundles()}.
   *
   * @return {@link ISkTagGroup} - the parent group or <code>null</code> for bundle.
   */
  ISkTagGroup parent();

  /**
   * Returns child groups of this group.
   *
   * @return {@link IStridablesList}&lt;{@link ISkTagGroup}&gt; - list of child * Returns child groups
   */
  IStridablesList<ISkTagGroup> childGroups();

  // ------------------------------------------------------------------------------------
  // tags

  /**
   * Returns child tag by ID if any found.
   *
   * @param aTagId String - the child tag ID
   * @return {@link ISkTagGroup} - the child tag or <code>null</code> if no child with the specified ID
   */
  ISkTag findTag( String aTagId );

  /**
   * Returns child tags of this group.
   *
   * @return {@link IStridablesList}&lt;{@link ISkTag}&gt; - list of child tags
   */
  IStridablesList<ISkTag> childTags();

  /**
   * Returns all tags of this and descendants groups.
   *
   * @return {@link IStridablesList}&lt;{@link ISkTag}&gt; - list of all tags
   */
  IStridablesList<ISkTag> listAllTags();

}
