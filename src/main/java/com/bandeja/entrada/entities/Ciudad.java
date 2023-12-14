package com.bandeja.entrada.entities;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;

/**
 * Entidad
 *
 * @author UT SunGemini-Zabala II
 */

@SuppressWarnings("rawtypes")
@Entity
@NamedQueries({ @NamedQuery(name = "Ciudad.listarTodos", query = "select object(o) from Ciudad o ORDER BY o.nombreMunicipio") })
// @Inheritance(strategy=InheritanceType.JOINED)
// @DiscriminatorColumn(name="DISC", discriminatorType=DiscriminatorType.STRING,length=10)
public class Ciudad implements Serializable, Comparable {

    /**
     * Campo para identificar los elementos durante la serializacion
     *
     * @author Camilo Cruz 8/06/2010 9:20:35
     */
    private static final long serialVersionUID = 7232541444703132587L;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEC_CIUDAD")
    @SequenceGenerator(name = "SEC_CIUDAD", sequenceName = "CIUDAD_SEQ", allocationSize = 1)
    private Long idCiudad;
    private String nombreMunicipio;

    public Long getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(Long idCiudad) {
        this.idCiudad = idCiudad;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public String getNombreCiudad() {
        return nombreMunicipio;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreMunicipio = nombreCiudad;
    }

    @Override
    public int compareTo(Object o) {
        Ciudad c = (Ciudad) o;
        return this.nombreMunicipio.compareToIgnoreCase(c.nombreMunicipio);
    }

    /**
     * Codigo del departamento segun divipola de la ciudad
     *
     * @return
     */
    public String getCodigoDepartamento() {
        if (idCiudad == null)
            return null;

        return idCiudad.toString().substring(0, 2);
    }

    /**
     * Codigo del la siudad segun divipola de ciudad
     *
     * @return
     */
    public String getCodigiCiudad() {
        if (idCiudad == null)
            return null;

        return idCiudad.toString().substring(3, idCiudad.toString().length() - 1);
    }

}
