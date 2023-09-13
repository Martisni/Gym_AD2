package com.sanvalero.gym.exception;

public class PerfilNotFoundException extends Exception {

    public PerfilNotFoundException() {super("Perfil not found");}

    public PerfilNotFoundException(String message) {super(message);}

}
