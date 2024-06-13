package com.example.test.models;
import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "colaboradores", schema = "pruebadb")
@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class,
		  property = "colaboradorId")
public class Colaborador {

	 @Id
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	 @Column(name = "colaborador_id")
	 private Long colaboradorId;

	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sucursal_id", nullable = false)
	 @JsonBackReference("sucursal-colaborador")
    private Sucursal sucursal;

    @Column(nullable = false, length = 255)
    private String nombre;

    @Column(name = "cui",nullable = false, length = 50, unique = true)
    @JsonProperty("CUI")
    private String CUI;

	public Long getColaboradorId() {
		return colaboradorId;
	}

	public void setColaboradorId(Long colaboradorId) {
		this.colaboradorId = colaboradorId;
	}

	public Sucursal getSucursal() {
		return sucursal;
	}

	public void setSucursal(Sucursal sucursal) {
		this.sucursal = sucursal;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCUI() {
		return CUI;
	}

	public void setCUI(String cUI) {
		CUI = cUI;
	}

	public Colaborador(Long colaboradorId, Sucursal sucursal, String nombre, String cUI) {
		this.colaboradorId = colaboradorId;
		this.sucursal = sucursal;
		this.nombre = nombre;
		CUI = cUI;
	}

	public Colaborador() {
	}

    
}
