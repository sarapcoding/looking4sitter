# looking4sitter

## Descripción
Looking4Sitter es una aplicación web en la cual los padres o guarderias podrán buscar el niñero o niñera adecuado a sus necesidades. Cuenta con una lista de niñeros que muestran sus cualidades, páginas individuales para cada niñero con comentarios y puntuación de otros padres al niñero, un tablón de anuncios donde los padres podrán informar de qué tipo de niñero buscan y un sistema de mensajería.

## Entidades

Nombre | Descripción (Las entidades cuentan con un Id único y autoincremental que se comporta como la Primary Key de su tabla específica)
------- | -------
Usuario | Los usuarios cuentan con los siguientes atributos: login (string único de identificación), nombre, email, password, provincia, tarifa (para Sitters y Star Sitters) perfil (objeto Perfil) y descripción, además de una serie de listas que las relaciona con otras entidades OneToMany. Los usuarios vienen definidos por su relación con el tipo de Perfil con el que cuentan.
Perfil | Cada perfil viene identificado con su nombre. Se cuenta con un Administrador, Padre, Sitter, Star Sitter y Centro. Cada perfil cuenta con una relación con varios usuarios que viene definida en la lista de usuarios.
Anuncio | Escrito por los padres y agregado al tablón de anuncios. Los niñeros pueden pinchar en el y acceder a los datos de contacto del padre
Comentario | Comentario de los padres al niñero en el que se indica tambien la puntuación.
Mensaje | Los usuarios podrán comunicarse entre si mediante el envío de mensajes.

## Diagrama UML
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas/L4S_UML.jpg"
     alt="Diagrama UML"
     style="float: left; margin-right: 10px;" />
## Diagrama de entidades y su relación
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/diagramaer.png"
     alt="Diagrama entidades"
     style="float: left; margin-right: 10px;" />

## Configuración de la máquina virtual
Usamos vagrant/trusty32 para la ejecución de la aplicación.

### Instalación del JDK y de MYSQL Server
```bash
sudo apt-get update
# En el caso de que la instalación del JDK no funcionase por no encontrar el paquete, se debe ejecutar lo siguiente
sudo add-apt-repository ppa:openjdk-r/ppa
sudo apt-get update
# Instalación JDK
sudo apt-get install -y openjdk-8-jre
# Instalación de MySQL Server
sudo apt-get install mysql-server
mysql -u root -p
```

#### Configuración de la base de datos
Creación del schema vacío
```mysql
create database mybbdd;
```
Creación del usuario de la aplicación que accederá a la base de datos
```mysql
create user 'sitteradmin'@'localhost' identified by 'sitterpass';
grant all privileges on *.* to 'sitteradmin'@'localhost' with grant option;
```
#### Ejecución del jar en la máquina virtual
Arrancamos la máquina virtual
```
vagrant up
vagrant ssh
```
Accedemos a la carpeta en la que se contiene los jar (tanto el jar de la aplicación como el jar del servicio rest)
```
$ cd /vagrant
```
Arrancamos los jars de la siguiente manera en diferentes terminales:
```
$ java -jar xxxxxxxxxxxxxxxx-x.x.x-SNAPSHOT.jar
```

## Capturas
# Inicio
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas/welcome.PNG"
     alt="Página de inicio"
     style="float: left; margin-right: 10px;" />
     
# Inicio sesión
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas/inicio.PNG"
     alt="Inicio sesión"
     style="float: left; margin-right: 10px;" />
     
# Perfiles
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas/perfilpadre.PNG"
     alt="Perfil padre"
     style="float: left; margin-right: 10px;" />

<img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas/perfilsitter.PNG"
     alt="Perfil Sitter"
     style="float: left; margin-right: 10px;" />


# Registro
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas/registro1.PNG"
     alt="Registro"
     style="float: left; margin-right: 10px;" />
     
     
 <img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas/registropadre.PNG"
     alt="Registro Padre"
     style="float: left; margin-right: 10px;" />
     
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas/registrositter.PNG"
     alt="Registro Sitter"
     style="float: left; margin-right: 10px;" />
     
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas/registroexitoso.PNG"
     alt="Registro Exitoso"
     style="float: left; margin-right: 10px;" />



# Búsqueda de sitters
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas/resultadobusqueda.PNG"
     alt="Búsqueda sitters"
     style="float: left; margin-right: 10px;" />

# Búsqueda de anuncios
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas/tablonanuncios.PNG"
     alt="Búsqueda anuncio"
     style="float: left; margin-right: 10px;" />

# Creación de un anuncio
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas/publicaranuncio.PNG"
     alt="Creación anuncio"
     style="float: left; margin-right: 10px;" />


## Funcionalidades del servicio interno
- Servicio de notificación ante la recepcion de mensajes, tanto dentro de la propia pagina como notificacion via e-mail.
- Tablón de anuncios.
- Busqueda avanzada.
## Integrantes

Nombre | Apellidos | Correo | Cuenta de GitHub
------- | ------- | ------- | -------
Sara Patricia | Núñez Aguirre | sp.nuneza | sarapcoding
Alejandro | Checha Sánchez-Isasi | a.checas | xialda

## Herramienta de organización: Trello
Se ha empleado Trello para la organización del proyecto. Pueden verse los tableros en el siguiente enlace:
* [Fase 2](https://trello.com/b/qOFdWSJC)
* Fase 3 (no empezado)
* Fase 4 (no empezado)
* Fase 5 (no empezado)
