CREATE TABLE ledger_user (
    id BIGINT NOT NULL AUTO_INCREMENT,
    email VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL,
    pass_word VARCHAR(255),
    outh_provider VARCHAR(64) NOT NULL,
    image_url VARCHAR(2083),

    PRIMARY KEY (id)
);

CREATE TABLE translated_name (
    id BIGINT NOT NULL,
    english VARCHAR(255),
    korean VARCHAR(255),

    PRIMARY KEY (id)
);

CREATE TABLE account_category (
    main BIGINT NOT NULL,
    sub BIGINT NOT NULL,

    PRIMARY KEY (sub),

    FOREIGN KEY (main) 
        REFERENCES translated_name(id)
        ON UPDATE RESTRICT ON DELETE RESTRICT,

    FOREIGN KEY (sub) 
        REFERENCES translated_name(id)
        ON UPDATE RESTRICT ON DELETE RESTRICT,
);

CREATE TABLE official_account (
    code BIGINT NOT NULL,
    category BIGINT NOT NULL,
    account_name BIGINT NOT NULL,

    PRIMARY KEY (code),

    FOREIGN KEY (category) 
        REFERENCES account_category(id)
        ON UPDATE RESTRICT ON DELETE RESTRICT,

    FOREIGN KEY (account_name) 
        REFERENCES translated_name(id)
        ON UPDATE RESTRICT ON DELETE RESTRICT,
)

CREATE TABLE user_defined_account (
    id BIGINT NOT NULL AUTO_INCREMENT,
    official_account BIGINT NOT NULL,
    belong_to BIGINT NOT NULL,
    account_name VARCHAR(255) NOT NULL

    PRIMARY KEY (id),

    FOREIGN KEY (official_account)
        REFERENCES official_account(code)
        ON UPDATE RESTRICT ON DELETE RESTRICT,
    
    FOREIGN KEY (belong_to)
        REFERENCES ledger_user(id)
        ON UPDATE RESTRICT ON DELETE RESTRICT,
);
