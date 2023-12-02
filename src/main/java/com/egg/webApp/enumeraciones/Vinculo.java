package com.egg.webApp.enumeraciones;

public enum Vinculo {
    PADRE("Es mi padre"),
    MADRE("Es mi madre"),
    HIJO("Es mis hijo/a"),
    CONYUGE("Es mi conyuge"),
    TUTOR("Soy tutor/a");

    private String descripcion;

    Vinculo(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
