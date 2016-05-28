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

package uy.com.antel.tallercamel.soap;

import org.apache.camel.Body;
import org.apache.camel.Exchange;

import uy.com.antel.tallercamel.soap.model.Hincha;
import uy.com.antel.tallercamel.soap.model.HinchaException;

import java.util.HashMap;
import java.util.Map;

public class ServiceHandler {

    public static final String ERR_HINCHA_X_NOT_FOUND = "Hincha %s no encontrado";

    Map<Integer,Hincha> hinchas = new HashMap<Integer,Hincha>();

    public void init(){
        Hincha hincha = new Hincha(0,"Gustavo","Tuerto");
        hinchas.put(hincha.getId(), hincha);
    }

    public void getHincha(@Body String id,Exchange exchange){
        Hincha result = hinchas.get(Integer.parseInt(id));
        checkResult(id, exchange, result);
    }

    public Hincha putHincha(Hincha hincha){
        hinchas.put(hincha.getId(), hincha);
        return hincha;
    }

    public void deleteHincha(@Body String id,Exchange exchange){
        Hincha result = hinchas.remove(Integer.parseInt(id));
        checkResult(id, exchange, result);
    }


    private void checkResult(String id, Exchange exchange, Hincha result) {
        if (result == null){
            exchange.getOut().setFault(true);
            exchange.getOut().setBody(new HinchaException(String.format(ERR_HINCHA_X_NOT_FOUND, id), id));
        }else{
            exchange.getOut().setBody(result);
        }
    }




}
