

-- Disparador para Crear en Clientes
CREATE OR REPLACE TRIGGER tr_CrearCliente
AFTER INSERT ON Clientes
FOR EACH ROW
BEGIN
    INSERT INTO Bitacora (accion, tabla, registro)
    VALUES ('CREAR', 'Clientes', 'idCliente=' || :NEW.idCliente || ', nombre=' || :NEW.nombre || ', direccion=' || :NEW.direccion || ', noTelefono=' || :NEW.noTelefono || ', sexo=' || :NEW.sexo || ', ingresosAnuales=' || :NEW.ingresosAnuales);
END;

/

-- Disparador para Modificar en Clientes
CREATE OR REPLACE TRIGGER tr_ModificarCliente
AFTER UPDATE ON Clientes
FOR EACH ROW
BEGIN
    INSERT INTO Bitacora (accion, tabla, registro)
    VALUES ('MODIFICAR', 'Clientes', 'idCliente=' || :NEW.idCliente || ', nombre=' || :NEW.nombre || ', direccion=' || :NEW.direccion || ', noTelefono=' || :NEW.noTelefono || ', sexo=' || :NEW.sexo || ', ingresosAnuales=' || :NEW.ingresosAnuales);
END;

/

-- Disparador para Eliminar en Clientes
CREATE OR REPLACE TRIGGER tr_EliminarCliente
AFTER DELETE ON Clientes
FOR EACH ROW
BEGIN
    INSERT INTO Bitacora (accion, tabla, registro)
    VALUES ('ELIMINAR', 'Clientes', 'idCliente=' || :OLD.idCliente || ', nombre=' || :OLD.nombre || ', direccion=' || :OLD.direccion || ', noTelefono=' || :OLD.noTelefono || ', sexo=' || :OLD.sexo || ', ingresosAnuales=' || :OLD.ingresosAnuales);
END;

/

-- Disparador para Crear en Concesionarios
CREATE OR REPLACE TRIGGER tr_CrearConcesionario
AFTER INSERT ON Concesionarios
FOR EACH ROW
BEGIN
    INSERT INTO Bitacora (accion, tabla, registro)
    VALUES ('CREAR', 'Concesionarios', 'idConcesionario=' || :NEW.idConcesionario || ', nombre=' || :NEW.nombre || ', direccion=' || :NEW.direccion || ', noTelefono=' || :NEW.noTelefono);
END;

/

-- Disparador para Modificar en Concesionarios
CREATE OR REPLACE TRIGGER tr_ModificarConcesionario
AFTER UPDATE ON Concesionarios
FOR EACH ROW
BEGIN
    INSERT INTO Bitacora (accion, tabla, registro)
    VALUES ('MODIFICAR', 'Concesionarios', 'idConcesionario=' || :NEW.idConcesionario || ', nombre=' || :NEW.nombre || ', direccion=' || :NEW.direccion || ', noTelefono=' || :NEW.noTelefono);
END;

/

-- Disparador para Eliminar en Concesionarios
CREATE OR REPLACE TRIGGER tr_EliminarConcesionario
AFTER DELETE ON Concesionarios
FOR EACH ROW
BEGIN
    INSERT INTO Bitacora (accion, tabla, registro)
    VALUES ('ELIMINAR', 'Concesionarios', 'idConcesionario=' || :OLD.idConcesionario || ', nombre=' || :OLD.nombre || ', direccion=' || :OLD.direccion || ', noTelefono=' || :OLD.noTelefono);
END;

/

-- Disparador para Crear en Proveedores
CREATE OR REPLACE TRIGGER tr_CrearProveedor
AFTER INSERT ON Proveedores
FOR EACH ROW
BEGIN
    INSERT INTO Bitacora (accion, tabla, registro)
    VALUES ('CREAR', 'Proveedores', 'idProveedor=' || :NEW.idProveedor || ', nombre=' || :NEW.nombre || ', direccion=' || :NEW.direccion || ', noTelefono=' || :NEW.noTelefono);
END;

/

