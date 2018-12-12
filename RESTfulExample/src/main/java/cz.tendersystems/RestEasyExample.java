package cz.tendersystems;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import java.io.*;
import java.util.*;
import java.util.logging.*;

@Path("/service")
public class RestEasyExample {

	private static final Logger LOG = Logger.getLogger(RestEasyExample.class.getName());

	@GET
	@Path("/{message}")
	public Response getMessage(@PathParam("message") String message) {

		String responseStr = "Received message: " + message + "\n";

		return Response.status(200).entity(responseStr).build();
	}

	@POST
	@Path("/post")
	@Consumes(MediaType.APPLICATION_OCTET_STREAM)
	public Response getData(InputStream inputStream) {

		try {

			byte[] buffer = new byte[514];
			inputStream.read(buffer);
			inputStream.close();

			LOG.log(Level.INFO, Arrays.toString(buffer));

			// byte[] file = Decrypt.readFile("/home/lamp/Downloads/Ahoj jak se máš.encrypt");
			// LOG.log(Level.INFO, Arrays.toString(file));

			Decrypt.decryptFile(buffer, "123456789");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return Response.status(200).entity("123456789").build();
	}
}
