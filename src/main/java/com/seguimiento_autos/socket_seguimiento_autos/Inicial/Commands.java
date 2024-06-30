package com.seguimiento_autos.socket_seguimiento_autos.Inicial;

import com.seguimiento_autos.socket_seguimiento_autos.model.Coordenada;
import org.springframework.boot.CommandLineRunner;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class Commands implements CommandLineRunner {

    private final RestTemplate restTemplate = new RestTemplate();
    private final String url = "http://localhost:8080/autoapp/enviar-coordenada";

    @Override
    public void run(String... args) throws Exception {
        List<Coordenada> coordenadas = new ArrayList<>();
        coordenadas.add(new Coordenada(-32.866557556550056, -68.85169578822367));
        coordenadas.add(new Coordenada(-32.86652151844275, -68.85214658797395));
        coordenadas.add(new Coordenada(-32.866557556550056, -68.85264032103379));
        coordenadas.add(new Coordenada(-32.86617915569299, -68.85261885437902));
        coordenadas.add(new Coordenada(-32.8655484840098, -68.85274765430768));
        coordenadas.add(new Coordenada(-32.86517007884704, -68.85270472099813));
        coordenadas.add(new Coordenada(-32.864863749675514, -68.85270472099813));
        coordenadas.add(new Coordenada(-32.86405287323178, -68.85283352092677));
        coordenadas.add(new Coordenada(-32.86365644204982, -68.85285498758154));
        coordenadas.add(new Coordenada(-32.863368127349986, -68.85294085420067));
        coordenadas.add(new Coordenada(-32.86316991045014, -68.85294085420067));
        coordenadas.add(new Coordenada(-32.86316991045014, -68.85330578733186));
        coordenadas.add(new Coordenada(-32.86316991045014, -68.85352045387961));
        coordenadas.add(new Coordenada(-32.863205949919426, -68.85498018640436));
        coordenadas.add(new Coordenada(-32.86316991045014, -68.85519485295212));
        coordenadas.add(new Coordenada(-32.862665356342475, -68.85506605302346));
        coordenadas.add(new Coordenada(-32.862701396016774, -68.85543098615466));
        coordenadas.add(new Coordenada(-32.862701396016774, -68.85588178590494));
        coordenadas.add(new Coordenada(-32.86275545550079, -68.85635405231002));

        enviarCoordenadasPeriodicamente(coordenadas);
    }

    private void enviarCoordenadasPeriodicamente(List<Coordenada> coordenadas) {
        for (Coordenada coordenada : coordenadas) {
            enviarCoordenada(coordenada);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    private void enviarCoordenada(Coordenada coordenada) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Coordenada> requestEntity = new HttpEntity<>(coordenada, headers);
        ResponseEntity<Coordenada> responseEntity = restTemplate.postForEntity(url, requestEntity, Coordenada.class);

        if(responseEntity.getStatusCode().is2xxSuccessful()){
            System.out.println("Coordenada enviada: " + coordenada);
        }else {
            System.out.println("Error: " + coordenada);
        }
    }
}
