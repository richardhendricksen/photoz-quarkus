package nl.codecontrol.rest;

import nl.codecontrol.model.Photo;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Path("/photoz")
public class PhotozResource {

    private final Map<Long,Photo> db = new HashMap<>(){{
        put(1L, new Photo(1L, "myFilename.jpg"));
    }};

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Photo> get() {
        return db.values();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Photo get(@PathParam("id") long id) {
        return db.get(id);
    }
}