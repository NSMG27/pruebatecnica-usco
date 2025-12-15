## ▶️ Ejecución del Backend

### Requisitos

- Java 17 o superior  
- Maven 3.8 o superior  
- MySQL 8 (opcional H2)  
- Git  

### Pasos

1. Clonar el repositorio

```bash
git clone https://github.com/NSMG27/pruebatecnica-usco.git
```

2. Entrar al proyecto backend

```bash
cd prueba-tecnica/backend
```

3. Compilar el proyecto

```bash
mvn clean install
```

4. Ejecutar la aplicación

```bash
mvn spring-boot:run
```

📍 El backend quedará disponible en:

```
http://localhost:8080
```

---

## ⚙️ Configuración del Backend

Archivo: `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/pruebatecnica
spring.datasource.username=root
spring.datasource.password=root

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true

jwt.secret=miClaveSecretaSuperSeguraParaJWT2024DebeSerLargaYCompleja
jwt.expiration=86400000
```

---

