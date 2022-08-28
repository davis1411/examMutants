# Examen mutantes
# Juan David Salazar Arcila

Proyecto realizado con Sprint boot desplegado en un elastic beantstalk de aws con base de datos en un rds de aws

Se exponen dos servicios solicitados en el examen para consultar una secuencia de adn y saber si equivale a un mutante o a un humano y otro servicio para consultar las estadisticas de adn analizados previamente

# url repositorio git
Repositorio publico para visualizar el ejercicio realizado
https://github.com/davis1411/examMutants



# url elastic beanstalk
Se crea un elastic beanstalk en aws para desplegar el jar generado del proyecto

http://mutant-env.eba-6xx9ay9m.us-east-1.elasticbeanstalk.com



# url servicio post para analizar mutante
En la ruta src/main/resources se encuentra una coleccion de postman diferentes casos de prueba para ser ejecutados 
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
Se adiciona sonar cloud para evaluar el codigo desarrollado

https://sonarcloud.io/summary/overall?id=davis1411_examMutants

# Colecciones postman
Se adjunta en el correo y se adiciona a la carpeta src/main/resources una coleccion de postman utilizado para hacer pruebas

# url Swagger
Se documenta la API por medio de Swagger, por esta herramienta es posible realizar las pruebas de los servicios expuestos

http://mutant-env.eba-6xx9ay9m.us-east-1.elasticbeanstalk.com/swagger-ui/index.html

# Jacoco - cobertura de pruebas unitarias
La cobertura de pruebas unitarias puede ser verificada por medio de jacoco, haciendo clic derecho en la carpeta test - run test with coverage

# application.properties
En el aplication.properties se configura la conexión a la base de datos (rds en amazon), se encuentra expuesto publicamente la conexion por motivos de revisión de la prueba, pero por seguridad no deberian estar en el repositorio
