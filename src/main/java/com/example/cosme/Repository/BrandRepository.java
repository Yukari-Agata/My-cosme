package com.example.cosme.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.cosme.domain.Brand;

@Repository
public class BrandRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Brand> BRAND_ROW_MAPPER = (rs, i) -> {
        Brand brand = new Brand();
        brand.setId(rs.getInt("id"));
        brand.setName(rs.getString("name"));
        return brand;
    };

    public List<Brand> findAll() {
        String sql = """
                SELECT
                id,
                name
                FROM brands
                ORDER BY id
                """;
        return template.query(sql, BRAND_ROW_MAPPER);
    }
}
