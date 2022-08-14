package es.alb.shop.rest;

import es.alb.shop.rest.dtos.Article;
import es.alb.shop.rest.dtos.ArticleCreation;
import es.alb.shop.services.ArticlePriceUpdating;
import es.alb.shop.services.ArticleService;
import es.alb.shop.data.ArticleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

@RestController
@RequestMapping(ArticleResource.ARTICLES)
public class ArticleResource {
    static final String ARTICLES = "/articles";
    static final String SEARCH = "/search";

    private ArticleService articleService;

    @Autowired
    public ArticleResource(ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping
    public Article create(@RequestBody ArticleCreation articleCreation) {
        ArticleEntity articleEntity = new ArticleEntity(
                articleCreation.getBarcode(),
                articleCreation.getDescription(),
                articleCreation.getPrice(),
                articleCreation.getProvider()
        );
        return new Article(this.articleService.create(articleEntity));
    }

    @PatchMapping
    public void updatePrices(@RequestBody List<ArticlePriceUpdating> articlePriceUpdatingList) {
        this.articleService.updatePrices(articlePriceUpdatingList);
    }

    @GetMapping(SEARCH)
    public Stream<Article> findByProviderAndPriceGreaterThan(@RequestParam String q) {
        String provider = new LexicalAnalyzer().extractWithAssure(q, "provider");
        BigDecimal price = new LexicalAnalyzer().extractWithAssure(q, "price", BigDecimal::new);
        return this.articleService.findByProviderAndPriceGreaterThan(provider, price)
                .map(Article::new);
    }

}
