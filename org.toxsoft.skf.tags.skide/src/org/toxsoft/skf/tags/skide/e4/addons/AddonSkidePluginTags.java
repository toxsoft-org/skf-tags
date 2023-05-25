package org.toxsoft.skf.tags.skide.e4.addons;

import org.eclipse.e4.core.contexts.*;
import org.toxsoft.core.tsgui.bricks.quant.*;
import org.toxsoft.core.tsgui.mws.bases.*;
import org.toxsoft.skf.tags.gui.*;
import org.toxsoft.skf.tags.skide.*;
import org.toxsoft.skf.tags.skide.Activator;
import org.toxsoft.skf.tags.skide.main.*;
import org.toxsoft.skide.core.api.*;

/**
 * Plugin addon.
 *
 * @author dima
 */
public class AddonSkidePluginTags
    extends MwsAbstractAddon {

  /**
   * Constructor.
   */
  public AddonSkidePluginTags() {
    super( Activator.PLUGIN_ID );
  }

  // ------------------------------------------------------------------------------------
  // MwsAbstractAddon
  //

  @Override
  protected void initApp( IEclipseContext aAppContext ) {
    ISkideEnvironment skEnv = aAppContext.get( ISkideEnvironment.class );
    skEnv.pluginsRegistrator().registerPlugin( SkidePluginTags.INSTANCE );
  }

  @Override
  protected void doRegisterQuants( IQuantRegistrator aQuantRegistrator ) {
    aQuantRegistrator.registerQuant( new QuantTagsGui() );
  }

  @Override
  protected void initWin( IEclipseContext aWinContext ) {
    ISkidePluginTagsConstants.init( aWinContext );
    //
  }

}
