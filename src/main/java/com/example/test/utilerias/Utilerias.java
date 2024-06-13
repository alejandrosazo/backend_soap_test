package com.example.test.utilerias;

import org.springframework.stereotype.Component;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@Component
public class Utilerias {

    public static Date convertirStringADate(String fechaTexto) {
        DateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
        try {
            java.util.Date fechaUtil = formatoFecha.parse(fechaTexto);
            return new Date(fechaUtil.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
    }
}
