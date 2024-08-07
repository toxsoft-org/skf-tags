package org.toxsoft.skf.tags.lib;

import org.toxsoft.core.tslib.bricks.strid.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;

/**
 * The group is a node of the tags tree in some bundle.
 *
 * @author hazard157
 */
public interface ISkTagGroup
    extends IStridableParameterized {

  /**
   * Returns the bundle this group belongs to.
   * <p>
   * Method returns <code><b>this</b></code> reference when called for the bundle {@link ISkTagBundle}.
   *
   * @return {@link ISkTagBundle} - the owner bundle
   */
  ISkTagBundle bundle();

  // ------------------------------------------------------------------------------------
  // hierarchy

  /**
   * Returns parent group of this group.
   * <p>
   * Method returns <code>null</code> when called for the bundle {@link ISkTagBundle}.
   *
   * @return {@link ISkTagGroup} - the parent group or <code>null</code> for byndle
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
