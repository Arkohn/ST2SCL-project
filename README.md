# ST2SCL-project

First, before all, open Docker Desktop
Then, in the folder where this README is, open a terminal and run :
  > docker-compose up -d

Then run and LET run :
  > docker exec --interactive --tty kafka-broker kafka-console-consumer --bootstrap-server kafka:9092 --topic quickstart --from-beginning

In another console while the previous one is still running, run :



##TEMP FOR DEV

In a console, run :
  > docker exec --interactive --tty kafka-broker kafka-topics --bootstrap-server kafka:9092 --create --topic quickstart

to start the bootstrap topic

Then run and LET run :
  > docker exec --interactive --tty kafka-broker kafka-console-consumer --bootstrap-server kafka:9092 --topic quickstart --from-beginning

In another console while the previous one is still running, run :
  > docker exec --interactive --tty kafka-broker kafka-console-producer --bootstrap-server kafka:9092 --topic quickstart

In that second console, you can write whatever you want, it will show on the first one when using the 'enter' key
