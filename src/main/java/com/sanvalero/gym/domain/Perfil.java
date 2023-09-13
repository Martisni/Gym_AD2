package com.sanvalero.gym.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "perfil")
public class Perfil {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "ritmo_cardiaco")
    private int ritmoCardico;
    @Column
    @NotNull(message = "El campo es obligatorio")
    private int peso;
    @Column
    private int imc;
    @Column
    private boolean obesidad;
    @Column(name = "fecha_medicion")
    private Date fechaMedicion;
    @Column
    @NotNull(message = "El campo es obligatorio")
    private String medidas;

}
