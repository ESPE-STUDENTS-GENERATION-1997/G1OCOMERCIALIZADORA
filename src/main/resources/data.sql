INSERT INTO xeest_estado (vigente_estado,descripcion_estado,keyword_estado,acceso_habilitado_estado) VALUES
	 (1,'REGISTRADO - DEBE CAMBIAR LA CONTRASEÑA','E001',1),
	 (1,' ESTADO ACTIVO','E002',1),
	 (1,'BLOQUEADO','E003',0),
	 (1,'OLVIDO LA CONTRASEÑA','E004',1),
	 (1,'CON CONTRASEÑA TEMPORAL','E005',1);
	 
 INSERT INTO pronvicias (descripcion_provincia,nombre_provincia) VALUES
 ('Está ubicada en la Sierra','Pichincha'),
 ('Está ubicada en la Costa Ecuatoriana','Guayas'),
 ('Está ubicada en el Oriente Ecuatoriano','Sucumbíos');
 
 
 
 INSERT INTO ciudades (descripcion_ciudad,nombre_ciudad,provincia) VALUES
	 ('Está ubicada sobre la hoya de Guayllabamba','Quito',1),
	 ('Su cabecera cantonal es la ciudad de Nueva Loja','Lago Agrio',3),
	 ('es la ciudad más grande de Ecuador','Guayaquil',2);
 
 
INSERT INTO xesis_sistem (vigencia_sistema,descripcion_sistema,keyword_sistema,nombre_sistema,id_sistema_original, img_portada , img_logo ) VALUES
	 (1,'Administrar usuarios y  productos','A001','Administración',NULL , '/img/monster-graffio.svg', '/img/monster-graffio.svg'),
	 (1,'Generar Reportes del Sistema','A002','Reportes',NULL , '/img/monster-graffio.svg', '/img/monster-graffio.svg'),
     (1,'Generar Comprobantes','A003','Comprobantes Electrónicos',NULL, '/img/monster-graffio.svg', '/img/monster-graffio.svg'),
	 (1,'Programar tareas de marketing','A004','Mercadeo',NULL, '/img/monster-graffio.svg', '/img/monster-graffio.svg'),
	 (1,'Tienta Virtual, enfocada a los clientes externos','A005','Tienda Virtual',NULL, '/img/monster-graffio.svg', '/img/monster-graffio.svg'),
	 (1,'Tiene acceso a todos los sistemas y subsitemas registrados','A000','Superusuario',NULL, '/img/monster-graffio.svg', '/img/monster-graffio.svg'),
	 (1,'Configurar recursos y variables del aplicativo','A006','Configuraciones',NULL, '/img/monster-graffio.svg', '/img/monster-graffio.svg'),
	 (1,'Administración Agencia de Viajes','A007','Agencia de Viajes',NULL, '/img/Portada-Viajecito.png', '/img/monster-graffio.svg' ),
	 (1,'Administración Aerolinea Condor','A008','Aerolinea Condor',NULL, '/img/Portada-Aerolinea.png', '/img/monster-graffio.svg');

INSERT INTO xerec_recur (descripcion_recurso,nombre_menu_recurso,titulo_pag_recurso,url_recurso ) VALUES
	 ('Usuarios','Usuarios','Administración de Usuarios','/users/index'),
	 ('Gestionar Productos','Gestión de Productos','Gestión de Productos','/store/products'),
	 ('Crear Facturas','Facturas','Facturas Electrónicas','/store/factura'),
	 ('Gestionar Clientes','Clientes','Gestionar Clientes','/customers'),
	 ('Gestionar los páginas del aplicativo','Páginas','Páginas del Aplicativo','/config/pages'),
	 ('Permite Editar Sistemas Originales y Crear Subsistemas','Sistemas del Aplicativo','Gestón Sistemas del Aplicativo','/config/systems'),
	 ('Permite gestionar los permisos del usuario','Permisos del Usuario','Gestión de Permisos del Usuario','/users/permissons'),
	 ('Venta Boletos de Aerolineas','Venta Boletos','Venta Boletos de Aerolineas','/agencia'),
	 ('Reporte de los Boletos vendidos a Clientes','Boletos Vendidos','Reporte de los Boletos vendidos a Clientes','/agencia/ventas'),
	 ('Creación de Vuelos','Creación de Vuelos','Creación de Vuelos','/aerolinea');



