package org.toxsoft.skf.tags.lib;

import org.toxsoft.core.tslib.coll.helpers.*;

/**
 * The service listener.
 *
 * @author hazard157
 */
public interface ISkTagsServiceListener {

  /**
   * Called when tags tree structure changed.
   * <p>
   * Note: this method is not called when the root tags are created/removed, instead
   * {@link #onTagsRootsChanged(ISkTagsService, ECrudOp, String)} method is called.
   *
   * @param aSource {@link ISkTagsService} - the event source
   * @param aChangesRoot {@link ISkRootTag} - the root of changed hierarchy
   */
  void onTagsTreeChanged( ISkTagsService aSource, ISkRootTag aChangesRoot );

  /**
   * Called when single tag properties has beeing changed.
   *
   * @param aSource {@link ISkTagsService} - the event source
   * @param aChangedTag {@link ISkTag} - the changed tag ID
   */
  void onTagPropsChanged( ISkTagsService aSource, ISkTag aChangedTag );

  /**
   * Called when root tags list changed.
   *
   * @param aSource {@link ISkTagsService} - the event source
   * @param aOp {@link ECrudOp} - what changed
   * @param aRootId String - affected root tag ID or <code>null</code> for {@link ECrudOp#LIST}
   */
  void onTagsRootsChanged( ISkTagsService aSource, ECrudOp aOp, String aRootId );

}
