Backend para test SOAP, para poder ejecutarlo de forma correcta es necesario los siguientes pasos:

Ejecutar Script dentro de la carpeta db en el IDE conectado a una base de datos PostgresQL.

La aplicacion utiliza Java 17, SpringBoot 2.2.4 las configuraciones correspondientes a la base de datos estan ya colocadas, el usuario: postgres y la clave: 123456.

Las apis utilizadas son:

http://localhost:5007/testController/solicitarCambioPorFecha - POST
{fechaInicio: '12/07/2024', fechaFin: '12/07/2024'}


http://localhost:5007/testController/insertarDatoTabla - POST
{fecha: '12/07/2024'}

http://localhost:5007/testController/obtenerSolicitudes - GET
