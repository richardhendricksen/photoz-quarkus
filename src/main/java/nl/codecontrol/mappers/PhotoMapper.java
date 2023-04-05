package nl.codecontrol.mappers;

import nl.codecontrol.entity.Photo;
import nl.codecontrol.model.PhotoDto;

import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MediaType;

@ApplicationScoped
public class PhotoMapper {

    public PhotoDto toDto(final Photo photo) {
        return PhotoDto.builder()
                .id(photo.getId())
                .fileName(photo.getFileName())
                .contentType(MediaType.valueOf(photo.getContentType()))
                .data(photo.getData())
                .build();
    }
}
