package org.toxsoft.skf.tags.lib;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.validator.impl.*;
import org.toxsoft.core.tslib.coll.basis.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.uskat.core.api.objserv.*;

/**
 * The tags section is the root of the child tags tree.
 *
 * @author hazard157
 */
public interface ISkTagSection
    extends ISkObject, ITsClearable {

  IStridablesList<ISkTag> rootTags();

  /**
   * Finds the tag by its ID.
   *
   * @param aTagId String - the tag ID
   * @return {@link ISkTag} - the found tag or <code>null</code>
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  ISkTag findTag( String aTagId );

  ISkTag createTag( ISkTag aParent, IOptionSet aAttrs );

  ISkTag editTag( String aTagId, IOptionSet aAttrs );

  ISkTag removeTag( String aTagId );

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
