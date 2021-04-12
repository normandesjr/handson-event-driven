## 2º Hands-On

### Criando o tópico

````
kafka-topics.sh --bootstrap-server localhost:9092 --create --topic recharge_realized --partitions 1 --replication-factor 1
````

### Entendendo sobre partições

Inicie novas aplicações consumidoras e produza novas mensagens, verá que sempre a mesma instância consumirá as mensagens.


Apague o tópico de recarga e vamos criar com 3 partições:

````
kafka-topics.sh --bootstrap-server localhost:9092 --topic recharge_realized --delete
````

````
kafka-topics.sh --bootstrap-server localhost:9092 --create --topic recharge_realized --partitions 3 --replication-factor 1
````

Inicie novamente o produtor e consumidores e observe o novo comportamento.

### Novo grupo

Caso uma nova aplicação inicie, o grupo muda, e portanto ela também consumirá as mensagens.

````
kafka-console-consumer.sh --bootstrap-server localhost:9092 --topic recharge_realized --group data-system
````