package org.toxsoft.skf.tags.skide.main;

import static org.toxsoft.core.tsgui.bricks.actions.ITsStdActionDefs.*;
import static org.toxsoft.core.tslib.av.metainfo.IAvMetaConstants.*;
import static org.toxsoft.skf.tags.skide.ISkidePluginTagsConstants.*;
import static org.toxsoft.skf.tags.skide.ISkidePluginTagsSharedResources.*;

import org.toxsoft.core.tsgui.bricks.ctx.*;
import org.toxsoft.core.tslib.av.opset.impl.*;
import org.toxsoft.skide.core.*;
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
  public static final String UNIT_ID = ISkideCoreConstants.SKIDE_FULL_ID + ".unit.tags.editor"; //$NON-NLS-1$

  SkideUnitTagsEditor( ITsGuiContext aContext, AbstractSkidePlugin aCreator ) {
    super( UNIT_ID, OptionSetUtils.createOpSet( //
        TSID_NAME, STR_SKIDE_TAGS_EDITOR, //
        TSID_DESCRIPTION, STR_SKIDE_TAGS_EDITOR_D, //
        TSID_ICON_ID, ICONID_SKIDE_TAGS_EDITOR //
    ), aContext, aCreator );
    unitActions().add( ACDEF_ABOUT );
  }

  @Override
  protected AbstractSkideUnitPanel doCreateUnitPanel( ITsGuiContext aContext ) {
    return new SkideUnitPanelTagsEditor( aContext, this );
  }

}
