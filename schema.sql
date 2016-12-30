CREATE TABLE IF NOT EXISTS Account (id INTEGER PRIMARY KEY, first_name STRING, last_name STRING)
CREATE TABLE IF NOT EXISTS Address (id INTEGER PRIMARY KEY, address STRING, city STRING, state STRING,
                                    account_id INTEGER NOT NULL, FOREIGN KEY(account_id) REFERENCES Account(id))
CREATE TABLE IF NOT EXISTS Credit (id INTEGER PRIMARY KEY, type STRING, num STRING, exp_date STRING,
                                   account_id INTEGER NOT NULL, FOREIGN KEY(account_id) REFERENCES Account(id))

INSERT INTO Account (first_name, last_name) VALUES('Ahmed', 'Emad')
INSERT INTO Address (address, city, state) VALUES('35 Salah Zohny', 'Alexandria', 'Alexandria', 1)
INSERT INTO Credit (type, num, exp_date) VALUES('Visa', '12345678', '10/18', 1)
