import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.eclipse.jetty.webapp.WebAppContext;

public class Launcher {

  public static void main(String[] args) throws Exception {
    Server server = new Server();

    SelectChannelConnector connector = new SelectChannelConnector();
    connector.setPort(8090);
    server.addConnector(connector);

    WebAppContext context = new WebAppContext();
    context.setWar("web-app.war");

    server.setHandler(context);
    server.start();
  }

}
