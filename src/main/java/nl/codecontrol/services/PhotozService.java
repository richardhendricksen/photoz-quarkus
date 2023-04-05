package nl.codecontrol.services;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import nl.codecontrol.entity.Photo;
import nl.codecontrol.repository.PhotoRepository;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.core.MediaType;
import java.util.Collection;

@ApplicationScoped
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PhotozService {

    private final PhotoRepository photoRepository;

    public Collection<Photo> getPhotos() {
        return photoRepository.findAll().list();
    }

    public Photo getPhoto(long id) {
        return photoRepository.findById(id);
    }

    public void deletePhoto(long id) {
        photoRepository.deleteById(id);
    }

    @Transactional
    public Photo addPhoto(String fileName, MediaType contentType, byte[] data) {
        final var photo = Photo.builder()
            .fileName(fileName)
            .data(data)
            .contentType(contentType.toString())
            .build();

        photoRepository.persist(photo);

        return photo;
    }
}
