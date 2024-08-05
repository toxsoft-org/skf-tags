package org.toxsoft.skf.tags.lib2;

import org.toxsoft.core.tslib.coll.helpers.*;
import org.toxsoft.core.tslib.gw.skid.*;
import org.toxsoft.uskat.core.*;

public interface ISkTagManagerListener {

  void onTagsChanged( ISkCoreApi aCoreApi, ECrudOp aOp, Skid aTagSkid );

}
