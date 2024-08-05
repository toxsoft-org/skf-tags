package org.toxsoft.skf.tags.gui.km5;

import static org.toxsoft.skf.tags.lib_old_4.ISkTagServiceHardConstants.*;
import static org.toxsoft.uskat.core.ISkHardConstants.*;

import org.toxsoft.core.tsgui.m5.*;
import org.toxsoft.core.tsgui.m5.model.impl.*;
import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.coll.*;
import org.toxsoft.core.tslib.coll.impl.*;
import org.toxsoft.core.tslib.gw.gwid.*;
import org.toxsoft.core.tslib.gw.skid.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.tags.lib_old_4.*;
import org.toxsoft.uskat.core.connection.*;

/**
 * Lifecycle manager for {@link SkTagM5Model}.
 *
 * @author dima
 */
public class SkTagM5LifecycleManager
    extends M5LifecycleManager<ISkTag, ISkConnection> {

  /**
   * Constructor.
   *
   * @param aModel {@link IM5Model}&lt;T&gt; - the model
   * @param aMaster &lt;M&gt; - master object, may be <code>null</code>
   * @throws TsNullArgumentRtException model is <code>null</code>
   */
  public SkTagM5LifecycleManager( IM5Model<ISkTag> aModel, ISkConnection aMaster ) {
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
  protected ValidationResult doBeforeCreate( IM5Bunch<ISkTag> aValues ) {
    // собираем данные полученные от пользователя
    ISkTag parent = aValues.getAsAv( SkTagM5Model.FID_PARENT ).asValobj();
    String localId = aValues.getAsAv( SkTagM5Model.AID_LOCAL_ID ).asString();
    String name = aValues.getAsAv( AID_NAME ).asString();
    String description = aValues.getAsAv( AID_DESCRIPTION ).asString();
    // FIXME
    // return skTagsService().svs().validator().canCreateChildTag( parent, localId, name, description, IOptionSet.NULL
    // );
    return ValidationResult.SUCCESS;
  }

  @Override
  protected ISkTag doCreate( IM5Bunch<ISkTag> aValues ) {
    ISkTag parent = aValues.getAsAv( SkTagM5Model.FID_PARENT ).asValobj();
    ISkTagSection section = findSection( parent.id() );
    String localId = aValues.getAsAv( SkTagM5Model.AID_LOCAL_ID ).asString();
    String name = aValues.getAsAv( AID_NAME ).asString();
    String description = aValues.getAsAv( AID_DESCRIPTION ).asString();
    ESkTagKind kind = aValues.getAsAv( SkTagM5Model.FID_KIND ).asValobj();
    ISkTag retVal = section.createTag( localId, kind, name, description, IOptionSet.NULL );
    // назначаем родителя
    master().coreApi().linkService().defineLink( new Skid( CLSID_TAG_BASE, retVal.id() ),
        // FIXME "parent"
        "parent", ISkidList.EMPTY, new SkidList( new Skid( CLSID_TAG_BASE, parent.id() ) ) );
    // назначаем root
    ISkTag root = aValues.getAsAv( SkTagM5Model.FID_ROOT ).asValobj();
    master().coreApi().linkService().defineLink( new Skid( CLSID_TAG_BASE, retVal.id() ),
        // FIXME "root"
        "root", ISkidList.EMPTY, new SkidList( new Skid( CLSID_TAG_BASE, root.id() ) ) );

    return retVal;
  }

  @Override
  protected ValidationResult doBeforeEdit( IM5Bunch<ISkTag> aValues ) {
    String localId = aValues.getAsAv( SkTagM5Model.AID_LOCAL_ID ).asString();
    String name = aValues.getAsAv( AID_NAME ).asString();
    String description = aValues.getAsAv( AID_DESCRIPTION ).asString();
    // FIXME
    // return skTagsService().svs().validator().canEditTag( localId, name, description, IOptionSet.NULL );
    return ValidationResult.SUCCESS;
  }

  @Override
  protected ISkTag doEdit( IM5Bunch<ISkTag> aValues ) {
    String localId = aValues.getAsAv( SkTagM5Model.AID_LOCAL_ID ).asString();
    String name = aValues.getAsAv( AID_NAME ).asString();
    String description = aValues.getAsAv( AID_DESCRIPTION ).asString();
    ESkTagKind kind = aValues.getAsAv( SkTagM5Model.FID_KIND ).asValobj();
    ISkTagSection section = findSection( localId );
    ISkTag retVal = section.editTag( localId, kind, name, description, IOptionSet.NULL );
    IList<Gwid> markedGwids = aValues.getAs( SkTagM5Model.FID_MARKED_GWIDS, IList.class );
    String markedGwidsStr = Gwid.KEEPER.coll2str( markedGwids );
    Gwid mgGwid = Gwid.createClob( CLSID_TAG_BASE, localId, ISkTagServiceHardConstants.CLBID_MARKED_GWIDS );
    master().coreApi().clobService().writeClob( mgGwid, markedGwidsStr );

    return retVal;
  }

  @Override
  protected ValidationResult doBeforeRemove( ISkTag aEntity ) {
    // return skTagsService().svs().validator().canRemoveTag( aEntity.id() );
    // FIXME
    return ValidationResult.SUCCESS;
  }

  @Override
  protected void doRemove( ISkTag aEntity ) {
    ISkTagSection section = findSection( aEntity.id() );
    section.removeTag( aEntity.id() );
  }

  private ISkTagSection findSection( String aTagId ) {
    // получаем список roots и рекурсивно получаем все метки
    IStridablesList<ISkTagSection> sections = skTagsService().sections();
    for( ISkTagSection section : sections ) {
      ISkTag sectionRoot = section.root();
      IStridablesList<ISkTag> allTags = sectionRoot.listScionTags( true, true, true );
      if( allTags.hasKey( aTagId ) ) {
        return section;
      }
    }
    return null;
  }

  @Override
  protected IList<ISkTag> doListEntities() {
    // получаем список roots и рекурсивно получаем все метки
    IStridablesList<ISkTagSection> sections = skTagsService().sections();
    IListEdit<ISkTag> retVal = new ElemArrayList<>();
    for( ISkTagSection section : sections ) {
      ISkTag sectionRoot = section.root();
      retVal.addAll( readChildTags( sectionRoot ) );
    }
    return retVal;
  }

  private IList<ISkTag> readChildTags( ISkTag aParent ) {
    IListEdit<ISkTag> retVal = new ElemArrayList<>();
    for( ISkTag child : aParent.childTags() ) {
      retVal.addAll( readChildTags( child ) );
    }
    return retVal;
  }

}
