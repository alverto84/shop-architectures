package es.alb.shop.adapters.mongodb;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface TagRepository extends MongoRepository<TagEntity, String> {
}
