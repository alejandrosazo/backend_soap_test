package com.example.test.models;

import lombok.Data;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;

@Data
@Entity
@Table(name = "tasa_cambio", schema = "soap")
@Transactional
public class TasaCambio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "fecha", nullable = false)
    private Date fecha;

    @Column(name = "tasa_venta", nullable = false)
    private BigDecimal tasaVenta;

    @Column(name = "tasa_compra", nullable = false)
    private BigDecimal tasaCompra;

    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "num_peticion_seq")
    @SequenceGenerator(name = "num_peticion_seq", sequenceName = "num_peticion_seq", allocationSize = 1,schema = "soap")
    @Column(name = "num_peticion")
    private Integer numPeticion;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public BigDecimal getTasaVenta() {
		return tasaVenta;
	}

	public void setTasaVenta(BigDecimal tasaVenta) {
		this.tasaVenta = tasaVenta;
	}

	public BigDecimal getTasaCompra() {
		return tasaCompra;
	}

	public void setTasaCompra(BigDecimal tasaCompra) {
		this.tasaCompra = tasaCompra;
	}

	public Integer getNumPeticion() {
		return numPeticion;
	}

	public void setNumPeticion(Integer numPeticion) {
		this.numPeticion = numPeticion;
	}
    
    

}