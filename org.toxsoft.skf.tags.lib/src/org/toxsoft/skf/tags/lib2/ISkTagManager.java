package org.toxsoft.skf.tags.lib2;

import org.toxsoft.core.tslib.bricks.events.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.uskat.core.api.objserv.*;

public interface ISkTagManager {

  // CRUD ops

  IStridablesList<ISkTag> tags();

  ISkTag defineTag( IDtoObject aTagDto );

  void removeTag( String aTagId );

  // TODO catalogization

  // service support

  ITsValidationSupport<ISkTagManagerValidator> svs();

  ITsEventer<ISkTagManagerListener> eventer();

}
