# Sistema de Alquiler de Vehículos
## Estructura del Proyecto
```plaintext
RuedaFacil
│
├── src
│   ├── App_Main
│   └── ConexionBD
│
├── DAO
│   ├── AlquilerD
│   ├── CategoriaD
│   ├── ClienteD
│   ├── EmpleadoD
│   └── VehiculoD
│
└── DTO
    ├── Alquiler
    ├── Cargo (Enum)
    ├── Categoria
    ├── Cliente
    ├── ClienteNoEncontradoException
    ├── Empleado
    ├── EstadoContrato (Enum)
    ├── Vehiculo
    └── VehiculoNoDisponibleException
```

## Descripción
RuedaFácil es una aplicación en Java para la gestión de una empresa de alquiler de vehículos por consola. El sistema permite administrar clientes, vehículos, categorías, empleados y alquileres, además de consultar disponibilidad de vehículos por categoría e historial de alquileres de clientes.

## Funcionalidades
### Gestión de clientes: 
- Crear nuevos clientes.
- Modificar datos de clientes.
- Eliminar clientes del sistema.
- Consultar historial de alquileres de un cliente.

### Gestión de vehículos:
- Registrar nuevos vehículos.
- Modificar información de vehículos.
- Eliminar vehículos.
- Consultar vehículos disponibles por categoría.

### Gestión de alquileres:
- Crear contratos de alquiler.
- Modificar datos de alquileres.
- Eliminar alquileres.
- Gestión de estados del contrato.

### Gestión de categorías y empleados:
- Asociación de vehículos con categorías.
- Gestión de empleados relacionados con los alquileres.

### Gestión de excepciones:
- `ClienteNoEncontradoException`
- `VehiculoNoDisponibleException`
