package com.bandeja.entrada.entities;
import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * Company UT SUNGEMINI � ZABALA II
 *
 * @project : Siri
 * @author : Camilo Cruz
 * @created : 3/02/2011
 *
 *          Entidad con la informacion del nivel de vigilancia de una entidad
 *
 */
@Entity
@NamedQueries({ @NamedQuery(name = "NivelVigilancia.listarTodos", query = "select object(o) from NivelVigilancia o") })
public class NivelVigilancia implements Serializable {

    /**
     * Campo
     *
     * @author Camilo Cruz for UT SunGemini-Zabala II 3/02/2011 15:22:14
     */
    private static final long serialVersionUID = 8038681544914589954L;
    @Id
    private Long idNivelVigilancia;
    private String nombre;

    /**
     * Retorna el valor actual idNivelVigilancia (getter)
     *
     * @author Camilo Cruz UT SunGemini-Zabala II 3/02/2011 15:09:22
     * @return idNivelVigilancia actual
     */
    public Long getIdNivelVigilancia() {
        return idNivelVigilancia;
    }

    /**
     * Sustituye el valor actual de idNivelVigilancia por el valor ingresado en idNivelVigilancia (setter)
     *
     * @author Camilo Cruz UT SunGemini-Zabala II 3/02/2011 15:09:22
     * @param idNivelVigilancia
     *            valor a actualizar
     */
    public void setIdNivelVigilancia(Long idNivelVigilancia) {
        this.idNivelVigilancia = idNivelVigilancia;
    }

    /**
     * Retorna el valor actual nombre (getter)
     *
     * @author Camilo Cruz UT SunGemini-Zabala II 3/02/2011 15:09:22
     * @return nombre actual
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Sustituye el valor actual de nombre por el valor ingresado en nombre (setter)
     *
     * @author Camilo Cruz UT SunGemini-Zabala II 3/02/2011 15:09:22
     * @param nombre
     *            valor a actualizar
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
