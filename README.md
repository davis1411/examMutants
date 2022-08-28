# examMutants
Examen mutantes

Proyecto realizado con Sprint boot desplegado en un elastic beantstalk de aws con base de datos en un rds de aws

Se exponen dos servicios solicitados en el examen para consultar una secuencia de adn y saber si equivale a un mutante o a un humano y otro servicio para consultar las estadisticas de adn analizados previamente

# url repositorio git
https://github.com/davis1411/examMutants

Repositorio publico para visualizar el ejercicio realizado

# url elastic beanstalk
http://mutant-env.eba-6xx9ay9m.us-east-1.elasticbeanstalk.com

Se crea un elastic beanstalk en aws para desplegar el jar generado del proyecto

# url servicio post para analizar mutante
http://mutant-env.eba-6xx9ay9m.us-east-1.elasticbeanstalk.com/mutant

# url de servicio para consultar estadisticas
http://mutant-env.eba-6xx9ay9m.us-east-1.elasticbeanstalk.com/stats

# Script base de datos

create database mutantdb;

create table sequencedna (
id_sequence int not null auto_increment,
sequence_dna text not null,
is_mutant BOOLEAN not null,
fe_analysis DATETIME not null,
primary key(id_sequence)
);

select * from sequencedna;

La base de datos se encuentra en un rds de aws

# url sonar
Se adiciona sonar cloud para validar la cobertura de codigo

# Colecciones postman

# url Swagger
http://mutant-env.eba-6xx9ay9m.us-east-1.elasticbeanstalk.com/swagger-ui/index.html



