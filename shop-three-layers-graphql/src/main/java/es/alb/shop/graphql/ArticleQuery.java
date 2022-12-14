package es.alb.shop.graphql;

import es.alb.shop.data.ArticleEntity;
import es.alb.shop.services.ArticleService;
import es.alb.shop.services.exceptions.LexicalAnalyzer;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class ArticleQuery implements GraphQLQueryResolver {

    private ArticleService articleService;

    @Autowired
    public ArticleQuery(ArticleService articleService) {
        this.articleService = articleService;
    }

    public List<ArticleEntity> articles() {
        return this.articleService.readAll()
                .collect(Collectors.toList());
    }

    public List<ArticleEntity> findArticlesByProviderAndPriceGreaterThan(String q) {
        String provider = new LexicalAnalyzer().extractWithAssure(q, "provider");
        BigDecimal price = new LexicalAnalyzer().extractWithAssure(q, "price", BigDecimal::new);
        return this.articleService.findByProviderAndPriceGreaterThan(provider, price)
                .collect(Collectors.toList());

    }

}
