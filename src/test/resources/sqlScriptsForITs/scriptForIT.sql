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
                     trader, book, creation_name, creation_date, revision_name, revision_date, deal_name, deal_type, source_list_id, side)
VALUES ( 1, 'Account', 'Type', 100.0, 100.0, 200.0, 200.0,
        'Benchmark', '2020-11-15T00:11:22', 'Commentary', 'Security', 'Status',
        'Trader', 'Book', 'Creation name', '2020-11-15T00:15:00',
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
VALUES ( 1, 22, '2020-11-16T00:11:22', 22.1, 22.2, '2020-11-15T00:15:00')