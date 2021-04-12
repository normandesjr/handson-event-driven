## 1º Hands-On

### Iniciando o Broker

Vamos iniciar o Kafka/Zookeeper com o arquivo _docker-compose.yml_.

````
docker-compose up
````

### Criando o tópico

Vamos criar o primeiro tópico "first_topic" com o comando:

````
kafka-topics.sh --bootstrap-server localhost:9092 --topic first_topic --create --partitions 1 --replication-factor 1
````

Mais pra frente vamos entender melhor o que são as propriedades _partitions_ e _replication-factor_

Se quiser conferir que o tópico foi criado, use o comando abaixo:

````
kafka-topics.sh --bootstrap-server localhost:9092 --list
````

### Produzindo mensagens para o tópico

Inicie o produtor em uma janela do terminal:

````
kafka-console-producer.sh --broker-list localhost:9092 --topic first_topic
````

O terminal irá parar esperando você inserir algum dado a ser enviado para o tópico _first_topic_. Digite algumas strings e vá pressionando _enter_.

### Consumindo mensagens

Para consumir, em outro terminal use o comando:

````
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic first_topic --from-beginning
````
