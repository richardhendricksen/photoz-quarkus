package nl.codecontrol.rest;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import nl.codecontrol.model.Photo;
import org.jboss.resteasy.annotations.jaxrs.PathParam;
import org.jboss.resteasy.plugins.providers.multipart.InputPart;
import org.jboss.resteasy.plugins.providers.multipart.MultipartFormDataInput;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

@Path("/photoz")
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PhotozResource {

    private final PhotozController photozController;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Photo> get() {
        return photozController.getPhotos();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Photo get(@PathParam("id") long id) {
        final Photo photo = photozController.getPhoto(id);
        if (isNull(photo)) {
            throw new NotFoundException("Unknown photo: %s".formatted(id));
        }

        return photo;
    }

    @DELETE
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Photo delete(@PathParam("id") long id) {
        final Photo photo = photozController.deletePhoto(id);
        if (isNull(photo)) {
            throw new NotFoundException("Unknown photo: %s".formatted(id));
        }

        return photo;
    }

    @POST
    @Consumes(MediaType.MULTIPART_FORM_DATA)
    @Produces(MediaType.APPLICATION_JSON)
    public Photo create(MultipartFormDataInput input) throws IOException {
        MultiPartFile multiPartFile = extractData(input);

        return photozController.addPhoto(multiPartFile.fileName, multiPartFile.contentType, multiPartFile.data);
    }

    private MultiPartFile extractData(MultipartFormDataInput input) throws IOException {
        String fileName = "";

        final Map<String, List<InputPart>> formParts = input.getFormDataMap();
        List<InputPart> inPart = formParts.get("data");

        InputPart inputPart = inPart.stream().findFirst().orElseThrow();

        // Retrieve headers, read the Content-Disposition header to obtain the original name of the file
        MultivaluedMap<String, String> headers = inputPart.getHeaders();
        String[] contentDispositionHeader = headers.getFirst("Content-Disposition").split(";");
        for (String name : contentDispositionHeader) {
            if ((name.trim().startsWith("filename"))) {
                String[] tmp = name.split("=");
                fileName = tmp[1].trim().replaceAll("\"", "");
            }
        }

        return new MultiPartFile(fileName, inputPart.getBody(byte[].class, null), inputPart.getMediaType());
    }

    private record MultiPartFile(String fileName, byte[] data, MediaType contentType) {}
}