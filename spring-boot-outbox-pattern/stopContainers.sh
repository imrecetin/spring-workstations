echo -e "\n Stopping containers ....."
docker stop $(docker ps -a | grep postgres_ecommercedb_order | cut -d ' ' -f 1)
docker stop $(docker ps -a | grep postgres_ecommercedb_shipment | cut -d ' ' -f 1)
docker stop $(docker ps -a | grep kafka | cut -d ' ' -f 1)
docker stop $(docker ps -a | grep zookeeper | cut -d ' ' -f 1)
docker stop $(docker ps -a | grep connect | cut -d ' ' -f 1)
docker rm postgres_ecommercedb_order
docker rm postgres_ecommercedb_shipment
docker rm kafka
docker rm zookeeper
docker rm debezium-connect
echo -e "\n All Containers stopped."
