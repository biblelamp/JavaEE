package cz.tendersystems;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.HeaderParam;
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
	public Response getData(InputStream inputStream,
			@HeaderParam("Content-Length") int contentLength,
			@HeaderParam("Title") String fileName) {

		LOG.log(Level.INFO, Integer.toString(contentLength));
		LOG.log(Level.INFO, fileName);

		try {

			byte[] buffer = new byte[contentLength];
			inputStream.read(buffer);
			inputStream.close();

			// LOG.log(Level.INFO, Arrays.toString(buffer));

			Decrypt.decryptFile(buffer, Decrypt.PATH + fileName);

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return Response.status(200).entity(fileName).build();
	}
}
