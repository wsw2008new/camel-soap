Ejemplo de Servicio SOAP CXF atendido por Camel sobre ServiceMix 7


Las funciones del WS se definen en la interfaz ruta-soap/src/main/java/.../PersonService.java

En ruta-soap/src/main/resources/OSGI-INF/blueprint/blueprint.xml :

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


En servicio-soap/src/main/resources/OSGI-INF/blueprint/blueprint.xml :

1. Se inicializa el bean servicio-soap/src/main/java/.../ServiceHandler.java

2. Se definen varias rutas para soportar los metodos SOAP utilizando el bean ServiceHandler.


El cliente-soap/src/main/Client.java testea cada metodo SOAP definido y muestra el resultado.



Instalacion
-----------
Posicionado en el proyecto padre (ejemplo-camel-soap):
     mvn install

En la consola de servicemix:

bundle:install -s mvn:uy.com.antel.tallercamel.soap/ejemplo-camel-soap/1.0.0-SNAPSHOT
     features:install ejemplo-camel-soap

   It makes use of the ServiceMix features facility. For more information
   about the features facility, see the README.txt file in the examples
   parent directory.

See "Running the Client" above for information on how to make invocations
on the web service.


Stopping and Uninstalling the Example
-------------------------------------
To stop the example, you must first know the bundle IDs that ServiceMix
has assigned to the example bundels. To get the bundle IDs, enter the
following command in the ServiceMix console (Note, the text you are
typing will intermingle with the output being logged. This is nothing
to worry about.):

  osgi:list

At the end of the listing, you should see an entry similar to the
following:

  [ 173] [Active     ] [Created     ] [       ] [   80] Apache ServiceMix :: Examples :: Camel CXF SOAP :: Route (5.0.1.SNAPSHOT)
  [ 175] [Active     ] [Created     ] [       ] [   80] Apache ServiceMix :: Examples :: Camel CXF SOAP :: Service (5.0.1.SNAPSHOT)

In this case, the bundle IDs are 173 and 175.

To stop a bundle, enter the following command in the ServiceMix
console:

  osgi:stop <bundle_id>

For example:

  osgi:stop 173

To uninstall the example, enter one of the following commands at
the ServiceMix console:

  features:uninstall examples-camel-cxf-soap

or

  osgi:uninstall <bundle_id>


Viewing the Log Entries
-----------------------
You can view the entries in the log file in the data/log
directory of your ServiceMix installation, or by typing
the following command in the ServiceMix console:

  log:display
