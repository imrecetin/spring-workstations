echo -e "\n Stopping containers ....."
docker stop $(docker ps -a | grep postgres_order | cut -d ' ' -f 1)
docker stop $(docker ps -a | grep postgres_shipment | cut -d ' ' -f 1)
docker stop $(docker ps -a | grep kafka | cut -d ' ' -f 1)
docker stop $(docker ps -a | grep zookeeper | cut -d ' ' -f 1)
docker stop $(docker ps -a | grep connect | cut -d ' ' -f 1)
docker rm postgres_order
docker rm postgres_shipment
docker rm kafka
docker rm zookeeper
docker rm debezium-connect
echo -e "\n All Containers stopped."
