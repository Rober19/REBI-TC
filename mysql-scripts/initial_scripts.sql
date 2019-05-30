#create database if not exists rebi_tc;

#use rebi_tc;

/*
	private int id_db;
    private String nombre;
    private String email;
    private String password;
    private String id_institucional;
    private String id_ciudadania;
    private String role = "user";
    private String descripcion;
    private String ubicacion;
    private String img;
*/


CREATE TABLE IF NOT EXISTS usuarios
(
id int auto_increment PRIMARY KEY,
nombre VARCHAR(30) not null, 
password VARCHAR(30) not null,
email VARCHAR(30) not null,
id_ciudadania VARCHAR(30) not null,
id_institucional VARCHAR(30) not null,
role VARCHAR(30),
descripcion varchar(100),
ubicacion varchar(50),
img varchar(2000)
);

/*
	private int id_db;
    private int id_db_user;
    private int descargados;
    private int favoritos;
    private int visto;
    private String nombre;
    private String link_view;
    private String link_download;
    private String titulo;
    private String decripcion;
    private String categoria;
    private String[] tags;
*/

CREATE TABLE IF NOT EXISTS archivos
(
id int auto_increment PRIMARY KEY,
id_db_user int not null,
descargados int ,
favoritos int ,
visto int ,
nombre VARCHAR(100) not null, 
link_view VARCHAR(2000) not null,
link_download VARCHAR(2000) not null,
titulo VARCHAR(100) not null,
descripcion VARCHAR(100) not null,
categoria VARCHAR(100) not null,
tags VARCHAR(2000) not null
);

insert into archivos (id_db_user, nombre, link_view, link_download, titulo, descripcion, categoria, tags) values (
3,
"REBI-TC/FILES/234523241/Bk1nEn3pV",
"https://firebasestorage.googleapis.com/v0/b/rober-firebase.appspot.com/o/REBI-TC%2FFILES%2F234523241%2FBk1nEn3pV?generation=1559180451387795&alt=media&&token=HJBcEn364",
"https://www.googleapis.com/download/storage/v1/b/rober-firebase.appspot.com/o/REBI-TC%2FFILES%2F234523241%2FBk1nEn3pV?generation=1559180451387795&alt=media",
"proyecto de aula.pdf",
"trabajo de sustentación",
"TECNOLOGÍA",
"proyectos;tecnologia"
);

#ALTER TABLE usuarios CHANGE id_estudiantil id_institucional VARCHAR(30);
#ALTER TABLE usuarios add column role VARCHAR(30);
                 
select * from usuarios;

select * from archivos;

truncate table usuarios;


/*
Product product2 = new Product(2, "Queso Campesino Alpina", "https://images.rappi.com/products/599875-1534181942.png?d=150x150&e=webp", 5850);
Product product3 = new Product(3, "Jamon de Cerdo Pietran", "https://images.rappi.com/products/342208-1532483068.png?d=150x150&e=webp", 7050);
*/

insert into usuarios (nombre, password, email, id_ciudadania, id_institucional, role, descripcion, ubicacion, img) 
values (
"Fernando Araujo", "12345", "fercho@clandestine.com", "a123", "b123", "admin", "lo que sea mi tio",
 "La perimetral", "https://www.icesi.edu.co/unicesi/images/unicesi/eventos/2017/PLAGIO800_x600.jpg"
 );

