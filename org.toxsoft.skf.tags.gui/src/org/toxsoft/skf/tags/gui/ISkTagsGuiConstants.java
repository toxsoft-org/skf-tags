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

  String PREFIX_OF_ICONID_FIELD_NAME = "ICONID_";    //$NON-NLS-1$
  String ICONID_TAG                  = "legenda_on"; //$NON-NLS-1$
  String ICONID_GROUP                = "users-list"; //$NON-NLS-1$
  String ICONID_SECTION              = "users-list"; //$NON-NLS-1$

  // ------------------------------------------------------------------------------------
  // Actions

  String ACTID_CREATE_FOLDER = SK_ID + ".tags.gui.create.section"; //$NON-NLS-1$

  TsActionDef ACDEF_CREATE_FOLDER = TsActionDef.ofPush2( ACTID_CREATE_FOLDER, //
      STR_N_CREATE_FOLDER, STR_D_CREATE_FOLDER, ICONID_SECTION );

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
