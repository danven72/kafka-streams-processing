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

### How it works
First, pay attention to the correct dependencies in the pom.xml files.</br>
When you have @Bean annotated method in a @Configuration annotated class that return a Supplier<T> Functional interface, it is associated by spring boot to an output stream. In the application.yaml magic happens (see kstream-producer).<br/>
```
spring.cloud.stream:
  function:
    definition: <Supplier-method>
  bindings:
    <Supplier-method>-out-0:
      destination: <kafka-topic-name>
      content-type: application/json
```
The same things happens whe you have a @Bean methods annotated that return Function<K,T>. If K and T are KStream types, the application.yaml file binds all together (see kstream-processor)
```
spring.cloud.stream:
  function:
    definition: <Function-method1>;<Function-method2>
  bindings:
    <Function-method1>-in-0:
      destination: <kafka-topic-input>
      content-type: application/json
    <Function-method1>-out-0:
      destination: <kafka-topic-output>
      content-type: application/json
    <Function-method2>-in-0:
      destination: <kafka-topic-input>
      content-type: application/json
    <Function-method2>-out-0:
      destination: <kafka-topic-output>
      content-type: application/json
```
Likewise, if you have a method @Bean annotated that return a Consumer<KStream<K,T>> functional Interface, you can binds it to an Input Kakfa topic using application.yaml (see kstream-consumer).
```
spring.cloud.stream:
  function:
    definition: <Consumer-method1>;<Consumer-method2>
  bindings:
    <Consumer-method1>-in-0:
      destination: <kafka-topic-input>
      content-type: application/json
    <Consumer-method2>-in-0:
      destination: <kafka-topic-input>
      content-type: application/json
```


