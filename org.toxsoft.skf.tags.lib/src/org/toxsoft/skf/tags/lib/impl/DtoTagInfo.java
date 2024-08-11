package org.toxsoft.skf.tags.lib.impl;

import static org.toxsoft.skf.tags.lib.impl.ISkTagServiceInternalHardConstants.*;
import static org.toxsoft.uskat.core.ISkHardConstants.*;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.av.opset.impl.*;
import org.toxsoft.core.tslib.bricks.keeper.*;
import org.toxsoft.core.tslib.bricks.keeper.AbstractEntityKeeper.*;
import org.toxsoft.core.tslib.bricks.strid.impl.*;
import org.toxsoft.core.tslib.bricks.strio.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.tags.lib.*;
import org.toxsoft.uskat.core.impl.dto.*;

/**
 * {@link IDtoTagInfo} implementation.
 *
 * @author hazard157
 */
public class DtoTagInfo
    extends StridableParameterized
    implements IDtoTagInfo {

  /**
   * The keeper singleton.
   */
  @SuppressWarnings( "hiding" )
  public static final IEntityKeeper<IDtoTagInfo> KEEPER =
      new AbstractEntityKeeper<>( IDtoTagInfo.class, EEncloseMode.ENCLOSES_BASE_CLASS, null ) {

        @Override
        protected void doWrite( IStrioWriter aSw, IDtoTagInfo aEntity ) {
          aSw.writeAsIs( aEntity.id() );
          aSw.writeSeparatorChar();
          aSw.writeQuotedString( aEntity.nmName() );
          aSw.writeSeparatorChar();
          aSw.writeQuotedString( aEntity.description() );
          aSw.writeSeparatorChar();
          OptionSetKeeper.KEEPER.write( aSw, aEntity.params() );
        }

        @Override
        protected IDtoTagInfo doRead( IStrioReader aSr ) {
          String id = aSr.readIdPath();
          aSr.ensureSeparatorChar();
          String name = aSr.readQuotedString();
          aSr.ensureSeparatorChar();
          String description = aSr.readQuotedString();
          aSr.ensureSeparatorChar();
          IOptionSet params = OptionSetKeeper.KEEPER.read( aSr );
          return new DtoTagInfo( id, name, description, params );
        }
      };

  private final String name;
  private final String description;

  /**
   * Constructor.
   *
   * @param aId String - the ID (IDpath)
   * @param aName String - the name
   * @param aDescription String - the description
   * @param aParams {@link IOptionSet} - {@link #params()} initial values
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsIllegalArgumentRtException ID is not an IDpath
   */
  public DtoTagInfo( String aId, String aName, String aDescription, IOptionSet aParams ) {
    super( aId, aParams );
    TsNullArgumentRtException.checkNulls( aName, aDescription );
    name = aName;
    description = aDescription;
  }

  // ------------------------------------------------------------------------------------
  // API
  //

  /**
   * Updates destination Sk-object DTO from the source tag information DTO.
   * <p>
   * Note: both arguments must refer to the same object.
   *
   * @param aDest {@link DtoObject} - the destination object info
   * @param aSource {@link IDtoTagInfo} - the source tag info
   * @throws TsNullArgumentRtException any argument = <code>null</code>
   * @throws TsIllegalArgumentRtException the destination is not an object of class
   *           {@link ISkTagServiceInternalHardConstants#CLSID_TAG}
   * @throws TsIllegalArgumentRtException the STRIDs of arguments are different
   */
  public static void updateObjDtoFromTag( DtoObject aDest, IDtoTagInfo aSource ) {
    TsNullArgumentRtException.checkNulls( aDest, aSource );
    TsIllegalArgumentRtException.checkFalse( aDest.classId().equals( CLSID_TAG ) );
    TsIllegalArgumentRtException.checkFalse( aDest.strid().equals( aSource.id() ) );
    aDest.attrs().setStr( AID_NAME, aSource.nmName() );
    aDest.attrs().setStr( AID_DESCRIPTION, aSource.description() );
    aDest.attrs().setValobj( ATRID_STRIDABLES_PARAMS, aSource.params() );
  }

  // ------------------------------------------------------------------------------------
  // IStridable
  //

  @Override
  public String nmName() {
    return name;
  }

  @Override
  public String description() {
    return description;
  }

}
