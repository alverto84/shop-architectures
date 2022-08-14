package es.alb.shop.domain.in_ports;

import es.alb.shop.domain.models.Tag;

import java.util.stream.Stream;

public interface TagService {

    Tag read(String id);

    void delete(String id);

    Stream<Tag> findByArticlesInShoppingCarts();
}