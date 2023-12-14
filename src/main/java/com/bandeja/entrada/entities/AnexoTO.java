package com.bandeja.entrada.entities;

public class AnexoTO {
    private boolean conAdjuntos;
    private String numeroRadicado;
    private String nombreArchivo;
    private String tipoArchivo;
    public boolean isConAdjuntos() {
        return conAdjuntos;
    }
    public void setConAdjuntos(boolean conAdjuntos) {
        this.conAdjuntos = conAdjuntos;
    }
    public String getNumeroRadicado() {
        return numeroRadicado;
    }
    public void setNumeroRadicado(String numeroRadicado) {
        this.numeroRadicado = numeroRadicado;
    }
    public String getNombreArchivo() {
        return nombreArchivo;
    }
    public void setNombreArchivo(String nombreArchivo) {
        this.nombreArchivo = nombreArchivo;
    }
    public String getTipoArchivo() {
        return tipoArchivo;
    }
    public void setTipoArchivo(String tipoArchivo) {
        this.tipoArchivo = tipoArchivo;
    }

}

