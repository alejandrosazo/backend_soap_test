//
// Este archivo ha sido generado por la arquitectura JavaTM para la implantación de la referencia de enlace (JAXB) XML v2.3.0 
// Visite <a href="https://javaee.github.io/jaxb-v2/">https://javaee.github.io/jaxb-v2/</a> 
// Todas las modificaciones realizadas en este archivo se perderán si se vuelve a compilar el esquema de origen. 
// Generado el: 2024.06.12 a las 05:28:13 PM CST 
//


package com.example.test.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para anonymous complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TipoCambioDiaStringResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "tipoCambioDiaStringResult"
})
@XmlRootElement(name = "TipoCambioDiaStringResponse")
public class TipoCambioDiaStringResponse {

    @XmlElement(name = "TipoCambioDiaStringResult")
    protected String tipoCambioDiaStringResult;

    /**
     * Obtiene el valor de la propiedad tipoCambioDiaStringResult.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTipoCambioDiaStringResult() {
        return tipoCambioDiaStringResult;
    }

    /**
     * Define el valor de la propiedad tipoCambioDiaStringResult.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTipoCambioDiaStringResult(String value) {
        this.tipoCambioDiaStringResult = value;
    }

}
