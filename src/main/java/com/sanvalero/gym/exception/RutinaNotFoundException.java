package com.sanvalero.gym.exception;

public class RutinaNotFoundException extends Exception {

    public RutinaNotFoundException() {super("Rutina not found");}

    public RutinaNotFoundException(String message) {super(message);}

}
