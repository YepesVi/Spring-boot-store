
Insert INTO cliente(nombre,apellido,email,create_at) VALUES('Fulanito','Calle','fc@g.com','2023-12-20');

Insert INTO cliente(nombre,apellido,email,create_at, clave, roles, status) VALUES('Fulanito','Calle','fc@g.com','2023-12-20', '123', 'Admin', 'true');

Insert INTO cliente(nombre,apellido,email,create_at) VALUES('Pepito','Perez','pp@g.com','2023-03-05');
Insert INTO Producto(nombre,descripcion, valor_unidad, stock) VALUES('Pala','paleadora','50000','20');
Insert INTO compra(cliente_id,valor, create_at) VALUES('1','50000','2023-03-05');
Insert INTO detalle(compra_id,Producto_id, cantidad) VALUES('1','2','5');