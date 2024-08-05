package org.toxsoft.skf.tags.gui.km5;

import static org.toxsoft.core.tsgui.m5.IM5Constants.*;
import static org.toxsoft.core.tslib.av.metainfo.IAvMetaConstants.*;
import static org.toxsoft.skf.tags.gui.ISkTagsGuiSharedResources.*;
import static org.toxsoft.uskat.core.ISkHardConstants.*;
import static org.toxsoft.uskat.core.gui.km5.ISkKm5SharedResources.*;

import org.toxsoft.core.tsgui.m5.*;
import org.toxsoft.core.tsgui.m5.model.*;
import org.toxsoft.core.tsgui.m5.model.impl.*;
import org.toxsoft.core.tslib.coll.*;
import org.toxsoft.core.tslib.coll.impl.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.tags.lib_old_4.*;
import org.toxsoft.uskat.core.*;
import org.toxsoft.uskat.core.api.objserv.*;
import org.toxsoft.uskat.core.connection.*;
import org.toxsoft.uskat.core.gui.km5.*;

/**
 * M5-model of {@link ISkTagSection}.
 *
 * @author dima
 */
public class SkTagSectionM5Model
    extends KM5ConnectedModelBase<ISkTagSection> {

  /**
   * Tag class ID.
   */
  public static String MODEL_ID = ISkHardConstants.SK_ID + ".TagSectionM5Model"; //$NON-NLS-1$

  /**
   * id field of list tags {@link ISkTagSection#listMarkableTags()}
   */
  public static final String FID_MARKABLE_TAGS = "listMarkableTags"; //$NON-NLS-1$

  /**
   * Поле связм с метками даннрого раздела
   */
  public final IM5MultiLookupFieldDef<ISkTagSection, ISkTag> MARKABLE_TAGS =
      new M5MultiLookupFieldDef<>( FID_MARKABLE_TAGS, SkTagM5Model.MODEL_ID ) {

        public IList<ISkTag> getFieldValue( ISkTagSection aEntity ) {
          return aEntity.listMarkableTags();
        }

        public IM5LookupProvider<ISkTag> lookupProvider() {
          return new IM5LookupProvider<>() {

            @Override
            public String getName( ISkTag aItem ) {
              ISkObject skObject = (ISkObject)aItem;
              return skObject.attrs().getStr( IM5Constants.FID_NAME );
            }

            @Override
            public IList<ISkTag> listItems() {
              ISkTagService tagService = coreApi().getService( ISkTagService.SERVICE_ID );
              // FIXME чет не понимаю что тут можно подсунуть
              IListEdit<ISkTag> retVal = new ElemArrayList<>();
              for( ISkTagSection section : tagService.sections() ) {
                retVal.addAll( section.listMarkableTags() );
              }
              return retVal;
            }
          };
        }
      };

  /**
   * ID of link {@link ISkTagSection#root()}.
   */
  static String FID_ROOT = "root"; //$NON-NLS-1$

  /**
   * Attribute {@link ISkTagSection#root()}.
   */
  public final IM5SingleLookupFieldDef<ISkTagSection, ISkTag> ROOT =
      new M5SingleLookupFieldDef<>( FID_ROOT, MODEL_ID ) {

        @Override
        protected void doInit() {
          setNameAndDescription( STR_N_ROOT, STR_D_ROOT );
          setFlags( M5FF_INVARIANT | M5FF_DETAIL );
        }

        protected ISkTag doGetFieldValue( ISkTagSection aEntity ) {
          return aEntity.root();
        }

      };

  /**
   * ID of attribute {@link ISkTagSection#id()}.
   */
  static String AID_SECTION_ID = "sectionId"; //$NON-NLS-1$

  /**
   * Attribute {@link ISkTagSection#id()}
   */
  public final M5AttributeFieldDef<ISkTagSection> TAG_SECTION_ID =
      new M5AttributeFieldDef<>( AID_SECTION_ID, DDEF_IDNAME ) {

        @Override
        protected void doInit() {
          setNameAndDescription( STR_N_SECTION_ID, STR_D_SECTION_ID );
          setFlags( M5FF_COLUMN );
        }

        @Override
        protected String doGetFieldValueName( ISkTagSection aEntity ) {
          return aEntity.id();
        }

      };

  /**
   * Attribute {@link ISkTagSection#nmName()} - {@link ISkHardConstants#AID_NAME}.
   */
  public final M5AttributeFieldDef<ISkTagSection> NAME = new M5AttributeFieldDef<>( AID_NAME, DDEF_NAME ) {

    @Override
    protected void doInit() {
      setNameAndDescription( STR_N_FDEF_NAME, STR_D_FDEF_NAME );
      setFlags( M5FF_COLUMN );
    }

    @Override
    protected String doGetFieldValueName( ISkTagSection aEntity ) {
      String s = super.doGetFieldValueName( aEntity );
      if( !s.isBlank() ) {
        return s;
      }
      return aEntity.id();
    }

  };

  /**
   * Attribute {@link ISkTagSection#description()} - {@link ISkHardConstants#AID_DESCRIPTION}.
   */
  public final M5AttributeFieldDef<ISkTagSection> DESCRIPTION =
      new M5AttributeFieldDef<>( AID_DESCRIPTION, DDEF_DESCRIPTION ) {

        @Override
        protected void doInit() {
          setNameAndDescription( STR_N_FDEF_DESCRIPTION, STR_D_FDEF_DESCRIPTION );
          setFlags( M5FF_DETAIL );
        }

      };

  /**
   * Constructor.
   *
   * @param aConn {@link ISkConnection} - the connection
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   */
  public SkTagSectionM5Model( ISkConnection aConn ) {
    super( MODEL_ID, ISkTagSection.class, aConn );
    setNameAndDescription( STR_N_TAG_SECTION, STR_D_TAG_SECTION );

    // add fields
    addFieldDefs( TAG_SECTION_ID, NAME, DESCRIPTION, MARKABLE_TAGS, ROOT );

  }

  @Override
  protected IM5LifecycleManager<ISkTagSection> doCreateDefaultLifecycleManager() {
    return new SkTagSectionM5LifecycleManager( this, skConn() );
  }

  @Override
  protected IM5LifecycleManager<ISkTagSection> doCreateLifecycleManager( Object aMaster ) {
    ISkConnection master = ISkConnection.class.cast( aMaster );
    return new SkTagSectionM5LifecycleManager( this, master );
  }

}
