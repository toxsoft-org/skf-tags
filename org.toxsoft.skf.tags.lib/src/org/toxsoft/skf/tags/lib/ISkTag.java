package org.toxsoft.skf.tags.lib;

import org.toxsoft.core.tslib.av.metainfo.*;
import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.bricks.strid.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;

/**
 * The tag.
 *
 * @author hazard157
 */
public interface ISkTag
    extends IStridableParameterized {

  /**
   * Returns the local ID, an IDname.
   * <p>
   * local ID is the last component of the tags fille ID {@link #id()}. For root tag local and full ID is the same.
   *
   * @return String - the local ID
   */
  String localId();

  /**
   * The parameters of the tag is not used by the tags service. The content of the {@link #params()} are
   * application-specific.
   * <p>
   * The only exception are options with IDs {@link IAvMetaConstants#TSID_NAME} and
   * {@link IAvMetaConstants#TSID_DESCRIPTION} TODO ???
   * <p>
   * {@inheritDoc}
   */
  @Override
  IOptionSet params();

  /**
   * Determines if the tag can be used for entities marking.
   * <p>
   * Unmarkable tags are designed to be the parent of the child tags.
   *
   * @return boolean - <code>true</code> if this tag can not be used for marking
   */
  boolean isUnmarkable();

  /**
   * Returns the root tag of the hierarchy of this tag.
   *
   * @return {@link ISkRootTag} - the root of hierarchy
   */
  ISkRootTag root();

  /**
   * Returns the parent tag.
   *
   * @return {@link ISkTag} - the parent tag or <code>null</code> for root tag
   */
  ISkTag parent();

  /**
   * Returns the child tags.
   *
   * @return {@link IStridablesList}&lt;{@link ISkTag}&gt; - the list of the child tags
   */
  IStridablesList<ISkTag> childTags();

}
