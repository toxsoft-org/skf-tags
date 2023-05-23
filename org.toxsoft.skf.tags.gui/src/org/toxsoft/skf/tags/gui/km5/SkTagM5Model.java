package org.toxsoft.skf.tags.gui.km5;

import static org.toxsoft.core.tsgui.m5.IM5Constants.*;
import static org.toxsoft.core.tslib.av.EAtomicType.*;
import static org.toxsoft.core.tslib.av.impl.AvUtils.*;
import static org.toxsoft.core.tslib.av.metainfo.IAvMetaConstants.*;
import static org.toxsoft.skf.tags.gui.ISkTagsGuiSharedResources.*;
import static org.toxsoft.uskat.core.ISkHardConstants.*;
import static org.toxsoft.uskat.core.gui.km5.ISkKm5SharedResources.*;

import org.toxsoft.core.tsgui.bricks.ctx.*;
import org.toxsoft.core.tsgui.m5.gui.mpc.impl.*;
import org.toxsoft.core.tsgui.m5.gui.panels.*;
import org.toxsoft.core.tsgui.m5.gui.panels.impl.*;
import org.toxsoft.core.tsgui.m5.model.*;
import org.toxsoft.core.tsgui.m5.model.impl.*;
import org.toxsoft.core.tslib.av.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.strid.coll.impl.*;
import org.toxsoft.core.tslib.coll.*;
import org.toxsoft.core.tslib.gw.gwid.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.tags.lib.*;
import org.toxsoft.uskat.core.*;
import org.toxsoft.uskat.core.connection.*;
import org.toxsoft.uskat.core.gui.km5.*;

/**
 * M5-model of {@link ISkTag}.
 *
 * @author dima
 */
