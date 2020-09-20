## Use to run mysql db docker image, optional if you're not using a local mysqldb
# docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql

# connect to mysql and run as root user
#Create Databases
CREATE DATABASE bbd_dev;
CREATE DATABASE bbd_prod;

#Create database service accounts
CREATE USER 'bbd_dev_user'@'localhost' IDENTIFIED BY 'bbd';
CREATE USER 'bbd_prod_user'@'localhost' IDENTIFIED BY 'bbd';
CREATE USER 'bbd_dev_user'@'%' IDENTIFIED BY 'bbd';
CREATE USER 'bbd_prod_user'@'%' IDENTIFIED BY 'bbd';

#Database grants
GRANT SELECT ON bbd_dev.* to 'bbd_dev_user'@'localhost';
GRANT INSERT ON bbd_dev.* to 'bbd_dev_user'@'localhost';
GRANT DELETE ON bbd_dev.* to 'bbd_dev_user'@'localhost';
GRANT UPDATE ON bbd_dev.* to 'bbd_dev_user'@'localhost';
GRANT SELECT ON bbd_prod.* to 'bbd_prod_user'@'localhost';
GRANT INSERT ON bbd_prod.* to 'bbd_prod_user'@'localhost';
GRANT DELETE ON bbd_prod.* to 'bbd_prod_user'@'localhost';
GRANT UPDATE ON bbd_prod.* to 'bbd_prod_user'@'localhost';
GRANT SELECT ON bbd_dev.* to 'bbd_dev_user'@'%';
GRANT INSERT ON bbd_dev.* to 'bbd_dev_user'@'%';
GRANT DELETE ON bbd_dev.* to 'bbd_dev_user'@'%';
GRANT UPDATE ON bbd_dev.* to 'bbd_dev_user'@'%';
GRANT SELECT ON bbd_prod.* to 'bbd_prod_user'@'%';
GRANT INSERT ON bbd_prod.* to 'bbd_prod_user'@'%';
GRANT DELETE ON bbd_prod.* to 'bbd_prod_user'@'%';
GRANT UPDATE ON bbd_prod.* to 'bbd_prod_user'@'%';