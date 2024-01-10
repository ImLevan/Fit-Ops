# 🏋️ Fit Ops Gestion System

<p align="center"><img width=65% height=45% src=".src/main/resources/icons/logogym.png"></p>

> © Desarrollado por Valentin Chianese
## Descripción

Under Gym Gestion System es una aplicación de escritorio la cual consiste en un sistema de gestión para los gimnasios.  El objetivo del proyecto es automatizar las tareas de gestión que pueden tener estas instituciones, como el registro de clientes, la gestión de membresías y la gestión de pagos.

Las características principales del proyecto son:

- **Registro de clientes:** El sistema permite registrar a los clientes del gimnasio, incluyendo sus datos personales, sus datos de contacto y sus preferencias de entrenamiento.

- **Registro de usuarios:** El sistema permite registrar a los empleados del gimnasio para que cada uno tenga acceso al sistema y puedo hacer uso de las funcionalidades que le correspondan.

- **Gestión de membresías:** El sistema permite gestionar las membresías de los clientes, incluyendo la creación, la renovación y la cancelación de las mismas.

- **Gestión de pagos:** El sistema permite gestionar los pagos de los clientes, incluyendo el registro de los pagos y un historial con los mismos, además de contar con métricas sobre las ganancias que se estan generando con los clientes.

El proyecto está desarrollado en Java utilizando el patrón de arquitectura MVC y utilizando las siguientes tecnologías:

- Java Swing
- JPA
- Postgresql

El proyecto fue desarrollado en un entorno de desarrollo local con NetBeans y Maven. El tiempo de desarrollo fue de aproximadamente 2 meses.

## Diseño

En cuanto a la estructura de la base de datos y las clases, este es el DER del proyecto, en el cual se muestra como se conectan las entidades, que atributos tienen de que manera estan relacionados:

![Screenshot DER](.src/main/resources/icons/SSDER.png)


## Implementación

El código está organizado en capas, siguiendo el patrón de diseño Model-View-Controller (MVC). La capa de modelo contiene la lógica de negocio, la capa de vista contiene la interfaz de usuario y la capa de controlador controla la interacción entre la vista y el modelo. Utilicé JPA para ayudarme a mappear las clases con las tablas de la base de datos, y de esta forma poder crear los CRUD consistentemente.
A continuación voy a mostrar imagenes de la interfaz del proyecto:

### Login
- Primero nos encontramos con la pantalla de inicio de sesión, donde un usuario debe ingresar nombre y contraseña para entrar al menú de la aplicación. Normalmente se le facilita al cliente un primer usuario para que pueda acceder, y luego desde el menú ya puede crear los usuarios que crea necesarios.

<p align="center"><img width=100% height=45% src=".src/main/resources/icons/1Login.png"></p>

### Dashboard

- El dashboard donde se pueden ver los miembros registrados y unas metricas relacionadas a los ingresos que está obteniendo el gimnasio.

![Screenshot Dashboard](.src/main/resources/icons/2Dashboard.png)

### Miembros

- El apartado de miembros que muestra la tabla de los mismos, y cuenta con la creación, edición y eliminación de cada una. Además de tener la posibilidad de asignarle las membresias a los miembros. Cuando se le asigna una membresia a un miembro, además se efectua el pago.

![Screenshot Miembros](.src/main/resources/icons/5Miembros.png)
![Screenshot Carga Miembros](.src/main/resources/icons/4CargaMiembros.png)
![Screenshot Edición miembros](.src/main/resources/icons/6EditarMiembro.png)
![Screenshot asignación membresia](.src/main/resources/icons/7AsignarMembresiaMiembro.png)

### Usuarios

- El apartado de usuarios que muestra la tabla de los mismos, y cuenta con la creación, edición y eliminación de cada uno.

![Screenshot usuarios](.src/main/resources/icons/3Usuarios.png)

### Membresías

- El apartado de membresias que muestra la tabla de las mismas, y cuenta con la creación, edición y eliminación de cada una.

![Screenshot membresías](.src/main/resources/icons/8Membresias.png)

### Pagos

- El apartado de pagos que muestra la tabla de los mismos, y muestra información adicional sobre los ingresos.

![Screenshot pagos](.src/main/resources/icons/9Pagos.png)

### Visitas

- El apartado de visitas que da la opcion de registrar las visitas de los miembros o bien registrar las visitas de un cliente que pagó solamente el día.

![Screenshot Visitas](.src/main/resources/icons/10Visitas.png)
![Screenshot Visita Miembro](.src/main/resources/icons/11VisitaMiembro.png)

### Historial de ventas

- Un historial de ventas, donde se pueden visualizar métricas sobre el dinero recaudado en cada mes, y de donde llegó este dinero. Este historial cuenta con la información de los ultimos 12 meses.

![Screenshot historial](.src/main/resources/icons/12HistorialVentas.png)


## Documentación

En este archivo está la documentación técnica del proyecto, donde se profundizará con mas detalle la composición de la solución de software.

<a href="https://docs.google.com/document/d/1Ib_MgmMK8CmQHtCWghYcJ8DVYipcR7gc2sPzEKmKQ6I/edit?usp=sharing">Documentación Técnica</a>

## Contribuciones

Este proyecto fue desarrollado como parte de un proyecto personal y se aceptan cualquier tipo de contribuciones externas. Agradecemos tus comentarios y sugerencias para mejorar el sistema en futuras versiones.

## Créditos

Desarrollado por [Valentin Chianese](https://github.com/ImLevan).
