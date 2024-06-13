package com.example.test.controller;

import com.example.test.models.TasaCambio;
import com.example.test.services.RegistrarTasasService;
import com.example.test.services.TiposDeCambioService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/testController")
public class SOAPController {

    @Autowired
    TiposDeCambioService tiposDeCambioService;

    @Autowired
    RegistrarTasasService registrarTasasService;

    
    @PostMapping("/insertarDatoTabla")
    public ResponseEntity<String> SolicitarCambioRango(@RequestBody Map parametros) throws ParseException {

        if(tiposDeCambioService.insertarSolicitud(parametros.get("fecha").toString())){
            return new ResponseEntity<>("Exito, solicitud creada", HttpStatus.OK);
        }else {
            return new ResponseEntity<>("Error, solicitud no procesada", HttpStatus.BAD_REQUEST);
        }
    }

    
    @PostMapping("/solicitarCambioPorFecha")
    public ResponseEntity<Map> solicitarCambioPorFecha(@RequestBody Map parametros) throws ParseException {
    	Map respuesta = new HashMap<>();
        try {
            respuesta = tiposDeCambioService.cambioPorFechas(parametros);
        } catch (Exception e) {
            respuesta.clear();
            respuesta.put("Error", e.getMessage());
            return new ResponseEntity<>(respuesta, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
       
    }

    @GetMapping("/obtenerSolicitudes")
    public ResponseEntity<List<TasaCambio>> obtenerSolicitudes(){
        List<TasaCambio> lista = new ArrayList<>();
        lista = registrarTasasService.getAllTasas();
        return new ResponseEntity<>(lista, HttpStatus.OK);
    }

    @PostMapping("/obtenerPromedioTasa")
    public ResponseEntity<Map> promedioTasa(@RequestBody Map parametros){
        Map respuesta = new HashMap<>();
        respuesta = tiposDeCambioService.cambioPorFechas(parametros);
        return new ResponseEntity<>(respuesta,HttpStatus.OK);
    }
}
