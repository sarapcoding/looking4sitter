# looking4sitter

## Descripción
Looking4Sitter es una aplicación web en la cual los padres o guarderias podrán buscar el niñero o niñera adecuado a sus necesidades. Cuenta con una lista de niñeros que muestran sus cualidades, páginas individuales para cada niñero con comentarios y puntuación de otros padres al niñero y un tablón de anuncios donde los padres podrán informar de qué tipo de niñero buscan.

## Entidades

Nombre | Descripción (Las entidades cuentan con un Id único y autoincremental que se comporta como la Primary Key de su tabla específica)
------- | -------
Usuario | Los usuarios cuentan con los siguientes atributos: login (string único de identificación), nombre, email, password, provincia, tarifa (para Sitters y Star Sitters) perfil (objeto Perfil) y descripción, además de una serie de listas que las relaciona con otras entidades OneToMany. Los usuarios vienen definidos por su relación con el tipo de Perfil con el que cuentan.
Anuncio | Escrito por los padres y agregado al tablón de anuncios. Los niñeros pueden pinchar en el y acceder a los datos de contacto del padre
Comentario | Comentario de los padres al niñero en el que se indica tambien la puntuación.


## Diagrama UML
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas_finales/L4SF_UML.jpg"
     alt="Diagrama UML"
     style="float: left; margin-right: 10px;" />
## Diagrama de entidades y su relación
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/diagramaer.png"
     alt="Diagrama entidades"
     style="float: left; margin-right: 10px;" />

## Configuración de la máquina virtual
Usamos ubuntu/trusty32 con vagrant para la ejecución de la aplicación. En nuestro caso empleamos dos máquinas virtuales con las direcciones IP 192.168.33.10 y 192.168.33.20, reservando la dirección IP 192.168.33.33 para HAProxy

### Instalación del JDK y de MYSQL Server
```bash
$ sudo apt-get update
# En el caso de que la instalación del JDK no funcionase por
# no encontrar el paquete, se debe ejecutar lo siguiente
$ sudo add-apt-repository ppa:openjdk-r/ppa
$ sudo apt-get update
# Instalación JDK
$ sudo apt-get install -y openjdk-8-jre
# Instalación de MySQL Server
$ sudo apt-get install mysql-server
$ mysql -u root -p
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
```bash
$ vagrant up
$ vagrant ssh
```
Accedemos a la carpeta en la que se contiene los jar (tanto el jar de la aplicación como el jar del servicio rest)
```
$ cd /vagrant
```
Arrancamos los jars de la siguiente manera en diferentes terminales:
```
$ java -jar xxxxxxxxxxxxxxxx-x.x.x-SNAPSHOT.jar
```

## Balanceo de carga
### Configuración de HAProxy
Creamos una máquina virtual separada en la que instalamos HAProxy.
```bash
$ sudo apt-get install haproxy
```
Nos aseguramos que tengamos la versión **HAProxy 1.6**:
```
$ haproxy -version
HA-Proxy version 1.6.14-1ppa1~trusty-66af4a1 2018/01/06
Copyright 2000-2018 Willy Tarreau <willy@haproxy.org>
```
Se debe configurar el archivo haproxy.cfg:
```bash
$ cd /etc/haproxy
$ sudo vim haproxy.cfg
```
Se añade después de las secciones global y default con lo siguiente para tener dos nodos:
```
frontend localhost
        bind *:80
        bind *:443
        option tcplog
        mode tcp
        default_backend nodes

backend nodes
        mode tcp
        balance roundrobin
        option ssl-hello-chk
        server node1 192.168.33.10:8443 check
        server node2 192.168.33.20:8443 check