-- Disparador para Modificar en Proveedores
CREATE OR REPLACE TRIGGER tr_ModificarProveedor
AFTER UPDATE ON Proveedores
FOR EACH ROW
BEGIN
    INSERT INTO Bitacora (accion, tabla, registro)
    VALUES ('MODIFICAR', 'Proveedores', 'idProveedor=' || :NEW.idProveedor || ', nombre=' || :NEW.nombre || ', direccion=' || :NEW.direccion || ', noTelefono=' || :NEW.noTelefono);
END;

/

-- Disparador para Eliminar en Proveedores
CREATE OR REPLACE TRIGGER tr_EliminarProveedor
AFTER DELETE ON Proveedores
FOR EACH ROW
BEGIN
    INSERT INTO Bitacora (accion, tabla, registro)
    VALUES ('ELIMINAR', 'Proveedores', 'idProveedor=' || :OLD.idProveedor || ', nombre=' || :OLD.nombre || ', direccion=' || :OLD.direccion || ', noTelefono=' || :OLD.noTelefono);
END;

/

-- Disparador para Crear en Plantas
CREATE OR REPLACE TRIGGER tr_CrearPlanta
AFTER INSERT ON Plantas
FOR EACH ROW
BEGIN
    INSERT INTO Bitacora (accion, tabla, registro)
    VALUES ('CREAR', 'Plantas', 'idPlanta=' || :NEW.idPlanta || ', nombre=' || :NEW.nombre || ', ubicacion=' || :NEW.ubicacion);
END;

/

-- Disparador para Modificar en Plantas
CREATE OR REPLACE TRIGGER tr_ModificarPlanta
AFTER UPDATE ON Plantas
FOR EACH ROW
BEGIN
    INSERT INTO Bitacora (accion, tabla, registro)
    VALUES ('MODIFICAR', 'Plantas', 'idPlanta=' || :NEW.idPlanta || ', nombre=' || :NEW.nombre || ', ubicacion=' || :NEW.ubicacion);
END;

/

-- Disparador para Eliminar en Plantas
CREATE OR REPLACE TRIGGER tr_EliminarPlanta
AFTER DELETE ON Plantas
FOR EACH ROW
BEGIN
    INSERT INTO Bitacora (accion, tabla, registro)
    VALUES ('ELIMINAR', 'Plantas', 'idPlanta=' || :OLD.idPlanta || ', nombre=' || :OLD.nombre || ', ubicacion=' || :OLD.ubicacion);
END;

/

-- Disparador para Crear en Modelos
CREATE OR REPLACE TRIGGER tr_CrearModelo
AFTER INSERT ON Modelos
FOR EACH ROW
BEGIN
    INSERT INTO Bitacora (accion, tabla, registro)
    VALUES ('CREAR', 'Modelos', 'idModelo=' || :NEW.idModelo || ', nombre=' || :NEW.nombre || ', estiloCarroceria=' || :NEW.estiloCarroceria || ', marca=' || :NEW.marca);
END;

/

-- Disparador para Modificar en Modelos
CREATE OR REPLACE TRIGGER tr_ModificarModelo
AFTER UPDATE ON Modelos
FOR EACH ROW
BEGIN
    INSERT INTO Bitacora (accion, tabla, registro)
    VALUES ('MODIFICAR', 'Modelos', 'idModelo=' || :NEW.idModelo || ', nombre=' || :NEW.nombre || ', estiloCarroceria=' || :NEW.estiloCarroceria || ', marca=' || :NEW.marca);
END;

/

-- Disparador para Eliminar en Modelos
CREATE OR REPLACE TRIGGER tr_EliminarModelo
AFTER DELETE ON Modelos
FOR EACH ROW
BEGIN
    INSERT INTO Bitacora (accion, tabla, registro)
    VALUES ('ELIMINAR', 'Modelos', 'idModelo=' || :OLD.idModelo || ', nombre=' || :OLD.nombre || ', estiloCarroceria=' || :OLD.estiloCarroceria || ', marca=' || :OLD.marca);
END;