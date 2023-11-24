package ru.shasoka.dcis.prac_5.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * Сущность, представляющая пользователя магазина.
 */
@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    @NotEmpty
    private String username;

    @NotEmpty
    @Column(name = "password")
    private String password;

    @Column(name = "user_role")
    private String role;

}
