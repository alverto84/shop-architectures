package es.alb.shop.adapters.rest;

import es.alb.shop.domain.in_ports.ShoppingCartService;
import es.alb.shop.domain.models.ArticleItem;
import es.alb.shop.domain.models.ShoppingCart;
import es.alb.shop.domain.models.ShoppingCartReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(ShoppingCartResource.SHOPPING_CARTS)
public class ShoppingCartResource {
    static final String SHOPPING_CARTS = "/shopping-carts";
    static final String ID_ID = "/{id}";
    static final String ARTICLE_ITEMS = "/article-items";
    static final String SEARCH = "/search";

    private ShoppingCartService shoppingCartService;

    @Autowired

    public ShoppingCartResource(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @PutMapping(ID_ID + ARTICLE_ITEMS)
    public ShoppingCart updateArticleItems(@PathVariable String id, @RequestBody List<ArticleItem> articleItemList) {
        return this.shoppingCartService.updateArticleItems(id, articleItemList);
    }

    @GetMapping(SEARCH)
    public Stream<ShoppingCartReference> findByPriceGreaterThan(@RequestParam String q) {
        BigDecimal price = new LexicalAnalyzer().extractWithAssure(q, "price", BigDecimal::new);
        return this.shoppingCartService.findByPriceGreaterThan(price);
    }
}
