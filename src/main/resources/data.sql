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
	 
	 
 
 
INSERT INTO xesis_sistem (vigencia_sistema,descripcion_sistema,keyword_sistema,nombre_sistema,id_sistema_original) VALUES
	 (1,'Administrar usuarios y  productos','A001','Administracion',NULL),
	 (1,'Generar Reportes del Sistema','A002','Reportes',NULL),
	 (1,'Generar Comprobantes','A003','Comprobantes Eléctronicos',NULL),
	 (1,'Programar tareas de marketing','A004','Mercadeo',NULL),
	 (1,'Tienta Virtual, enfocada a los clientes externos','A005','Tienda Virtual',NULL),
	 (1,'Tiene acceso a todos los sistemas y subsitemas registrados','A000','Superusuario',NULL),
	 (1,'Configurar recursos y variables del aplicativo','A006','Configuraciones',NULL);


INSERT INTO xerec_recur (descripcion_recurso,nombre_menu_recurso,titulo_pag_recurso,url_recurso) VALUES
	 ('Usuarios','Usuarios','Administración de Usuarios','/users/index'),
	 ('Gestionar Productos','Gestión de Productos','Gestión de Productos','/store/products'),
	 ('Crear Facturas','Facturas','Facturas Electrónicas','/store/factura'),
	 ('Gestionar Clientes','Clientes','Gestionar Clientes','/customers'),
	 ('Gestionar los páginas del aplicativo','Páginas','Páginas del Aplicativo','/config/pages'),
	 ('Permite Editar Sistemas Originales y Crear Subsistemas','Sistemas del Aplicativo','Gestón Sistemas del Aplicativo','/config/systems'),
	 ('Permite gestionar los permisos del usuario','Permisos del Usuario','Gestión de Permisos del Usuario','/users/permissons');



INSERT INTO xeres_recsys (fec_asignacion_sis_rec,fec_modificacion_perfil,fk_cod_recurso,fk_id_sistema) VALUES
	 ('2021-02-23 14:25:10',NULL,1,1),
	 ('2021-02-23 14:25:10',NULL,2,1),
	 ('2021-02-23 14:25:10',NULL,4,1),
	 ('2021-02-23 14:25:10',NULL,3,3),
	 ('2021-02-23 14:25:10',NULL,5,7),
	 ('2021-02-23 14:25:10',NULL,6,7),
	 ('2021-02-23 14:25:10',NULL,7,1);


INSERT INTO xedid_dirdom (ciudad_domicilio,num_domicilio,calle_prin_domicilio,barrio_domicilio,provincia_domicilio,calle_sec_domicilio) VALUES
	 ('QUITO','E00-001','El Inca','San Isidro','Pichincha','6 de Diciembre'),
	 ('QUITO','E00-001','10 de Agosto','El Eden','Pichincha','Rio Coca'),
	 ('Lago Agrio','2224','aaa','aa','Lago Agrio','ssss'),
	 ('Quito','12','Sangolqui, Av el inca y caranquis','sangolqui','Quito','sangolqui');
	 
/**
dflasso  - pass: 123456789
socrisanto - pass: 123456789
vxvaca - pass: 12345
*/

INSERT INTO xeusa_usuario (fec_creacion_usuario,fec_modificacion_usuario,fec_nacimiento_usuario,discapacidad_usuario,correo_usuario,apellidos_usuario,nombres_usuario,username_usuario,num_documento_usuario,password_usuario,telefono_usuario,tipo_usuario,fk_cod_domicilio) VALUES
	 ('2021-02-23 14:25:10',NULL,'2021-02-23 14:25:10',NULL,'dflasso1@espe.edu.ec','Lasso Ayala','Dany Fernando','dflasso','1726039967','{bcrypt}$2a$10$Y9BasCU6DF4sNGw3H6EBGuGSrEZArP1L1lvomPAmZ96gW0f1MQbf6','0999258192','ut001',1),
	 ('2021-02-23 14:25:10',NULL,'2021-02-23 14:25:10',NULL,'socrisanto@espe.edu.ec','Crisanto','Stalin','socrisanto','1723401715','{bcrypt}$2a$10$Y9BasCU6DF4sNGw3H6EBGuGSrEZArP1L1lvomPAmZ96gW0f1MQbf6','0995077348','ut001',2),
	 ('2021-03-07 00:48:25.284508000',NULL,'2021-03-07 00:48:25.284486000','N/A','dannyflasso99@gmail.com','aaaaa','prueba','prueba','1726039967','{bcrypt}$2a$10$n8vztPQRbBJ3DD04nDVhv.KHk0Iqk5W0ULPBEl1rYK7TLOSQQJJr.','0999258192','ut001',4),
	 ('2021-03-08 21:53:39.243995000',NULL,'2021-03-08 21:53:39.243995000','no','vxvaca1@espe.edu.ec','Vaca','Xavier','vxvaca','1725982985','{bcrypt}$2a$10$QNAsnUchU46BwLlaAuWsVefOHlkA2iU6i1FtOmo3YX2WLKLwKUnzm','0998868008','ut001',4);


	 