INSERT INTO xeres_recsys (fec_asignacion_sis_rec,fec_modificacion_perfil,fk_cod_recurso,fk_id_sistema) VALUES
	 ('2021-02-23 14:25:10',NULL,1,1),
	 ('2021-02-23 14:25:10',NULL,2,1),
	 ('2021-02-23 14:25:10',NULL,4,1),
	 ('2021-02-23 14:25:10',NULL,3,3),
	 ('2021-02-23 14:25:10',NULL,5,7),
	 ('2021-02-23 14:25:10',NULL,6,7),
	 ('2021-02-23 14:25:10',NULL,7,1),
	 ('2021-02-23 14:25:10',NULL,8,8),
	 ('2021-02-23 14:25:10',NULL,9,8),
	 ('2021-02-23 14:25:10',NULL,10,9);


INSERT INTO xedid_dirdom (ciudad_domicilio,num_domicilio,calle_prin_domicilio,barrio_domicilio,provincia_domicilio,calle_sec_domicilio) VALUES
	 ('QUITO','E00-001','El Inca','San Isidro','Pichincha','6 de Diciembre'),
	 ('QUITO','E00-001','10 de Agosto','El Eden','Pichincha','Rio Coca'),
	 ('Lago Agrio','2224','aaa','aa','Lago Agrio','ssss'),
	 ('Quito','12','Sangolqui, Av el inca y caranquis','sangolqui','Quito','sangolqui'),
	 ('QUITO','E00-001','10 de Agosto','El Eden','Pichincha','Rio Coca'),
	 ('QUITO','E00-001','10 de Agosto','El Eden','Pichincha','Rio Coca'),
	 ('QUITO','E00-001','10 de Agosto','El Eden','Pichincha','Rio Coca'),
	 ('QUITO','E00-001','10 de Agosto','El Eden','Pichincha','Rio Coca'),
	 ('QUITO','E00-001','10 de Agosto','El Eden','Pichincha','Rio Coca'),
	 ('QUITO','E00-001','10 de Agosto','El Eden','Pichincha','Rio Coca'),
	 ('QUITO','E00-001','10 de Agosto','El Eden','Pichincha','Rio Coca'),
	 ('QUITO','E00-001','10 de Agosto','El Eden','Pichincha','Rio Coca'),
	 ('QUITO','E00-001','10 de Agosto','El Eden','Pichincha','Rio Coca');
	 
/**
Tienda
dflasso  - pass: 123456789
socrisanto - pass: 123456789
vxvaca - pass: 12345
Arolinea
ae_dflasso  - pass: 123456789
ae_socrisanto - pass: 123456789
ae_vxvaca - pass: 12345
Agencia
ag_dflasso  - pass: 123456789
ag_socrisanto - pass: 123456789
ag_vxvaca - pass: 12345
*/

