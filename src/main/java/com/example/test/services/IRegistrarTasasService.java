package com.example.test.services;

import java.util.List;

import com.example.test.models.TasaCambio;

public interface IRegistrarTasasService {

    TasaCambio guardarTasaCambio(TasaCambio tasaCambio);

    List<TasaCambio> getAllTasas();

}
