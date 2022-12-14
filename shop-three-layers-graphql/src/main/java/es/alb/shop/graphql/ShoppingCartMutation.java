package es.alb.shop.graphql;

import es.alb.shop.services.ShoppingCartService;
import es.alb.shop.data.ShoppingCartEntity;
import es.alb.shop.services.ArticleItem;
import graphql.kickstart.tools.GraphQLMutationResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class ShoppingCartMutation implements GraphQLMutationResolver {

    private ShoppingCartService shoppingCartService;

    @Autowired
    public ShoppingCartMutation(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    public ShoppingCartEntity updateShoppingCartArticleItems(String id, List<ArticleItem> articleItemList) {
        return this.shoppingCartService.updateArticleItems(id, articleItemList);
    }

}
