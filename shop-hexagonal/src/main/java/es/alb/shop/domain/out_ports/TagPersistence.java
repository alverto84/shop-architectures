package es.alb.shop.domain.out_ports;

import es.alb.shop.domain.models.Tag;
import org.springframework.stereotype.Repository;

import java.util.stream.Stream;

@Repository
public interface TagPersistence {

    Tag readById(String id);

    void deleteById(String id);

    Stream<Tag> readAll();
}
