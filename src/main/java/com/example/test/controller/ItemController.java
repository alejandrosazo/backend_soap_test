package com.example.test.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.test.models.Empresa;
import com.example.test.services.EmpresaService;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@SuppressWarnings({"unused","unchecked"})
@RestController
@RequestMapping("/items")
public class ItemController {

	 @Autowired
	    private EmpresaService empresaService;

	    @GetMapping("/{id}")
	    public ResponseEntity<Empresa> getEmpresa(@PathVariable Long id) {
	        return empresaService.getEmpresaDetails(id)
	                .map(empresa -> ResponseEntity.ok(empresa))
	                .orElseGet(() -> ResponseEntity.notFound().build());
	    }
	    
	    @PostMapping("/upload")
	    public ResponseEntity<String> uploadFile(@ RequestParam("file") MultipartFile file) {
	        if (file.isEmpty()) {
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("No file uploaded");
	        }
	        try {
	            String content = new String(file.getBytes(), StandardCharsets.UTF_8);
	            // Aquí iría la lógica para parsear el JSON y guardar los datos
	            empresaService.processJson(content);
	            return ResponseEntity.ok("Se Subío el archivo y se proceso correctamente");
	        } catch (IOException e) {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.toString());
	        }
	    }
}
