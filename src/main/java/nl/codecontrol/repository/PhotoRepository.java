package nl.codecontrol.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import nl.codecontrol.model.Photo;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PhotoRepository implements PanacheRepository<Photo> {
}
