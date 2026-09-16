package com.example.cosme.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.cosme.domain.Category;

@Repository
public class CategoryRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    public CategoryRepository(NamedParameterJdbcTemplate template) {
        this.template = template;
    }

    private static final RowMapper<Category> CATEGORY_ROW_MAPPER = (rs, i) -> {
        Category category = new Category();
        category.setId(rs.getInt("id"));
        category.setName(rs.getString("name"));
        return category;
    };

    public List<Category> findAll() {
        String sql = """
                SELECT id,name
                FROM cosmetic_categories
                ORDER BY id
                """;
                
        return template.query(sql, CATEGORY_ROW_MAPPER);
    }
}
