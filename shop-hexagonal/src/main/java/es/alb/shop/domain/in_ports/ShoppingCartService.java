package es.alb.shop.domain.in_ports;

import es.alb.shop.domain.models.ArticleItem;
import es.alb.shop.domain.models.ShoppingCart;
import es.alb.shop.domain.models.ShoppingCartReference;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

public interface ShoppingCartService {

    ShoppingCart updateArticleItems(String id, List<ArticleItem> articleItemList);

    Stream<ShoppingCartReference> findByPriceGreaterThan(BigDecimal price);
}
