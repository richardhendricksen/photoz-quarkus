package nl.codecontrol.rest;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static java.util.Objects.isNull;
import static javax.ws.rs.core.HttpHeaders.CONTENT_DISPOSITION;

@Path("/download")
@ApplicationScoped
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class DownloadResource {

    private final PhotozController photozController;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    public Response download(@PathParam("id") long id) {
        final var photo = photozController.getPhoto(id);
        if (isNull(photo)) {
            throw new NotFoundException("Unknown photo: %s".formatted(id));
        }

        return Response.ok(photo.data())
                .type(photo.contentType())
                .header(CONTENT_DISPOSITION, "attachment;filename=" + photo.fileName())
                .build();
    }
}
