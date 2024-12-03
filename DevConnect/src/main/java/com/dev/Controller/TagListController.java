package com.dev.Controller;

import com.dev.Dao.TagDAO;
import com.dev.Model.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class TagListController {

    private final TagDAO tagDAO;

    @GetMapping("/tags")
    public String index(Model model) {
        List<Tag> tags = tagDAO.findAll().stream().filter(tag -> !tag.getArticles().isEmpty()).toList();
        model.addAttribute("tags", tags);
        model.addAttribute("tagspopular", tags.subList(0, Math.min(10, tags.size())).stream().sorted((o1, o2) -> Integer.compare(o2.getArticles().size(), o1.getArticles().size())).toList());
        return "tag/taglist";
    }

    @GetMapping("/tags/{name}")
    public String tag(@PathVariable String name, Model model) {
        Optional<Tag> optionalTag = tagDAO.findAll().stream().filter(t -> t.getTagByName().equals(name)).findFirst();
        if (optionalTag.isPresent()) {
            Tag tag = optionalTag.get();
            tag.setArticles(tag.getArticles().stream().sorted((o1, o2) -> o2.getCreateat().compareTo(o1.getCreateat())).toList());
            model.addAttribute("tag", tag);
        } else {
            return "redirect:/tags";
        }
        return "tag/each";
    }

}
