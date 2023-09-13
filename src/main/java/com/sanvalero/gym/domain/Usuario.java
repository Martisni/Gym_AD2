package com.sanvalero.gym.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(name = "nombre_usuario")
    private String nombreUsuario;
    @Column(name = "dni_usuario")
    @NotNull(message = "El campo es obligatorio")
    private String dniUsuario;
    @Column(name = "correo")
    @Email(message = "El campo debe ser email")
    private String correo;
    @Column(name = "fecha_nacimiento")
    private LocalDate fechaNacimiento;
    @Column
    private String phone;
    @Column(name = "lesionado")
    private boolean lesionado;

}
