package org.toxsoft.skf.tags.lib.impl;

import org.toxsoft.core.tslib.bricks.validator.*;
import org.toxsoft.core.tslib.bricks.validator.impl.*;
import org.toxsoft.core.tslib.utils.errors.*;
import org.toxsoft.skf.tags.lib.*;

/**
 * {@link ISkTagService#svs()} implementation.
 *
 * @author hazard157
 */
class InternalValidationSupport
    extends AbstractTsValidationSupport<ISkTagServiceValidator>
    implements ISkTagServiceValidator {

  public InternalValidationSupport() {
    // nop
  }

  @Override
  public ValidationResult canCreateTag( ISkTagService aSource, IDtoTagInfo aTagInfo ) {
    TsNullArgumentRtException.checkNulls( aSource, aTagInfo );
    ValidationResult vr = ValidationResult.SUCCESS;
    for( ISkTagServiceValidator v : validatorsList() ) {
      vr = v.canCreateTag( aSource, aTagInfo );
      if( vr.isError() ) {
        break;
      }
    }
    return vr;
  }

  @Override
  public ValidationResult canEditTag( ISkTagService aSource, ISkTag aTag, IDtoTagInfo aTagInfo ) {
    TsNullArgumentRtException.checkNulls( aSource, aTag, aTagInfo );
    ValidationResult vr = ValidationResult.SUCCESS;
    for( ISkTagServiceValidator v : validatorsList() ) {
      vr = v.canEditTag( aSource, aTag, aTagInfo );
      if( vr.isError() ) {
        break;
      }
    }
    return vr;
  }

  @Override
  public ValidationResult canRemoveTag( ISkTagService aSource, String aTagId ) {
    TsNullArgumentRtException.checkNulls( aSource, aTagId );
    ValidationResult vr = ValidationResult.SUCCESS;
    for( ISkTagServiceValidator v : validatorsList() ) {
      vr = v.canRemoveTag( aSource, aTagId );
      if( vr.isError() ) {
        break;
      }
    }
    return vr;
  }

  @Override
  public ValidationResult canCreateBundle( ISkTagService aSource, IDtoTagInfo aBundleInfo ) {
    TsNullArgumentRtException.checkNulls( aSource, aBundleInfo );
    ValidationResult vr = ValidationResult.SUCCESS;
    for( ISkTagServiceValidator v : validatorsList() ) {
      vr = v.canCreateBundle( aSource, aBundleInfo );
      if( vr.isError() ) {
        break;
      }
    }
    return vr;
  }

  @Override
  public ValidationResult canEditBundle( ISkTagService aSource, ISkTagGroup aBundle, IDtoTagInfo aBundleInfo ) {
    TsNullArgumentRtException.checkNulls( aSource, aBundle, aBundleInfo );
    ValidationResult vr = ValidationResult.SUCCESS;
    for( ISkTagServiceValidator v : validatorsList() ) {
      vr = v.canEditBundle( aSource, aBundle, aBundleInfo );
      if( vr.isError() ) {
        break;
      }
    }
    return vr;
  }

  @Override
  public ValidationResult canRemoveBundle( ISkTagService aSource, String aBundleId ) {
    TsNullArgumentRtException.checkNulls( aSource, aBundleId );
    ValidationResult vr = ValidationResult.SUCCESS;
    for( ISkTagServiceValidator v : validatorsList() ) {
      vr = v.canRemoveBundle( aSource, aBundleId );
      if( vr.isError() ) {
        break;
      }
    }
    return vr;
  }

  @Override
  public ValidationResult canCreateGroup( ISkTagService aSource, ISkTagGroup aParent, IDtoTagInfo aGroupInfo ) {
    TsNullArgumentRtException.checkNulls( aSource, aParent, aGroupInfo );
    ValidationResult vr = ValidationResult.SUCCESS;
    for( ISkTagServiceValidator v : validatorsList() ) {
      vr = v.canCreateGroup( aSource, aParent, aGroupInfo );
      if( vr.isError() ) {
        break;
      }
    }
    return vr;
  }

  @Override
  public ValidationResult canEditGroup( ISkTagService aSource, ISkTagGroup aGroup, IDtoTagInfo aGroupInfo ) {
    TsNullArgumentRtException.checkNulls( aSource, aGroup, aGroupInfo );
    ValidationResult vr = ValidationResult.SUCCESS;
    for( ISkTagServiceValidator v : validatorsList() ) {
      vr = v.canEditGroup( aSource, aGroup, aGroupInfo );
      if( vr.isError() ) {
        break;
      }
    }
    return vr;
  }

  @Override
  public ValidationResult canRemoveGroup( ISkTagService aSource, ISkTagGroup aParent, String aGroupId ) {
    TsNullArgumentRtException.checkNulls( aSource, aParent, aGroupId );
    ValidationResult vr = ValidationResult.SUCCESS;
    for( ISkTagServiceValidator v : validatorsList() ) {
      vr = v.canRemoveGroup( aSource, aParent, aGroupId );
      if( vr.isError() ) {
        break;
      }
    }
    return vr;
  }

  @Override
  public ValidationResult canAddTagToGroup( ISkTagService aSource, ISkTagGroup aGroup, String aTagId ) {
    TsNullArgumentRtException.checkNulls( aSource, aGroup, aTagId );
    ValidationResult vr = ValidationResult.SUCCESS;
    for( ISkTagServiceValidator v : validatorsList() ) {
      vr = v.canAddTagToGroup( aSource, aGroup, aTagId );
      if( vr.isError() ) {
        break;
      }
    }
    return vr;
  }

  @Override
  public ValidationResult canRemoveTagFromGroup( ISkTagService aSource, ISkTagGroup aGroup, String aTagId ) {
    TsNullArgumentRtException.checkNulls( aSource, aGroup, aTagId );
    ValidationResult vr = ValidationResult.SUCCESS;
    for( ISkTagServiceValidator v : validatorsList() ) {
      vr = v.canRemoveTag( aSource, aTagId );
      if( vr.isError() ) {
        break;
      }
    }
    return vr;
  }

  @Override
  public ISkTagServiceValidator validator() {
    return this;
  }

}
