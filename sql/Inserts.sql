-- Inserts de la tabla Modelos
INSERT INTO Modelos (nombre, estiloCarroceria, marca) VALUES ('Porsche 911', 'Coupé', 'Porsche');
INSERT INTO Modelos (nombre, estiloCarroceria, marca) VALUES ('Ferrari F8 Tributo', 'Coupé', 'Ferrari');
INSERT INTO Modelos (nombre, estiloCarroceria, marca) VALUES ('Lamborghini Huracán', 'Coupé', 'Lamborghini');
INSERT INTO Modelos (nombre, estiloCarroceria, marca) VALUES ('Aston Martin Vantage', 'Coupé', 'Aston Martin');
INSERT INTO Modelos (nombre, estiloCarroceria, marca) VALUES ('McLaren 720S', 'Coupé', 'McLaren');
INSERT INTO Modelos (nombre, estiloCarroceria, marca) VALUES ('Chevrolet Corvette C8', 'Coupé', 'Chevrolet');
INSERT INTO Modelos (nombre, estiloCarroceria, marca) VALUES ('Porsche 718 Cayman', 'Coupé', 'Porsche');
INSERT INTO Modelos (nombre, estiloCarroceria, marca) VALUES ('Ferrari 488 GTB', 'Coupé', 'Ferrari');
INSERT INTO Modelos (nombre, estiloCarroceria, marca) VALUES ('Lamborghini Aventador', 'Coupé', 'Lamborghini');
INSERT INTO Modelos (nombre, estiloCarroceria, marca) VALUES ('Aston Martin DB11', 'Coupé', 'Aston Martin');
INSERT INTO Modelos (nombre, estiloCarroceria, marca) VALUES ('McLaren 600LT', 'Coupé', 'McLaren');
INSERT INTO Modelos (nombre, estiloCarroceria, marca) VALUES ('Chevrolet Camaro ZL1', 'Coupé', 'Chevrolet');
INSERT INTO Modelos (nombre, estiloCarroceria, marca) VALUES ('Ferrari Portofino', 'Convertible', 'Ferrari');

--Inserts de la tabla Plantas
INSERT INTO Plantas (nombre, ubicacion) VALUES ('Planta Porsche', 'Stuttgart, Alemania');
INSERT INTO Plantas (nombre, ubicacion) VALUES ('Planta Ferrari', 'Maranello, Italia');
INSERT INTO Plantas (nombre, ubicacion) VALUES ('Planta Lamborghini', 'Sant’Agata Bolognese, Italia');
INSERT INTO Plantas (nombre, ubicacion) VALUES ('Planta Aston Martin', 'Gaydon, Inglaterra');
INSERT INTO Plantas (nombre, ubicacion) VALUES ('Planta McLaren', 'Woking, Inglaterra');
INSERT INTO Plantas (nombre, ubicacion) VALUES ('Planta Chevrolet', 'Detroit, Michigan, Estados Unidos');

-- Inserts de la tabla Proveedores
INSERT INTO Proveedores (nombre, direccion, noTelefono) VALUES ('Bosch', 'Stuttgart, Alemania', 493071305);
INSERT INTO Proveedores (nombre, direccion, noTelefono) VALUES ('Magna International', 'Aurora, Ontario, Canadá', 19057916600);
INSERT INTO Proveedores (nombre, direccion, noTelefono) VALUES ('Continental', 'Hannover, Alemania', 49551130);
INSERT INTO Proveedores (nombre, direccion, noTelefono) VALUES ('Denso', 'Kariya, Japón', 815669010);
INSERT INTO Proveedores (nombre, direccion, noTelefono) VALUES ('Valeo', 'Paris, Francia', 33147184000);
INSERT INTO Proveedores (nombre, direccion, noTelefono) VALUES ('ZF Friedrichshafen', 'Friedrichshafen, Alemania', 497541970);

