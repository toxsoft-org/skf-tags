package org.toxsoft.skf.tags.lib.impl;

import static org.toxsoft.skf.tags.lib.l10n.ISkTagServiceSharedResources.*;

import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.skf.tags.lib.*;

/**
 * Instance of this class is used as non-removable builtin validator of the tag service.
 *
 * @author hazard157
 */
class InternalBuiltinValidator
    implements ISkTagServiceValidator {

  private final SkTagService owner;

  public InternalBuiltinValidator( SkTagService aOwner ) {
    owner = aOwner;
  }

  @Override
  public ValidationResult canCreateTag( ISkTagService aSource, IDtoTagInfo aTagInfo ) {
    // TODO Auto-generated method stub

    return ValidationResult.SUCCESS;
  }

  @Override
  public ValidationResult canEditTag( ISkTagService aSource, ISkTag aTag, IDtoTagInfo aTagInfo ) {
    // TODO Auto-generated method stub

    return ValidationResult.SUCCESS;
  }

  @Override
  public ValidationResult canRemoveTag( ISkTagService aSource, String aTagId ) {
    // TODO Auto-generated method stub

    return ValidationResult.SUCCESS;
  }

  @Override
  public ValidationResult canCreateBundle( ISkTagService aSource, IDtoTagInfo aBundleInfo ) {
    // TODO Auto-generated method stub

    return ValidationResult.SUCCESS;
  }

  @Override
  public ValidationResult canEditBundle( ISkTagService aSource, ISkTagGroup aBundle, IDtoTagInfo aBundleInfo ) {
    // TODO Auto-generated method stub

    return ValidationResult.SUCCESS;
  }

  @Override
  public ValidationResult canRemoveBundle( ISkTagService aSource, String aBundleId ) {
    // TODO Auto-generated method stub

    return ValidationResult.SUCCESS;
  }

  @Override
  public ValidationResult canCreateGroup( ISkTagService aSource, ISkTagGroup aParent, IDtoTagInfo aGroupInfo ) {
    // error if parent group does not exists ("phantom", already removed group)
    if( owner.groupMan().findGroup( aParent.id() ) == null ) {
      return ValidationResult.error( FMT_ERR_NO_SUCH_GROUP, aParent.id() );
    }
    // TODO error if the group with the same ID already exists

    // TODO Auto-generated method stub

    return ValidationResult.SUCCESS;
  }

  @Override
  public ValidationResult canEditGroup( ISkTagService aSource, ISkTagGroup aGroup, IDtoTagInfo aGroupInfo ) {
    // error if group does not exists ("phantom", already removed group)
    if( owner.groupMan().findGroup( aGroup.id() ) == null ) {
      return ValidationResult.error( FMT_ERR_NO_SUCH_GROUP, aGroup.id() );
    }

    // TODO Auto-generated method stub

    return ValidationResult.SUCCESS;
  }

  @Override
  public ValidationResult canRemoveGroup( ISkTagService aSource, ISkTagGroup aParent, String aGroupId ) {
    // error if parent group does not exists ("phantom", already removed group)
    if( owner.groupMan().findGroup( aParent.id() ) == null ) {
      return ValidationResult.error( FMT_ERR_NO_SUCH_GROUP, aParent.id() );
    }
    // error if group does not exists
    if( owner.groupMan().findGroup( aGroupId ) == null ) {
      return ValidationResult.error( FMT_ERR_NO_SUCH_GROUP, aGroupId );
    }

    // TODO Auto-generated method stub

    return ValidationResult.SUCCESS;
  }

  @Override
  public ValidationResult canAddTagToGroup( ISkTagService aSource, ISkTagGroup aGroup, String aTagId ) {
    // error if group does not exists ("phantom", already removed group)
    if( owner.groupMan().findGroup( aGroup.id() ) == null ) {
      return ValidationResult.error( FMT_ERR_NO_SUCH_GROUP, aGroup.id() );
    }
    // error if tag does not exists
    if( owner.findTag( aTagId ) == null ) {
      return ValidationResult.error( FMT_ERR_NO_SUCH_TAG, aTagId );
    }
    // error if group does not contains tag
    if( aGroup.findTag( aTagId ) != null ) {
      return ValidationResult.error( FMT_ERR_GROUP_ALREADY_HAS_TAG, aGroup.id(), aTagId );
    }
    // warn if tag already is in this bundle
    if( aGroup.bundle().listAllTags().hasKey( aTagId ) ) {
      return ValidationResult.warn( FMT_WARN_BUNDLE_ALREADY_HAS_TAG, aGroup.id(), aTagId );
    }
    return ValidationResult.SUCCESS;
  }

  @Override
  public ValidationResult canRemoveTagFromGroup( ISkTagService aSource, ISkTagGroup aGroup, String aTagId ) {
    // error if group does not exists ("phantom", already removed group)
    if( owner.groupMan().findGroup( aGroup.id() ) == null ) {
      return ValidationResult.error( FMT_ERR_NO_SUCH_GROUP, aGroup.id() );
    }
    // warn if group does not contains the tag
    if( aGroup.findTag( aTagId ) == null ) {
      return ValidationResult.error( FMT_ERR_NO_TAG_IN_GROUP, aGroup.id(), aTagId );
    }
    return ValidationResult.SUCCESS;
  }

}
