package org.toxsoft.skf.tags.lib_old_2;

import static org.toxsoft.core.tslib.av.impl.AvUtils.*;
import static org.toxsoft.core.tslib.av.metainfo.IAvMetaConstants.*;
import static org.toxsoft.skf.tags.lib_old_2.ISkTagServiceSharedResources.*;
import static org.toxsoft.uskat.core.ISkHardConstants.*;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.av.opset.impl.*;
import org.toxsoft.core.tslib.coll.helpers.*;
import org.toxsoft.core.tslib.coll.primtypes.impl.*;
import org.toxsoft.uskat.core.api.sysdescr.dto.*;
import org.toxsoft.uskat.core.impl.dto.*;

/**
 * {@link ISkTagService} hard-coded constants.
 *
 * @author hazard157
 */
public interface ISkTagServiceHardConstants {

  /**
   * Identifier prefix of all classes owned by this service.
   */
  String CLSID_PREFIX = SK_ID + ".TagManager.class"; //$NON-NLS-1$

  /**
   * Tag section class ID.
   */
  String CLSID_TAG_SECTION = CLSID_PREFIX + ".TagSection"; //$NON-NLS-1$

  /**
   * {@link ISkTag} implementation base class ID.
   */
  String CLSID_TAG_BASE = CLSID_PREFIX + ".TagBase"; //$NON-NLS-1$

  /**
   * ID of the attribute {@link #ATRINF_TAG_KIND}.
   */
  String ATRID_TAG_KIND = "tagKind"; //$NON-NLS-1$

  /**
   * Definition of the attribute {@link ISkTag#kind()}.
   */
  IDtoAttrInfo ATRINF_TAG_KIND = DtoAttrInfo.create2( ATRID_TAG_KIND, DDEF_VALOBJ, //
      TSID_NAME, STR_TAG_KIND, //
      TSID_DESCRIPTION, STR_TAG_KIND_D, //
      TSID_KEEPER_ID, ESkTagKind.KEEPER_ID, //
      TSID_DEFAULT_VALUE, avValobj( ESkTagKind.REGULAR ) //
  );

  /**
   * ID of the attribute {@link #ATRINF_TAG_PARAMS}.
   */
  String ATRID_TAG_PARAMS = "tagParams"; //$NON-NLS-1$

  /**
   * Definition of the attribute {@link ISkTag#kind()}.
   */
  IDtoAttrInfo ATRINF_TAG_PARAMS = DtoAttrInfo.create2( ATRID_TAG_PARAMS, DDEF_VALOBJ, //
      TSID_NAME, STR_TAG_PARAMS, //
      TSID_DESCRIPTION, STR_TAG_PARAMS_D, //
      TSID_KEEPER_ID, OptionSetKeeper.KEEPER_ID, //
      TSID_DEFAULT_VALUE, avValobj( IOptionSet.NULL ) //
  );

  /**
   * ID of the link {@link #LNKID_SECTION_TAGS}.
   */
  String LNKID_SECTION_TAGS = "sectionTags"; //$NON-NLS-1$

  /**
   * Definition of the link to the tags contained in the section.
   */
  IDtoLinkInfo LNKINF_SECTION_TAGS = DtoLinkInfo.create2( LNKID_SECTION_TAGS, //
      new SingleStringList( CLSID_TAG_BASE ), //
      CollConstraint.NONE, //
      TSID_NAME, STR_LINK_SECTION_TAGS, //
      TSID_DESCRIPTION, STR_LINK_SECTION_TAGS_D //
  );

  String CLBID_MARKED_GWIDS = "markedGwids"; //$NON-NLS-1$

  IDtoClobInfo CLBINF_MARKED_GWIDS = DtoClobInfo.create2( CLBID_MARKED_GWIDS, //
      TSID_NAME, "", //
      TSID_DESCRIPTION, "" //
  );

}
