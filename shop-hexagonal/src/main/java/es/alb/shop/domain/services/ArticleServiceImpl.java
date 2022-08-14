package es.alb.shop.domain.services;

import es.alb.shop.domain.out_ports.ArticlePersistence;
import es.alb.shop.domain.in_ports.ArticleService;
import es.alb.shop.domain.models.Article;
import es.alb.shop.domain.models.ArticleCreation;
import es.alb.shop.domain.models.ArticlePriceUpdating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

@Service("articleService")
public class ArticleServiceImpl implements ArticleService {

    private ArticlePersistence articlePersistence;

    @Autowired
    public ArticleServiceImpl(ArticlePersistence articlePersistence) {
        this.articlePersistence = articlePersistence;
    }

    @Override
    public Article create(ArticleCreation articleCreation) {
        return this.articlePersistence.create(articleCreation);
    }

    @Override
    public void updatePrices(List<ArticlePriceUpdating> articlePriceUpdatingList) {
        articlePriceUpdatingList.stream()
                .map(articleNewPrice -> {
                    Article article = this.articlePersistence.readByBarcode(articleNewPrice.getBarcode());
                    article.setPrice(articleNewPrice.getPrice());
                    return article;
                })
                .forEach(article -> this.articlePersistence.update(article));
    }

    @Override
    public Stream<Article> findByProviderAndPriceGreaterThan(String provider, BigDecimal price) {
        return this.articlePersistence.findByProviderAndPriceGreaterThan(provider, price);
    }
}
