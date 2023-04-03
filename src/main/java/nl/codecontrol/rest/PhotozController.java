package nl.codecontrol.rest;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import nl.codecontrol.model.Photo;
import nl.codecontrol.services.PhotozService;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@ApplicationScoped
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PhotozController {

    private final PhotozService photozService;

    public Collection<Photo> getPhotos() {
        return photozService.getPhotos();
    }

    public Photo getPhoto(final long id) {
        return photozService.getPhoto(id);
    }

    public void deletePhoto(long id) {
        photozService.deletePhoto(id);
    }

    public Photo addPhoto(String fileName, MediaType contentType, byte[] data) {
        return photozService.addPhoto(fileName, contentType, data);
    }
}
