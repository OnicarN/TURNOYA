package com.danielpolanco.turnoya.domain.exception;

public class HuecoOcupadoException extends RuntimeException {

    public HuecoOcupadoException() {
        super("Ese hueco ya está reservado");
    }
}