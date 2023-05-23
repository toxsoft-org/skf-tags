package org.toxsoft.skf.tags.gui;

import static org.toxsoft.skf.tags.gui.ISkTagsGuiSharedResources.*;
import static org.toxsoft.uskat.core.ISkHardConstants.*;

import org.eclipse.e4.core.contexts.*;
import org.toxsoft.core.tsgui.bricks.actions.*;
import org.toxsoft.core.tsgui.graphics.icons.*;

/**
 * Plugin constants.
 *
 * @author dima
 */
@SuppressWarnings( "javadoc" )
public interface ISkTagsGuiConstants {

  // ------------------------------------------------------------------------------------
  // Icons

  String PREFIX_OF_ICONID_FIELD_NAME = "ICONID_";   //$NON-NLS-1$
  String ICONID_TAG                  = "yarlik";    //$NON-NLS-1$
  String ICONID_GROUP                = "yarlik-ok"; //$NON-NLS-1$
  String ICONID_SECTION              = "plant";     //$NON-NLS-1$

  // ------------------------------------------------------------------------------------
  // Actions

  String ACTID_CREATE_FOLDER = SK_ID + ".tags.gui.create.section"; //$NON-NLS-1$

  TsActionDef ACDEF_CREATE_FOLDER = TsActionDef.ofPush2( ACTID_CREATE_FOLDER, //
      STR_N_CREATE_FOLDER, STR_D_CREATE_FOLDER, ICONID_SECTION );

  String ACTID_COPY_TAG = SK_ID + ".tags.gui.copy.tag"; //$NON-NLS-1$

  TsActionDef ACDEF_COPY_TAG = TsActionDef.ofPush2( ACTID_CREATE_FOLDER, //
      STR_N_COPY_TAG, STR_D_COPY_TAG, ITsStdIconIds.ICONID_EDIT_COPY );

  String ACTID_SELECT_MULTY_GWIDS = SK_ID + ".tags.gui.select.gwids"; //$NON-NLS-1$

  TsActionDef ACDEF_SELECT_MULTY_GWIDS = TsActionDef.ofPush2( ACTID_SELECT_MULTY_GWIDS, //
      STR_N_SELECT_MULTY_GWIDS, STR_D_SELECT_MULTY_GWIDS, ITsStdIconIds.ICONID_ITEMS_CHECK_ALL );

  /**
   * Constants registration.
   *
   * @param aWinContext {@link IEclipseContext} - windows level context
   */
  static void init( IEclipseContext aWinContext ) {
    ITsIconManager iconManager = aWinContext.get( ITsIconManager.class );
    iconManager.registerStdIconByIds( Activator.PLUGIN_ID, ISkTagsGuiConstants.class, PREFIX_OF_ICONID_FIELD_NAME );
  }

}
