package org.toxsoft.skf.tags.skide.main;

import static org.toxsoft.core.tslib.av.metainfo.IAvMetaConstants.*;
import static org.toxsoft.skf.tags.skide.ISkidePluginTagsConstants.*;
import static org.toxsoft.skf.tags.skide.ISkidePluginTagsSharedResources.*;
import static org.toxsoft.skide.core.ISkideCoreConstants.*;

import org.toxsoft.core.tsgui.bricks.ctx.*;
import org.toxsoft.core.tslib.av.opset.impl.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.skide.core.api.*;

/**
 * SkIDE plugin: tags editor.
 *
 * @author dima
 */
public class SkidePluginTags
    extends AbstractSkidePlugin {

  /**
   * The plugin ID.
   */
  public static final String SKIDE_PLUGIN_ID = SKIDE_FULL_ID + ".plugin.tags.editor"; //$NON-NLS-1$

  /**
   * The singleton instance.
   */
  public static final AbstractSkidePlugin INSTANCE = new SkidePluginTags();

  SkidePluginTags() {
    super( SKIDE_PLUGIN_ID, OptionSetUtils.createOpSet( //
        TSID_NAME, STR_SKIDE_PLUGIN_TAGS_EDITOR, //
        TSID_DESCRIPTION, STR_SKIDE_PLUGIN_TAGS_EDITOR_D, //
        TSID_ICON_ID, ICONID_SKIDE_TAGS_EDITOR //
    ) );
  }

  @Override
  protected void doCreateUnits( ITsGuiContext aContext, IStridablesListEdit<ISkideUnit> aUnitsList ) {
    aUnitsList.add( new SkideUnitTagsEditor( aContext, this ) );
  }

}
