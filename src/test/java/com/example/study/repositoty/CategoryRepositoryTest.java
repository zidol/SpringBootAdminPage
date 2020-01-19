package com.example.study.repositoty;

import com.example.study.DemoApplication;
import com.example.study.DemoApplicationTests;
import com.example.study.model.entity.Category;
import com.example.study.repository.CategoryRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Optional;

public class CategoryRepositoryTest extends DemoApplicationTests {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void create() {
        String type ="COMPUTER";
        String title = "컴퓨터";
        LocalDateTime createdAt = LocalDateTime.now();
        String createdBy = "AdminServer";

        Category category = new Category();
        category.setType(type);
        category.setTitle(title);
        category.setCreatedAt(createdAt);
        category.setCreatedBy(createdBy);

        Category newCategory = categoryRepository.save(category);

        Assert.assertNotNull(newCategory);
        Assert.assertEquals(newCategory.getType(), type);
        Assert.assertEquals(newCategory.getTitle(), title);
    }

    @Test
    public void read() {

        String type ="COMPUTER";
        Optional<Category> optionalCategory = categoryRepository.findByType(type);
        //select * from category where type = 'COMPUTER';
        optionalCategory.ifPresent(c -> {
            System.out.println(c.getId());
            System.out.println(c.getType());
            System.out.println(c.getTitle());
        });
    }

}
