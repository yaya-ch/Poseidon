DROP SCHEMA IF EXISTS test_db;
CREATE SCHEMA test_db;
SET SCHEMA test_db;
DROP TABLE IF EXISTS bid_list;
CREATE TABLE bid_list(
    bid_list_id INT NOT NULL AUTO_INCREMENT,
    account VARCHAR,
    type VARCHAR,
    bid_quantity DOUBLE,
    ask_quantity DOUBLE,
    bid DOUBLE,
    ask DOUBLE,
    benchmark VARCHAR,
    bid_list_date TIMESTAMP,
    commentary VARCHAR,
    security VARCHAR,
    status VARCHAR,
    trader VARCHAR,
    book VARCHAR,
    creation_name VARCHAR,
    creation_date TIMESTAMP,
    revision_name VARCHAR,
    revision_date TIMESTAMP,
    deal_name VARCHAR,
    deal_type VARCHAR,
    source_list_id VARCHAR,
    side VARCHAR,
    PRIMARY KEY (bid_list_id)

);
INSERT INTO bid_list(bid_list_id, account, type, bid_quantity, ask_quantity, bid, ask,
                     benchmark, bid_list_date, commentary, security, status,
                     trader, book, creation_name, creation_date, revision_name, revision_date,
                     deal_name, deal_type, source_list_id, side)
VALUES ( 1, 'Account', 'Type', 100.0, 100.0, 200.0,
        200.0, 'Benchmark', '2020-11-15T00:11:22',
        'Commentary', 'Security', 'Status', 'Trader',
        'Book', 'Creation name', '2020-11-15T00:15:00',
        'Revision name', '2020-11-15T07:15:00', 'Deal name',
        'Deal type', '12', 'Side');

DROP TABLE IF EXISTS curve_point;
CREATE TABLE curve_point(
    curve_point_id INT NOT NULL AUTO_INCREMENT,
    curve_id INT NOT NULL,
    as_of_date TIMESTAMP,
    term DOUBLE NOT NULL,
    value DOUBLE NOT NULL,
    creation_date TIMESTAMP,
    PRIMARY KEY(curve_point_id)
);
INSERT INTO curve_point(curve_point_id, curve_id, as_of_date, term, value, creation_date)
VALUES ( 1, 22, '2020-11-16T00:11:22', 22.1, 22.2,
        '2020-11-15T00:15:00');

DROP TABLE IF EXISTS rating;
CREATE TABLE rating(
    rating_id INT NOT NULL AUTO_INCREMENT,
    moodys_rating VARCHAR NOT NULL ,
    s_and_p_rating VARCHAR NOT NULL,
    fitch_rating VARCHAR NOT NULL,
    order_number VARCHAR NOT NULL,
    PRIMARY KEY(rating_id)
);
INSERT INTO rating(rating_id, moodys_rating, s_and_p_rating, fitch_rating, order_number)
VALUES (5, 'Moody s Rating', 'S and P Rating',
        'Fitch', 12);

DROP TABLE IF EXISTS rule_name;
CREATE TABLE rule_name(
    rule_name_id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR NOT NULL,
    description VARCHAR NOT NULL,
    json VARCHAR NOT NULL,
    template VARCHAR NOT NULL,
    sql_str VARCHAR NOT NULL,
    sql_part VARCHAR NOT NULL,
    PRIMARY KEY(rule_name_id)
);
INSERT INTO rule_name(rule_name_id, name, description, json, template, sql_str, sql_part)
VALUES (12, 'Name', 'Description', 'json', 'template',
        'sql str', 'sql part');

DROP TABLE IF EXISTS trade;
CREATE TABLE trade(
    trade_id INT NOT NULL AUTO_INCREMENT,
    account VARCHAR NOT NULL,
    type VARCHAR NOT NULL,
    buy_quantity DOUBLE,
    sell_quantity DOUBLE,
    buy_price DOUBLE,
    sell_price DOUBLE,
    trade_date TIMESTAMP,
    security VARCHAR,
    status VARCHAR,
    trader VARCHAR,
    benchmark VARCHAR,
    book VARCHAR,
    creation_name VARCHAR,
    creation_date TIMESTAMP,
    revision_name VARCHAR,
    revision_date TIMESTAMP,
    deal_name VARCHAR,
    deal_type VARCHAR,
    source_list_id VARCHAR,
    side VARCHAR,
    PRIMARY KEY(trade_id)
);

INSERT INTO trade(trade_id, account, type, buy_quantity, sell_quantity, buy_price,
                  sell_price, trade_date, security, status, trader, benchmark, book,
                  creation_name, creation_date, revision_name, revision_date, deal_name,
                  deal_type, source_list_id, side)
VALUES (44, 'trade db account', 'trade db type', 100.0,
        100.0, 100.12, 150.55,'2020-11-27T03:11:22',
        'trade db security', 'trade db status', 'trade db trader',
        'trade db benchmark', 'trade db book', 'trade db creation name',
        '2020-11-27T03:15:22', 'trade db revision name',
        '2020-11-16T00:11:22', 'trade db deal name', 'trade db deal type',
        'trade db source list id', 'trade db side');