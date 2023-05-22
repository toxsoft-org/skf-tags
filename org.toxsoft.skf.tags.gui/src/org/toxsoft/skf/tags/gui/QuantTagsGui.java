package org.toxsoft.skf.tags.gui;

import org.eclipse.e4.core.contexts.*;
import org.toxsoft.core.tsgui.bricks.quant.*;
import org.toxsoft.skf.tags.gui.km5.*;
import org.toxsoft.uskat.core.gui.km5.*;

/**
 * The library quant.
 *
 * @author dima
 */
public class QuantTagsGui
    extends AbstractQuant {

  /**
   * Constructor.
   */
  public QuantTagsGui() {
    super( QuantTagsGui.class.getSimpleName() );
    KM5Utils.registerContributorCreator( KM5TagsContributor.CREATOR );
  }

  @Override
  protected void doInitApp( IEclipseContext aAppContext ) {
    // nop
  }

  @Override
  protected void doInitWin( IEclipseContext aWinContext ) {
    ISkTagsGuiConstants.init( aWinContext );
  }

}
