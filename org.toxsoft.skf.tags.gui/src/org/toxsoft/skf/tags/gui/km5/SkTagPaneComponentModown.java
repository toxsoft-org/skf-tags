package org.toxsoft.skf.tags.gui.km5;

import static org.toxsoft.core.tsgui.bricks.actions.ITsStdActionDefs.*;
import static org.toxsoft.skf.tags.gui.ISkTagsGuiConstants.*;
import static org.toxsoft.skf.tags.gui.ISkTagsGuiSharedResources.*;

import org.toxsoft.core.tsgui.bricks.actions.*;
import org.toxsoft.core.tsgui.bricks.ctx.*;
import org.toxsoft.core.tsgui.bricks.stdevents.*;
import org.toxsoft.core.tsgui.bricks.tstree.tmm.*;
import org.toxsoft.core.tsgui.dialogs.datarec.*;
import org.toxsoft.core.tsgui.graphics.icons.*;
import org.toxsoft.core.tsgui.m5.*;
import org.toxsoft.core.tsgui.m5.gui.*;
import org.toxsoft.core.tsgui.m5.gui.mpc.impl.*;
import org.toxsoft.core.tsgui.m5.model.*;
import org.toxsoft.core.tsgui.m5.model.impl.*;
import org.toxsoft.core.tsgui.panels.toolbar.*;
import org.toxsoft.core.tslib.av.impl.*;
import org.toxsoft.core.tslib.coll.*;
import org.toxsoft.core.tslib.gw.gwid.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.reports.gui.panels.*;
import org.toxsoft.skf.tags.lib.*;
import org.toxsoft.uskat.core.connection.*;
import org.toxsoft.uskat.core.gui.conn.*;

/**
 * Панель просмотра/редактирования ISkTag
 *
 * @author dima
 */
