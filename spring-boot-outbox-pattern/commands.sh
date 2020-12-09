docker-compose up -d
docker-compose up --build -d
docker-compose down

docker exec -it postgres_ecommercedb_order /bin/bash
docker exec -it postgres_ecommercedb_shipment /bin/bash
psql -h localhost -p 5432 -U ecommerce_user -d ecommerce_password

docker exec -it postgres_ecommercedb_order psql -U ecommerce_user -W ecommercedb
docker exec -it postgres_ecommercedb_shipment psql -U ecommerce_user -W ecommercedb

docker exec -it kafka
# Make sure you are in the Kafka directory before running this command.
bin/kafka-topics.sh --list --bootstrap-server localhost:9092


\? list all the commands
\l list databases
\conninfo display information about current connection
\c [DBNAME] connect to new database, e.g., \c template1
\dt list tables of the public schema
\dt <schema-name>.* list tables of certain schema, e.g., \dt public.*
\dt *.* list tables of all schemas
Then you can run SQL statements, e.g., SELECT * FROM my_table;(Note: a statement must be terminated with semicolon ;)
\q quit psql

--- Configure transaction Logs (WAL) inside container ---
https://medium.com/@ssiddhant3030/log-postgres-running-queries-docker-c5d1d9037833
https://stackoverflow.com/questions/57015003/log-all-queries-in-the-official-postgres-docker-image
postgres=# SHOW config_file;
postgres=# SHOW data_directory;

select * from ordersch.databasechangelog;
select * from ordersch.order;
select * from ordersch.outbox;

select * from shipmentsch.databasechangelog;
select * from shipmentsch.shipment;