-- Inserts de la tabla Vehiculos
INSERT INTO Vehiculos (VIN, idModelo, color, noMotor, transmision, fechaIngreso) 
VALUES ('WP0AA2A91JS123456', 1, 'Rojo', 123456789, 'Automática', TO_DATE('2024-12-08', 'YYYY-MM-DD'));
INSERT INTO Vehiculos (VIN, idModelo, color, noMotor, transmision, fechaIngreso) 
VALUES ('ZFF79ALA4K0245678', 2, 'Amarillo', 987654321, 'Manual', TO_DATE('2024-12-08', 'YYYY-MM-DD'));
INSERT INTO Vehiculos (VIN, idModelo, color, noMotor, transmision, fechaIngreso) 
VALUES ('ZHWUC1ZF8JLA45678', 3, 'Verde', 112233445, 'Automática', TO_DATE('2024-12-08', 'YYYY-MM-DD'));
INSERT INTO Vehiculos (VIN, idModelo, color, noMotor, transmision, fechaIngreso) 
VALUES ('SCFRMFAW7JGA98765', 4, 'Negro', 998877665, 'Manual', TO_DATE('2024-12-08', 'YYYY-MM-DD'));
INSERT INTO Vehiculos (VIN, idModelo, color, noMotor, transmision, fechaIngreso) 
VALUES ('SBM13RCA1JW432456', 5, 'Azul', 223344556, 'Automática', TO_DATE('2024-12-08', 'YYYY-MM-DD'));
INSERT INTO Vehiculos (VIN, idModelo, color, noMotor, transmision, fechaIngreso) 
VALUES ('1G1YX3D74J5123456', 6, 'Blanco', 556677889, 'Manual', TO_DATE('2024-12-08', 'YYYY-MM-DD'));
INSERT INTO Vehiculos (VIN, idModelo, color, noMotor, transmision, fechaIngreso) 
VALUES ('WP0AA2A91JS123457', 7, 'Azul', 123456790, 'Automática', TO_DATE('2024-12-08', 'YYYY-MM-DD'));
INSERT INTO Vehiculos (VIN, idModelo, color, noMotor, transmision, fechaIngreso) 
VALUES ('ZFF79ALA4K0245679', 8, 'Blanco', 987654322, 'Automática', TO_DATE('2024-12-08', 'YYYY-MM-DD'));
INSERT INTO Vehiculos (VIN, idModelo, color, noMotor, transmision, fechaIngreso) 
VALUES ('ZHWUC1ZF8JLA45679', 9, 'Rojo', 112233446, 'Manual', TO_DATE('2024-12-08', 'YYYY-MM-DD'));
INSERT INTO Vehiculos (VIN, idModelo, color, noMotor, transmision, fechaIngreso) 
VALUES ('SCFRMFAW7JGA98766', 10, 'Verde', 998877667, 'Automática', TO_DATE('2024-12-08', 'YYYY-MM-DD'));
INSERT INTO Vehiculos (VIN, idModelo, color, noMotor, transmision, fechaIngreso) 
VALUES ('SBM13RCA1JW432457', 11, 'Negro', 223344557, 'Manual', TO_DATE('2024-12-08', 'YYYY-MM-DD'));
INSERT INTO Vehiculos (VIN, idModelo, color, noMotor, transmision, fechaIngreso) 
VALUES ('1G1YX3D74J5123457', 12, 'Amarillo', 556677890, 'Automática', TO_DATE('2024-12-08', 'YYYY-MM-DD'));
INSERT INTO Vehiculos (VIN, idModelo, color, noMotor, transmision, fechaIngreso) 
VALUES ('ZFF80AMAXK0256789', 13, 'Rojo', 334455667, 'Automática', TO_DATE('2024-12-08', 'YYYY-MM-DD'));

-- Inserts de la tabla Concesionarios
INSERT INTO Concesionarios (nombre, direccion, noTelefono) VALUES ('Porsche Zentrum', 'Stuttgart, Alemania', 490711120);
INSERT INTO Concesionarios (nombre, direccion, noTelefono) VALUES ('Ferrari World', 'Maranello, Italia', 39053440200);
INSERT INTO Concesionarios (nombre, direccion, noTelefono) VALUES ('Lamborghini Milano', 'Milán, Italia', 39028901234);
INSERT INTO Concesionarios (nombre, direccion, noTelefono) VALUES ('Aston Martin Works', 'Gaydon, Inglaterra', 442932147800);
INSERT INTO Concesionarios (nombre, direccion, noTelefono) VALUES ('McLaren London', 'Woking, Inglaterra', 442087512345);
INSERT INTO Concesionarios (nombre, direccion, noTelefono) VALUES ('Chevrolet Performance', 'Detroit, Michigan, Estados Unidos', 3138524765);

-- Inserts de la tabla Clientes
INSERT INTO Clientes (nombre, direccion, noTelefono, sexo, ingresosAnuales) VALUES ('Juan Pérez', 'Calle Reforma 45, Ciudad de México', 5552321345, 'Masculino', 45000);
INSERT INTO Clientes (nombre, direccion, noTelefono, sexo, ingresosAnuales) VALUES ('María González', 'Calle Fco. Villa 99, Guadalajara', 5559988776, 'Femenino', 75000);
INSERT INTO Clientes (nombre, direccion, noTelefono, sexo, ingresosAnuales) VALUES ('Carlos López', 'Av. 5 de Febrero 123, Querétaro', 5556754321, 'Masculino', 120000);
INSERT INTO Clientes (nombre, direccion, noTelefono, sexo, ingresosAnuales) VALUES ('Isabella Müller', 'Berliner Strasse 45, Berlín, Alemania', 4930178567, 'Femenino', 95000);
INSERT INTO Clientes (nombre, direccion, noTelefono, sexo, ingresosAnuales) VALUES ('David Schneider', 'Wiener Strasse 75, Viena, Austria', 4312345678, 'Masculino', 60000);
INSERT INTO Clientes (nombre, direccion, noTelefono, sexo, ingresosAnuales) VALUES ('Hyun Lee', 'Gwangju, Corea del Sur', 8263254567, 'Femenino', 52000);

