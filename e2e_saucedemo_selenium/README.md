SauceDemo E2E Automation
Descripción

Automatización E2E desarrollada para validar el flujo completo de compra en la aplicación SauceDemo.

La solución fue implementada utilizando Selenium WebDriver, Java, Maven y JUnit 5 siguiendo el patrón de diseño Page Object Model (POM).

Flujo automatizado

El escenario implementado valida las siguientes acciones:

Inicio de sesión con usuario estándar
Adición de dos productos al carrito
Visualización del carrito
Diligenciamiento de información de compra
Validación del resumen de compra
Finalización de la compra
Validación del mensaje de confirmación
Tecnologías utilizadas
Java
Selenium WebDriver
Maven
JUnit 5
ExtentReports
Page Object Model (POM)
Estructura del proyecto
e2e_saucedemo_selenium
│
├── src
│   └── test
│       └── java
│           ├── pages
│           ├── tests
│           └── utils
│
├── target
│   ├── screenshots
│   └── reports
│
├── pom.xml
├── README.md
└── conclusiones.txt
Ejecución

Desde la raíz del proyecto ejecutar:

mvn clean test

También es posible ejecutar la clase:

tests.PurchaseFlowTest

directamente desde IntelliJ IDEA.

Evidencias

Durante la ejecución se generan capturas de pantalla automáticas para cada paso relevante del flujo.

Ubicación:

target/screenshots
Reporte

Al finalizar la ejecución se genera un reporte HTML con el detalle de cada validación realizada.

Ubicación:

target/reports
Validaciones implementadas
Login exitoso
Visualización del inventario
Adición de productos
Validación del carrito
Validación del checkout
Confirmación de compra exitosa
Observaciones

La automatización fue desarrollada utilizando el patrón Page Object Model con el objetivo de mejorar la mantenibilidad, reutilización y escalabilidad de la solución.