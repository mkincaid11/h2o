package water.api;

import water.*;
import water.util.Log;
import water.util.Log.Tag.Sys;

public class Debug extends Request {
  @Override protected Response serve() {
    int kcnt=0;
    for( Key key : H2O.keySet() ) {
      kcnt++;
      Value v = H2O.raw_get(key);
      Log.debug(this,Sys.WATER, "K: ",key," V:",(v==null?"null":""+v._max));
    }
    return Response.error("Dumped "+kcnt+" keys");
  }
}
