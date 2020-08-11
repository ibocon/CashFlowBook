-- #region TranslatedString

INSERT INTO translated_string (english, korean) VALUES 
        ('Assets', '자산'),
            ('Quick Assets', '당좌자산'),
                ('Cash', '현금'),
                ('Current Deposits', '당좌예금'),
                ('Deposits on Demand', '보통예금'),
            ('Inventories', '재고자산'),
            ('Investments', '투자자산'),
            ('Property and Equipment', '유형자산'),
            ('Intangible Assets', '무형자산'),
            ('Other Assets', '기타비유동자산'),
        ('Capital', '자본'),
        ('Liability', '부채'),
        ('Revenue', '수익'),
        ('Expense', '비용')
;
-- #endregion

-- #region AccountCategory
INSERT INTO account_category (main_id, sub_id) VALUES
    ((SELECT id FROM translated_string WHERE korean = '자산'), (SELECT id FROM translated_string WHERE korean = '당좌자산')),
    ((SELECT id FROM translated_string WHERE korean = '자산'), (SELECT id FROM translated_string WHERE korean = '재고자산')),
    ((SELECT id FROM translated_string WHERE korean = '자산'), (SELECT id FROM translated_string WHERE korean = '투자자산')),
    ((SELECT id FROM translated_string WHERE korean = '자산'), (SELECT id FROM translated_string WHERE korean = '유형자산')),
    ((SELECT id FROM translated_string WHERE korean = '자산'), (SELECT id FROM translated_string WHERE korean = '무형자산')),
    ((SELECT id FROM translated_string WHERE korean = '자산'), (SELECT id FROM translated_string WHERE korean = '기타비유동자산'))
;
-- #endregion

-- #region OfficialAccount
INSERT INTO official_account (code, category_id, account_name_id) VALUES
    -- #region 당좌자산
    (
        101,
        (SELECT id FROM account_category WHERE sub_id IN (SELECT id FROM translated_string WHERE korean = '당좌자산')), 
        (SELECT id FROM translated_string WHERE korean = '현금')
    ),
    (
        102,
        (SELECT id FROM account_category WHERE sub_id IN (SELECT id FROM translated_string WHERE korean = '당좌자산')), 
        (SELECT id FROM translated_string WHERE korean = '당좌예금')
    ),
    (
        103,
        (SELECT id FROM account_category WHERE sub_id IN (SELECT id FROM translated_string WHERE korean = '당좌자산')), 
        (SELECT id FROM translated_string WHERE korean = '보통예금')
    )
    -- #endregion
;
-- #endregion