package com.api.coooms.Model;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Value;

@Entity
@Table(name="users")
public class Users {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="email")
    private String email;
    
    @Column(name="name")
    private String name;
    
    @Column(name="password")
    private String password;
    
    //@JsonPropertyを付けないとPOST時にnullになる
    @JsonProperty("total_time")
    @Column(name="total_time")
    private Long totalTime;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public Long getTotalTime() {
        return totalTime;
    }
    public void setTotalTime(Long totalTime) {
        this.totalTime = totalTime;
    }
}

