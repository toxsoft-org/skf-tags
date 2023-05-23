package org.toxsoft.skf.tags.gui.km5;

import static org.toxsoft.skf.tags.lib.ISkTagServiceHardConstants.*;
import static org.toxsoft.uskat.core.ISkHardConstants.*;

import org.toxsoft.core.tsgui.m5.*;
import org.toxsoft.core.tsgui.m5.model.impl.*;
import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.av.opset.impl.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.coll.*;
import org.toxsoft.core.tslib.gw.skid.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.tags.lib.*;
import org.toxsoft.uskat.core.connection.*;

/**
 * Lifecycle manager for {@link SkTagSectionM5Model}.
 *
 * @author dima
 */
public class SkTagSectionM5LifecycleManager
    extends M5LifecycleManager<ISkTagSection, ISkConnection> {

  /**
   * Constructor.
   *
   * @param aModel {@link IM5Model}&lt;T&gt; - the model
   * @param aMaster &lt;M&gt; - master object, may be <code>null</code>
   * @throws TsNullArgumentRtException model is <code>null</code>
   */
  public SkTagSectionM5LifecycleManager( IM5Model<ISkTagSection> aModel, ISkConnection aMaster ) {
    super( aModel, true, true, true, true, aMaster );
  }

  // ------------------------------------------------------------------------------------
  // implementation
  //

  private ISkTagService skTagsService() {
    return master().coreApi().getService( ISkTagService.SERVICE_ID );
  }

  // ------------------------------------------------------------------------------------
  // M5LifecycleManager
  //

  @Override
  protected ValidationResult doBeforeCreate( IM5Bunch<ISkTagSection> aValues ) {
    // собираем данные полученные от пользователя
    String localId = aValues.getAsAv( SkTagSectionM5Model.AID_SECTION_ID ).asString();
    String name = aValues.getAsAv( AID_NAME ).asString();
    String description = aValues.getAsAv( AID_DESCRIPTION ).asString();
    // return skTagsService().svs().validator().canCreateChildTag( parent, localId, name, description, IOptionSet.NULL
    // );
    // FIXME
    return ValidationResult.SUCCESS;
  }

  @Override
  protected ISkTagSection doCreate( IM5Bunch<ISkTagSection> aValues ) {
    String sectionId = aValues.getAsAv( SkTagSectionM5Model.AID_SECTION_ID ).asString();
    IOptionSetEdit ops = new OptionSet();
    String name = aValues.getAsAv( AID_NAME ).asString();
    String description = aValues.getAsAv( AID_DESCRIPTION ).asString();
    ops.setStr( AID_NAME, name );
    ops.setStr( AID_DESCRIPTION, description );
    ISkTagSection retVal = skTagsService().createSection( sectionId, ops );
    // создаем и назначаем root
    ISkTag root = retVal.createTag( sectionId, ESkTagKind.GROUP, name, description, IOptionSet.NULL );
    master().coreApi().linkService().defineLink( new Skid( CLSID_TAG_SECTION, retVal.id() ),
        // FIXME "root"
        "root", ISkidList.EMPTY, new SkidList( new Skid( CLSID_TAG_BASE, root.id() ) ) );

    return retVal;
  }

  @Override
  protected ValidationResult doBeforeEdit( IM5Bunch<ISkTagSection> aValues ) {
    String localId = aValues.getAsAv( SkTagSectionM5Model.AID_SECTION_ID ).asString();
    String name = aValues.getAsAv( AID_NAME ).asString();
    String description = aValues.getAsAv( AID_DESCRIPTION ).asString();
    // return skTagsService().svs().validator().canEditTag( localId, name, description, IOptionSet.NULL );
    // FIXME
    return ValidationResult.SUCCESS;
  }

  @Override
  protected ISkTagSection doEdit( IM5Bunch<ISkTagSection> aValues ) {
    String sectionId = aValues.getAsAv( SkTagSectionM5Model.AID_SECTION_ID ).asString();
    IOptionSetEdit ops = new OptionSet();
    String name = aValues.getAsAv( AID_NAME ).asString();
    String description = aValues.getAsAv( AID_DESCRIPTION ).asString();
    ops.setStr( AID_NAME, name );
    ops.setStr( AID_DESCRIPTION, description );
    ISkTagSection retVal = skTagsService().editSection( sectionId, ops );

    return retVal;
  }

  @Override
  protected ValidationResult doBeforeRemove( ISkTagSection aEntity ) {
    // return skTagsService().svs().validator().canRemoveTag( aEntity.id() );
    // FIXME
    return ValidationResult.SUCCESS;
  }

  @Override
  protected void doRemove( ISkTagSection aEntity ) {
    skTagsService().removeSection( aEntity.id() );
  }

  @Override
  protected IList<ISkTagSection> doListEntities() {
    return skTagsService().sections();
  }

}
