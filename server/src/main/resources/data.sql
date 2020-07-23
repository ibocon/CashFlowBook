INSERT INTO translated_name (id, english, korean) 
VALUES  -- Assets 자산
        (10000, 'Assets', '자산'),
            (11000, 'Quick Assets', '당좌자산'),
                (11100, 'Cash', '현금'),
                (11200, 'Current Deposits', '당좌예금'),
                (11300, 'Deposits on Demand', '보통예금'),
            (12000, 'Inventories', '재고자산'),
            (13000, 'Investments', '투자자산'),
            (14000, 'Property and Equipment', '유형자산'),
            (15000, 'Intangible Assets', '무형자산'),
            (16000, 'Other Assets', '기타비유동자산'),
        -- Capital 자본
        (20000, 'Capital', '자본'),

        (30000, 'Liability', '부채'),
        (40000, 'Revenue', '수익'),
        (50000, 'Expense', '비용')
;

INSERT INTO account_category (id, main, sub) 
VALUES  (11000, 10000, 11000),      -- 자산, 당좌자산
        (12000, 10000, 12000),      -- 자산, 재고자산
        (13000, 10000, 13000),      -- 자산, 투자자산
        (14000, 10000, 14000),      -- 자산, 유형자산
        (15000, 10000, 15000),     -- 자산, 무형자산
        (16000, 10000, 16000)      -- 자산, 기타비유동자산
;

INSERT INTO official_account (code, category, account_name)
VALUES  (1, 101, 11000, 11100),
        (2, 102, 11000, 11200),
        (3, 103, 11000, 11300)
;