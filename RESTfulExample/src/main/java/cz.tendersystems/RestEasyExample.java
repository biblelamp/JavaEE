package cz.tendersystems;

import org.json.*;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Consumes;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
	@Consumes("application/json")
	public Response postMessage(String message) {

		JSONObject json = new JSONObject(message);
		JSONArray body = json.getJSONArray("body");
		byte[] buffer = new byte[body.length()];
		int i = 0;

		LOG.log(Level.INFO, body.toString());
		LOG.log(Level.INFO, Integer.toString(body.length()));

		try {

			File file = new File("/home/lamp/Downloads/Ahoj jak se máš.encrypt");
			byte[] buf = new byte[(int) file.length()];
			BufferedInputStream bis =
					new BufferedInputStream(new FileInputStream(file));
			bis.read(buf);
			bis.close();

			for (i = 0; i < buffer.length; i++) {
				buffer[i] = ((Integer) body.get(i)).byteValue();

				if (buffer[i] != buf[i]) {
					LOG.log(Level.INFO, String.format("buffer[ %d ] = %d != %d", i, buf[i], buffer[i]));
				}
			}

			Decrypt.decryptFile(buffer, json.getString("fileName"));

		} catch (Exception ex) {
			LOG.log(Level.INFO, String.format("buffer[ %d ] = %d", i, buffer[i]));
			ex.printStackTrace();
		}

		return Response.status(200).entity(message).build();
	}
}
