# Gestor de videoclubes con Spring y Hibernate
Se trata de una aplicación simple cliente - base de datos para gestión de videoclubes (algo muy útil para esta época)
la cual gestiona su acceso a base de datos a través del framework Hibernate, mientras que se mantiene la independencia
entre clases mediante la inyección de objetos con Spring framework.

La base de datos para la que fue desarrollada es HSQLDB, aunque es posible usar cualquier otra simplemente cambiando 
el dialecto de Hibernate en el archivo beans.xml dentro de la carpeta src.

Los scripts usados para la creación de las tablas dentro de la base de datos se encuentran en el archivo
scriptSQL-creacion-tablas, mientras que los scripts para relacionar las tablas mediante claves foráneas están
dentro del archivo scriptSQL-creacion-llaves-foraneas.

El ejecutable se encuentra dentro de la carpeta dist, junto con los .jar de las librerías usadas en el proyecto, para su correcto funcionamiento es necesario que se haya iniciado una base de datos HSQL con las tablas y las relaciones indicadas por los scripts sql.
