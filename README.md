# MarkRight ✅ | Productivity Task Management Platform

MarkRight is a full-stack task manager that helps users organize daily tasks, collaborate with others, and stay on top of their goals — all in a clean, responsive UI.

Built with **Spring Boot** and **React.js**, MarkRight features secure JWT authentication, day-to-task mapping, shared task assignment, and urgency tracking.

---

## 🔥 Features

### 🔐 Authentication & Security
- JWT-based Authentication (Access + Refresh Tokens)
- Role-based access control (ROLE_ADMIN, ROLE_USER)
- Secure password hashing with BCrypt
- Input validation + CSRF-safe token flow
- Auth state preserved with HttpOnly cookies

---

### 📅 Task Management System
- Add tasks to specific days
- Create tasks with:
  - Description
  - Start Date
  - Deadline
  - Status (Active / Urgent)
  - Assignee (Self or Friend)
- Urgency flag if deadline is within 1 day
- Line-through completed tasks
- Pop-up task form with overlay

---

### 🧑‍🤝‍🧑 User Features
- Assign tasks to friends or team members
- Username-based task sharing
- View assigned and created tasks separately

---

## 🧠 Tech Stack

### ⚙️ Backend (Spring Boot)
- Spring Security 6 (JWT Filter Chains, Role Management)
- RESTful APIs with DTOs
- Layered Architecture (Controller / Service / Repository)
- Relational schema with MySQL + Hibernate
- Builder & Utility Classes for mapping and formatting

### 🎨 Frontend (React.js)
- Axios with JWT + cookie-based auth
- Modular components (TaskCard, CreatePopup, etc.)
- Responsive UI with custom CSS
- React Router + Protected Routes

---

## 🛠️ Setup Instructions

### 📦 Backend

```bash
cd backend
./mvnw spring-boot:run
