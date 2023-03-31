package nl.codecontrol.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.ws.rs.core.MediaType;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Photo {

    private Long id;

    @NotNull
    private String fileName;

    @NotNull
    @JsonIgnore
    private byte[] data;

    @NotNull
    private MediaType contentType;
}
