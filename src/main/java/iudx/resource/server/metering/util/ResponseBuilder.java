package iudx.resource.server.metering.util;

import static iudx.resource.server.metering.util.Constants.COUNT;
import static iudx.resource.server.metering.util.Constants.DETAIL;
import static iudx.resource.server.metering.util.Constants.ERROR_TYPE;
import static iudx.resource.server.metering.util.Constants.FAILED;
import static iudx.resource.server.metering.util.Constants.RESULTS;
import static iudx.resource.server.metering.util.Constants.SUCCESS;
import static iudx.resource.server.metering.util.Constants.TITLE;

import io.vertx.core.json.JsonArray;
import io.vertx.core.json.JsonObject;

public class ResponseBuilder {
  private String status;
  private JsonObject response;

  /** Initialise the object with Success or Failure. */
  public ResponseBuilder(String status) {
    this.status = status;
    response = new JsonObject();
  }

  public ResponseBuilder setTypeAndTitle(int statusCode) {
    response.put(ERROR_TYPE, statusCode);
    if (SUCCESS.equalsIgnoreCase(status)) {
      response.put(TITLE, SUCCESS);
    } else if (FAILED.equalsIgnoreCase(status)) {
      response.put(TITLE, FAILED);
    }
    return this;
  }
  //
  //
  //
  //    public ResponseBuilder setMessage(JsonArray results) {
  //        response.put(RESULTS, results);
  //        return this;
  //    }

  /** Overloaded methods for Error messages. */
  public ResponseBuilder setMessage(String error) {
    response.put(DETAIL, error);
    return this;
  }

  //    ResponseBuilder setMessage(JsonObject error) {
  //        int statusCode = error.getInteger(STATUS);
  //        String type = error.getJsonObject(ERROR.toLowerCase()).getString(TYPE_KEY);
  //        if (statusCode == 404 && INDEX_NOT_FOUND.equalsIgnoreCase(type)) {
  //            response.put(DETAIL, INVALID_RESOURCE_ID);
  //        } else {
  //            response.put(DETAIL,
  //
  // error.getJsonObject(ERROR.toLowerCase()).getJsonArray(ROOT_CAUSE).getJsonObject(0)
  //                            .getString(REASON));
  //        }
  //        return this;
  //    }

  public ResponseBuilder setCount(int count) {
    response.put(RESULTS, new JsonArray().add(new JsonObject().put(COUNT, count)));
    return this;
  }

  //    public ResponseBuilder setFromParam(int from) {
  //        response.put(FROM_KEY, from);
  //        return this;
  //    }

  //    public ResponseBuilder setSizeParam(int size) {
  //        response.put(SIZE_KEY, size);
  //        return this;
  //    }

  public JsonObject getResponse() {
    return response;
  }
}
