package water.api;

import hex.gbm.GBM.GBMModel;
import water.*;
import water.api.RequestServer.API_VERSION;

import com.google.gson.JsonObject;

public class GBMProgressPage extends Progress2 {
  /** Return {@link Response} for finished job. */
  @Override protected Response jobDone(final Job job, final String dst) {
    JsonObject args = new JsonObject();
    args.addProperty("model_key", job.dest().toString());
    return GBMModelView.redirect(this, job.dest());
  }

  public static Response redirect(Request req, Key jobkey, Key dest) {
    return new Response(Response.Status.redirect, req, -1, -1, "GBMProgressPage", "job", jobkey, "dst_key", dest);
  }

  @Override public boolean toHTML(StringBuilder sb) {
    Job jjob = Job.findJob(Key.make(job.value()));
    Value value = DKV.get(jjob.dest());
    GBMModel m = value != null ? (GBMModel) value.get() : null;
    if( m != null )
      m.generateHTML("GBM Model", sb);
    else
      DocGen.HTML.paragraph(sb, "Pending...");
    return true;
  }
  @Override protected API_VERSION[] supportedVersions() { return SUPPORTS_V1_V2; }
}
