# Appointment Service API

## Project Overview

The **Appointment Service API** is a backend REST API developed using **Java and Spring Boot** for the UK Healthcare Patient Management Platform.

The service manages patient appointments, including appointment scheduling, retrieving appointment details, updating appointments, cancelling appointments, and detecting scheduling conflicts.

The API uses **Spring Data JPA** for database operations and **MySQL** for persistent data storage.

---

## Project Details

- **Project:** UK Healthcare Patient Management Platform
- **Task:** T-002 – Appointment Service API
- **Module:** Appointment Management
- **Role:** Java Full Stack / Backend Development
- **Backend:** Java + Spring Boot
- **Database:** MySQL
- **API Type:** REST API
- **Build Tool:** Maven
- **Java Version:** 17

---

## Features

### 1. Appointment Creation

Create a new appointment with:

- Patient ID
- Provider ID
- Start time
- End time
- Appointment reason
- Appointment status

### 2. Appointment Retrieval

The API supports:

- Get all appointments
- Get appointment by ID
- Get appointments by patient
- Get appointments by provider

### 3. Appointment Update

Existing appointment details can be updated while checking for scheduling conflicts.

### 4. Appointment Cancellation

Appointments can be cancelled using the DELETE endpoint.

The appointment is **soft-cancelled** by changing its status to `CANCELLED` instead of physically removing the database record.

### 5. Conflict Detection

The service prevents overlapping appointments for the same provider.

For example:

```text
Existing appointment:
10:00 AM - 10:30 AM

New appointment:
10:15 AM - 10:45 AM
