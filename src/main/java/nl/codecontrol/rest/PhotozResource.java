package nl.codecontrol.rest;

import nl.codecontrol.model.Photo;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;

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
        final Photo photo = db.get(id);
        if (isNull(photo)) {
            throw new NotFoundException("Unknown photo: %s".formatted(id));
        }

        return photo;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Photo delete(@PathParam("id") long id) {
        final Photo photo = db.remove(id);
        if (isNull(photo)) {
            throw new NotFoundException("Unknown photo: %s".formatted(id));
        }

        return photo;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Photo create(Photo photo) {
        long id = db.keySet().stream().max(Long::compare).get() + 1;
        photo.setId(id);
        db.put(photo.getId(), photo);

        return photo;
    }
}