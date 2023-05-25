package org.toxsoft.skf.tags.skide.main;

import static org.toxsoft.skf.tags.skide.ISkidePluginTagsSharedResources.*;

import org.eclipse.swt.*;
import org.eclipse.swt.widgets.*;
import org.toxsoft.core.tsgui.bricks.ctx.*;
import org.toxsoft.core.tsgui.graphics.icons.*;
import org.toxsoft.core.tsgui.panels.*;
import org.toxsoft.core.tsgui.utils.layout.*;
import org.toxsoft.skf.tags.gui.*;
import org.toxsoft.skf.tags.gui.panels.*;
import org.toxsoft.skide.core.api.*;
import org.toxsoft.skide.core.api.impl.*;

/**
 * {@link AbstractSkideUnitPanel} implementation.
 *
 * @author dima
 */
class SkideUnitPanelTagsEditor
    extends AbstractSkideUnitPanel {

  public SkideUnitPanelTagsEditor( ITsGuiContext aContext, ISkideUnit aUnit ) {
    super( aContext, aUnit );
  }

  @Override
  protected Control doCreateControl( Composite aParent ) {
    TsPanel backPanel = new TsPanel( aParent, tsContext() );
    backPanel.setLayout( new BorderLayout() );
    TabFolder tabFolder = new TabFolder( backPanel, SWT.TOP );
    EIconSize tabIconSize = hdpiService().getJFaceCellIconsSize();
    // tags editor tab
    TabItem tiTagsEditor = new TabItem( tabFolder, SWT.NONE );
    PanelTagsEditor panelTagsEditor = new PanelTagsEditor( tsContext(), null );
    tiTagsEditor.setControl( panelTagsEditor.createControl( tabFolder ) );
    tiTagsEditor.setText( STR_SKIDE_TAGS_EDITOR );
    tiTagsEditor.setToolTipText( STR_SKIDE_TAGS_EDITOR_D );
    tiTagsEditor.setImage( iconManager().loadStdIcon( ISkTagsGuiConstants.ICONID_TAG_PLANT, tabIconSize ) );
    return backPanel;
  }

}
