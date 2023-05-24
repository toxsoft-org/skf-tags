package org.toxsoft.skf.tags.gui.panels;

import org.eclipse.swt.widgets.*;
import org.toxsoft.core.tsgui.bricks.ctx.*;
import org.toxsoft.core.tsgui.bricks.ctx.impl.*;
import org.toxsoft.core.tsgui.m5.*;
import org.toxsoft.core.tsgui.m5.gui.panels.*;
import org.toxsoft.core.tsgui.m5.model.*;
import org.toxsoft.core.tsgui.utils.layout.*;
import org.toxsoft.core.tslib.bricks.strid.more.*;
import org.toxsoft.core.tslib.gw.gwid.*;
import org.toxsoft.skf.tags.gui.km5.*;
import org.toxsoft.uskat.core.connection.*;
import org.toxsoft.uskat.core.gui.conn.*;
import org.toxsoft.uskat.core.gui.glib.*;

/**
 * Tags editor - allows to create and edit GwidList
 * <p>
 * Panel contents:
 * <ul>
 * <li>left pane - GwidList;</li>
 * <li>right pane - selected Gwids editor.</li>
 * </ul>
 *
 * @author dima
 */
public class PanelGwidsEditor
    extends AbstractSkLazyPanel {

  private final IM5CollectionPanel<Gwid> gwidListEditorPane;

  /**
   * Constrcutor.
   * <p>
   * Panel will use {@link ISkConnection} with the given ID from {@link ISkConnectionSupplier#getConn(IdChain)}. If
   * <code>aSuppliedConnectionId</code> = <code>null</code>, then {@link ISkConnectionSupplier#defConn()} will be used.
   *
   * @param aContext {@link ITsGuiContext} - the context
   * @param aSuppliedConnectionId {@link IdChain} - ID of connection or <code>null</code> for default
   */
  public PanelGwidsEditor( ITsGuiContext aContext, IdChain aSuppliedConnectionId ) {
    super( aContext, aSuppliedConnectionId );
    IM5Model<Gwid> gwidM5Model = m5().getModel( GwidM5Model.MODEL_ID, Gwid.class );
    IM5LifecycleManager<Gwid> lm = gwidM5Model.getLifecycleManager( skConn() );
    ITsGuiContext ctx = new TsGuiContext( tsContext() );
    gwidListEditorPane = gwidM5Model.panelCreator().createCollEditPanel( ctx, lm.itemsProvider(), lm );
  }

  // ------------------------------------------------------------------------------------
  // implementation
  //

  // ------------------------------------------------------------------------------------
  // AbstractSkStdEventsProducerLazyPanel
  //

  @Override
  protected void doInitGui( Composite aParent ) {
    gwidListEditorPane.createControl( aParent );
    gwidListEditorPane.getControl().setLayoutData( BorderLayout.CENTER );
  }

}
