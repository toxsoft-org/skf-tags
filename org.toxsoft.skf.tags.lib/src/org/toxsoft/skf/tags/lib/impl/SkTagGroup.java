package org.toxsoft.skf.tags.lib.impl;

import static org.toxsoft.skf.tags.lib.impl.ISkTagServiceInternalHardConstants.*;

import org.toxsoft.core.tslib.av.opset.*;
import org.toxsoft.core.tslib.av.opset.impl.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.bricks.strid.coll.impl.*;
import org.toxsoft.core.tslib.coll.*;
import org.toxsoft.core.tslib.gw.skid.*;
import org.toxsoft.skf.tags.lib.*;
import org.toxsoft.uskat.core.api.objserv.*;
import org.toxsoft.uskat.core.impl.*;

/**
 * {@link ISkTag} implementation.
 *
 * @author hazard157
 */
class SkTagGroup
    extends SkObject
    implements ISkTagGroup {

  static final ISkObjectCreator<SkTagGroup> CREATOR = SkTagGroup::new;

  SkTagGroup( Skid aSkid ) {
    super( aSkid );
  }

  // ------------------------------------------------------------------------------------
  // implementation
  //

  private void internalCollectTagsInGroupSubtree( IStridablesListEdit<ISkTag> aTagsList ) {
    aTagsList.addAll( childTags() );
    for( ISkTagGroup g : childGroups() ) {
      ((SkTagGroup)g).internalCollectTagsInGroupSubtree( aTagsList );
    }
  }

  // ------------------------------------------------------------------------------------
  // package API
  //

  /**
   * Returns the SKID of the parent bundle.
   *
   * @return {@link Skid} - the parent group SKID or {@link Skid#NONE} for bundle (root group)
   */
  Skid getParentSkid() {
    return getSingleRivetSkid( RIVID_GROUP_PARENT );
  }

  /**
   * Determines if this group is a root one, that is parent SKID of this group is {@link Skid#NONE}.
   *
   * @return boolean - true if this is a root group (bundle)
   */
  boolean isRootGroup() {
    return getSingleRivetSkid( RIVID_GROUP_PARENT ).equals( Skid.NONE );
  }

  // ------------------------------------------------------------------------------------
  // ISkTagGroup
  //

  @Override
  public IOptionSet params() {
    String s = getClob( ATRINF_STRIDABLE_PARAMS.id(), null );
    if( s != null ) {
      return OptionSetKeeper.KEEPER.str2ent( s );
    }
    return IOptionSet.NULL;
  }

  @Override
  public SkTagGroup bundle() {
    SkTagGroup g = this;
    while( !g.parent().skid().isNone() ) {
      g = g.parent();
    }
    return g;
  }

  @Override
  public SkTagGroup parent() {
    Skid s = getSingleRivetSkid( RIVID_GROUP_PARENT );
    if( s.isNone() ) { // null for bundle, the root group
      return null;
    }
    return coreApi().objService().get( s );
  }

  @Override
  public ISkTagGroup findGroup( String aGroupId ) {
    Skid skid = new Skid( CLSID_TAG_GROUP, aGroupId );
    SkTagGroup group = coreApi().objService().find( skid );
    if( group != null ) {
      if( group.getSingleRivet( RIVID_GROUP_PARENT ).skid().equals( this.skid() ) ) {
        return group;
      }
    }
    return null;
  }

  @Override
  public IStridablesList<ISkTagGroup> childGroups() {
    IStridablesListEdit<ISkTagGroup> ll = new StridablesList<>();
    for( ISkObject o : coreApi().objService().listObjs( CLSID_TAG_GROUP, false ) ) {
      if( o.getSingleRivet( RIVID_GROUP_PARENT ).skid().equals( this.skid() ) ) {
        ll.add( (ISkTagGroup)o );
      }
    }
    return ll;
  }

  @Override
  public ISkTag findTag( String aTagId ) {
    Skid skid = new Skid( CLSID_TAG, aTagId );
    SkTag tag = coreApi().objService().find( skid );
    if( tag != null ) {
      ISkidList sl = getLinkSkids( LNKID_CHILD_TAGS );
      if( sl.hasElem( skid ) ) {
        return tag;
      }
    }
    return null;
  }

  @SuppressWarnings( { "unchecked", "rawtypes" } )
  @Override
  public IStridablesList<ISkTag> childTags() {
    IList<ISkObject> ll = getLinkObjs( LNKID_CHILD_TAGS );
    return new StridablesList<>( (IList)ll );
  }

  @Override
  public IStridablesList<ISkTag> listAllTags() {
    IStridablesListEdit<ISkTag> ll = new StridablesList<>();
    internalCollectTagsInGroupSubtree( ll );
    return ll;
  }

}