INSERT INTO xeest_estusu (fec_asignacion_estusu,fec_expiracion_estusu,observation_estusu,fk_cod_estado,fk_cod_user) VALUES
	 ('2021-02-23 14:25:10',NULL,NULL,1,1),
	 ('2021-02-23 14:25:10',NULL,NULL,2,2),
	 ('2021-03-10 05:00:00',NULL,'generación de contraseña por pruebas',5,3),
	 ('2021-03-08 23:50:51.464986000',NULL,NULL,2,4);


INSERT INTO xeper_perfil (fec_asignacion_perfil,fec_expiracion_perfil,fec_modificacion_perfil,observation_perfil,fk_id_sistema,fk_cod_user_perfil) VALUES
	 ('2021-03-08 21:18:56.198041000',NULL,NULL,NULL,1,4),
	 ('2021-03-08 21:18:56.300326000',NULL,NULL,NULL,7,4),
	 ('2021-03-08 21:53:40.613345000',NULL,NULL,NULL,1,1),
	 ('2021-03-08 21:55:32.966298000',NULL,NULL,NULL,3,1),
	 ('2021-03-08 21:55:33.061321000',NULL,NULL,NULL,7,1),
	 ('2021-03-08 21:13:56.301151000',NULL,NULL,NULL,1,2),
	 ('2021-03-08 21:31:26.976692000',NULL,NULL,NULL,3,2),
	 ('2021-03-09 02:53:46.086332000',NULL,NULL,NULL,7,2);



INSERT INTO fepro_produc (created_product,description_product,img_product,name_product,price_product,stock_product) VALUES
	 ('2021-03-05','Descripción del producto 1','1e133819-9824-40b7-a2c1-2bd1c647fa25_55 vision.jpg','producto 1',2.5,4),
	 ('2021-03-06','Descripción del producto 2','0e6eb80d-3348-4257-a298-f19198552d30_20 integridad.jpg','producto 2',0.25,5),
	 ('2020-10-10','descripción del producto 4','782cffca-358f-4a73-a402-3b6ab0ac68a5_36 servicio8.jpg','producto 4',0.75,4);



INSERT INTO fecli_client (address_customer,document_customer,email_customer,lastnames_customer,names_customer,phone_customer) VALUES
	 ('sangolqui','1723401715','stalincrisanto@hotmail.com','Crisanto','Stalin','0995077348'),
	 ('conocoto','1708051311','luiscrisanto@hotmail.com','Caiza','Oswaldo','0998868008'),
	 ('sangolqui','1708102718','miriam@hotmail.com','Caiza Llumiquinga','Miriam Mónica','0996901618');

INSERT INTO fefac_factur (descripcion_factura,emision_factura,observacion_factura,customer_id_customer) VALUES
	 ('factura de compras','2021-03-07',NULL,1),
	 ('Factura de compras generales','2021-03-08',NULL,2),
	 ('Factura de artículos de aseo','2021-03-08',NULL,1),
	 ('Compra de artículos escolares','2021-03-08',NULL,2),
	 ('Factura de compra de víveres','2021-03-08',NULL,3),
	 ('factura de compras','2021-03-08',NULL,1),
	 ('','2021-03-08',NULL,1),
	 ('','2021-03-08',NULL,2),
	 ('','2021-03-08',NULL,1),
	 ('compras','2021-03-09',NULL,1);

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


