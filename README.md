# Better Not Flop - Local Market Platform

A robust local e-commerce platform built with a modern tech stack. The application consists of a Spring Boot backend providing a RESTful API and a responsive React frontend powered by Vite. The platform features product listings, category filtering, a local database seeder, and is designed for scalability and performance.

## 🚀 Tech Stack

### Frontend
- **Framework**: React 18
- **Build Tool**: Vite
- **Styling**: Vanilla CSS
- **Icons**: Lucide React

### Backend
- **Framework**: Java 17 + Spring Boot 3
- **Data Access**: Spring Data JPA / Hibernate
- **Database**: PostgreSQL
- **Architecture**: MVC & REST API

## 📁 Project Structure

The repository contains two main projects:
- `localmarket/`: The Spring Boot Java backend application.
- `bruh-tf/`: The React + Vite frontend application.

## 🛠️ Prerequisites

Before you begin, ensure you have the following installed on your machine:
- **Java Development Kit (JDK)** 17 or higher
- **Node.js** (v18 or higher) and npm
- **PostgreSQL** (running locally on default port `5432`)

## ⚙️ Backend Setup (`localmarket`)

The backend is a Spring Boot application configured to use PostgreSQL. 

1. **Database Configuration**
   Ensure PostgreSQL is running and create a new database named `localmarket`:
   ```sql
   CREATE DATABASE localmarket;
   ```
   *Note: The default credentials are `postgres` (username) and `local` (password). You can change this in `localmarket/src/main/resources/application.properties`.*

2. **Run the Application**
   Navigate to the backend directory and run the Spring Boot application using the Maven wrapper:
   ```bash
   cd localmarket
   ./mvnw spring-boot:run
   ```
   *(On Windows, you can use `.\mvnw.cmd spring-boot:run`)*

3. **Data Seeding**
   Upon successful startup, the `DataInitializer` will automatically seed the database with mock users and product listings. The API will be available at `http://localhost:8080/api/product`.

   *Test Admin Account*: `admin@localmarket.com` / `admin123`

## 🖥️ Frontend Setup (`bruh-tf`)

The frontend is a React application served via Vite.

1. **Install Dependencies**
   Navigate to the frontend directory and install the necessary npm packages:
   ```bash
   cd bruh-tf
   npm install
   ```

2. **Start the Development Server**
   Start the Vite dev server to view the application in your browser:
   ```bash
   npm run dev
   ```

3. The application will start at `http://localhost:5173/` by default and seamlessly connect to your Spring Boot API.

## 🌟 Key Features

- **Dynamic Product Grid**: Explore seeded e-commerce products fetched directly from the backend.
- **Search & Filtering**: Filter local market items dynamically by category and custom search queries.
- **Auto Data-Initialization**: Spring Boot instantly seeds mock data, meaning the platform is ready-to-test without manual configuration.
- **Local Asset Serving**: Static assets and product imagery are robustly served directly from Vite's `public/images` directory.
- **Responsive Layout**: Designed to adapt and format beautifully across mobile and desktop environments.

## 📝 Troubleshooting

- **`ENOENT: no such file or directory, open 'package.json'`**: Ensure you are in the correct frontend directory (`bruh-tf/`) before running `npm install` or `npm run dev`. Running these commands from the repository root or a nested folder without a `package.json` will result in this error.
- **Database Connection Error**: Verify that PostgreSQL is actively running on port `5432` and that the credentials in `application.properties` match your local postgres installation.
