package com.example.test.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.client.SOAPTest_Client;
import com.example.test.models.TasaCambio;
import com.example.test.utilerias.Utilerias;
import com.example.test.wsdl.TipoCambioDiaResponse;
import com.example.test.wsdl.TipoCambioFechaInicialResponse;
import com.example.test.wsdl.TipoCambioRangoResponse;
import com.example.test.wsdl.Var;
import com.example.test.wsdl.VarDolar;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class TiposDeCambioService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TiposDeCambioService.class);

    @Autowired
    private SOAPTest_Client soapClient;

    private Random random = new Random();

    private final RegistrarTasasService registrarTasasService;

    @Autowired
    public TiposDeCambioService(RegistrarTasasService registrarTasasService) {
        this.registrarTasasService = registrarTasasService;
    }

    public Map cambioDelDia(){
        Map cambioDia = new HashMap<>();
        TipoCambioDiaResponse tipoCambioDiaResponse = soapClient.tipoCambioDiaResponse();
        List<VarDolar> valorDolares = tipoCambioDiaResponse.getTipoCambioDiaResult().getCambioDolar().getVarDolar();
        for(VarDolar var : valorDolares){
            LOGGER.info(var.getFecha());
            LOGGER.info(var.getReferencia()+"");
            cambioDia.put("dia", var.getFecha());
            cambioDia.put("cambio", var.getReferencia()+"");
        }

        return cambioDia;
    }

    public Map cambioPorFechas(Map parametros){
        String fechaInicio = parametros.get("fechaInicio").toString();
        String fechaFin = parametros.get("fechaFin").toString();
        Map porFechas = new HashMap<>();
        BigDecimal promedioVenta = BigDecimal.ZERO;
        BigDecimal promedioCompra = BigDecimal.ZERO;
        porFechas.put("fechaInicio", fechaInicio);
        porFechas.put("fechaFinal", fechaFin);

        TipoCambioRangoResponse tipoCambioRangoResponse = soapClient.tipoCambioRangoResponse(fechaInicio, fechaFin);

        List <Var> datos = tipoCambioRangoResponse.getTipoCambioRangoResult().getVars().getVar();
        int size = datos.size();
        for (Var e : datos){
            promedioCompra = promedioCompra.add(BigDecimal.valueOf(e.getCompra()));
            promedioVenta = promedioVenta.add(BigDecimal.valueOf(e.getVenta()));
        }

        promedioCompra = promedioCompra.divide(new BigDecimal(size), 2,RoundingMode.HALF_UP );
        promedioVenta = promedioVenta.divide(new BigDecimal(size), 2, RoundingMode.HALF_UP);
        porFechas.put("promedioCompra", promedioCompra);
        porFechas.put("promedioVenta", promedioVenta);
        return porFechas;
    }

    public Boolean insertarSolicitud(String fecha) throws ParseException {
        try{
            Date date1 = new SimpleDateFormat("dd/MM/yyyy").parse(fecha);
            SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM/yyyy");
            fecha = DATE_FORMAT.format(date1);
            LOGGER.info("fecha {}", fecha);
            TipoCambioFechaInicialResponse tipoCambioFechaInicialResponse = soapClient.tipoCambioFechaInicialResponse(fecha);
            obtenerCambios(tipoCambioFechaInicialResponse, LOGGER);

        }catch (Exception e) {
            LOGGER.info("Error al parsear la fecha {}", e);
            return false;
        }
        return true;

    }

    public void obtenerCambios(TipoCambioFechaInicialResponse tipoCambioFechaInicialResponse, Logger logger) {
        List<Var> lista = tipoCambioFechaInicialResponse.getTipoCambioFechaInicialResult().getVars().getVar();
        TasaCambio tasaCambio;
        /**Insercion*/
        for (Var e : lista){
            LOGGER.info("inicia insercion");
            tasaCambio = new TasaCambio();
            tasaCambio.setFecha(Utilerias.convertirStringADate(e.getFecha()));
            tasaCambio.setTasaCompra(new BigDecimal(e.getCompra()));
            tasaCambio.setTasaVenta(new BigDecimal(e.getVenta()));
            tasaCambio.setNumPeticion(generarNumeroPeticionUnico());
            LOGGER.info("tasaCambio {}", tasaCambio);
            registrarTasasService.guardarTasaCambio(tasaCambio);
        }
    }

    private int generarNumeroPeticionUnico() {
        // Generar un número de petición único, por ejemplo, un número aleatorio
        return random.nextInt(Integer.MAX_VALUE);
    }
}
