package org.toxsoft.skf.tags.lib_old_2;

import org.toxsoft.core.tslib.bricks.strid.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.gw.gwid.*;
import org.toxsoft.core.tslib.utils.errors.*;

/**
 * The tag.
 * <p>
 * Tag parameters {@link ISkTag#params()} is not used by the tag service itself,. It is designed for
 * application-specific needs.
 *
 * @author hazard157
 */
public interface ISkTag
    extends IStridableParameterized {

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
   * Returns the local ID, an IDname.
   * <p>
   * local ID is the last component of the tags fille ID {@link #id()}. For root tag local and full ID is the same.
   *
   * @return String - the local ID
   */
  String localId();

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

  /**
   * Returns the specified tags subtree under this tag as a root.
   * <p>
   * Note: setting both <code>aChildables</code> and <code>aMarkables</code> to <code>false</code> will return an empty
   * list unless <code>aIsSelf</code> is set to <code>true</code>.
   *
   * @param aIsSelf boolean - <code>true</code> to include this tag as the first element in the list
   * @param aChildables boolean - <code>true</code> to include tags with flag {@link ESkTagKind#isChildable()}
   * @param aMarkables boolean - <code>true</code> to include tags with flag {@link ESkTagKind#isMarkable()}
   * @return {@link IStridablesList}&lt;{@link ISkTag}&gt; - the scion tags content as a list
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  IStridablesList<ISkTag> listScionTags( boolean aIsSelf, boolean aChildables, boolean aMarkables );

  // ------------------------------------------------------------------------------------
  // inline methods for convenience

  @SuppressWarnings( "javadoc" )
  default ISkTag root() {
    return section().root();
  }

}
