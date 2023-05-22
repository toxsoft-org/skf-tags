package org.toxsoft.skf.tags.gui.km5;

import static org.toxsoft.core.tsgui.m5.IM5Constants.*;
import static org.toxsoft.core.tslib.av.metainfo.IAvMetaConstants.*;
import static org.toxsoft.skf.tags.gui.ISkTagsGuiSharedResources.*;

import org.toxsoft.core.tsgui.m5.*;
import org.toxsoft.core.tsgui.m5.model.*;
import org.toxsoft.core.tsgui.m5.model.impl.*;
import org.toxsoft.core.tslib.av.*;
import org.toxsoft.core.tslib.av.impl.*;
import org.toxsoft.core.tslib.gw.gwid.*;

/**
 * M5-model of the {@link Gwid} entities.
 *
 * @author dima
 */
public class GwidM5Model
    extends M5Model<Gwid> {

  /**
   * The model ID.
   */
  public static final String MODEL_ID = M5_ID + ".Gwid"; //$NON-NLS-1$

  /**
   * ID of field {@link #GWID}.
   */
  public static final String FID_GWID = "Gwid"; //$NON-NLS-1$

  /**
   * Field {@link String#toString()}.
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
   * Конструктор встроенной модели.
   */
  public GwidM5Model() {
    super( MODEL_ID, Gwid.class );
    setNameAndDescription( STR_N_M5M_GWID, STR_D_M5M_GWID );
    addFieldDefs( GWID );
  }

  @Override
  protected IM5LifecycleManager<Gwid> doCreateDefaultLifecycleManager() {
    return new DefaultLifecyleManager( this );
  }

}
