package com.example.cosme.Repository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.example.cosme.domain.Cosmetic;

@Repository
public class CosmeticRepository {

    @Autowired
    private NamedParameterJdbcTemplate template;

    private static final RowMapper<Cosmetic> COSMETIC_ROW_MAPPER = (rs, i) -> {
        Cosmetic cosmetic = new Cosmetic();
        cosmetic.setId(rs.getInt("id"));
        cosmetic.setName(rs.getString("name"));
        cosmetic.setBrandId(rs.getInt("brand_id"));
        cosmetic.setBrandName(rs.getString("brand_name"));
        cosmetic.setCategoryId(rs.getInt("category_id"));
        cosmetic.setCategoryName(rs.getString("category_name"));
        cosmetic.setPriceType(rs.getInt("price_type"));
        cosmetic.setDeletedFlag(rs.getInt("deleted_flag"));
        cosmetic.setRepeatFlag((Integer) rs.getObject("repeat_flag"));

        return cosmetic;
    };

    // 所持中コスメを全件取得
    public List<Cosmetic> findAll() {
        String sql = """
                    SELECT
                    c.id,
                    c.name,
                    c.brand_id,
                    b.name AS brand_name,
                    c.category_id,
                    cc.name AS category_name,
                    c.price_type,
                    c.deleted_flag,
                    c.repeat_flag
                FROM cosmetics c
                INNER JOIN brands b
                    ON c.brand_id = b.id
                INNER JOIN cosmetic_categories cc
                    ON c.category_id = cc.id
                WHERE c.deleted_flag = 0
                ORDER BY c.id DESC
                """;

        return template.query(sql, COSMETIC_ROW_MAPPER);
    }

    // 名前で曖昧検索
    public List<Cosmetic> findByName(String name) {
        String sql = """
                SELECT
                    c.id,
                    c.name,
                    c.brand_id,
                    b.name AS brand_name,
                    c.category_id,
                    cc.name AS category_name,
                    c.price_type,
                    c.deleted_flag,
                    c.repeat_flag
                FROM cosmetics c
                INNER JOIN brands b
                    ON c.brand_id = b.id
                INNER JOIN cosmetic_categories cc
                    ON c.category_id = cc.id
                WHERE c.deleted_flag = 0
                  AND c.name LIKE :name
                ORDER BY c.id DESC
                """;

        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("name", "%" + name + "%");

        return template.query(sql, param, COSMETIC_ROW_MAPPER);
    }

    // ブランドIDで検索
    public List<Cosmetic> findByBrandId(Integer brandId) {
        String sql = """
                SELECT
                    c.id,
                    c.name,
                    c.brand_id,
                    b.name AS brand_name,
                    c.category_id,
                    cc.name AS category_name,
                    c.price_type,
                    c.deleted_flag,
                    c.repeat_flag
                FROM cosmetics c
                INNER JOIN brands b
                    ON c.brand_id = b.id
                INNER JOIN cosmetic_categories cc
                    ON c.category_id = cc.id
                WHERE c.deleted_flag = 0
                  AND c.brand_id = :brandId
                ORDER BY c.id DESC
                """;

        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("brandId", brandId);
        return template.query(sql, param, COSMETIC_ROW_MAPPER);
    }

    // カテゴリIDで検索
    public List<Cosmetic> findByCategoryId(Integer categoryId) {
        String sql = """
                SELECT
                    c.id,
                    c.name,
                    c.brand_id,
                    b.name AS brand_name,
                    c.category_id,
                    cc.name AS category_name,
                    c.price_type,
                    c.deleted_flag,
                    c.repeat_flag
                FROM cosmetics c
                INNER JOIN brands b
                    ON c.brand_id = b.id
                INNER JOIN cosmetic_categories cc
                    ON c.category_id = cc.id
                WHERE c.deleted_flag = 0
                  AND c.category_id = :categoryId
                ORDER BY c.id DESC
                """;

        MapSqlParameterSource param =

                new MapSqlParameterSource()
                        .addValue("categoryId", categoryId);

        return template.query(sql, param, COSMETIC_ROW_MAPPER);
    }

    // プチプラ / ハイブラで検索
    public List<Cosmetic> findByPriceType(Integer priceType) {
        String sql = """
                SELECT
                    c.id,
                    c.name,
                    c.brand_id,
                    b.name AS brand_name,
                    c.category_id,
                    cc.name AS category_name,
                    c.price_type,
                    c.deleted_flag,
                    c.repeat_flag
                FROM cosmetics c
                INNER JOIN brands b
                    ON c.brand_id = b.id
                INNER JOIN cosmetic_categories cc
                    ON c.category_id = cc.id
                WHERE c.deleted_flag = 0
                  AND c.price_type = :priceType
                ORDER BY c.id DESC
                """;

        MapSqlParameterSource param =
                new MapSqlParameterSource()
                        .addValue("priceType", priceType);

        return template.query(sql, param, COSMETIC_ROW_MAPPER);
    }

    // 新規登録
    public void insert(Cosmetic cosmetic) {
        String sql = """
                INSERT INTO cosmetics
                (
                    name,
                    brand_id,
                    category_id,
                    price_type,
                    deleted_flag,
                    repeat_flag
                )

                VALUES
                (
                    :name,
                    :brandId,
                    :categoryId,
                    :priceType,
                    0,
                    NULL
                )

                """;
        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("name", cosmetic.getName())
                .addValue("brandId", cosmetic.getBrandId())
                .addValue("categoryId", cosmetic.getCategoryId())
                .addValue("priceType", cosmetic.getPriceType());

        template.update(sql, param);

    }

    // 論理削除
    public void logicalDelete(Integer id,Integer repeatFlag) {
        String sql = """
                UPDATE cosmetics
                SET deleted_flag = 1,
                    repeat_flag = :repeatFlag
                WHERE id = :id
                """;

        MapSqlParameterSource param = new MapSqlParameterSource()
                .addValue("id", id)
                .addValue("repeatFlag", repeatFlag);
        template.update(sql, param);
    }

    // 削除済みコスメ一覧
    public List<Cosmetic> findDeleted() {
        String sql = """
                SELECT
                    c.id,
                    c.name,
                    c.brand_id,
                    b.name AS brand_name,
                    c.category_id,
                    cc.name AS category_name,
                    c.price_type,
                    c.deleted_flag,
                    c.repeat_flag
                FROM cosmetics c
                INNER JOIN brands b
                    ON c.brand_id = b.id
                INNER JOIN cosmetic_categories cc
                    ON c.category_id = cc.id
                WHERE c.deleted_flag = 1
                ORDER BY c.id DESC
                """;
                
        return template.query(sql, COSMETIC_ROW_MAPPER);
    }
}
