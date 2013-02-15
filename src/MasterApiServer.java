import org.restlet.Component;
import org.restlet.data.Protocol;
import org.restlet.ext.jaxrs.JaxRsApplication;

/**
 * The main class for the RESTlet test application API server.
 * 
 * @author dhalperi
 * 
 */
public final class MasterApiServer {

  /** The instance. */
  public static final MasterApiServer INSTANCE = new MasterApiServer();

  /** The default port for the server. */
  private static final int PORT = 8080;

  /**
   * The RESTlet Component is the main class that holds multiple servers/hosts for this application.
   */
  private final Component component;

  /** The RESTlet server object. */
  private final org.restlet.Server restletServer;

  /**
   * Constructor for the Master API Server.
   */
  private MasterApiServer() {
    /* create Component (as ever for Restlet) */
    component = new Component();

    /* Add a server that responds to HTTP on port PORT. */
    restletServer = component.getServers().add(Protocol.HTTP, PORT);

    /* Add a JAX-RS runtime environment, using our MasterApplication implementation. */
    final JaxRsApplication application = new JaxRsApplication();
    application.add(new MasterApplication());

    /* Attach the application to the component */
    component.getDefaultHost().attach(application);
  }

  /**
   * Starts the master RESTlet API server.
   * 
   * @throws Exception if there is an error starting the server.
   */
  public void start() throws Exception {
    System.out.println("Starting server");
    component.start();
    System.out.println("Server started on port " + restletServer.getPort());
  }

  /**
   * Stops the master RESTlet API server.
   * 
   * @throws Exception if there is an error stopping the server.
   */
  public void stop() throws Exception {
    System.out.println("Stopping server");
    component.stop();
    System.out.println("Server stopped");
  }

  /**
   * @param args the command-line arguments to start the daemon.
   * @throws Exception if the Restlet server can't start.
   */
  public static void main(final String[] args) throws Exception {
    MasterApiServer.INSTANCE.start();
    /* Wait for the user to press enter before exiting. */
    System.in.read();
    MasterApiServer.INSTANCE.stop();
  }
}