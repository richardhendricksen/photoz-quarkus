package nl.codecontrol.rest;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import nl.codecontrol.mappers.PhotoMapper;
import nl.codecontrol.model.PhotoDto;
import nl.codecontrol.services.PhotozService;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MediaType;
import java.util.List;

@ApplicationScoped
@RequiredArgsConstructor(access = AccessLevel.PROTECTED)
public class PhotozController {

    private final PhotozService photozService;
    private final PhotoMapper photoMapper;

    public List<PhotoDto> getPhotos() {
        return photozService.getPhotos().stream().map(photoMapper::toDto).toList();
    }

    public PhotoDto getPhoto(final long id) {
        return photoMapper.toDto(photozService.getPhoto(id));
    }

    public void deletePhoto(long id) {
        photozService.deletePhoto(id);
    }

    public PhotoDto addPhoto(String fileName, MediaType contentType, byte[] data) {
        return photoMapper.toDto(photozService.addPhoto(fileName, contentType, data));
    }
}