-- Inserts de la tabla VehiculosxConcesionarios
INSERT INTO VehiculosxConcesionarios (VIN, idConcesionario) VALUES ('WP0AA2A91JS123456', 1);
INSERT INTO VehiculosxConcesionarios (VIN, idConcesionario) VALUES ('ZFF79ALA4K0245678', 2);
INSERT INTO VehiculosxConcesionarios (VIN, idConcesionario) VALUES ('ZHWUC1ZF8JLA45678', 3);
INSERT INTO VehiculosxConcesionarios (VIN, idConcesionario) VALUES ('SCFRMFAW7JGA98765', 4);
INSERT INTO VehiculosxConcesionarios (VIN, idConcesionario) VALUES ('SBM13RCA1JW432456', 5);
INSERT INTO VehiculosxConcesionarios (VIN, idConcesionario) VALUES ('1G1YX3D74J5123456', 6);
INSERT INTO VehiculosxConcesionarios (VIN, idConcesionario) VALUES ('ZFF80AMAXK0256789', 2);

-- Inserts de la tabla ModelosxPlantas
INSERT INTO ModelosxPlantas (idModelo, idPlanta) VALUES (1, 1);
INSERT INTO ModelosxPlantas (idModelo, idPlanta) VALUES (2, 2);
INSERT INTO ModelosxPlantas (idModelo, idPlanta) VALUES (3, 3);
INSERT INTO ModelosxPlantas (idModelo, idPlanta) VALUES (4, 4);
INSERT INTO ModelosxPlantas (idModelo, idPlanta) VALUES (5, 5);
INSERT INTO ModelosxPlantas (idModelo, idPlanta) VALUES (6, 6);
INSERT INTO ModelosxPlantas (idModelo, idPlanta) VALUES (13, 2);

-- Inserts de la tabla ModelosxProveedores
INSERT INTO ModelosxProveedores (idModelo, idProveedor) VALUES (1, 1);
INSERT INTO ModelosxProveedores (idModelo, idProveedor) VALUES (2, 2);
INSERT INTO ModelosxProveedores (idModelo, idProveedor) VALUES (3, 3);
INSERT INTO ModelosxProveedores (idModelo, idProveedor) VALUES (4, 4);
INSERT INTO ModelosxProveedores (idModelo, idProveedor) VALUES (5, 5);
INSERT INTO ModelosxProveedores (idModelo, idProveedor) VALUES (6, 6);
INSERT INTO ModelosxProveedores (idModelo, idProveedor) VALUES (13, 2);


-- Inserts de la tabla Ventas
INSERT INTO Ventas (fecha, idConcesionario, idCliente, VIN, precio)
VALUES (TO_DATE('2024-12-08', 'YYYY-MM-DD'), 1, 1, 'WP0AA2A91JS123456', 150000);
INSERT INTO Ventas (fecha, idConcesionario, idCliente, VIN, precio)
VALUES (TO_DATE('2024-12-08', 'YYYY-MM-DD'), 2, 2, 'ZFF79ALA4K0245678', 220000);
INSERT INTO Ventas (fecha, idConcesionario, idCliente, VIN, precio)
VALUES (TO_DATE('2024-12-08', 'YYYY-MM-DD'), 3, 3, 'ZHWUC1ZF8JLA45678', 180000);
INSERT INTO Ventas (fecha, idConcesionario, idCliente, VIN, precio)
VALUES (TO_DATE('2024-12-08', 'YYYY-MM-DD'), 4, 4, 'SCFRMFAW7JGA98765', 210000);
INSERT INTO Ventas (fecha, idConcesionario, idCliente, VIN, precio)
VALUES (TO_DATE('2024-12-08', 'YYYY-MM-DD'), 5, 5, 'SBM13RCA1JW432456', 200000);
INSERT INTO Ventas (fecha, idConcesionario, idCliente, VIN, precio)
VALUES (TO_DATE('2024-12-08', 'YYYY-MM-DD'), 6, 6, '1G1YX3D74J5123456', 75000);
INSERT INTO Ventas (fecha, idConcesionario, idCliente, VIN, precio) 
VALUES (TO_DATE('2024-12-08', 'YYYY-MM-DD'), 2, 2, 'ZFF80AMAXK0256789', 230000);
