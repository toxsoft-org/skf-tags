/**
 *
 */
package org.toxsoft.skf.tags.gui.km5;

import static org.toxsoft.skf.tags.gui.ISkTagsGuiConstants.*;

import org.toxsoft.core.tsgui.bricks.tsnodes.*;
import org.toxsoft.core.tsgui.bricks.tstree.tmm.*;
import org.toxsoft.core.tslib.bricks.strid.coll.*;
import org.toxsoft.core.tslib.coll.*;
import org.toxsoft.core.tslib.coll.impl.*;
import org.toxsoft.core.tslib.coll.primtypes.*;
import org.toxsoft.core.tslib.coll.primtypes.impl.*;
import org.toxsoft.skf.tags.lib.*;

/**
 * Построитель полного дерева меток системы.
 *
 * @author dima
 */
public class TagsTreeMaker
    implements ITsTreeMaker<ISkTag> {

  /**
   * лист обычной метки
   */
  final ITsNodeKind<ISkTag> NK_TAG_LEAF = new TsNodeKind<>( "TagLeaf", ISkTag.class, false, ICONID_TAG ); //$NON-NLS-1$

  /**
   * узел группа
   */
  final ITsNodeKind<ISkTag> nK_TAG_GROUP = new TsNodeKind<>( "TagGroupNode", ISkTag.class, true, ICONID_TAG_OK ); //$NON-NLS-1$

  /**
   * узел секция
   */
  final ITsNodeKind<ISkTagSection> nK_SECTION =
      new TsNodeKind<>( "TagSectionNode", ISkTagSection.class, true, ICONID_TAG_PLANT ); //$NON-NLS-1$

  private final ISkTagService                         tagService;
  private final IStringMapEdit<DefaultTsNode<ISkTag>> allNodes = new StringMap<>();

  /**
   * @param aTagService сервис меток
   */
  public TagsTreeMaker( ISkTagService aTagService ) {
    tagService = aTagService;
  }

  @Override
  public boolean isItemNode( ITsNode aNode ) {
    return aNode.kind() == NK_TAG_LEAF;
  }

  private IList<DefaultTsNode<ISkTagSection>> makeSectionList( ITsNode aRootNode ) {
    IListEdit<DefaultTsNode<ISkTagSection>> retVal = new ElemArrayList<>();
    IStridablesList<ISkTagSection> sections = tagService.sections();
    for( ISkTagSection section : sections ) {
      DefaultTsNode<ISkTagSection> sectionNode = new DefaultTsNode<>( nK_SECTION, aRootNode, section );
      DefaultTsNode<ISkTag> sectionRootNode = new DefaultTsNode<>( nK_TAG_GROUP, sectionNode, section.root() );
      allNodes.put( sectionRootNode.entity().id(), sectionRootNode );
      retVal.add( sectionNode );
    }
    return retVal;
  }

  @SuppressWarnings( { "unchecked", "rawtypes" } )
  @Override
  public IList<ITsNode> makeRoots( ITsNode aRootNode, IList<ISkTag> aTags ) {
    IList<DefaultTsNode<ISkTagSection>> sections = makeSectionList( aRootNode );
    for( ISkTag tag : aTags ) {
      DefaultTsNode<ISkTag> parentNode = findParent( tag );
      DefaultTsNode<ISkTag> n =
          new DefaultTsNode<>( tag.childTags().isEmpty() ? NK_TAG_LEAF : nK_TAG_GROUP, parentNode, tag );
      parentNode.addNode( n );
    }

    return (IList)sections;
  }

  /**
   * Ищет родительский узел данного тэга, если не находит, то рекурсивно создает полную цепочку до корневого узла
   * раздела
   *
   * @param aTag метка
   * @return родительский узел
   */
  private DefaultTsNode<ISkTag> findParent( ISkTag aTag ) {
    if( allNodes.hasKey( aTag.parent().id() ) ) {
      return allNodes.getByKey( aTag.parent().id() );
    }
    // родительского узла еще нет, создаем родителя
    DefaultTsNode<ISkTag> retVal = new DefaultTsNode<>( nK_TAG_GROUP, findParent( aTag.parent() ), aTag.parent() );
    allNodes.put( retVal.entity().id(), retVal );
    return retVal;
  }

}