public class SkTagM5Model
    extends KM5ConnectedModelBase<ISkTag> {

  /**
   * Tag class ID.
   */
  public static String MODEL_ID = ISkHardConstants.SK_ID + ".TagM5Model"; //$NON-NLS-1$

  /**
   * id field list child tags {@link ISkTag#childTags()}
   */
  public static final String FID_CHILD_TAGS = "childTags"; //$NON-NLS-1$

  /**
   * id field of GwidList {@link ISkTag#getMarkedGwids()}
   */
  public static final String FID_MARKED_GWIDS = "markedGwids"; //$NON-NLS-1$

  /**
   * Структура для описания поля типа списка Gwid которые хранятся ВМЕСТЕ с сущностью. Ключевое отличие от связи с
   * объектами в том что по связи объекты хранятся отдельно от сущности.
   */
  public final IM5MultiModownFieldDef<ISkTag, Gwid> MARKED_GWIDS =
      new M5MultiModownFieldDef<>( FID_MARKED_GWIDS, GwidM5Model.MODEL_ID ) {

        @Override
        protected void doInit() {
          setNameAndDescription( STR_N_PARAM_GWID_LIST, STR_D_PARAM_GWID_LIST );
          // TODO
          // params().setStr( TSID_ICON_ID, ICONID_SDED_CLASS_DATA );
          setFlags( M5FF_DETAIL );
        }

        protected IList<Gwid> doGetFieldValue( ISkTag aEntity ) {
          return aEntity.getMarkedGwids();
        }

      };

  /**
   * id field of tag kind {@link ISkTag#kind()}
   */
  public static final String FID_KIND = "kind"; //$NON-NLS-1$

  /**
   * Attribute {@link ISkTag#kind() }
   */
  public M5AttributeFieldDef<ISkTag> KIND = new M5AttributeFieldDef<>( FID_KIND, VALOBJ, //
      TSID_NAME, STR_N_TAG_KIND, //
      TSID_DESCRIPTION, STR_D_TAG_KIND, //
      TSID_KEEPER_ID, ESkTagKind.KEEPER_ID, //
      TSID_DEFAULT_VALUE, avValobj( ESkTagKind.LEAF ) ) {

    @Override
    protected void doInit() {
      setFlags( M5FF_COLUMN );
    }

    protected IAtomicValue doGetFieldValue( ISkTag aEntity ) {
      return avValobj( aEntity.kind() );
    }

  };

  /**
   * ID of link {@link ISkTag#root()}.
   */
  static String FID_ROOT = "root"; //$NON-NLS-1$

  /**
   * Attribute {@link ISkTag#root()}.
   */
  public final IM5SingleLookupFieldDef<ISkTag, ISkTag> ROOT = new M5SingleLookupFieldDef<>( FID_ROOT, MODEL_ID ) {

    @Override
    protected void doInit() {
      setNameAndDescription( STR_N_ROOT, STR_D_ROOT );
      setFlags( M5FF_INVARIANT | M5FF_DETAIL );
      setLookupProvider( () -> {
        ISkTagService tagService = coreApi().getService( ISkTagService.SERVICE_ID );
        // По всем секциям
        IStridablesList<ISkTagSection> sections = tagService.sections();
        // по ним собираем список для выбора
        IStridablesListEdit<ISkTag> retVal = new StridablesList<>();
        for( ISkTagSection section : sections ) {
          retVal.add( section.root() );
        }
        return retVal;
      } );
    }

    protected ISkTag doGetFieldValue( ISkTag aEntity ) {
      return aEntity.root();
    }

  };

  /**
   * ID of attribute {@link ISkTag#localId()}.
   */
  static String AID_LOCAL_ID = "localId"; //$NON-NLS-1$

  /**
   * Attribute {@link ISkTag#id()} - {@link SkTagM5Model#AID_LOCAL_ID}.
   */
  public final M5AttributeFieldDef<ISkTag> TAG_ID = new M5AttributeFieldDef<>( AID_LOCAL_ID, DDEF_IDNAME ) {

    @Override
    protected void doInit() {
      setNameAndDescription( STR_N_LOCAL_ID, STR_D_LOCAL_ID );
      setFlags( M5FF_COLUMN );
    }

    @Override
    protected String doGetFieldValueName( ISkTag aEntity ) {
      return aEntity.localId();
    }

  };

  /**
   * Attribute {@link ISkTag#nmName()} - {@link ISkHardConstants#AID_NAME}.
   */
  public final M5AttributeFieldDef<ISkTag> NAME = new M5AttributeFieldDef<>( AID_NAME, DDEF_NAME ) {

    @Override
    protected void doInit() {
      setNameAndDescription( STR_N_FDEF_NAME, STR_D_FDEF_NAME );
      setFlags( M5FF_COLUMN );
    }

    @Override
    protected String doGetFieldValueName( ISkTag aEntity ) {
      String s = super.doGetFieldValueName( aEntity );
      if( !s.isBlank() ) {
        return s;
      }
      return aEntity.id();
    }

  };

  /**
   * Attribute {@link ISkTag#description()} - {@link ISkHardConstants#AID_DESCRIPTION}.
   */
  public final M5AttributeFieldDef<ISkTag> DESCRIPTION =
      new M5AttributeFieldDef<>( AID_DESCRIPTION, DDEF_DESCRIPTION ) {

        @Override
        protected void doInit() {
          setNameAndDescription( STR_N_FDEF_DESCRIPTION, STR_D_FDEF_DESCRIPTION );
          setFlags( M5FF_DETAIL );
        }

      };

  /**
   * ID of link {@link ISkTag#parent()}.
   */
  static String FID_PARENT = "parent"; //$NON-NLS-1$

  /**
   * Link {@link ISkTag#parent()}.
   */
  public final IM5SingleLookupFieldDef<ISkTag, ISkTag> PARENT = new M5SingleLookupFieldDef<>( FID_PARENT, MODEL_ID ) {

    @Override
    protected void doInit() {
      setNameAndDescription( STR_N_PARENT, STR_D_PARENT );
      setFlags( M5FF_INVARIANT | M5FF_DETAIL );
      setLookupProvider( () -> {
        ISkTagService tagService = coreApi().getService( ISkTagService.SERVICE_ID );
        // По всем секциям
        IStridablesList<ISkTagSection> sections = tagService.sections();
        // по ним собираем список для выбора
        IStridablesListEdit<ISkTag> retVal = new StridablesList<>();
        for( ISkTagSection section : sections ) {
          for( ISkTag tag : section.listMarkableTags() ) {
            retVal.add( tag );
            addChildren( retVal, tag );
          }
        }
        return retVal;
      } );
    }

    private void addChildren( IStridablesListEdit<ISkTag> aChildrenList, ISkTag aParent ) {
      if( !aParent.childTags().isEmpty() ) {
        for( ISkTag node : aParent.childTags() ) {
          aChildrenList.add( node );
          addChildren( aChildrenList, node );
        }
      }
    }

    protected ISkTag doGetFieldValue( ISkTag aEntity ) {
      return aEntity.parent();
    }

  };

  /**
   * ID of link {@link ISkTag#section()}.
   */
  static String FID_SECTION = "section"; //$NON-NLS-1$

  /**
   * Link {@link ISkTag#section()}.
   */
  public final IM5SingleLookupFieldDef<ISkTag, ISkTagSection> SECTION =
      new M5SingleLookupFieldDef<>( FID_SECTION, MODEL_ID ) {

        @Override
        protected void doInit() {
          setNameAndDescription( STR_N_PARENT, STR_D_PARENT );
          setFlags( M5FF_INVARIANT | M5FF_DETAIL );
          setLookupProvider( () -> {
            ISkTagService tagService = coreApi().getService( ISkTagService.SERVICE_ID );
            return tagService.sections();
          } );
        }

        protected ISkTagSection doGetFieldValue( ISkTag aEntity ) {
          return aEntity.section();
        }

      };

  /**
   * Constructor.
   *
   * @param aConn {@link ISkConnection} - the connection
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  public SkTagM5Model( ISkConnection aConn ) {
    super( MODEL_ID, ISkTag.class, aConn );
    setNameAndDescription( STR_N_TAG, STR_D_TAG );

    // add fields
    addFieldDefs( TAG_ID, NAME, DESCRIPTION, MARKED_GWIDS, SECTION, ROOT, PARENT );
    // panels creator
    setPanelCreator( new M5DefaultPanelCreator<>() {

      protected IM5CollectionPanel<ISkTag> doCreateCollEditPanel( ITsGuiContext aContext,
          IM5ItemsProvider<ISkTag> aItemsProvider, IM5LifecycleManager<ISkTag> aLifecycleManager ) {
        MultiPaneComponentModown<ISkTag> mpc =
            new SkTagPaneComponentModown( aContext, model(), aItemsProvider, aLifecycleManager );
        return new M5CollectionPanelMpcModownWrapper<>( mpc, false );
      }
    } );

  }

  @Override
  protected IM5LifecycleManager<ISkTag> doCreateDefaultLifecycleManager() {
    return new SkTagM5LifecycleManager( this, skConn() );
  }

  @Override
  protected IM5LifecycleManager<ISkTag> doCreateLifecycleManager( Object aMaster ) {
    ISkConnection master = ISkConnection.class.cast( aMaster );
    return new SkTagM5LifecycleManager( this, master );
  }

}
