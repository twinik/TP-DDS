package ar.edu.utn.frba.dds.services;

public class LoginFailedException extends RuntimeException{
  public LoginFailedException(String message) {
    super(message);
  }
}
