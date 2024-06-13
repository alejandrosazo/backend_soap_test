package com.example.test.models;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Set;

@Entity
@Table(name = "empresas", schema = "pruebadb")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class,
		  property = "empresaId")
public class Empresa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empresa_id")
    private Long empresaId;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(nullable = false, length = 100)
    private String pais;

    @OneToMany(mappedBy = "empresa", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("empresa-sucursal")
    private Set<Sucursal> sucursales;

	public Long getEmpresaId() {
		return empresaId;
	}

	public void setEmpresaId(Long empresaId) {
		this.empresaId = empresaId;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Set<Sucursal> getSucursales() {
		return sucursales;
	}

	public void setSucursales(Set<Sucursal> sucursales) {
		this.sucursales = sucursales;
	}

	public Empresa(Long empresaId, String nombre, String pais, Set<Sucursal> sucursales) {
		this.empresaId = empresaId;
		this.nombre = nombre;
		this.pais = pais;
		this.sucursales = sucursales;
	}

	public Empresa() {	}

   
}
