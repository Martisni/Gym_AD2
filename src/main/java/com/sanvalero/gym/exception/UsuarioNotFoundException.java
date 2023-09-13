package com.sanvalero.gym.exception;

public class UsuarioNotFoundException extends Exception {

    public UsuarioNotFoundException() {super("Usuario not found");}

    public UsuarioNotFoundException(String message) {super(message);}

}
