docker-compose up -d

docker exec -it postgres_ecommercedb_order psql -U ecommerce_user -W ecommercedb
docker exec -it postgres_ecommercedb_shipment psql -U ecommerce_user -W ecommercedb

\? list all the commands
\l list databases
\conninfo display information about current connection
\c [DBNAME] connect to new database, e.g., \c template1
\dt list tables of the public schema
\dt <schema-name>.* list tables of certain schema, e.g., \dt public.*
\dt *.* list tables of all schemas
Then you can run SQL statements, e.g., SELECT * FROM my_table;(Note: a statement must be terminated with semicolon ;)
\q quit psql

select * from ordersch.databasechangelog;
select * from ordersch.order;

select * from shipmentsch.databasechangelog;
select * from shipmentsch.shipment;
