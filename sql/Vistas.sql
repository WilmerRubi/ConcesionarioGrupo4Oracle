
-- Vista tendenciasVentas
CREATE VIEW TendenciasVentas AS
SELECT 
    EXTRACT(YEAR FROM V.fecha) AS anio,
    EXTRACT(MONTH FROM V.fecha) AS mes,
    TO_CHAR(V.fecha, 'IW') AS semana,
    M.marca,
    C.sexo,
    CASE 
        WHEN C.ingresosAnuales < 30000 THEN 'Bajo'
        WHEN C.ingresosAnuales BETWEEN 30000 AND 60000 THEN 'Medio'
        ELSE 'Alto'
    END AS rango_ingresos,
    COUNT(V.idVenta) AS cantidad_ventas,
    SUM(V.precio) AS total_ventas
FROM 
    Ventas V
JOIN 
    Vehiculos VE ON V.VIN = VE.VIN
JOIN 
    Modelos M ON VE.idModelo = M.idModelo
JOIN 
    Clientes C ON V.idCliente = C.idCliente
GROUP BY 
    EXTRACT(YEAR FROM V.fecha), EXTRACT(MONTH FROM V.fecha), TO_CHAR(V.fecha, 'IW'), M.marca, C.sexo,
    CASE 
        WHEN C.ingresosAnuales < 30000 THEN 'Bajo'
        WHEN C.ingresosAnuales BETWEEN 30000 AND 60000 THEN 'Medio'
        ELSE 'Alto'
    END;


-- Vista top2marcasdolares
CREATE OR REPLACE VIEW Top2MarcasDolares AS
SELECT 
    M.marca, 
    SUM(V.precio) AS total_ventas
FROM 
    Ventas V
JOIN 
    Vehiculos VE ON V.VIN = VE.VIN
JOIN 
    Modelos M ON VE.idModelo = M.idModelo
WHERE 
    EXTRACT(YEAR FROM V.fecha) = EXTRACT(YEAR FROM SYSDATE)
GROUP BY 
    M.marca
ORDER BY 
    total_ventas DESC;

-- Vista top2marcasunidades
CREATE OR REPLACE VIEW Top2MarcasUnidades AS
SELECT 
    M.marca, 
    COUNT(V.idVenta) AS total_unidades
FROM 
    Ventas V
JOIN 
    Vehiculos VE ON V.VIN = VE.VIN
JOIN 
    Modelos M ON VE.idModelo = M.idModelo
WHERE 
    EXTRACT(YEAR FROM V.fecha) = EXTRACT(YEAR FROM SYSDATE)
GROUP BY 
    M.marca
ORDER BY 
    total_unidades DESC
FETCH FIRST 2 ROWS WITH TIES;


-- Vista mejoresconvertibles
CREATE OR REPLACE VIEW MejorMesConvertibles AS
SELECT 
    EXTRACT(MONTH FROM V.fecha) AS mes,
    COUNT(V.idVenta) AS total_convertibles
FROM 
    Ventas V
JOIN 
    Vehiculos VE ON V.VIN = VE.VIN
JOIN 
    Modelos M ON VE.idModelo = M.idModelo
WHERE 
    M.estiloCarroceria = 'Convertible'
GROUP BY 
    EXTRACT(MONTH FROM V.fecha)
ORDER BY 
    total_convertibles DESC
FETCH FIRST 1 ROWS ONLY;


-- Vista inventariopromedio
CREATE OR REPLACE VIEW InventarioPromedio AS
SELECT 
    C.nombre AS concesionario,
    AVG(SYSDATE - VE.fechaIngreso) AS tiempo_promedio_inventario
FROM 
    VehiculosxConcesionarios VC
JOIN 
    Vehiculos VE ON VC.VIN = VE.VIN
JOIN 
    Concesionarios C ON VC.idConcesionario = C.idConcesionario
WHERE 
    VE.VIN NOT IN (SELECT VIN FROM Ventas)
GROUP BY 
    C.nombre;

-- Vista VehiculoDefectuoso
CREATE VIEW VehiculosConTransmisionesDefectuosas AS
SELECT v.VIN, 
       v.idModelo, 
       v.transmision, 
       v.fechaIngreso, 
       c.nombre AS cliente, 
       c.idCliente, 
       p.nombre AS proveedor, 
       v.defectuosa
FROM Vehiculos v
JOIN Ventas ve ON v.VIN = ve.VIN
JOIN Clientes c ON ve.idCliente = c.idCliente
JOIN ModelosxProveedores mp ON v.idModelo = mp.idModelo
JOIN Proveedores p ON mp.idProveedor = p.idProveedor
WHERE v.defectuosa = 'S'; 