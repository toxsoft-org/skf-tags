package org.toxsoft.skf.tags.skide;

import org.eclipse.e4.core.contexts.*;
import org.toxsoft.core.tsgui.graphics.icons.*;

/**
 * Application common constants.
 *
 * @author hazard157
 */
@SuppressWarnings( "javadoc" )
public interface ISkidePluginTagsConstants {

  // ------------------------------------------------------------------------------------
  // Icons

  String PREFIX_OF_ICON_FIELD_NAME = "ICONID_";            //$NON-NLS-1$
  String ICONID_SKIDE_TAGS_EDITOR  = "app-yarliks-editor"; //$NON-NLS-1$

  /**
   * Constants registration.
   *
   * @param aWinContext {@link IEclipseContext} - windows level context
   */
  static void init( IEclipseContext aWinContext ) {
    ITsIconManager iconManager = aWinContext.get( ITsIconManager.class );
    iconManager.registerStdIconByIds( Activator.PLUGIN_ID, ISkidePluginTagsConstants.class, PREFIX_OF_ICON_FIELD_NAME );
    //
  }

}
