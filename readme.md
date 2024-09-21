# Banco App

## Descripción del Proyecto

Banco App es una aplicación web desarrollada con **Next.js** en el frontend y **Spring Boot** en el backend. La aplicación permite la creación de cuentas bancarias, consultas de saldo y la realización de transacciones (depósitos y retiros).

## Estructura del Proyecto

### Frontend (Next.js)

- **Componentes**: La aplicación frontend se organiza en componentes modulares, incluyendo formularios para crear cuentas y realizar transacciones. Cada componente se encarga de su propia lógica y presentación.
- **Estilos**: Se utiliza **Tailwind CSS** para el diseño responsivo y atractivo de la interfaz de usuario.
- **Microfrontend**: Los componentes están diseñados como microfrontends, facilitando la escalabilidad y mantenimiento. Cada microfrontend representa una funcionalidad específica (creación de cuentas, consulta de saldo, transacciones).

### Backend (Spring Boot)

- **Controladores**: La lógica de negocio se gestiona en controladores que manejan las peticiones HTTP para crear cuentas, consultar saldos y realizar transacciones.
- **CORS**: Se configuró CORS en el backend para permitir que el frontend en localhost acceda a los recursos.

## Decisiones Tomadas

1. **Uso de Next.js**: Se eligió Next.js por su capacidad para crear aplicaciones React con renderizado del lado del servidor, lo que mejora el SEO y la velocidad de carga.
  
2. **Microfrontends**: Se adoptó una arquitectura de microfrontends para que cada funcionalidad pueda desarrollarse, probarse y desplegarse de manera independiente.
  
3. **Tailwind CSS**: Se utilizó Tailwind CSS para facilitar un diseño flexible y responsivo sin tener que escribir mucho CSS personalizado.

4. **API REST**: El backend se implementó como una API RESTful, lo que permite que el frontend y el backend estén desacoplados y se comuniquen a través de peticiones HTTP.

## Ejecución del Proyecto

### Backend (Spring Boot)

1. **Requisitos Previos**:
   - Java 21 o superior.
   - Maven para la gestión de dependencias.

2. **Ejecutar la Aplicación**:
   - Clona el repositorio del backend.
   - Navega a la carpeta del proyecto.
   - Ejecuta el siguiente comando:
     ```bash
     mvn spring-boot:run
     ```
   - La API estará disponible en `http://localhost:8080`.

### Frontend (Next.js)

1. **Requisitos Previos**:
   - Node.js y npm instalados.

2. **Ejecutar la Aplicación**:
   - Clona el repositorio del frontend.
   - Navega a la carpeta del proyecto.
   - Instala las dependencias:
     ```bash
     npm install
     ```
   - Ejecuta el siguiente comando:
     ```bash
     npm run dev
     ```
   - La aplicación estará disponible en `http://localhost:3000`.

