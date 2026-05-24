# RuedaFácil - Sistema de Alquiler de Vehículos

## Estructura
RuedaFacil
|____ src
|    |__ App
|    |__ ConexionBD
|
|___ DAO
|    |__ AlquilerD
|    |__ CategoriaD
|    |__ ClienteD
|    |__ EmpleadoD
|    |__ VehiculoD
|
|___ DTO
    |__ Alquilable (interfaz)
    |__ Alquiler
    |__ Cargo (enum de Empleado)
    |__ Categoria
    |__ Cliente
    |__ ClienteNoEncontradoException
    |__ Empleado
    |__ EstadoContrato (enum de Alquiler)
    |__ Vehiculo
    |__ VehiculoNoDisponibleException
    
## Descripción
Aplicación para la gestión de una empresa de alquiler de vehículos. Permite administrar clientes, vehículos, categorías, empleados y contratos de alquiler, con cálculo automático de precios e interfaz por consola.

## Requisitos funcionales del sistema
- Gestión de clientes
- Gestión de vehículos y categorías
- Gestión de empleados
- Gestión de alquileres
- Cálculo de precios de alquiler por vehiculo por dia
- Consultas

## ¿Qué se ha usado?
- Java
- MySQL
- JDBC
