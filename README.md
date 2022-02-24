# kafka-streams-processing
This is an example of a Java Spring boot Kafka Streams project.
It use functional/streams paradigm

The project is a multi-module maven and is composed by 4 components:
```
model-library     --> Common Object models library shared by all modules
kstream-producer  --> Application that produce on a topic
kstream-processor --> Application that process from a topic to other topics
kstream-consumer  --> Application that consume from all topics
```
## Requirements
- Java 11 + Maven 3.8.x
- Kafka installed and running in localhost:9092 (Follow steps 1, 2 of this <a href="https://kafka.apache.org/quickstart">link</a>)

### Main branch example
The Producer builds Order objects and publishes them on "orders" topic.

The Processor filters the Order objects, picking from "orders" topic, by Destination, calculates the appropriate taxes and
addresses to one of the following topic: "orders-italy-taxed", "orders-french-taxed", "orders-spain-taxed".

The consumer picks all messages from each "order-<country>-taxed" topic and simulates an email sent to the appropriate courier.

