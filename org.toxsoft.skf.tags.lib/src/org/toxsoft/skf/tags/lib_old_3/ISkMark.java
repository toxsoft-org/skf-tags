package org.toxsoft.skf.tags.lib_old_3;

import org.toxsoft.core.tslib.av.*;
import org.toxsoft.core.tslib.gw.gwid.*;
import org.toxsoft.core.tslib.gw.skid.*;

public interface ISkMark {

  Skid tag();

  Gwid entity();

  IAtomicValue value();

}
