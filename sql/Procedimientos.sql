-- Procedimientos para la entidad Clientes

-- Insertar un nuevo cliente
CREATE OR REPLACE PROCEDURE InsertarCliente(
    p_nombre IN VARCHAR2,
    p_direccion IN VARCHAR2,
    p_noTelefono IN NUMBER,
    p_sexo IN VARCHAR2,
    p_ingresosAnuales IN NUMBER
) AS
BEGIN
    INSERT INTO Clientes (nombre, direccion, noTelefono, sexo, ingresosAnuales) 
    VALUES (p_nombre, p_direccion, p_noTelefono, p_sexo, p_ingresosAnuales);
    COMMIT;
END;
/

-- Actualizar la dirección de un cliente
CREATE OR REPLACE PROCEDURE ActualizarDireccionCliente(
    p_idCliente IN NUMBER,
    p_direccion IN VARCHAR2
) AS
BEGIN
    UPDATE Clientes
    SET direccion = p_direccion
    WHERE idCliente = p_idCliente;
    COMMIT;
END;
/

-- Eliminar un cliente
CREATE OR REPLACE PROCEDURE EliminarCliente(
    p_idCliente IN NUMBER
) AS
BEGIN
    DELETE FROM Clientes 
    WHERE idCliente = p_idCliente;
    COMMIT;
END;
/

-- Procedimientos para la entidad Modelos

-- Insertar un nuevo modelo
CREATE OR REPLACE PROCEDURE InsertarModelo(
    p_nombre IN VARCHAR2,
    p_estiloCarroceria IN VARCHAR2,
    p_marca IN VARCHAR2
) AS
BEGIN
    INSERT INTO Modelos (nombre, estiloCarroceria, marca) 
    VALUES (p_nombre, p_estiloCarroceria, p_marca);
    COMMIT;
END;
/

-- Actualizar el nombre de un modelo
CREATE OR REPLACE PROCEDURE ActualizarNombreModelo(
    p_idModelo IN NUMBER,
    p_nombre IN VARCHAR2
) AS
BEGIN
    UPDATE Modelos
    SET nombre = p_nombre
    WHERE idModelo = p_idModelo;
    COMMIT;
END;
/

-- Eliminar un modelo
CREATE OR REPLACE PROCEDURE EliminarModelo(
    p_idModelo IN NUMBER
) AS
BEGIN
    DELETE FROM Modelos 
    WHERE idModelo = p_idModelo;
    COMMIT;
END;
/

-- Procedimientos para la entidad Plantas

-- Insertar una nueva planta
CREATE OR REPLACE PROCEDURE InsertarPlanta(
    p_nombre IN VARCHAR2,
    p_ubicacion IN VARCHAR2
) AS
BEGIN
    INSERT INTO Plantas (nombre, ubicacion) 
    VALUES (p_nombre, p_ubicacion);
    COMMIT;
END;
/

-- Actualizar la ubicación de una planta
CREATE OR REPLACE PROCEDURE ActualizarUbicacionPlanta(
    p_idPlanta IN NUMBER,
    p_ubicacion IN VARCHAR2
) AS
BEGIN
    UPDATE Plantas
    SET ubicacion = p_ubicacion
    WHERE idPlanta = p_idPlanta;
    COMMIT;
END;
/

-- Eliminar una planta
CREATE OR REPLACE PROCEDURE EliminarPlanta(
    p_idPlanta IN NUMBER
) AS
BEGIN
    DELETE FROM Plantas 
    WHERE idPlanta = p_idPlanta;
    COMMIT;
END;
/

-- Procedimientos para la entidad Concesionarios

-- Insertar un nuevo concesionario
CREATE OR REPLACE PROCEDURE InsertarConcesionario(
    p_nombre IN VARCHAR2,
    p_direccion IN VARCHAR2,
    p_noTelefono IN NUMBER
) AS
BEGIN
    INSERT INTO Concesionarios (nombre, direccion, noTelefono) 
    VALUES (p_nombre, p_direccion, p_noTelefono);
    COMMIT;
END;
/

-- Actualizar el número de teléfono de un concesionario
CREATE OR REPLACE PROCEDURE ActualizarTelefonoConcesionario(
    p_idConcesionario IN NUMBER,
    p_noTelefono IN NUMBER
) AS
BEGIN
    UPDATE Concesionarios
    SET noTelefono = p_noTelefono
    WHERE idConcesionario = p_idConcesionario;
    COMMIT;
END;
/

-- Eliminar un concesionario
CREATE OR REPLACE PROCEDURE EliminarConcesionario(
    p_idConcesionario IN NUMBER
) AS
BEGIN
    DELETE FROM Concesionarios 
    WHERE idConcesionario = p_idConcesionario;
    COMMIT;
END;
/

-- Procedimientos para la entidad Proveedores

-- Insertar un nuevo proveedor
CREATE OR REPLACE PROCEDURE InsertarProveedor(
    p_nombre IN VARCHAR2,
    p_direccion IN VARCHAR2,
    p_noTelefono IN NUMBER
) AS
BEGIN
    INSERT INTO Proveedores (nombre, direccion, noTelefono) 
    VALUES (p_nombre, p_direccion, p_noTelefono);
    COMMIT;
END;
/

-- Actualizar la dirección de un proveedor
CREATE OR REPLACE PROCEDURE ActualizarDireccionProveedor(
    p_idProveedor IN NUMBER,
    p_direccion IN VARCHAR2
) AS
BEGIN
    UPDATE Proveedores
    SET direccion = p_direccion
    WHERE idProveedor = p_idProveedor;
    COMMIT;
END;
/

-- Eliminar un proveedor
CREATE OR REPLACE PROCEDURE EliminarProveedor(
    p_idProveedor IN NUMBER
) AS
BEGIN
    DELETE FROM Proveedores 
    WHERE idProveedor = p_idProveedor;
    COMMIT;
END;
/
