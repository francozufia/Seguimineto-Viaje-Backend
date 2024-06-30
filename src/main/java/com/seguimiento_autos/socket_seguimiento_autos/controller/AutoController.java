package com.seguimiento_autos.socket_seguimiento_autos.controller;

import com.seguimiento_autos.socket_seguimiento_autos.model.Coordenada;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/autoapp")
public class AutoController {

    private final SimpMessagingTemplate template;

    public AutoController(SimpMessagingTemplate template) {
        this.template = template;
    }


    @PostMapping("/enviar-coordenada")
    public void enviarCoordenada(@RequestBody Coordenada coordenada){
        this.template.convertAndSend("/auto/coordenada", coordenada);
    }
}
