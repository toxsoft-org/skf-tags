package org.toxsoft.skf.tags.skide.main;

import static org.toxsoft.core.tsgui.bricks.actions.ITsStdActionDefs.*;
import static org.toxsoft.core.tslib.av.metainfo.IAvMetaConstants.*;
import static org.toxsoft.skf.tags.skide.ISkidePluginTagsSharedResources.*;
import static org.toxsoft.skide.core.ISkideCoreConstants.*;

import org.toxsoft.core.tsgui.bricks.ctx.*;
import org.toxsoft.core.tsgui.dialogs.*;
import org.toxsoft.core.tslib.av.opset.impl.*;
import org.toxsoft.skf.tags.gui.*;
import org.toxsoft.skide.core.api.*;
import org.toxsoft.skide.core.api.impl.*;

/**
 * SkiDE unit: tags editor.
 *
 * @author dima
 */
public class SkideUnitTagsEditor
    extends AbstractSkideUnit {

  /**
   * The plugin ID.
   */
  public static final String UNIT_ID = SKIDE_FULL_ID + ".unit.template_1"; //$NON-NLS-1$

  SkideUnitTagsEditor( ITsGuiContext aContext, AbstractSkidePlugin aCreator ) {
    super( UNIT_ID, OptionSetUtils.createOpSet( //
        TSID_NAME, STR_SKIDE_TAGS_EDITOR, //
        TSID_DESCRIPTION, STR_SKIDE_TAGS_EDITOR_D, //
        TSID_ICON_ID, ISkTagsGuiConstants.ICONID_TAG_PLANT //
    ), aContext, aCreator );
    unitActions().add( ACDEF_ABOUT );
  }

  @Override
  protected void doHandleAction( String aActionId ) {
    switch( aActionId ) {
      case ACTID_ABOUT: {
        // TODO display complete info about unit
        TsDialogUtils.info( getShell(), id() + '\n' + nmName() + '\n' + description() );
        break;
      }
      default: {
        // TODO display info about known but unhandled action
        TsDialogUtils.info( getShell(), aActionId );
      }
    }
  }

  @Override
  protected AbstractSkideUnitPanel doCreateUnitPanel( ITsGuiContext aContext ) {
    return new SkideUnitPanelTagsEditor( aContext, this );
  }

}
