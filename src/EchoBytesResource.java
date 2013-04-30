import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jackson.map.ObjectMapper;

/**
 * Class that echoes its input back to the requester.
 * 
 * @author dhalperi
 */
@Path("/echo/bytes")
public final class EchoBytesResource {
  /**
   * For now, simply echoes back its input.
   * 
   * @param userData the input.
   * @return the input.
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public int[] postObject(final byte[] userData) {
    ObjectMapper mapper = new ObjectMapper();
    try {
      return mapper.readValue(userData, int[].class);
    } catch (Exception e) {
      throw new WebApplicationException(Status.BAD_REQUEST);
    }
  }
}
