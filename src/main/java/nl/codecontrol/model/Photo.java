package nl.codecontrol.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name="PHOTOZ")
public class Photo extends PanacheEntity {

    private Long id;

    @NotNull
    @Column(name="file_name")
    private String fileName;

    @NotNull
    @JsonIgnore
    @Column(columnDefinition = "BINARY")
    private byte[] data;

    @NotNull
    @Column(name="content_type")
    private String contentType;
}
