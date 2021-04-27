## Demo com Kafka Connect e KSQL

Este demo simula uma aplicação de recarga de celulares onde iremos reagir em tempo real a cada recarga efetuada pelo cliente.

Vamos deixar alguns serviços configurados e depois simular o comportamento da recarga.

### Inicie o docker-compose.yml

````
docker-compose up -d
````

Esse comando irá iniciar vários serviços, por isso é importante que você garanta que o Docker tenha memória suficiente reservada para os containers.

### Kafka Connect

Entre os serviços iniciados temos o Postgres, que pode ser considerado uma fonte de dados para o Kafka, e para não termos que escrever uma aplicação
que faça a leitura no banco de dados e poste no Kafka, vamos usar o Kafka Connect.

Para isso faça um POST em _http://localhost:8083/connectors_ com o conteúdo em JSON abaixo:

````json
{
    "name": "jdbc_source_postgres",
    "config": {
        "connector.class": "io.confluent.connect.jdbc.JdbcSourceConnector",
        "connection.url": "jdbc:postgresql://postgres:5432/rechargedb",
        "connection.user": "postgres",
        "connection.password": "postgres",
        "topic.prefix": "postgres-01-",
        "poll.interval.ms": 5000,
        "table.whitelist": "recharge",
        "mode": "incrementing",
        "incrementing.column.name": "id"
    }
}
````

### KSQL

Nosso objetivo é reagir a cada nova recarga, somando os valores já realizados por clientes, isso poderia ser usado em uma possível prevenção a fraudes por exemplo.

Vamos conectar no KSQL usando o ksql-cli, no terminal use o comando abaixo:

````
docker-compose run ksql-cli http://ksql:8088
````

E então crie os _streams_ e _tables_ do arquivo ksql/queries.sql

### Simulando uma recarga

O primeiro passo é produzir um cliente, pense que ele acabou de se cadastrar no sistema:

````
kafka-console-producer.sh --broker-list localhost:9092 --topic client --property parse.key=true --property key.separator=":"
1111:{"number":"1111","name":"Pedro"}
2222:{"number":"2222","name":"Maria"}
````

Então no banco de dados faça o insert de uma nova recarga:

````sql
INSERT INTO recharge VALUES (1, 10, '1111')
INSERT INTO recharge VALUES (2, 5, '1111')

INSERT INTO recharge VALUES (3, 20, '2222')
INSERT INTO recharge VALUES (4, 30, '2222')
````