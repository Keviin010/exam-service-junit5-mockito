# Exam Management Service

Servicio backend en Java para la gestión de exámenes y preguntas, con arquitectura 
en capas, inyección de dependencias por constructor y cobertura de pruebas unitarias 
con JUnit 5 y Mockito.

## Descripción

Simula el core de un sistema de gestión de exámenes: buscar un examen por nombre 
y cargar dinámicamente sus preguntas asociadas. La lógica de negocio está 
desacoplada de la persistencia, lo que permite testearla de forma aislada y confiable.

## Tecnologías

- Java 17
- Maven
- JUnit 5
- Mockito

## Decisiones técnicas

- **Inyección por constructor** sobre field injection para hacer las dependencias 
  explícitas y facilitar el testing unitario.
- **Mocks con Mockito** para aislar la capa de servicio de la persistencia 
  y probar únicamente la lógica de negocio.
- **Optional** en las respuestas del repositorio para forzar el manejo 
  explícito de casos donde el examen no existe.

## Funcionalidades

- Búsqueda de examen por nombre (caso encontrado y no encontrado)
- Carga de preguntas asociadas a un examen
- Verificación de interacciones entre capas con Mockito
- Manejo de casos con datos vacíos o inexistentes

## Ejecución

# Compilar
mvn clean compile

# Ejecutar tests
mvn test

## Autor

Kevin Royo
[LinkedIn](https://www.linkedin.com/in/kevin-royo-09a427216/)
