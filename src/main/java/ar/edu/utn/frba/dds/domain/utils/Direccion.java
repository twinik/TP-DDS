package ar.edu.utn.frba.dds.domain.utils;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public class Direccion {


    private String calle;

    private Integer altura;

    private Integer piso;

    private Integer codigoPostal;

}