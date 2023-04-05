package nl.codecontrol.rest;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import nl.codecontrol.model.PhotoDto;
import nl.codecontrol.util.MultiPartFile;
import nl.codecontrol.util.MultiPartHelper;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.util.List;

import static java.util.Objects.isNull;

@Path("/photoz")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PhotozResource {

    private final PhotozController photozController;
    private final MultiPartHelper multiPartHelper;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<PhotoDto> get() {
        return photozController.getPhotos();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public PhotoDto get(@PathParam("id") long id) {
        final PhotoDto photo = photozController.getPhoto(id);
        if (isNull(photo)) {
            throw new NotFoundException("Unknown photo: %s".formatted(id));
        }

        return photo;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public void delete(@PathParam("id") long id) {
        photozController.deletePhoto(id);
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public PhotoDto create(MultipartFormDataInput input) throws IOException {
        MultiPartFile multiPartFile = multiPartHelper.extractData(input);

        return photozController.addPhoto(multiPartFile.fileName(), multiPartFile.contentType(), multiPartFile.data());
    }
}