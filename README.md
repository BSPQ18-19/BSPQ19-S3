# HBN

> HBN es una plataforma que aloja diferentes tipos de contenido multimedia (películas y series), proporcionando acceso a gran variedad de información acerca de películas y series, incluso de las temporadas y cada uno de sus capítulos. Todo este contenido audiovisual es accesible para cualquier usuario de la plataforma, teniendo la posibilidad buscar el mismo manualmente o incluso crear sus propias listas personalizadas.

## TECNOLOGÍAS UTILIZADAS

 * [ Java ]
 * [Maven]
 * [Datanucleus - JDO]
 * [XAMPP]
 * [MySql]
 * [RMI]
 * [Git]
 * [GitHub]
 * [YouTrack]

## NUEVAS FUNCIONALIDADES!

  - Cuentas de administradores y usuarios creadas
  - Películas y series creadas
  - Funcionalidades de inicio de sesión y registro añadidas
  - BD creada

## Instalación

En el proyecto _comun_:

  > mvn install

En el proyecto cliente y en el servidor:

  - Añadir el siguiente argumento a la VM de java o al ejecutarlo:

  > -Djava.security.policy=security/java.policy

  - mvn mvn compile

En el servidor:
  
  > mvn datanucleus:schema-create 

### Versión
v1.0

 
   [Java]: <https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html> 
   [Maven]: <https://maven.apache.org/>
   [Datanucleus - JDO]: <http://www.datanucleus.org/products/accessplatform/jdo/getting_started.html>
   [XAMPP]: <https://www.apachefriends.org/es/index.html>
   [MySql]: <https://www.mysql.com/>
   [RMI]: <https://es.wikipedia.org/wiki/Java_Remote_Method_Invocation>
   [Git]: <https://git-scm.com/>
   [GitHub]: <https://github.com/>
   [YouTrack]: <https://www.jetbrains.com/youtrack/>

   
