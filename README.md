# RuedaFácil - Sistema de Alquiler de Vehículos

## Descripción
Aplicación para la gestión de una empresa de alquiler de vehículos. Permite administrar clientes, vehículos, categorías, empleados y contratos de alquiler, con cálculo automático de precios e interfaz por consola.

# Requisitos funcionales del sistema
- Gestión de clientes
- Gestión de vehículos -> implementa Comparable<Vehiculo> y la interfaz Alquilable
- Gestión de categorías de vehículos
- Gestión de empleados
- Gestión de alquileres: registro de un nuevo alquiler (asociando cliente, vehículo y empleado), registro de devolución, cancelación de alquiler y cálculo automático del precio total.
- Cálculo de precios por interfaz Alquilable
- Consultas para filtrar vehículos disponibles por categoría, obtener historial de alquileres de un cliente, ordenar vehículos por precio o por marca/modelo.

## ¿Qué se ha usado?
- Java
- MySQL
- JDBC

## Estructura del Proyecto
- `App.java`: Principal
- `ConexionBD.java`: Conexión a base de datos
- `DAO/`: Clases de métodos 
- `DTO/`: Clases de datos
