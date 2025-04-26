# Product Service – Backend

Spring Boot backend for managing manual accounting transactions.

---

## 🧰 Tech Stack

- Java 17
- Spring Boot 3.4.4
- Oracle DB 21C
- Maven
- MapStruct

---

## ▶️ API Endpoints

Base URL: `http://localhost:8080/api/manual-transactions`

- `GET /products` – List all products
- `GET /products/{productCode}` – Get a single product by code
- `GET /product-cosifs` – List COSIFs by product
- `GET /manual-transactions` – List all manual transactions
- `POST /manual-transactions` – Create a new manual transaction

---

🛠️ Oracle DB Setup for Development & Testing

 Login as system
```bash
sqlplus system/1234@localhost:1521/XEPDB1
```
 Create test_user and grant permissions
```bash
CREATE USER test_user IDENTIFIED BY test_password;

GRANT ALTER ANY TABLE TO test_user;
GRANT SELECT ANY TABLE, INSERT ANY TABLE TO test_user;
GRANT DROP ANY TABLE TO test_user;
GRANT UNLIMITED TABLESPACE TO test_user;

COMMIT;
```

 Populate development and testing data

```bash
 cd C:\dev\projects\product-service-java17\scripts

run_db_setup.bat       # Populate development environment  
run_db_setup_test.bat  # Populate testing environment

```





## 🏗️ Build & Run

### 🔧 Build the App

```bash
./mvnw clean install
```

### ✅ Run Tests

```bash
./mvnw test
```

### ▶️ Start the Application

```bash
./mvnw spring-boot:run
```

The app runs at: [http://localhost:8080](http://localhost:8080)
