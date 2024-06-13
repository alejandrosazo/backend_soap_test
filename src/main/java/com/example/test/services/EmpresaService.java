package com.example.test.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.type.TypeReference;
import com.example.test.models.Colaborador;
import com.example.test.models.Empresa;
import com.example.test.models.Sucursal;
import com.example.test.repository.ColaboradorRepository;
import com.example.test.repository.EmpresaRepository;
import com.example.test.repository.SucursalRepository;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class EmpresaService {

    @Autowired
    private EmpresaRepository empresaRepository;
    @Autowired
    private SucursalRepository sucursalRepository;
    @Autowired
    private ColaboradorRepository colaboradorRepository;


    public Optional<Empresa> getEmpresaDetails(Long empresaId) {
        // Recupera la empresa y sus relaciones con sucursales y colaboradores
        return empresaRepository.findById(empresaId);
    }
    
    public void processJson(String jsonContent) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode rootNode = objectMapper.readTree(jsonContent);
        // Extraemos la sección 'empresa'
        JsonNode empresaNode = rootNode.path("empresa");
        // Convertimos la sección 'empresa' en el objeto Empresa       
          Empresa empresas = objectMapper.treeToValue(empresaNode, Empresa.class);
        
        /*for (Empresa empresa : empresas) {*/
           Empresa savedEmpresa = empresaRepository.save(empresas);
          /*  for (Sucursal sucursal : empresas.getSucursales()) {
                sucursal.setEmpresa(savedEmpresa);
                Sucursal savedSucursal = sucursalRepository.save(sucursal);
                for (Colaborador colaborador : sucursal.getColaboradores()) {
                    colaborador.setSucursal(savedSucursal);
                    colaboradorRepository.save(colaborador);
                }
            }/*}*/
        
    }
}
