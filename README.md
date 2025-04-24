# Product Service – Backend

Spring Boot backend for managing manual accounting transactions.

---

## 🧰 Tech Stack

- Java 17
- Spring Boot 3.4.4
- Oracle DB
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
