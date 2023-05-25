package org.toxsoft.skf.tags.gui.km5;

import static org.toxsoft.core.tsgui.m5.IM5Constants.*;
import static org.toxsoft.core.tslib.av.metainfo.IAvMetaConstants.*;
import static org.toxsoft.skf.tags.gui.ISkTagsGuiSharedResources.*;
import static org.toxsoft.uskat.core.ISkHardConstants.*;
import static org.toxsoft.uskat.core.gui.km5.ISkKm5SharedResources.*;

import org.toxsoft.core.tsgui.m5.*;
import org.toxsoft.core.tsgui.m5.model.*;
import org.toxsoft.core.tsgui.m5.model.impl.*;
import org.toxsoft.core.tslib.av.*;
import org.toxsoft.core.tslib.av.impl.*;
import org.toxsoft.core.tslib.gw.gwid.*;
import org.toxsoft.uskat.core.*;
import org.toxsoft.uskat.core.api.objserv.*;
import org.toxsoft.uskat.core.api.sysdescr.*;
import org.toxsoft.uskat.core.api.sysdescr.dto.*;
import org.toxsoft.uskat.core.connection.*;
import org.toxsoft.uskat.core.gui.km5.*;

/**
 * M5-model of the {@link Gwid} entities.
 *
 * @author dima
 */
public class GwidM5Model
    extends KM5ConnectedModelBase<Gwid> {

  /**
   * The model ID.
   */
  public static final String MODEL_ID = M5_ID + ".Gwid"; //$NON-NLS-1$

  /**
   * ID of field {@link #GWID}.
   */
  public static final String FID_GWID = "Gwid"; //$NON-NLS-1$

  /**
   * Field {@link Gwid#asString()}.
   */
  public final M5AttributeFieldDef<Gwid> GWID = new M5AttributeFieldDef<>( FID_GWID, DDEF_VALOBJ ) {

    @Override
    protected void doInit() {
      setNameAndDescription( STR_N_GWID, STR_D_GWID );
      setDefaultValue( AvUtils.AV_VALOBJ_NULL );
      setFlags( M5FF_COLUMN );
    }

    @Override
    protected IAtomicValue doGetFieldValue( Gwid aEntity ) {
      return AvUtils.avValobj( aEntity );
    }
  };

  /**
   * Attribute {@link ISkHardConstants#AID_NAME}.
   */
  public final M5AttributeFieldDef<Gwid> NAME = new M5AttributeFieldDef<>( AID_NAME, DDEF_NAME ) {

    @Override
    protected void doInit() {
      setNameAndDescription( STR_N_FDEF_NAME, STR_D_FDEF_NAME );
      setFlags( M5FF_COLUMN );
    }

    @Override
    protected String doGetFieldValueName( Gwid aEntity ) {
      ISkClassInfo classInfo = coreApi().sysdescr().findClassInfo( aEntity.skid().classId() );
      ISkObject obj = coreApi().objService().find( aEntity.skid() );
      IDtoRtdataInfo rtData = classInfo.rtdata().list().findByKey( aEntity.propId() );
      String retVal = String.format( "%s[%s]:%s", classInfo.nmName(), obj.nmName(), rtData.nmName() ); //$NON-NLS-1$
      return retVal;
    }

  };

  /**
   * LM for this model.
   *
   * @author dima
   */
  private static class DefaultLifecyleManager
      extends M5LifecycleManager<Gwid, Object> {

    public DefaultLifecyleManager( IM5Model<Gwid> aModel ) {
      super( aModel, true, true, true, false, null );
    }

    @Override
    protected Gwid doCreate( IM5Bunch<Gwid> aValues ) {
      Gwid gwid = aValues.getAsAv( FID_GWID ).asValobj();
      return gwid;
    }

    @Override
    protected Gwid doEdit( IM5Bunch<Gwid> aValues ) {
      return doCreate( aValues );
    }

    @Override
    protected void doRemove( Gwid aEntity ) {
      // nop
    }

  }

  /**
   * Конструктор встроенной модели Gwid.
   *
   * @param aConn соединение с сервером
   */
  public GwidM5Model( ISkConnection aConn ) {
    super( MODEL_ID, Gwid.class, aConn );
    setNameAndDescription( STR_N_M5M_GWID, STR_D_M5M_GWID );
    addFieldDefs( GWID, NAME );
  }

  @Override
  protected IM5LifecycleManager<Gwid> doCreateDefaultLifecycleManager() {
    return new DefaultLifecyleManager( this );
  }

}
