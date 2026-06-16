PetStore API Automation
Descripción

Automatización de pruebas para los servicios REST de la API pública PetStore.

La solución valida el ciclo CRUD de usuarios utilizando RestAssured, JUnit 5 y Maven, generando evidencias JSON y reportes HTML de ejecución.

Flujo automatizado

Se implementó el siguiente escenario:

Crear usuario
Consultar usuario creado
Actualizar información del usuario
Consultar usuario actualizado
Eliminar usuario
Tecnologías utilizadas
Java
Maven
RestAssured
JUnit 5
ExtentReports
Estructura del proyecto
api_petstore
│
├── src
│   └── test
│       └── java
│           ├── tests
│           └── utils
│
├── target
│   ├── evidence
│   └── reports
│
├── pom.xml
├── README.md
└── conclusiones.txt
Ejecución

Desde la raíz del proyecto ejecutar:

mvn clean test

También es posible ejecutar la clase:

tests.UserApiTest

directamente desde IntelliJ IDEA.

Evidencias

Durante la ejecución se generan archivos JSON con las respuestas recibidas desde cada servicio consumido.

Ubicación:

target/evidence
Reporte

Al finalizar la ejecución se genera un reporte HTML con el resultado detallado de las validaciones realizadas.

Ubicación:

target/reports
Validaciones implementadas
Creación de usuario
Consulta de usuario
Actualización de información
Verificación de cambios realizados
Eliminación de usuario
Observaciones

La solución fue implementada siguiendo una estructura simple y mantenible, separando los casos de prueba de las utilidades utilizadas para generación de reportes y evidencias.