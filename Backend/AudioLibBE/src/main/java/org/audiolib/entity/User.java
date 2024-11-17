package org.audiolib.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@jakarta.persistence.Table(name = "users", schema = "public", catalog = "AudioLib")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private String id;
    @Basic
    @Column(name = "email")
    private String email;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "blocked")
    private boolean blocked;
    @Basic
    @Column(name = "pwd")
    private String password;
    public boolean isAdmin() {
        return id == "PYSARENKOV";
    }
}
