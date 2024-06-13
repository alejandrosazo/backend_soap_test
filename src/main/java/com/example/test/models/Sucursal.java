package com.example.test.models;


import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Set;

@Entity
@Table(name = "sucursales", schema = "pruebadb")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class,
		  property = "sucursalId")
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sucursal_id")
    private Long sucursalId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id", nullable = false)
    @JsonBackReference("empresa-sucursal")
    private Empresa empresa;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(nullable = false, length = 255)
    private String direccion;

    @Column(nullable = false, length = 20)
    private String telefono;

    @OneToMany(mappedBy = "sucursal", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference("sucursal-colaborador")
    private Set<Colaborador> colaboradores;

	public Long getSucursalId() {
		return sucursalId;
	}

	public void setSucursalId(Long sucursalId) {
		this.sucursalId = sucursalId;
	}

	public Empresa getEmpresa() {
		return empresa;
	}

	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public Set<Colaborador> getColaboradores() {
		return colaboradores;
	}

	public void setColaboradores(Set<Colaborador> colaboradores) {
		this.colaboradores = colaboradores;
	}

	public Sucursal(Long sucursalId, Empresa empresa, String nombre, String direccion, String telefono,
			Set<Colaborador> colaboradores) {
		this.sucursalId = sucursalId;
		this.empresa = empresa;
		this.nombre = nombre;
		this.direccion = direccion;
		this.telefono = telefono;
		this.colaboradores = colaboradores;
	}

	public Sucursal() {
	}

    
}

