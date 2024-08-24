package com.api.coooms.Model;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="music")
public class Music {
	@Id
    @Column(name="id")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    
    @Column(name="source")
    private String source;
    
    @Column(name="loop")
    private byte loop;
    
    @ManyToMany(mappedBy = "music_list")
    private List<Rooms> used_by_room;
    
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSource() {
        return source;
    }
    public void setSource(String source) {
        this.source = source;
    }
    public byte getLoop() {
        return loop;
    }
    public void setLoop(byte loop) {
        this.loop = loop;
    }
}
