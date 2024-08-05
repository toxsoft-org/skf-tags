package org.toxsoft.skf.tags.lib_old_2;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.av.utils.*;
import org.toxsoft.core.tslib.bricks.strid.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.validator.impl.*;
import org.toxsoft.core.tslib.coll.basis.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.uskat.core.api.objserv.*;

/**
 * The tags section is the root of the child tags tree.
 * <p>
 * Tree-formation of categories is provided by the format of ID-paths - identifiers {@link ISkTag#id()}. Child
 * categories append the local id-name identifier {@link ISkTag#localId()} to the id-path identifier of the parent. The
 * root tag is the {@link ISkTag} itself, whose identifier is an empty string.
 * <p>
 * Tag section parameters {@link ISkTagSection#params()} is not used by the tag service itself. It is designed for
 * application-specific needs.
 *
 * @author hazard157
 */
public interface ISkTagSection
    extends ISkObject, IParameterized, ITsClearable {

  /**
   * This class ID.
   */
  String CLASS_ID = ISkTagServiceHardConstants.CLSID_TAG_SECTION;

  /**
   * Returns the root of this section tags hierarchy.
   * <p>
   * Even though the root is a tag, and hence it is a {@link IStridable}, its id {@link #id()} is an empty string! The
   * {@link ISkTag#kind()} always returns {@link ESkTagKind#GROUP}. Root tag {@link ISkTag#nmName()} and
   * {@link ISkTag#description()} returns the same content as the section methods.
   * <p>
   * The root tag can not be removed or edited.
   *
   * @return {@link ISkTag} - the root tag, special implementation of a tag
   */
  ISkTag root();

  /**
   * Finds the tag by its ID.
   * <p>
   * For an empty string returns the {@link #root()}.
   *
   * @param aTagId String - the tag ID
   * @return {@link ISkTag} - the found tag or <code>null</code>
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ISkTag findTag( String aTagId );

  /**
   * Returns the markable tags from this section.
   * <p>
   * Markable tags are the tags with the flag {@link ESkTagKind#isMarkable()} set.
   *
   * @return {@link IStridablesList}&lt;{@link ISkTag}&gt; - the list of the markable tags in this section
   */
  IStridablesList<ISkTag> listMarkableTags();

  /**
   * Creates the new tag.
   *
   * @param aTagId String - the tag ID
   * @param aKind {@link ESkTagKind} - the kind of the tag to be created
   * @param aParams {@link IOptionSet} - the tag parameter values
   * @param aName String - the tag name
   * @param aDescription String - the tag description
   * @return {@link ISkTag} - created tag
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException validation failed
   */
  ISkTag createTag( String aTagId, ESkTagKind aKind, String aName, String aDescription, IOptionSet aParams );

  /**
   * Changes the tag parameters.
   * <p>
   * Note: <code>aParams</code> will replace previous set in {@link ISkTag#params()}.
   *
   * @param aTagId String - the tag ID
   * @param aNewKind {@link ESkTagKind} - the new kind of the tag
   * @param aName String - the tag name
   * @param aDescription String - the tag description
   * @param aParams {@link IOptionSet} - the new values for {@link ISkTag#params()}
   * @return {@link ISkTag} - the edited tag
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException validation failed
   */
  ISkTag editTag( String aTagId, ESkTagKind aNewKind, String aName, String aDescription, IOptionSet aParams );

  /**
   * Removes the tag with whole subtree.
   *
   * @param aTagId String - the tag ID
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsValidationFailedRtException validation failed
   */
  void removeTag( String aTagId );

  /**
   * Removes all child tags.
   * <p>
   * Checks one by one that all child tags can be removed.
   *
   * @throws TsValidationFailedRtException validation failed
   */
  @Override
  void clear();

  // ------------------------------------------------------------------------------------
  // inline methods for convenience

  @SuppressWarnings( "javadoc" )
  default ISkTag getTag( String aTagId ) {
    return TsItemNotFoundRtException.checkNoNull( findTag( aTagId ) );
  }

}
