//ESTE TEXTO ES GENERADO POR IA, COMO ALUMNO NO SE GENERAR UN README//

* **IDE y Versión de Java:** Configurado para **Java 17 (LTS)** o superior debido al uso de características modernas de orientación a objetos (Streams, Lambdas y Genéricos avanzados), recomendando entornos como IntelliJ IDEA o Eclipse.
* **Dependencias Externas:** Detalla el uso de **JUnit 5 (Jupiter)** para las pruebas de las excepciones personalizadas (`DniLongitudInvalidaException`, `ProveedorBloqueadoException`, etc.) e incluye una guía rápida de importación manual en el IDE.
* **Compilación y Ejecución:** Incluye comandos de consola nativos (`javac` y `java`) aislando el código por paquetes y explicando el flujo de arranque desde un entorno gráfico (IDE).
* **Credenciales y Configuración (Ampliación):** Divide el apartado en dos escenarios:
  1. El sistema por defecto de persistencia de ficheros (ficheros `.txt` locales autogenerados) con la clave de administración requerida para `GestionPersonal` (`admin1234`).
  2. El entorno de ampliación de Base de Datos relacional, configurando los parámetros `JDBC`, la URL del puerto (`3306`), las credenciales del `root` y las sentencias SQL necesarias para inicializar el esquema.

