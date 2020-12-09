curl -i -X POST -H "Accept:application/json" -H "Content-Type:application/json" 127.0.0.1:8083/connectors/ -d '
{ "name": "debezium-test-postgres-order-connector",
  "config": {
        "connector.class": "io.debezium.connector.postgresql.PostgresConnector",
        "tasks.max": "1",
        "group.id": "test",
        "database.hostname": "postgres_order",
        "database.port": "5432",
        "database.user": "ecommerce_user",
        "database.password": "ecommerce_password",
        "database.dbname" : "ecommercedb",
        "database.server.name": "debezium",
        "schema.whitelist": "ordersch",
        "table.whitelist" : "ordersch.outbox",
        "heartbeat.interval.ms": "1000",
        "tombstones.on.delete" : "false",
        "transforms" : "outbox",
        "database.history.kafka.bootstrap.servers": "kafka:9092",
        "key.converter": "org.apache.kafka.connect.json.JsonConverter",
        "key.converter.schemas.enable": "false",
        "value.converter": "org.apache.kafka.connect.json.JsonConverter",
        "value.converter.schemas.enable": "false",
        "plugin.name": "pgoutput"
	}
}'
#If 127.0.0.1 doesn't work, try your static ip.
