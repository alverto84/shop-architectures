package es.alb.shop.domain.services;

import es.alb.shop.domain.models.Tag;
import es.alb.shop.domain.out_ports.ShoppingCartPersistence;
import es.alb.shop.domain.out_ports.TagPersistence;
import es.alb.shop.domain.in_ports.TagService;
import es.alb.shop.domain.models.ArticleItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service("tagService")
public class TagServiceImpl implements TagService {

    private TagPersistence tagPersistence;

    private ShoppingCartPersistence shoppingCartPersistence;

    @Autowired
    public TagServiceImpl(TagPersistence tagPersistence, ShoppingCartPersistence shoppingCartPersistence) {
        this.tagPersistence = tagPersistence;
        this.shoppingCartPersistence = shoppingCartPersistence;
    }

    @Override
    public Tag read(String id) {
        return this.tagPersistence.readById(id);
    }

    @Override
    public void delete(String id) {
        this.tagPersistence.deleteById(id);
    }

    @Override
    public Stream<Tag> findByArticlesInShoppingCarts() {
        List<Long> barcodes = this.shoppingCartPersistence.readAll()
                .flatMap(shoppingCart -> shoppingCart.getArticleItems().stream())
                .map(ArticleItem::getBarcode)
                .collect(Collectors.toList());
        return this.tagPersistence.readAll()
                .filter(tag -> tag.getArticlesBarcode().stream().anyMatch(barcodes::contains));
    }
}
