# 🚀 LAB 9 - SPRING BOOT JWT AUTH

## 📌 Descripción
Este proyecto implementa autenticación y autorización con **Spring Boot + JWT**, permitiendo registro de usuarios, login y acceso a rutas protegidas por roles (USER / ADMIN).

---

# 🧪 PRUEBAS EN POSTMAN

---

## 🔐 1. SIGNUP (ADMIN)

### ➤ Método:
POST  
http://localhost:8080/api/auth/signup  

### ➤ Body (JSON):
```json
{
   "username":"admin",
   "email":"admin@test.com",
   "password":"123456",
   "roles":["ADMIN"]
}
