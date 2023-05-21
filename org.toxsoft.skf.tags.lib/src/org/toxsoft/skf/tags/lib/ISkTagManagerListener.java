package org.toxsoft.skf.tags.lib;

import org.toxsoft.core.tslib.coll.helpers.*;

/**
 * Listener to the {@link ISkTagService} changes.
 *
 * @author hazard157
 */
public interface ISkTagManagerListener {

  /**
   * Called when some changes in sections list occurs.
   * <p>
   * The event {@link ECrudOp#EDIT} occurs only when section parameters {@link ISkTagSection#params()} changes, not on
   * tags change. For tag change listen to the {@link #onTagChanged(ISkTagService, ISkTagSection, ECrudOp, String)}.
   *
   * @param aSource {@link ISkTagService} - the event source manager
   * @param aOp {@link ECrudOp} - what happened
   * @param aSectionId String - affected section ID or <code>null</code> for {@link ECrudOp#LIST}
   */
  void onSectionChanged( ISkTagService aSource, ECrudOp aOp, String aSectionId );

  /**
   * Called when some changes in section tags list occurs.
   *
   * @param aSource {@link ISkTagService} - the event source manager
   * @param aSection {@link ISkTagSection} - the event source section
   * @param aOp {@link ECrudOp} - what happened
   * @param aTagId String - affected tag ID or <code>null</code> for {@link ECrudOp#LIST}
   */
  void onTagChanged( ISkTagService aSource, ISkTagSection aSection, ECrudOp aOp, String aTagId );

}