INSERT INTO xeusa_usuario (fec_creacion_usuario,fec_modificacion_usuario,fec_nacimiento_usuario,discapacidad_usuario,correo_usuario,apellidos_usuario,nombres_usuario,username_usuario,num_documento_usuario,password_usuario,telefono_usuario,tipo_usuario,fk_cod_domicilio) VALUES
	 ('2021-02-23 14:25:10',NULL,'2021-02-23 14:25:10',NULL,'dflasso1@espe.edu.ec','Lasso Ayala','Dany Fernando','dflasso','1726039967','{bcrypt}$2a$10$Y9BasCU6DF4sNGw3H6EBGuGSrEZArP1L1lvomPAmZ96gW0f1MQbf6','0999258192','ut001',1),
	 ('2021-02-23 14:25:10',NULL,'2021-02-23 14:25:10',NULL,'socrisanto@espe.edu.ec','Crisanto','Stalin','socrisanto','1723401715','{bcrypt}$2a$10$Y9BasCU6DF4sNGw3H6EBGuGSrEZArP1L1lvomPAmZ96gW0f1MQbf6','0995077348','ut001',2),
	 ('2021-03-08 21:53:39.243995000',NULL,'2021-03-08 21:53:39.243995000','no','vxvaca1@espe.edu.ec','Vaca','Xavier','vxvaca','1725982985','{bcrypt}$2a$10$QNAsnUchU46BwLlaAuWsVefOHlkA2iU6i1FtOmo3YX2WLKLwKUnzm','0998868008','ut001',4),
	 ('2021-02-23 14:25:10',NULL,'2021-02-23 14:25:10',NULL,'dflasso1@espe.edu.ec','Lasso Ayala','Dany Fernando','ae_dflasso','1726039967','{bcrypt}$2a$10$Y9BasCU6DF4sNGw3H6EBGuGSrEZArP1L1lvomPAmZ96gW0f1MQbf6','0999258192','ut001',3),
	 ('2021-02-23 14:25:10',NULL,'2021-02-23 14:25:10',NULL,'socrisanto@espe.edu.ec','Crisanto','Stalin','ae_socrisanto','1723401715','{bcrypt}$2a$10$Y9BasCU6DF4sNGw3H6EBGuGSrEZArP1L1lvomPAmZ96gW0f1MQbf6','0995077348','ut001',5),
	 ('2021-03-08 21:53:39.243995000',NULL,'2021-03-08 21:53:39.243995000','no','vxvaca1@espe.edu.ec','Vaca','Xavier','ae_vxvaca','1725982985','{bcrypt}$2a$10$QNAsnUchU46BwLlaAuWsVefOHlkA2iU6i1FtOmo3YX2WLKLwKUnzm','0998868008','ut001',6),
	 ('2021-02-23 14:25:10',NULL,'2021-02-23 14:25:10',NULL,'dflasso1@espe.edu.ec','Lasso Ayala','Dany Fernando','ag_dflasso','1726039967','{bcrypt}$2a$10$Y9BasCU6DF4sNGw3H6EBGuGSrEZArP1L1lvomPAmZ96gW0f1MQbf6','0999258192','ut001',7),
	 ('2021-02-23 14:25:10',NULL,'2021-02-23 14:25:10',NULL,'socrisanto@espe.edu.ec','Crisanto','Stalin','ag_socrisanto','1723401715','{bcrypt}$2a$10$Y9BasCU6DF4sNGw3H6EBGuGSrEZArP1L1lvomPAmZ96gW0f1MQbf6','0995077348','ut001',8),
	 ('2021-03-08 21:53:39.243995000',NULL,'2021-03-08 21:53:39.243995000','no','vxvaca1@espe.edu.ec','Vaca','Xavier','ag_vxvaca','1725982985','{bcrypt}$2a$10$QNAsnUchU46BwLlaAuWsVefOHlkA2iU6i1FtOmo3YX2WLKLwKUnzm','0998868008','ut001',9);


	 
INSERT INTO xeest_estusu (fec_asignacion_estusu,fec_expiracion_estusu,observation_estusu,fk_cod_estado,fk_cod_user) VALUES
	 ('2021-02-23 14:25:10',NULL,NULL,2,1),
	 ('2021-02-23 14:25:10',NULL,NULL,2,2),
	 ('2021-03-08 23:50:51.464986000',NULL,NULL,2,3),
	 ('2021-02-23 14:25:10',NULL,NULL,2,4),
	 ('2021-02-23 14:25:10',NULL,NULL,2,5),
	 ('2021-02-23 14:25:10',NULL,NULL,2,6),
	 ('2021-02-23 14:25:10',NULL,NULL,2,7),
	 ('2021-02-23 14:25:10',NULL,NULL,2,8),
	 ('2021-02-23 14:25:10',NULL,NULL,2,9);


