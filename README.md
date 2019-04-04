HBN
======================

> HBN es una plataforma que aloja diferentes tipos de contenido multimedia (películas y series), proporcionando acceso a gran variedad de información acerca de películas y series, incluso de las temporadas y cada uno de sus capítulos. Todo este contenido audiovisual es accesible para cualquier usuario de la plataforma, teniendo la posibilidad buscar el mismo manualmente o incluso crear sus propias listas personalizadas.

## TECNOLOGÍAS UTILIZADAS

* [Eclipse IDE]
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

  [x] Cuentas de administradores y usuarios creadas
  [x] Películas y series creadas
  [ ] Diferentes perfiles de usuario
  [x] Funcionalidades de inicio de sesión y registro añadidas
  [X] BD creada

## Instalación
Pasos previos:
* Crear BD (_MySql_) en _localhost_ (127.0.0.1) con el nombre: **hbn**.
* Crear un usuario con permisos sobre la BD , cuyo nombre y contraseña han de ser, respectivamente: **hbnadmin** ; **admin**.

Pasos a seguir:
-En el proyecto _comun_:

```sh
$  mvn install
```

-En el proyecto cliente y en el servidor:

  - Añadir el siguiente argumento a la VM de java o al ejecutarlo:

  > -Djava.security.policy=security/java.policy

  - Compilamos usando la línea de comandos:
 
 ```sh
$ mvn compile
```

-En el servidor:
  
 ```sh
$ mvn datanucleus:schema-create 
```
  
  Para lanzar el `registry.bat`, debe de estar en **ServidorHBN/target/classes**.

### Versión
v1.0

   [Eclipse IDE]: <https://www.eclipse.org/ide/>
   [Java]: <https://www.oracle.com/technetwork/java/javase/downloads/jdk11-downloads-5066655.html> 
   [Maven]: <https://maven.apache.org/>
   [Datanucleus - JDO]: <http://www.datanucleus.org/products/accessplatform/jdo/getting_started.html>
   [XAMPP]: <https://www.apachefriends.org/es/index.html>
   [MySql]: <https://www.mysql.com/>
   [RMI]: <https://es.wikipedia.org/wiki/Java_Remote_Method_Invocation>
   [Git]: <https://git-scm.com/>
   [GitHub]: <https://github.com/>
   [YouTrack]: <https://www.jetbrains.com/youtrack/>
   
