📘 Prueba Técnica – Sistema de Gestión de Trámites

Backend: Java 17 · Spring Boot · Spring Security · JPA
Arquitectura: DDD (Domain Driven Design)
Autenticación: JWT

🚀 Instrucciones para ejecutar el Backend
🔧 Requisitos

Java 17+

Maven 3.8+

MySQL 8+ (o H2 para pruebas)

Git

▶️ Pasos

git clone https://github.com/tu-usuario/prueba-tecnica.git
cd prueba-tecnica/backend
mvn clean install
mvn spring-boot:run


El backend quedará disponible en:

http://localhost:8080

📄 Configuración (application.properties)
spring.datasource.url=jdbc:mysql://localhost:3306/pruebatecnica
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=miClaveSecretaSuperSeguraParaJWT2024DebeSerLargaYCompleja
jwt.expiration=86400000
