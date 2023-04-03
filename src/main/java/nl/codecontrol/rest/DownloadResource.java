package nl.codecontrol.rest;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import nl.codecontrol.model.Photo;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static java.util.Objects.isNull;

@Path("/download")
@ApplicationScoped
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class DownloadResource {

    private final PhotozController photozController;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("id") long id) {
        final Photo photo = photozController.getPhoto(id);
        if (isNull(photo)) {
            throw new NotFoundException("Unknown photo: %s".formatted(id));
        }

        Response.ResponseBuilder response = Response.ok(photo.getData());
        response.header("Content-Disposition", "attachment;filename=" + photo.getFileName());
        return response.build();
    }
}
