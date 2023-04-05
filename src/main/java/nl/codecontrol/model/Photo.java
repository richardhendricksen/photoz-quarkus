package nl.codecontrol.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@EqualsAndHashCode(callSuper = false)
@Table(name="PHOTOZ")
public class Photo extends PanacheEntity {

    private Long id;

    @NotNull
    private String fileName;

    @NotNull
    @JsonIgnore
    @Column(columnDefinition = "BINARY")
    private byte[] data;

    @NotNull
    private String contentType;
}