```
En esta configuración se tiene dos nodos, node1 y node2 que corresponden a las IP 192.168.33.10 y 192.168.33.20 respectivamente. Para poder configurar un nodo nuevo, se debe completar el siguiente código y añadirlo al final de backend nodes:
```
server nodeX XXX.XXX.XXX.XXX:YYYY check
```
Siendo XXX.XXX.XXX.XXX la IP de la máquina en la que se está ejecutando la aplicación e YYYY el puerto.
La configuración frontend cuenta con el modo TCP y la opción TCPLog, significando que las conexiones a los nodos quedarán reflejadas en el log que se encuentra en /var/log/haproxy.log al que se deberá acceder de la siguiente forma:
```
$ sudo vim /var/log/haproxy.log
```
En el podemos leer los logs, debido a problemas con la versión de HAProxy 1.6 y su incompatibilidad con 1.4, no se puede acceder a la página de stats para comprobar las conexiones a los nodos.

Para arrancar el servicio del balanceo de carga se usa el siguiente comando:
```
$ sudo service haproxy start
```
Y para volver a arrancarlo:
```
$ sudo service haproxy restart
```
Para pararlo:
```
$ sudo service haproxy stop
```

## Capturas
### Inicio
<img src="https://raw.githubusercontent.com/sarapcoding/looking4sitter/master/capturas_finales/welcome.PNG"
     alt="Página de inicio"
     style="float: left; margin-right: 10px;" />
     
### Inicio sesión
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas_finales/iniciosesion.PNG"
     alt="Inicio sesión"
     style="float: left; margin-right: 10px;" />
     
### Perfiles
#### Perfil de un padre
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas_finales/inicioperfilpadre.PNG"
     alt="Perfil padre"
     style="float: left; margin-right: 10px;" />
#### Perfil de un sitter
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas_finales/perfilsitter.PNG"
     alt="Perfil Sitter"
     style="float: left; margin-right: 10px;" />


### Registro
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas_finales/registro.PNG"
     alt="Registro"
     style="float: left; margin-right: 10px;" />
     
#### Registro de un padre
 <img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas_finales/registroPadre.PNG"
     alt="Registro Padre"
     style="float: left; margin-right: 10px;" />
     
#### Registro de un sitter
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas_finales/registrositter.PNG"
     alt="Registro Sitter"
     style="float: left; margin-right: 10px;" />
#### Registro exitoso
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas_finales/registrosuccess.PNG"
     alt="Registro Exitoso"
     style="float: left; margin-right: 10px;" />



### Búsqueda de sitters
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas_finales/busquedasitterspadre.PNG"
     alt="Búsqueda sitters"
     style="float: left; margin-right: 10px;" />

### Búsqueda de anuncios
#### Tablero de anuncios completo
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas_finales/tableroanunciossitter.PNG"
     alt="Búsqueda anuncio"
     style="float: left; margin-right: 10px;" />
#### Tablero de anuncios con búsqueda específica por fecha
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas_finales/busquedaanunciofechasitter.PNG"
     alt="Búsqueda anuncio por fecha"
     style="float: left; margin-right: 10px;" />

### Creación de un anuncio
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas_finales/publicacionanuncio.PNG"
     alt="Creación anuncio"
     style="float: left; margin-right: 10px;" />
     
### Edición de un anuncio
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas_finales/edicionanuncio.PNG"
     alt="Edición anuncio"
     style="float: left; margin-right: 10px;" />

### Anuncios publicados 
#### Tablero de anuncios del padre
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas_finales/tableroanunciospadre.PNG"
     alt="Listado de anuncios"
     style="float: left; margin-right: 10px;" />
     
#### Tablero de anuncios vacío
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas_finales/anunciospublicadosvaciopadre.PNG"
     alt="Sin anuncios"
     style="float: left; margin-right: 10px;" />

### Comentarios
#### Comentarios escritos por el padre
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas_finales/escribircomentario.PNG"
     alt="Escribir comentario"
     style="float: left; margin-right: 10px;" />
     
#### Comentarios recibidos del sitter
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/capturas_finales/comentariossitter.PNG"
     alt="Comentarios recibidos"
     style="float: left; margin-right: 10px;" />

## Funcionalidades del servicio interno
- Tablón de anuncios.
- Busqueda avanzada.
## Integrantes

Nombre | Apellidos | Correo | Cuenta de GitHub
------- | ------- | ------- | -------
Sara Patricia | Núñez Aguirre | sp.nuneza | sarapcoding
Alejandro | Checha Sánchez-Isasi | a.checas | xialda
