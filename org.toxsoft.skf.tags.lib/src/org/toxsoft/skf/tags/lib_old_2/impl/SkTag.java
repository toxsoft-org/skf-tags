package org.toxsoft.skf.tags.lib_old_2.impl;

import static org.toxsoft.skf.tags.lib_old_2.ISkTagServiceHardConstants.*;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.strid.coll.impl.*;
import org.toxsoft.core.tslib.bricks.strid.impl.*;
import org.toxsoft.core.tslib.coll.*;
import org.toxsoft.core.tslib.gw.gwid.*;
import org.toxsoft.core.tslib.gw.skid.*;
import org.toxsoft.skf.tags.lib_old_2.*;
import org.toxsoft.uskat.core.api.objserv.*;
import org.toxsoft.uskat.core.impl.*;

/**
 * @author hazard157
 */
public class SkTag
    extends SkObject
    implements ISkTag {

  static final ISkObjectCreator<SkTag> CREATOR = SkTag::new;

  private transient ISkTagSection tagSection = null;

  SkTag( Skid aSkid ) {
    super( aSkid );
  }

  // ------------------------------------------------------------------------------------
  // IParameterized
  //

  @Override
  public IOptionSet params() {
    return attrs().getValobj( ATRINF_TAG_PARAMS.id() );
  }

  // ------------------------------------------------------------------------------------
  // ISkTag
  //

  @Override
  public ESkTagKind kind() {
    return attrs().getValobj( ATRINF_TAG_KIND.id() );
  }

  @Override
  public void markGwids( IGwidList aGwids ) {
    // TODO Auto-generated method stub

  }

  @Override
  public IGwidList getMarkedGwids() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public String localId() {
    return StridUtils.getLast( id() );
  }

  @Override
  public ISkTagSection section() {
    return (ISkTagSection)getLinkRevObjs( CLSID_TAG_SECTION, LNKID_SECTION_TAGS ).first();
  }

  @Override
  public ISkTag parent() {
    String parentId = StridUtils.removeTailingIdNames( id(), 1 );
    if( parentId.isEmpty() ) {
      return section().root();
    }
    return coreApi().objService().get( new Skid( classId(), parentId ) );
  }

  @Override
  public IStridablesList<ISkTag> childTags() {
    IList<ISkObject> allTags = coreApi().objService().listObjs( classId(), false );
    IStridablesListBasicEdit<ISkTag> ll = new SortedStridablesList<>();
    for( ISkObject sko : allTags ) {
      // include only tags with parent ID equal to this tag ID
      if( id().equals( StridUtils.removeTailingIdNames( sko.id(), 1 ) ) ) {
        ll.add( ISkTag.class.cast( sko ) );
      }
    }
    return ll;
  }

  @Override
  public IStridablesList<ISkTag> listScionTags( boolean aIsSelf, boolean aChildables, boolean aMarkables ) {
    IList<ISkObject> allTags = coreApi().objService().listObjs( classId(), false );
    IStridablesListBasicEdit<ISkTag> ll = new SortedStridablesList<>();
    for( ISkObject sko : allTags ) {
      // consider only this tag and it's scions
      if( StridUtils.startsWithIdPath( id(), sko.id() ) ) {
        // include this tag if needed
        if( aIsSelf && id().equals( sko.id() ) ) {
          ll.add( ISkTag.class.cast( sko ) );
          continue;
        }
        ISkTag tag = ISkTag.class.cast( sko );
        ESkTagKind skoKind = tag.kind();
        if( aChildables && skoKind.isChildable() ) {
          ll.add( tag );
          continue;
        }
        if( aMarkables && skoKind.isMarkable() ) {
          ll.add( tag );
          continue;
        }
      }
    }
    return ll;
  }

}
