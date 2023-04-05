package nl.codecontrol.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import nl.codecontrol.entity.Photo;

public interface PhotoRepository extends PanacheRepository<Photo> {
}
