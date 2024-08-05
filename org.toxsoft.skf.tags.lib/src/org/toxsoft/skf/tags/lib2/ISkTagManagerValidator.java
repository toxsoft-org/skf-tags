package org.toxsoft.skf.tags.lib2;

import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.uskat.core.api.objserv.*;

public interface ISkTagManagerValidator {

  ValidationResult canCreateTag( IDtoObject aTagDto );

  ValidationResult canEditTag( IDtoObject aTagDto, ISkTag aTag );

  ValidationResult canRemoveTag( String aTagId );

}
