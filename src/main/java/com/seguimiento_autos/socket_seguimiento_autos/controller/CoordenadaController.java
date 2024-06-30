package com.seguimiento_autos.socket_seguimiento_autos.controller;

import com.seguimiento_autos.socket_seguimiento_autos.model.Coordenada;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

@Controller
public class CoordenadaController {

    @MessageMapping("/auto")
    @SendTo("/auto/coordenada")
    public Coordenada envio(Coordenada coordenada){
        return coordenada;
    }
}
