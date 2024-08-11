package org.toxsoft.skf.tags.lib.impl;

import static org.toxsoft.core.tslib.av.EAtomicType.*;
import static org.toxsoft.core.tslib.av.impl.AvUtils.*;
import static org.toxsoft.core.tslib.av.metainfo.IAvMetaConstants.*;
import static org.toxsoft.core.tslib.gw.IGwHardConstants.*;
import static org.toxsoft.skf.tags.lib.l10n.ISkTagServiceSharedResources.*;
import static org.toxsoft.uskat.core.ISkHardConstants.*;

import org.toxsoft.core.tslib.av.impl.*;
import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.av.opset.impl.*;
import org.toxsoft.core.tslib.bricks.strid.coll.impl.*;
import org.toxsoft.core.tslib.coll.helpers.*;
import org.toxsoft.core.tslib.coll.primtypes.impl.*;
import org.toxsoft.uskat.core.api.sysdescr.dto.*;
import org.toxsoft.uskat.core.impl.dto.*;

/**
 * Tag service implementation constants.
 *
 * @author hazard157
 */
interface ISkTagServiceInternalHardConstants {

  String CLSID_PREFIX    = SK_ID + ".tags.class";      //$NON-NLS-1$
  String CLSID_TAG       = CLSID_PREFIX + ".Tag";      //$NON-NLS-1$
  String CLSID_TAG_GROUP = CLSID_PREFIX + ".TagGroup"; //$NON-NLS-1$

  String ATRID_STRIDABLES_PARAMS = "Params";      //$NON-NLS-1$
  String RIVID_GROUP_PARENT      = "ParentGroup"; //$NON-NLS-1$
  String LNKID_CHILD_TAGS        = "ChildTags";   //$NON-NLS-1$

  DtoAttrInfo ATRINF_STRIDABLE_PARAMS = DtoAttrInfo.create1( ATRID_STRIDABLES_PARAMS, //
      DataType.create( VALOBJ, //
          TSID_KEEPER_ID, OptionSetKeeper.KEEPER_ID, //
          TSID_IS_NULL_ALLOWED, AV_TRUE, //
          TSID_DEFAULT_VALUE, avValobj( IOptionSet.NULL ) //
      ), //
      IOptionSet.NULL //
  );

  DtoRivetInfo RIVINF_GROUP_PARENT = DtoRivetInfo.create1( RIVID_GROUP_PARENT, CLSID_TAG_GROUP, 1, //
      IOptionSet.NULL //
  );

  DtoLinkInfo LNKINF_CHILD_TAGS = DtoLinkInfo.create1( LNKID_CHILD_TAGS, new SingleStringList( CLSID_TAG ), //
      CollConstraint.NONE, //
      IOptionSet.NULL //
  );

  IDtoClassInfo CLSINF_TAG = DtoClassInfo.create( CLSID_TAG, GW_ROOT_CLASS_ID, //
      OptionSetUtils.createOpSet( //
          TSID_NAME, STR_CLS_TAG, //
          TSID_NAME, STR_CLS_TAG_D, //
          OPDEF_SK_IS_SOURCE_CODE_DEFINED_CLASS, AV_TRUE, //
          OPDEF_SK_IS_SOURCE_USKAT_SYSEXT_CLASS, AV_TRUE //
      ), //
      new StridablesList<>( // ATTRs
          ATRINF_STRIDABLE_PARAMS //
      ), //
      new StridablesList<>( // CLOBs
      ), //
      new StridablesList<>( // RIVETs
      ), //
      new StridablesList<>( // LINKs
      ), //
      new StridablesList<>( // RTDATA
      ), //
      new StridablesList<>( // CMDs
      ), //
      new StridablesList<>( // EVENTs
      ) //
  );

  IDtoClassInfo CLSINF_TAG_GROUP = DtoClassInfo.create( CLSID_TAG_GROUP, GW_ROOT_CLASS_ID, //
      OptionSetUtils.createOpSet( //
          TSID_NAME, STR_CLS_TAG_GROUP, //
          TSID_NAME, STR_CLS_TAG_GROUP_D, //
          OPDEF_SK_IS_SOURCE_CODE_DEFINED_CLASS, AV_TRUE, //
          OPDEF_SK_IS_SOURCE_USKAT_SYSEXT_CLASS, AV_TRUE //
      ), //
      new StridablesList<>( // ATTRs
          ATRINF_STRIDABLE_PARAMS //
      ), //
      new StridablesList<>( // CLOBs
      ), //
      new StridablesList<>( // RIVETs
          RIVINF_GROUP_PARENT //
      ), //
      new StridablesList<>( // LINKs
          LNKINF_CHILD_TAGS //
      ), //
      new StridablesList<>( // RTDATA
      ), //
      new StridablesList<>( // CMDs
      ), //
      new StridablesList<>( // EVENTs
      ) //
  );

}
