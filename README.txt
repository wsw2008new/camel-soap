Ejemplo de Servicio SOAP CXF atendido por Camel sobre ServiceMix 7


Las funciones del WS se definen en la interfaz ruta-soap/src/main/java/.../PersonService.java

En src/main/resources/OSGI-INF/blueprint/blueprint.xml :

1. Se define el endpoint cxf:

    <camelcxf:cxfEndpoint id="personService"
        address="${CXFserver}${service}"
        serviceClass="uy.com.antel.tallercamel.soap.PersonService"
        />

2. Se inicializa la ruta camel PersonServiceRoute que rutea cada llamada a un metodo SOAP hacia
un endpoint distinto:

    <route id="PersonServiceRoute">
        <from uri="cxf:bean:personService" />
        <recipientList>
            <simple>direct-vm:${header.operationName}</simple>
        </recipientList>
    </route>


3. Se inicializa el bean servicio-soap/src/main/java/.../ServiceHandler.java

4. Se definen varias rutas para soportar los metodos SOAP utilizando el bean ServiceHandler.


El src/main/java/.../client/Client.java testea cada metodo SOAP definido y muestra el resultado.



Instalacion
-----------
En el proyecto:
     mvn install

En la consola de servicemix:

	bundle:install -s mvn:uy.com.antel.tallercamel.soap/ejemplo-camel-soap/1.0.0-SNAPSHOT
	
	
Uso
---
http://localhost:8989/soap?wsdl
