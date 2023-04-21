# ST2SCL-project

JRE set to Default (14 - SDK)
(You may need to Graddle bootjar)

## First, before all, open Docker Desktop

Then, in the folder where this README is, open a terminal and run :
  > docker-compose up --build -d

Then run and LET run :
  > docker exec --interactive --tty kafka-broker kafka-console-consumer --bootstrap-server broker:9092 --topic quickstart --from-beginning

Look ! You should see the cars the app added itself to the DB !

Now you can close using :
  > docker-compose down

add '-v' at the end if you want to also reset the DB, otherwise you will see that more cars get added the next time you compose up