public class SkTagPaneComponentModown
    extends MultiPaneComponentModown<ISkTag>
    implements ITsSelectionChangeListener<ISkTag> {

  SkTagPaneComponentModown( ITsGuiContext aContext, IM5Model<ISkTag> aModel, IM5ItemsProvider<ISkTag> aItemsProvider,
      IM5LifecycleManager<ISkTag> aLifecycleManager ) {
    super( aContext, aModel, aItemsProvider, aLifecycleManager );

    addTsSelectionListener( this );
    // установим панели деревопостроитель
    TagsTreeMaker treeMaker = new TagsTreeMaker( connection().coreApi().getService( ISkTagService.SERVICE_ID ) );
    tree().setTreeMaker( treeMaker );

    // установим текущий режим просмотра "дерево меток по секциям"
    treeModeManager().addTreeMode( new TreeModeInfo<>( TagsTreeMaker.TMID_GROUP_BY_SECTION, STR_N_TMID_GROUP_BY_SECTION,
        STR_D_TMID_GROUP_BY_SECTION, null, treeMaker ) );
    treeModeManager().setCurrentMode( TagsTreeMaker.TMID_GROUP_BY_SECTION );

  }

  @Override
  public void onTsSelectionChanged( Object aSource, ISkTag aSelectedItem ) {
    // регулируем активность
    if( aSelectedItem != null ) {
      toolbar().getAction( ACTID_COPY_TAG ).setEnabled( true );
    }
    else {
      toolbar().getAction( ACTID_COPY_TAG ).setEnabled( false );
    }
  }

  @Override
  protected ITsToolbar doCreateToolbar( ITsGuiContext aContext, String aName, EIconSize aIconSize,
      IListEdit<ITsActionDef> aActs ) {
    aActs.add( ACDEF_SEPARATOR );
    aActs.add( ACDEF_CREATE_FOLDER );
    aActs.add( ACDEF_COPY_TAG );
    aActs.add( ACDEF_SELECT_MULTY_GWIDS );

    ITsToolbar toolbar =

        super.doCreateToolbar( aContext, aName, aIconSize, aActs );

    toolbar.addListener( aActionId -> {
      // nop

    } );

    return toolbar;
  }

  @Override
  protected void doProcessAction( String aActionId ) {
    ISkTag selectedTag = selectedItem();

    switch( aActionId ) {
      case ACTID_COPY_TAG:
        // копирование существующего ярлыка
        copyTag( selectedTag );
        break;
      case ACTID_CREATE_FOLDER:
        // создаем новый раздел
        createFolder();
        break;
      case ACTID_SELECT_MULTY_GWIDS:
        selectParams( selectedTag );
        break;

      default:
        throw new TsNotAllEnumsUsedRtException( aActionId );
    }
  }

  /**
   * Создание нового раздела
   */
  private void createFolder() {
    // Получаем m5 модели секции и пользуемся ее диалогом создания
    IM5Domain m5 = connection().scope().get( IM5Domain.class );
    IM5Model<ISkTagSection> model = m5.getModel( SkTagSectionM5Model.MODEL_ID, ISkTagSection.class );
    ITsDialogInfo cdi = TsDialogInfo.forCreateEntity( tsContext() );
    IM5BunchEdit<ISkTagSection> initVals = new M5BunchEdit<>( model );
    if( M5GuiUtils.askCreate( tsContext(), model, initVals, cdi, model.getLifecycleManager( null ) ) != null ) {
      // добавили новую секцию, обновим дерево
      refresh();
    }
  }

  private void copyTag( ISkTag aSelTag ) {
    IM5Model<ISkTag> model = getModel();
    IM5Bunch<ISkTag> originalBunch = model.valuesOf( aSelTag );
    IM5BunchEdit<ISkTag> copyBunch = new M5BunchEdit<>( model );
    for( IM5FieldDef<ISkTag, ?> fd : originalBunch.model().fieldDefs() ) {
      copyBunch.set( fd.id(), originalBunch.get( fd ) );
    }
    ITsDialogInfo cdi = TsDialogInfo.forCreateEntity( tsContext() );
    ISkTag copyTag = M5GuiUtils.askCreate( tsContext(), model, copyBunch, cdi, lifecycleManager() );
    if( copyTag != null ) {
      // создали копию, обновим список
      refresh();
    }
  }

  private IM5Model<ISkTag> getModel() {
    ISkConnection conn = connection();

    IM5Domain m5 = conn.scope().get( IM5Domain.class );
    IM5Model<ISkTag> model = m5.getModel( SkTagM5Model.MODEL_ID, ISkTag.class );
    return model;
  }

  private ISkConnection connection() {
    ISkConnectionSupplier connSup = tsContext().get( ISkConnectionSupplier.class );
    ISkConnection conn = connSup.defConn();
    return conn;
  }

  /**
   * Открывает диалог для выбора списка Gwid
   *
   * @param aSelectedTag выбранный ярлык
   */
  private void selectParams( ISkTag aSelectedTag ) {
    IGwidList gwidList = new GwidList();

    if( aSelectedTag != null ) {
      gwidList = new GwidList( aSelectedTag.getMarkedGwids() );
    }
    gwidList = PanelGwidListSelector.selectGwids( gwidList, tsContext() );

    if( gwidList != null ) {
      IM5Model<ISkTag> model = getModel();
      IM5Bunch<ISkTag> originalBunch =
          aSelectedTag == null ? lifecycleManager().createNewItemValues() : model.valuesOf( aSelectedTag );
      IM5BunchEdit<ISkTag> copyBunch = new M5BunchEdit<>( model );
      for( IM5FieldDef<ISkTag, ?> fd : originalBunch.model().fieldDefs() ) {
        if( fd.id().equals( SkTagM5Model.FID_MARKED_GWIDS ) ) {
          copyBunch.set( fd.id(), AvUtils.avValobj( gwidList ) );
        }
        else {
          copyBunch.set( fd.id(), originalBunch.get( fd ) );
        }
      }
      lifecycleManager().create( copyBunch );
      // добавили новые параметры, обновим список
      refresh();
    }
  }

}
