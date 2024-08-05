package org.toxsoft.skf.tags.lib;

import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.gw.gwid.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.uskat.core.api.objserv.*;

/**
 * The tag.
 *
 * @author hazard157
 */
public interface ISkTag
    extends ISkObject {

  /**
   * This class ID.
   */
  String CLASS_ID = ISkTagServiceHardConstants.CLSID_TAG;

  /**
   * returns the tag kind.
   *
   * @return {@link ESkTagKind} - the tag kind
   */
  ESkTagKind kind();

  /**
   * Set the marks on the specified GWIDs.
   *
   * @param aGwids {@link IGwidList} - the list of GWIDs
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsUnsupportedFeatureRtException this is unmarkable {@link ESkTagKind#isMarkable()} = <code>false</code> tag
   */
  void markGwids( IGwidList aGwids );

  /**
   * Returns the marked GWIDs.
   * <p>
   * For unmarkable tag returns an empty list.
   *
   * @return {@link IGwidList} - the marked GWIDs
   */
  IGwidList getMarkedGwids();

  /**
   * Returns the section - the root of this tag's hierarchy.
   *
   * @return {@link ISkTagSection } - the section - owner and root of this tag
   */
  ISkTagSection section();

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
