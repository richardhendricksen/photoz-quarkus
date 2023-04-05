package nl.codecontrol.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.MediaType;

@Builder
public record PhotoDto(long id, @NotNull String fileName, @NotNull @JsonIgnore byte[] data,
                       @NotNull MediaType contentType) {
}
