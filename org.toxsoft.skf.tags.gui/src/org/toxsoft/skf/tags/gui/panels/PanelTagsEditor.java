package org.toxsoft.skf.tags.gui.panels;

import org.eclipse.swt.widgets.*;
import org.toxsoft.core.tsgui.bricks.ctx.*;
import org.toxsoft.core.tsgui.bricks.ctx.impl.*;
import org.toxsoft.core.tsgui.m5.*;
import org.toxsoft.core.tsgui.m5.gui.panels.*;
import org.toxsoft.core.tsgui.m5.model.*;
import org.toxsoft.core.tslib.bricks.strid.more.*;
import org.toxsoft.skf.tags.gui.km5.*;
import org.toxsoft.skf.tags.lib.*;
import org.toxsoft.uskat.core.connection.*;
import org.toxsoft.uskat.core.gui.conn.*;
import org.toxsoft.uskat.core.gui.glib.*;

/**
 * Tags editor - allows to create and edit tags and folders. As sample used Goga's SdedClassEditor
 * <p>
 * Panel contents:
 * <ul>
 * <li>left pane - tags tree;</li>
 * <li>right pane - selected Gwids editor.</li>
 * </ul>
 *
 * @author dima
 */
public class PanelTagsEditor
    extends AbstractSkLazyPanel {

  private final IM5CollectionPanel<ISkTag> tagsEditorPane;
  // private final IM5CollectionPanel<Gwid> gwidListEditorPane;

  /**
   * Constrcutor.
   * <p>
   * Panel will use {@link ISkConnection} with the given ID from {@link ISkConnectionSupplier#getConn(IdChain)}. If
   * <code>aSuppliedConnectionId</code> = <code>null</code>, then {@link ISkConnectionSupplier#defConn()} will be used.
   *
   * @param aContext {@link ITsGuiContext} - the context
   * @param aSuppliedConnectionId {@link IdChain} - ID of connection or <code>null</code> for default
   */
  public PanelTagsEditor( ITsGuiContext aContext, IdChain aSuppliedConnectionId ) {
    super( aContext, aSuppliedConnectionId );
    // left pane
    IM5Model<ISkTag> skTagM5Model = m5().getModel( SkTagM5Model.MODEL_ID, ISkTag.class );
    IM5LifecycleManager<ISkTag> tagLm = skTagM5Model.getLifecycleManager( skConn() );
    ITsGuiContext tgCtx = new TsGuiContext( tsContext() );
    tagsEditorPane = skTagM5Model.panelCreator().createCollEditPanel( tgCtx, tagLm.itemsProvider(), tagLm );

    // right pane
    // IM5Model<Gwid> gwidM5Model = m5().getModel( GwidM5Model.MODEL_ID, Gwid.class );
    // IM5LifecycleManager<Gwid> gwdListLm = gwidM5Model.getLifecycleManager( skConn() );
    // ITsGuiContext gwidCtx = new TsGuiContext( tsContext() );
    // gwidListEditorPane =
    // gwidM5Model.panelCreator().createCollEditPanel( gwidCtx, gwdListLm.itemsProvider(), gwdListLm );

  }

  // ------------------------------------------------------------------------------------
  // implementation
  //

  /**
   * Handles selection change in the left panel {@link #tagsEditorPane}.
   */
  private void whenTagTreeSelectionChanges() {
    // ISkTag sel = tagsEditorPane.selectedItem();
    // if( sel != null ) {
    // gwidListEditorPane.setItemsProvider( () -> sel.getMarkedGwids() );
    // }
    // else {
    // gwidListEditorPane.setItemsProvider( GwidList::new );
    // }
  }

  // ------------------------------------------------------------------------------------
  // AbstractSkStdEventsProducerLazyPanel
  //

  @Override
  protected void doInitGui( Composite aParent ) {
    // пока просто одну панель
    tagsEditorPane.createControl( aParent );

    // SashForm sfMain = new SashForm( aParent, SWT.HORIZONTAL );
    // tagsEditorPane.createControl( sfMain );
    // gwidListEditorPane.createControl( sfMain );
    // sfMain.setWeights( 3000, 7000 );
    // // setup
    // tagsEditorPane.addTsSelectionListener( ( s, i ) -> whenTagTreeSelectionChanges() );
  }

}
