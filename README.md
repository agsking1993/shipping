# Taghleef - Shipping

 <a href="url"><img src="https://github.com/agsking1993/shipping/blob/master/logo_jave.jpg" align="left" height="70" width="70" ></a>

Creación microservice shipping que contiene toda la lógica para escuchar y publicar eventos al momento en que se crea una orden, se actualiza en la orden principal el estado de shipping.

# Modificación servicio orden
Para la inclusión de un nuevo binding hacia shipping -event

![alt text](https://github.com/agsking1993/shipping/blob/master/order.jpg)


# Request
Se realiza la inclusión del campo postalCode para poder verificar si el shipping se puede realizar o no.

![alt text](https://github.com/agsking1993/shipping/blob/master/request.jpg)

# BD
Creación de la tabla shipping_info que contiene la información de los envíos disponibles.

![alt text](https://github.com/agsking1993/shipping/blob/master/bd.jpg)

# Orden final actualizada 

Se evidencia lo distintos escenarios de pruebas para la ejecución de los microservicios

![alt text](https://github.com/agsking1993/shipping/blob/master/final.JPG)
