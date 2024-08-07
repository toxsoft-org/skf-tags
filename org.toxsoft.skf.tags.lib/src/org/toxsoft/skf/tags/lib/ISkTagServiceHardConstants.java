package org.toxsoft.skf.tags.lib;

import static org.toxsoft.core.tslib.av.EAtomicType.*;
import static org.toxsoft.core.tslib.av.impl.AvUtils.*;
import static org.toxsoft.core.tslib.av.metainfo.IAvMetaConstants.*;
import static org.toxsoft.skf.tags.lib.l10n.ISkTagServiceSharedResources.*;
import static org.toxsoft.uskat.core.ISkHardConstants.*;

import org.toxsoft.core.tslib.av.impl.*;
import org.toxsoft.core.tslib.av.metainfo.*;

/**
 * {@link ISkTagService} hard-coded constants.
 *
 * @author hazard157
 */
public interface ISkTagServiceHardConstants {

  /**
   * {@link ISkTagGroup#params()} option: hint to mark entity by only one tag of this group. <br>
   * Usage: <code>true</code> means that tags of this group behave like radio buttons - when one tag marks entity all
   * other tags of this group does not marks entity. However, this is just a hint for application. No checks are done if
   * any entity is marked by two or more marks from this group.<br>
   * Default value: <code>false</code> - entity may be marked by any number of tags of this group
   */
  IDataDef OPDEF_IS_RADIO_GROUP = DataDef.create( SK_ID + ".IsRadioGroup", BOOLEAN, //$NON-NLS-1$
      TSID_NAME, STR_IS_RADIO_GROUP, //
      TSID_DESCRIPTION, STR_IS_RADIO_GROUP_D, //
      TSID_DEFAULT_VALUE, AV_FALSE //
  );

}