INSERT INTO xeper_perfil (fec_asignacion_perfil,fec_expiracion_perfil,fec_modificacion_perfil,observation_perfil,fk_id_sistema,fk_cod_user_perfil) VALUES
	 ('2021-03-08 21:18:56.198041000',NULL,NULL,NULL,1,3),
	 ('2021-03-08 21:18:56.300326000',NULL,NULL,NULL,7,3),
	 ('2021-03-08 21:18:56.300326000',NULL,NULL,NULL,3,3),
	 ('2021-03-08 21:53:40.613345000',NULL,NULL,NULL,1,1),
	 ('2021-03-08 21:55:32.966298000',NULL,NULL,NULL,3,1),
	 ('2021-03-08 21:55:33.061321000',NULL,NULL,NULL,7,1),
	 ('2021-03-08 21:13:56.301151000',NULL,NULL,NULL,1,2),
	 ('2021-03-08 21:31:26.976692000',NULL,NULL,NULL,3,2),
	 ('2021-03-09 02:53:46.086332000',NULL,NULL,NULL,7,2),
	 ('2021-03-08 21:13:56.301151000',NULL,NULL,NULL,9,4),
	 ('2021-03-08 21:31:26.976692000',NULL,NULL,NULL,9,5),
	 ('2021-03-09 02:53:46.086332000',NULL,NULL,NULL,9,6),
	 ('2021-03-09 02:53:46.086332000',NULL,NULL,NULL,8,7),
	 ('2021-03-09 02:53:46.086332000',NULL,NULL,NULL,8,8),
	 ('2021-03-09 02:53:46.086332000',NULL,NULL,NULL,8,9);



INSERT INTO fepro_produc (created_product,description_product,img_product,name_product,price_product,stock_product) VALUES
	 ('2021-03-05','Samsung Galaxy A51','2eb2071f-f5f6-47e3-8a24-981d1c48aa89_A51.jpg','Celular A51',189.58,94),
	 ('2021-03-06','Laptop HP 13','aeb70f3b-0098-4530-9029-c53335bcc09f_hp-lap13.jpg','hp 13',999.80, 85),
	 ('2020-10-10','Cocina Indurama','f01bcca9-ba2d-4f10-bffa-752f13ea526d_cocina.jpg','Cocina Indurama',580.00,99);



INSERT INTO fecli_client (address_customer,document_customer,email_customer,lastnames_customer,names_customer,phone_customer) VALUES
	 ('sangolqui','1723401715','xavier@hotmail.com','Vaca','Xavier','0995077348'),
	 ('conocoto','1708051311','luiscrisanto@hotmail.com','Caiza','Oswaldo','0998868008'),
	 ('sangolqui','1708102718','miriam@hotmail.com','Caiza Llumiquinga','Miriam Mónica','0996901618'),
     ('sangolqui','1723401716','paul@hotmail.com','Alcivar','Paul','0995077348'),
	 ('conocoto','1708051312','oscar@hotmail.com','Sandoval','Oscar','0998868008'),
	 ('sangolqui','1708102719','juan@hotmail.com','Mendoza','Juan','0996901618');

INSERT INTO fefac_factur (descripcion_factura,emision_factura,observacion_factura,customer_id_customer, type_paid, numero_cuotas) VALUES
	 ('001-001-000000001','2021-03-07',NULL,1,'1', 0),
	 ('Factura de compras generales','2021-03-08',NULL,2,'1', 0),
	 ('001-001-000000002','2021-03-08',NULL,1,'1', 0),
	 ('Compra de artículos escolares','2021-03-08',NULL,2,'1', 0),
	 ('Factura de compra de víveres','2021-03-08',NULL,3,'1', 0),
	 ('001-001-000000006','2021-03-08',NULL,1,'1', 0),
	 ('001-001-000000007','2021-03-08',NULL,1,'1', 0),
	 ('','2021-03-08',NULL,2,'1', 0),
	 ('001-001-000000009','2021-03-08',NULL,1,'1', 0),
	 ('001-001-000000010','2021-03-09',NULL,1,'2',10);

INSERT INTO feite_itefac (cantidad_item,product_code_product,factura_id) VALUES
	 (1,1,1),
	 (2,1,2),
	 (2,3,2),
	 (2,1,3),
	 (4,2,3),
	 (1,1,4),
	 (2,1,5),
	 (3,3,5),
	 (2,1,6),
	 (3,1,7);
INSERT INTO feite_itefac (cantidad_item,product_code_product,factura_id) VALUES
	 (4,2,7),
	 (2,1,8),
	 (3,2,8),
	 (2,3,8),
	 (1,1,9),
	 (3,1,10),
	 (2,2,10),
	 (5,3,10),
	 (1,1,10);


