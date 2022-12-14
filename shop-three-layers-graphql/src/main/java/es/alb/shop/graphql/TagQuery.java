package es.alb.shop.graphql;

import es.alb.shop.services.TagService;
import es.alb.shop.services.exceptions.BadRequestException;
import es.alb.shop.services.exceptions.LexicalAnalyzer;
import es.alb.shop.data.TagEntity;
import graphql.kickstart.tools.GraphQLQueryResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.stream.Collectors;

@Controller
public class TagQuery implements GraphQLQueryResolver {

    private TagService tagService;

    @Autowired
    public TagQuery(TagService tagService) {
        this.tagService = tagService;
    }

    public TagEntity readTag(String id) {
        return this.tagService.readById(id);
    }

    public List<TagEntity> findTagByArticlesInShoppingCarts(@RequestParam String q) {
        if (!"in".equals(new LexicalAnalyzer().extractWithAssure(q, "shopping-carts"))) {
            throw new BadRequestException("q incorrect, expected in");
        }
        return this.tagService.findByArticlesInShoppingCarts()
                .collect(Collectors.toList());
    }

}
