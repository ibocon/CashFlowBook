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
    id BIGINT NOT NULL AUTO_INCREMENT,
    english VARCHAR(255),
    korean VARCHAR(255),

    PRIMARY KEY (id)
);

CREATE TABLE account_category (
    id BIGINT NOT NULL AUTO_INCREMENT,
    main BIGINT NOT NULL,
    sub BIGINT NOT NULL,

    PRIMARY KEY (sub),

    FOREIGN KEY (main) REFERENCES translated_name(id),
    FOREIGN KEY (sub) REFERENCES translated_name(id)
);

CREATE TABLE official_account (
    code BIGINT NOT NULL,
    category BIGINT NOT NULL,
    account_name BIGINT NOT NULL,

    PRIMARY KEY (code),

    FOREIGN KEY (category) REFERENCES account_category(id),
    FOREIGN KEY (account_name) REFERENCES translated_name(id)
);

CREATE TABLE user_defined_account (
    id BIGINT NOT NULL AUTO_INCREMENT,
    official_account BIGINT NOT NULL,
    belong_to BIGINT NOT NULL,
    account_name VARCHAR(255) NOT NULL,

    PRIMARY KEY (id),

    FOREIGN KEY (official_account) REFERENCES official_account(code),
    FOREIGN KEY (belong_to) REFERENCES ledger_user(id)
);
