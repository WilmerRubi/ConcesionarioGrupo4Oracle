
-- Tabla MODELOS
CREATE TABLE Modelos (
    idModelo NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(255) NOT NULL, 
    estiloCarroceria VARCHAR2(255), 
    marca VARCHAR2(255) 
);

-- Tabla PLANTAS
CREATE TABLE Plantas (
    idPlanta NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(255) NOT NULL, 
    ubicacion VARCHAR2(255) NOT NULL 
);

-- Tabla PROVEEDORES
CREATE TABLE Proveedores (
    idProveedor NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(255) NOT NULL, 
    direccion VARCHAR2(255) NOT NULL, 
    noTelefono NUMBER 
);

-- Tabla CLIENTES
CREATE TABLE Clientes (
    idCliente NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    nombre VARCHAR2(255) NOT NULL, 
    direccion VARCHAR2(255) NOT NULL, 
    noTelefono NUMBER, 
    sexo VARCHAR2(10),
    ingresosAnuales NUMBER(10,2) 
);

-- Tabla CONCESIONARIOS
CREATE TABLE Concesionarios (
    idConcesionario NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY, 
    nombre VARCHAR2(255) NOT NULL,
    direccion VARCHAR2(255) NOT NULL,
    noTelefono NUMBER
);

-- Tabla VEHICULOS
CREATE TABLE Vehiculos (
    VIN VARCHAR2(17) PRIMARY KEY, 
    idModelo NUMBER NOT NULL, 
    color VARCHAR2(50),
    noMotor NUMBER NOT NULL,
    transmision VARCHAR2(50), 
    fechaIngreso DATE, 
    FOREIGN KEY (idModelo) REFERENCES Modelos(idModelo) 
);

-- Tabla VENTAS
CREATE TABLE Ventas (
    idVenta NUMBER GENERATED BY DEFAULT AS IDENTITY PRIMARY KEY,
    fecha DATE NOT NULL, 
    idConcesionario NUMBER NOT NULL, 
    idCliente NUMBER NOT NULL,
    VIN VARCHAR2(17) NOT NULL, 
    precio NUMBER(10,2) NOT NULL,
    FOREIGN KEY (idConcesionario) REFERENCES Concesionarios(idConcesionario), 
    FOREIGN KEY (idCliente) REFERENCES Clientes(idCliente), 
    FOREIGN KEY (VIN) REFERENCES Vehiculos(VIN)
);

-- Tabla MODELOSXPANTAS
CREATE TABLE ModelosxPlantas (
    idModelo NUMBER NOT NULL, 
    idPlanta NUMBER NOT NULL,
    PRIMARY KEY (idModelo, idPlanta), 
    FOREIGN KEY (idModelo) REFERENCES Modelos(idModelo), 
    FOREIGN KEY (idPlanta) REFERENCES Plantas(idPlanta) 
);

-- Tabla MODELOSXPROVEEDORES
CREATE TABLE ModelosxProveedores (
    idModelo NUMBER NOT NULL,
    idProveedor NUMBER NOT NULL,
    PRIMARY KEY (idModelo, idProveedor),
    FOREIGN KEY (idModelo) REFERENCES Modelos(idModelo),
    FOREIGN KEY (idProveedor) REFERENCES Proveedores(idProveedor) 
);

-- Tabla VEHICULOSXCONCESIONARIOS
CREATE TABLE VehiculosxConcesionarios (
    VIN VARCHAR2(17) NOT NULL,
    idConcesionario NUMBER NOT NULL,
    PRIMARY KEY (VIN, idConcesionario),
    FOREIGN KEY (VIN) REFERENCES Vehiculos(VIN),
    FOREIGN KEY (idConcesionario) REFERENCES Concesionarios(idConcesionario) 
);