/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package uy.com.antel.tallercamel.soap.client;

import uy.com.antel.tallercamel.soap.HinchaService;
import uy.com.antel.tallercamel.soap.model.Hincha;

import javax.xml.ws.Service;
import javax.xml.ws.soap.SOAPBinding;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import java.net.MalformedURLException;
import java.net.URL;

public class Client {
 
    public static void main(String[] args) throws MalformedURLException {
        Client client = new Client();
        try {
            client.postHincha(new Hincha(1,"Seba","Bolso"));
            client.getHincha(1);
            client.deleteHincha(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println();
    }


    private HinchaService hinchaService;
    private static final QName SERVICE_NAME = new QName("http://soap.tallercamel.antel.com.uy/", "HinchaService");
    private static final String wsdlLocation = "http://localhost:8989/hinchada/?wsdl";

    public Client() throws MalformedURLException {
        URL wsdlURL = new URL(wsdlLocation);
        Service service = Service.create(wsdlURL,SERVICE_NAME);
        hinchaService = service.getPort(HinchaService.class);
    }

    public void postHincha(Hincha hincha) throws Exception{
        System.out.println("\n### PUT hincha -> ");
        printHincha(hincha);
        Hincha result = hinchaService.putHincha(hincha);
        System.out.println("\n### PUT hincha RESPONSE ");
        printHincha(result);
    }

    public void getHincha(int id) throws Exception{
        System.out.println("\n### GET hincha WITH ID "+id);
        Hincha result = hinchaService.getHincha(id);
        System.out.println("\n### GET hincha RESPONSE");
        printHincha(result);
    }

    public void deleteHincha(int id) throws Exception{
        System.out.println("\n### DELETE hincha WITH ID "+id);
        Hincha result = hinchaService.deleteHincha(id);
        System.out.println("\n### DELETE hincha RESPONSE");
        printHincha(result);
    }




    private void printHincha(Hincha hincha){
    	System.out.println("Id: " + hincha.getId());
    	System.out.println("Nombre: " + hincha.getNombre());
    	System.out.println("Tipo: " + hincha.getTipo());
    }

}
