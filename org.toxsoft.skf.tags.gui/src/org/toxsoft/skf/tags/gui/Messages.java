package org.toxsoft.skf.tags.gui;

import org.eclipse.osgi.util.*;

public class Messages
    extends NLS {

  private static final String BUNDLE_NAME = Messages.class.getPackageName() + ".messages"; //$NON-NLS-1$
  public static String        STR_D_IS_UNMARKABLE;
  public static String        STR_D_LOCAL_ID;
  public static String        STR_D_PARAM_GWID_LIST;
  public static String        STR_D_PARENT;
  public static String        STR_D_ROOT;
  public static String        STR_D_TAG;
  public static String        STR_N_IS_UNMARKABLE;
  public static String        STR_N_LOCAL_ID;
  public static String        STR_N_PARAM_GWID_LIST;
  public static String        STR_N_PARENT;
  public static String        STR_N_ROOT;
  public static String        STR_N_TAG;
  static {
    // initialize resource bundle
    NLS.initializeMessages( BUNDLE_NAME, Messages.class );
  }

  private Messages() {
  }
}
