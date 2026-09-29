-- =========================
-- ブランドテーブル
-- =========================
CREATE TABLE brands (
id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
name VARCHAR(100) NOT NULL UNIQUE
);


-- =========================
-- コスメカテゴリテーブル
-- =========================
CREATE TABLE cosmetic_categories (
id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
name VARCHAR(50) NOT NULL UNIQUE
);


-- =========================
-- コスメテーブル
-- =========================
CREATE TABLE cosmetics (
id INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
name VARCHAR(150) NOT NULL,

brand_id INT NOT NULL,
category_id INT NOT NULL,

-- 0 = プチプラ
-- 1 = ハイブランド
price_type SMALLINT NOT NULL,

-- 0 = 所持中
-- 1 = 削除済み
deleted_flag SMALLINT NOT NULL DEFAULT 0,

-- NULL = まだ所持中なので未判定
-- 0 = リピなし
-- 1 = リピあり
repeat_flag SMALLINT NULL,

FOREIGN KEY (brand_id)
REFERENCES brands(id),

FOREIGN KEY (category_id)
REFERENCES cosmetic_categories(id)
);


-- =========================
-- ブランド初期データ
-- =========================
INSERT INTO brands (name) VALUES
('CANMAKE'),
('CEZANNE'),
('DECORTÉ'),
('SUQQU'),
('CHANEL');


-- =========================
-- コスメカテゴリ初期データ
-- =========================
INSERT INTO cosmetic_categories (name) VALUES
('アイシャドウ'),
('リップ'),
('チーク'),
('ファンデーション'),
('下地'),
('マスカラ');


-- =========================
-- コスメ初期データ
-- =========================
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
'むちぷるティント 02 モモ',
1,
2,
0,
0,
NULL
),
(
'ベージュトーンアイシャドウ',
2,
1,
0,
0,
NULL
),
(
'アイグロウジェム スキンシャドウ 11G milk azuki',
3,
1,
1,
0,
NULL
),
(
'シグニチャー カラー アイズ 02 陽香色',
4,
1,
1,
0,
NULL
),
(
'ルージュ ココ ボーム 918 マイ ローズ',
5,
2,
1,
0,
NULL
);

