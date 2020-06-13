package ru.itis.jlab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User implements Serializable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;

    @Column(name = "hash_password")
    private String hashPassword;

    private String mail;

    @Enumerated(EnumType.STRING)
    private State state;

    @Column(name = "confirm_code")
    private String confirmCode;

    @Enumerated(EnumType.STRING)
    private Role role;
}
