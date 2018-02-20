# looking4sitter

## Descripción
Looking4Sitter es una aplicación web en la cual los padres o guarderias podrán buscar el niñero o niñera adecuado a sus necesidades. Cuenta con una lista de niñeros que muestran sus cualidades, páginas individuales para cada niñero con comentarios y puntuación de otros padres al niñero, un tablón de anuncios donde los padres podrán informar de qué tipo de niñero buscan y un sistema de mensajería.

## Entidades

Nombre | Descripción (Las entidades cuentan con un Id único y autoincremental que se comporta como la Primary Key de su tabla específica)
------- | -------
Usuario | Los usuarios cuentan con los siguientes atributos: login (string único de identificación), nombre, email, password, provincia, tarifa (para Sitters y Star Sitters) y descripción. Los usuarios vienen definidos por su relación con el tipo de Perfil con el que cuentan.
Perfil | Cada perfil viene identificado con su nombre. Se cuenta con un Administrador, Padre, Sitter, Star Sitter y Centro. Cada perfil cuenta con una relación con varios usuarios que viene definida en la tabla de Relacion_Usuario_Perfil.
Relacion_Usuario_Perfil | Cada registro de esta entidad define una relación entre un usuario y su tipo de perfil (ambas por Id).
Relacion_Sitter_Centro | Se puede ver la relación entre un Star Sitter y un centro mediante la relación entre ambos tipos de usuario en esta tabla. Un centro puede contar con varios Sitters.
Anuncio | Escrito por los padres y agregado al tablón de anuncios. Los niñeros pueden pinchar en el y acceder a los datos de contacto del padre
Comentario | Comentario de los padres al niñero en el que se indica tambien la puntuación.
Mensaje | Los usuarios podrán comunicarse entre si mediante el envío de mensajes.

## Diagrama UML
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/diagramaUML.PNG"
     alt="Diagrama UML"
     style="float: left; margin-right: 10px;" />
## Diagrama de entidades y su relación
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/registro_2.PNG"
     alt="Diagrama entidades"
     style="float: left; margin-right: 10px;" />

## Capturas
# Inicio
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/welcome.PNG"
     alt="Página de inicio"
     style="float: left; margin-right: 10px;" />
     
# Inicio sesión
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/inicio_padre.PNG"
     alt="Inicio sesión"
     style="float: left; margin-right: 10px;" />
     
# Perfiles
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/perfil_padre.PNG"
     alt="Perfil padre"
     style="float: left; margin-right: 10px;" />

<img src="https://github.com/sarapcoding/looking4sitter/blob/master/perfil_sitter.PNG"
     alt="Perfil Sitter"
     style="float: left; margin-right: 10px;" />


# Registro
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/registro_1.PNG"
     alt="Registro"
     style="float: left; margin-right: 10px;" />
     
     
 <img src="https://github.com/sarapcoding/looking4sitter/blob/master/registro_2.PNG"
     alt="Registro"
     style="float: left; margin-right: 10px;" />
     
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/registro_3.PNG"
     alt="Registro"
     style="float: left; margin-right: 10px;" />
     
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/registro_4.PNG"
     alt="Registro"
     style="float: left; margin-right: 10px;" />



# Búsqueda de sitters
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/busqueda_sitters.PNG"
     alt="Búsqueda sitters"
     style="float: left; margin-right: 10px;" />

# Búsqueda de anuncios
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/busqueda_anuncio.PNG"
     alt="Búsqueda anuncio"
     style="float: left; margin-right: 10px;" />

# Creación de un anuncio
<img src="https://github.com/sarapcoding/looking4sitter/blob/master/anuncio_creado.PNG"
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
