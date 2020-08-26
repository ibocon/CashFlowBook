-- #region TranslatedString
-- https://www.ifrs.org/issued-standards/ifrs-taxonomy/#annual-taxonomies
INSERT INTO translated_string (korean) VALUES
('재무제표'),
    ('재무상태표'),
        ('자산'),
            ('유동자산'),
                ('현금 및 현금성 자산'),
                    ('현금'),
        ('자본'),
            ('자본금'),
        ('부채'),
            ('비유동부채'),
                ('장기매입채무 및 기타비유동채무'),
                    ('장기차입금'),
    ('손익계산서'),
        ('수익'),
            ('용역의 제공으로 인한 수익'),
                ('용역매출액'),
        ('매출원가'),
            ('용억의 제공으로 인한 수익에 대한 매출원가'),
                ('용역매출원가')
;
-- #endregion

-- #region RootAccountCategory
INSERT INTO root_account_category (standard, document, is_debit, name) VALUES

-- 재무상태표
('K_IFRS', (SELECT id FROM translated_string WHERE korean = '재무상태표'), TRUE, (SELECT id FROM translated_string WHERE korean = '자산')),
('K_IFRS', (SELECT id FROM translated_string WHERE korean = '재무상태표'), FALSE, (SELECT id FROM translated_string WHERE korean = '자본')),
('K_IFRS', (SELECT id FROM translated_string WHERE korean = '재무상태표'), FALSE, (SELECT id FROM translated_string WHERE korean = '부채')),

-- 손익계산서
('K_IFRS', (SELECT id FROM translated_string WHERE korean = '손익계산서'), FALSE, (SELECT id FROM translated_string WHERE korean = '수익')),
('K_IFRS', (SELECT id FROM translated_string WHERE korean = '손익계산서'), FALSE, (SELECT id FROM translated_string WHERE korean = '매출원가'))

;

-- #endregion

-- #region AccountCategory
INSERT INTO account_category (path, name) VALUES

-- TODO 2020.08.26 여기서부터 작업하자!
((SELECT id FROM translated_string WHERE korean = '자산'), (SELECT id FROM translated_string WHERE korean = '유동자산')),
    ((SELECT id FROM translated_string WHERE korean = '유동자산'), (SELECT id FROM translated_string WHERE korean = '현금 및 현금성 자산')),

    ((SELECT id FROM translated_string WHERE korean = '재무상태표'), (SELECT id FROM translated_string WHERE korean = '부채')),
        ((SELECT id FROM translated_string WHERE korean = '부채'), (SELECT id FROM translated_string WHERE korean = '비유동부채')),
            ((SELECT id FROM translated_string WHERE korean = '비유동부채'), (SELECT id FROM translated_string WHERE korean = '장기매입채무 및 기타비유동채무')),

((SELECT id FROM translated_string WHERE korean = '재무제표'), (SELECT id FROM translated_string WHERE korean = '손익계산서')),
    ((SELECT id FROM translated_string WHERE korean = '손익계산서'), (SELECT id FROM translated_string WHERE korean = '수익')),
        ((SELECT id FROM translated_string WHERE korean = '수익'), (SELECT id FROM translated_string WHERE korean = '용역의 제공으로 인한 수익')),

    ((SELECT id FROM translated_string WHERE korean = '손익계산서'), (SELECT id FROM translated_string WHERE korean = '매출원가')),
        ((SELECT id FROM translated_string WHERE korean = '매출원가'), (SELECT id FROM translated_string WHERE korean = '용억의 제공으로 인한 수익에 대한 매출원가'))
;
-- #endregion

-- #region OfficialAccount
INSERT INTO official_account (category_id, account_name_id) VALUES
    (
        (SELECT id FROM account_category WHERE child_id IN (SELECT id FROM translated_string WHERE korean = '현금 및 현금성 자산')), 
        (SELECT id FROM translated_string WHERE korean = '현금')
    ),
    (
        (SELECT id FROM account_category WHERE child_id IN (SELECT id FROM translated_string WHERE korean = '자본')), 
        (SELECT id FROM translated_string WHERE korean = '자본금')
    ),
    (
        (SELECT id FROM account_category WHERE child_id IN (SELECT id FROM translated_string WHERE korean = '장기매입채무 및 기타비유동채무')), 
        (SELECT id FROM translated_string WHERE korean = '장기차입금')
    ),
    (
        (SELECT id FROM account_category WHERE child_id IN (SELECT id FROM translated_string WHERE korean = '용역의 제공으로 인한 수익')), 
        (SELECT id FROM translated_string WHERE korean = '용역매출액')
    ),
    (
        (SELECT id FROM account_category WHERE child_id IN (SELECT id FROM translated_string WHERE korean = '용억의 제공으로 인한 수익에 대한 매출원가')), 
        (SELECT id FROM translated_string WHERE korean = '용역매출원가')
    )
;
-- #endregion