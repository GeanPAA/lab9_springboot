# 🚀 LAB 9 - SPRING BOOT JWT AUTH

## 📌 Descripción
Este proyecto implementa autenticación y autorización con **Spring Boot + JWT**, permitiendo registro de usuarios, login y acceso a rutas protegidas por roles (USER / ADMIN).

---

# 🧪 PRUEBAS EN POSTMAN

---

## 🔐 1. SIGNUP (ADMIN)

### ➤ Método:
POST  
http://localhost:8081/api/auth/signup  

<img width="1431" height="623" alt="image" src="https://github.com/user-attachments/assets/4e0a62c1-9a50-4863-836c-87b6aa4dd07a" />

## 👤 2. SIGNUP (USER NORMAL)

### ➤ Método:
POST  
http://localhost:8081/api/auth/signup  

<img width="1433" height="580" alt="image" src="https://github.com/user-attachments/assets/555f12e5-1c62-4a52-9a4f-c6868e47d0c8" />

## 🔑 3. LOGIN (ADMIN)

### ➤ Método:
POST  
http://localhost:8081/api/auth/signin

<img width="1433" height="752" alt="image" src="https://github.com/user-attachments/assets/d38ecf98-deb5-4a15-b3d5-4d79b9923331" />

## 🔒 4. PROBAR RUTA PROTEGIDA (ADMIN)

### ➤ Método:
GET
http://localhost:8081/api/test/admin

HEADERS
Authorization: Bearer "TOKEN"

<img width="1432" height="537" alt="image" src="https://github.com/user-attachments/assets/d5468d11-6850-4774-b6f9-ad4deb25a577" />

## 👤 5. LOGIN USER NORMAL

### ➤ Método:
POST  
http://localhost:8081/api/auth/signin

<img width="1431" height="651" alt="image" src="https://github.com/user-attachments/assets/c4deaeff-117a-4f5a-88b1-db92d186361f" />


