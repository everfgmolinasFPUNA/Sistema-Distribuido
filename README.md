# Sistema-Distribuido

##Integrantes
-Elias Santiago Cáceres Melgarejo  
-Ever Fernando Garay Molinas  


##Requerimientos
-Java 11  
-Maven  
-Ide (de su preferencia), utilizado para la importación de paquetes externos  
-Postgresql  

##Creación de la base de datos
En postgres cree la base de datos denominada ```hospital``` o en el archivo ```BD.java``` en la linea url ponga el nombre de la base de datos de su preferencia. 

##Importación del proyecto
-Importe el proyecto desde su IDE como poryecto MAVEN  
-Agregue las dependencias de ```Hamcrest-core-1.1.jar```, ```json-simple-1.1.1.jar```, ```unit-4.10.jar``` y ```postgresql-42.2.1.jar```  

##Cambios para su funcionamiento
En el archivo ```BD.java``` cambie el usuario y contraseña por los configurados por ud mismo al instalar la Postgresql  

##Estrucutra de Base de Datos
Para crear las estructuras de la base de datos, en postgres conectese a su base de datos y ejecute el script
```
CREATE TABLE hospitalcentral(
  nrohospital integer NOT NULL,
  nombre character varying(1000),
  CONSTRAINT pk_nrohospital PRIMARY KEY (nrohospital)
);

CREATE TABLE hospital(
  nrocama integer NOT NULL,
  estado integer,
  nrohospital integer NOT NULL,
  CONSTRAINT pk_nrocama PRIMARY KEY (nrocama),
  CONSTRAINT fk_nrohospital FOREIGN KEY (nrohospital) REFERENCES hospitalcentral(nrohospital)
);
```  
Tambien puede encontrarlo en la carpeta BASE DE DATOS con el nombre ```socket1.sql```  

Para ejecutar el modo servidor, ejecute el archivo ```UDPServer.java``` ubicado en la carpeta ```py.socket1.server```  
Para ejecutar el modo cliente, ejecute el archivo ```UDPClient.java``` ubicado en la carpeta ```py.socket1.client```  

##Uso
-Para agregar hospitales desde el cliente ingrese la opcion 1  
-Para cambiar nombres a los hospitales ingrese opción 2  
-Para eliminar una cama ingrese la opción 3  
-Para añadir una cama ingrese la opción 4  
-Para ver el estado general de cada hospital ingrese la opción 5  
-Para desocupar una cama ingrese la opción 6  
-Para ocupar una cama ingrese la opción 7  
-Para ver el registro de operaciones ingrese la opción 8. (Tenga en cuenta que el registro de operaciones se guarda solo durante el tiempo que el servidor está activo, no tiene persistencia de datos en la Base de Datos)  
