import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * Class that echoes its input back to the requester.
 * 
 * @author dhalperi
 */
@Path("/echo/object")
public final class EchoObjectResource {
  /**
   * For now, simply echoes back its input.
   * 
   * @param userData the input.
   * @return the input.
   */
  @POST
  @Consumes(MediaType.APPLICATION_JSON)
  @Produces(MediaType.APPLICATION_JSON)
  public int[] postObject(final int[] userData) {
    return userData;
  }
}
