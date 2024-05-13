## procedure de mise en place et outillage d'installation

## pour configurer la base de donnee sur resources->application.properties
### site pour acceder a la bd h2 
http://localhost:8021/h2-console

## installation de swagger 
## on installer les dependances dans le fichier pom.xml
<dependency>
<groupId>org.springdoc</groupId>
<artifactId>springdoc-openapi-starter-webmvc-ui</artifactId>
<version>2.5.0</version>
</dependency>
## lancement de l'application avec swagger
http://localhost:8021/swagger-ui/index.html/