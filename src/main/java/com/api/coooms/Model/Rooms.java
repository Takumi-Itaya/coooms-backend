package com.api.coooms.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="rooms")
public class Rooms {
	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="name")
    private String name;
    
    @ManyToMany
    @JoinTable(
    		name = "room_music", 
    		joinColumns = @JoinColumn(name = "room_id"), 
    		inverseJoinColumns = @JoinColumn(name = "music_id")
    		)
    private List<Music> music_list;
    
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public List<Music> getMusicList() {
        return music_list;
    }
    public void setMusicList(List<Music> music_list) {
        this.music_list = music_list;
    }
    
}
