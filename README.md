0. EL proyecto fue creado en java 21, base de datos SQL Server, Framework de Quarkus 3.28.5, Maven, Panache y el IDE VScode
1. EL Proyecto de Microservicio fue creado bajo la arquitectura DDD (Application, DOmain, Infraestructure), para que sea standard su entedimiento
2. En La capa de Aplicación encontramos: (DTO y los Services)
3. En la capa de Domain econtramos; (Entities o Modelos, Enums,Strategies, Repositories y Exepciones sobre los Modelos Implementados)
4. En la capa de Infraestuctura encontramos: (Controllers o Resources, Seeders y Exepciones para validaciones)

5. La entidad de clientes se relaciona con las tablas de COuntry, Gender y Status y la tabla de clientes tiene las especificaciones
que se solicitaron en el enunciado

6. Para validar la fecha de cumpleaños que no fuera antes de 1990 y que no aceptara fechas futuras, decidí crear una anotación propia
para poder realizar esta validacion.

7. Para controlar los getter y setters utilizé la libreria de lombok

8. Para cargar los datos por defectos utilizé seeders para cada entidad y luego creee un orquestador para que el sea quien inserte los datos en la base 
de datos.

9. Documetación de Swagger http://localhost:8080/q/swagger-ui/#/, para cada resource con sus respectivos ejemplos para pruebas

10. Para ejecutar el proyecto por Visual Studio  mvn clean compile quarkus:dev desde la consola.

11. Al ejecutar la aplicaciones se realizacion las migraciones por medio de la configuracion en el properties quarkus.hibernate-orm.database.generation=update
y tambien se ejecutar los seeders para llenar los datos por defecto que se pleantean en el enunciado.

12. Para poder crear los seeders siempre hago referencia a los Enums.

13. En la estructura interna del proyecto seria (Controller -> Service -> Repository -> Entities), desde que se recibe una peticion del usuario hasta que finaliza.

14. Cada COntrolador esta separado para poder cumplir con los principos solid.

15. Para manejar los DTO manejé una clase llamada ClientMapperService.java para no mostrar los datos directamente del modelo si no de un DTO basado en el modelo

16. Para validar que la cuenta inicie con el nro de cuente 003 si es de chile y el resto de paises no tengan esa validacion, utilizé el patron strategy implementado
interfaces y clases abtractas para las operaciones cuando fuera con el pais chile y para las demas cree una por defecto, con eso logro que cuando venga un pais
con otro tipo de restriccion en la cuenta no afecto la logica del negocio si no que implemento otra interfaz para el pais requerido.

17. EN la carpeta de Documentación se encuentra el diagrama entidad relación una vista de Swagger.

Gracias