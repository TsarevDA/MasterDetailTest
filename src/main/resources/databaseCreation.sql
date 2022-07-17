DROP DATABASE IF EXISTS master_detail_test_db;
CREATE DATABASE master_detail_test_db;
DROP ROLE IF EXISTS boss;
CREATE USER boss with password 'boss';
GRANT ALL PRIVILEGES ON DATABASE master_detail_test_db TO boss;