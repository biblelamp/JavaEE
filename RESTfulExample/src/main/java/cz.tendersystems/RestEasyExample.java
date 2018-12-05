package cz.tendersystems;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/service")
public class RestEasyExample {

	@GET
	@Path("/{message}")
	public Response getMessage(@PathParam("message") String message) {

		String responseStr = "Received message: " + message + "\n";

		return Response.status(200).entity(responseStr).build();
	}

	@POST
	@Path("/post")
	@Consumes("application/json")
	public Response postMessage(String message) {
		return Response.status(200).entity(message).build();
	}
}
