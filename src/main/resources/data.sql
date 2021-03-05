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
	 (1,'Administrar usuarios y  productos, acceso a todo el sistema.','A001','Administracion',null),
	 (1,'Generar Reportes del Sistema','A002','Reportes',null),
	 (1,'Generar Comprobantes','A003','Comprobantes Eléctronicos.',null),
	 (1,'Programar tareas de marketing.','A004','Mercadeo',null),
	 (1,'Tienta Virtual, enfocada a los clientes externos.','A005','Tienda Virtual',null);

INSERT INTO xedid_dirdom (ciudad_domicilio,num_domicilio,calle_prin_domicilio,barrio_domicilio,provincia_domicilio,calle_sec_domicilio) VALUES
	 ('QUITO','E00-001','El Inca','San Isidro','Pichincha','6 de Diciembre'),
	 ('QUITO','E00-001','10 de Agosto','El Eden','Pichincha','Rio Coca');
	 

INSERT INTO xeusa_usuario (fec_creacion_usuario,fec_modificacion_usuario,fec_nacimiento_usuario,discapacidad_usuario,correo_usuario,apellidos_usuario,nombres_usuario,username_usuario,num_documento_usuario,password_usuario,telefono_usuario,tipo_usuario,fk_cod_domicilio) VALUES
	 ('2021-02-23 14:25:10',NULL,'2021-02-23 14:25:10',NULL,'dflasso1@espe.edu.ec','Lasso Ayala','Dany Fernando','dflasso','1726039967','{bcrypt}$2a$10$gZYfdWr29/8f9CI2.7QBwuKCVsVsgLGG0kyBqs/1hkHtpq39OTRTK','0999258192','ut001',1),
	 ('2021-02-23 14:25:10',NULL,'2021-02-23 14:25:10',NULL,'socrisanto@espe.edu.ec','Crisanto','Stalin','socrisanto','1723401715','{bcrypt}$2a$10$Y9BasCU6DF4sNGw3H6EBGuGSrEZArP1L1lvomPAmZ96gW0f1MQbf6','0995077348','ut001',2);

	 
	INSERT INTO xeest_estusu (fec_asignacion_estusu,fec_expiracion_estusu,observation_estusu,fk_cod_estado,fk_cod_user) VALUES
	 ('2021-02-23 14:25:10',NULL,NULL,1,1),
	 ('2021-02-23 14:25:10',NULL,NULL,2,2);

INSERT INTO fepro_produc 
(name_product,description_product,price_product,stock_product,created_product,img_product) 
VALUES 
('producto 1','Descripción del producto 1',2.50,5,NOW(),''),
('producto 2','Descripción del producto 2',3.50,3,NOW(),'');

INSERT INTO fecli_client (names_customer,lastnames_customer,document_customer,email_customer,phone_customer,address_customer) VALUES
('Stalin','Crisanto','1723401715','stalincrisanto@hotmail.com','0995077348','sangolquí'),
('Oswald','Crisanto','1708051311','luiscrisanto2010@hotmail.com','0998868008','rumiloma'),
('Mónica','Caiza','1708102718','monica@hotmail.com','0996901618','fajardo');
