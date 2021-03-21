DROP DATABASE IF EXISTS db_sales;
CREATE DATABASE db_sales;
GRANT ALL ON db_sales.* TO 'lojaonline'@'%' IDENTIFIED BY 'lojaonline';
