package org.toxsoft.skf.tags.lib_old_3;

import org.toxsoft.core.tslib.coll.*;
import org.toxsoft.core.tslib.gw.gwid.*;

public interface ISkTagService {

  ISkTagManager tagManager();

  IGwidList getFlagged( String aTagId );

  IList<ISkMark> getMarks( String aTagId );

}
