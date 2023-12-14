package com.bandeja.entrada.anotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Company UT SUNGEMINI � ZABALA II
 *
 * @project : Siri
 * @author  : Camilo Cruz
 * @created : 6/04/2011
 *
 * Anotacion para  agregar un alias a los metodos get que seran usados en la generacion
 * del archivo
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LabelCampo {
    String label();

}
