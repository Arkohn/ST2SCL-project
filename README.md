# ST2SCL-project

docker exec --interactive --tty st2scl-project_kafka_1 kafka-topics --bootstrap-server kafka:9092 --create --topic quickstart

docker exec --interactive --tty st2scl-project_kafka_1 kafka-console-producer --bootstrap-server kafka:9092 --topic quickstart

docker exec --interactive --tty st2scl-project_kafka_1 kafka-console-consumer --bootstrap-server kafka:9092 --topic quickstart --from-beginning

(In another console while previous one is still running)

docker exec --interactive --tty st2scl-project_kafka_1 kafka-console-producer --bootstrap-server kafka:9092 --topic quickstart
