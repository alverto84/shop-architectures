package es.alb.shop.services;

import es.alb.shop.services.exceptions.NotFoundException;
import es.alb.shop.data.ArticleItemEntity;
import es.alb.shop.data.ArticleRepository;
import es.alb.shop.data.ShoppingCartEntity;
import es.alb.shop.data.ShoppingCartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ShoppingCartService {

    private ShoppingCartRepository shoppingCartRepository;

    private ArticleRepository articleRepository;

    @Autowired
    public ShoppingCartService(ShoppingCartRepository shoppingCartRepository, ArticleRepository articleRepository) {
        this.shoppingCartRepository = shoppingCartRepository;
        this.articleRepository = articleRepository;
    }

    public Stream<ShoppingCartEntity> readAll() {
        return this.shoppingCartRepository.findAll().stream();
    }

    public ShoppingCartEntity readById(String id) {
        return this.shoppingCartRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("ShoppingCart id:" + id));
    }

    public ShoppingCartEntity update(String id, String user, String address) {
        ShoppingCartEntity shoppingCartEntity = this.shoppingCartRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("ShoppingCart id:" + id));
        shoppingCartEntity.setUser(user);
        shoppingCartEntity.setAddress(address);
        return this.shoppingCartRepository.save(shoppingCartEntity);
    }

    public ShoppingCartEntity updateArticleItems(String id, List<ArticleItem> articleItemList) {
        ShoppingCartEntity shoppingCartEntity = this.shoppingCartRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("ShoppingCart id:" + id));
        List<ArticleItemEntity> articleItemEntityList = articleItemList.stream()
                .map(articleItem -> new ArticleItemEntity(
                        this.articleRepository
                                .findByBarcode(articleItem.getBarcode())
                                .orElseThrow(() -> new NotFoundException("Article barcode: " + articleItem.getBarcode())),
                        articleItem.getAmount(),
                        articleItem.getDiscount())
                ).collect(Collectors.toList());
        shoppingCartEntity.setArticleItemEntities(articleItemEntityList);
        return this.shoppingCartRepository.save(shoppingCartEntity);
    }

    public Stream<ShoppingCartEntity> findByPriceGreaterThan(BigDecimal price) {
        return this.shoppingCartRepository.findAll().stream()
                .filter(shoppingCartEntity -> price.compareTo(shoppingCartEntity.total()) < 0);
    }

}
