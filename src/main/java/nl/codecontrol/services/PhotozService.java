package nl.codecontrol.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import nl.codecontrol.model.Photo;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MediaType;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
@RequiredArgsConstructor(access= AccessLevel.PROTECTED)
public class PhotozService {

    private final Map<Long, Photo> db = new HashMap<>() {{
        put(1L, new Photo(1L, "myFilename.jpg", null, null));
    }};

    public Collection<Photo> getPhotos() {
        return db.values();
    }

    public Photo getPhoto(long id) {
        return db.get(id);
    }

    public Photo deletePhoto(long id) {
        return db.remove(id);
    }

    public Photo addPhoto(String fileName, MediaType contentType, byte[] data) {
        long id = db.keySet().stream().max(Long::compare).get() + 1;

        final var photo = Photo.builder()
                .id(id)
                .fileName(fileName)
                .data(data)
                .contentType(contentType)
                .build();

        db.put(photo.getId(), photo);

        return photo;
    }
}
