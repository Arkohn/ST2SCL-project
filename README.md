# ST2SCL-project

First, in a console, run :
  > docker exec --interactive --tty kafka-broker kafka-topics --bootstrap-server kafka:9092 --create --topic quickstart
 to start the bootstrap topic

Then run and LET run :
  > docker exec --interactive --tty st2scl-project_kafka_1 kafka-console-consumer --bootstrap-server kafka:9092 --topic quickstart --from-beginning

In another console while previous one is still running, run :
  > docker exec --interactive --tty st2scl-project_kafka_1 kafka-console-producer --bootstrap-server kafka:9092 --topic quickstart
