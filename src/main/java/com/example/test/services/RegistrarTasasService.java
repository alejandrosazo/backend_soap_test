package com.example.test.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.test.models.TasaCambio;
import com.example.test.repository.TasaCambioRepository;

import java.util.List;

@Service
public class RegistrarTasasService implements IRegistrarTasasService{

    private static final Logger LOGGER = LoggerFactory.getLogger(RegistrarTasasService.class);

    @Autowired
    private TasaCambioRepository tasaCambioRepository;

    @Override
    public TasaCambio guardarTasaCambio(TasaCambio tasaCambio) {
        LOGGER.info("objeto {}");
        return tasaCambioRepository.save(tasaCambio);
    }

    @Override
    public List<TasaCambio> getAllTasas() {
        return tasaCambioRepository.findAll();
    }
}
