package es.alb.shop.rest;

import es.alb.shop.rest.dtos.ShoppingCartReference;
import es.alb.shop.services.ArticleItem;
import es.alb.shop.services.ShoppingCartService;
import es.alb.shop.rest.dtos.ShoppingCart;
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
        return new ShoppingCart(this.shoppingCartService.updateArticleItems(id, articleItemList));
    }

    @GetMapping(SEARCH)
    public Stream<ShoppingCartReference> findByPriceGreaterThan(@RequestParam String q) {
        BigDecimal price = new LexicalAnalyzer().extractWithAssure(q, "price", BigDecimal::new);
        return this.shoppingCartService.findByPriceGreaterThan(price)
                .map(ShoppingCartReference::new);
    }
}
