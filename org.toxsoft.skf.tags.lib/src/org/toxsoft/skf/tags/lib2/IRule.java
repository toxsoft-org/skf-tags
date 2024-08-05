package org.toxsoft.skf.tags.lib2;

import org.toxsoft.core.tslib.gw.gwid.*;
import org.toxsoft.uskat.core.*;

public interface IRule {

  IGwidList query( ISkCoreApi aCoreApi, IGwidList aInput );

}
