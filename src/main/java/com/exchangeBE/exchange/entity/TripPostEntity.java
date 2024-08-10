package com.exchangeBE.exchange.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
public class TripPostEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String country;  // 새 필드 추가
    private LocalDate travelDateStart;  // 새 필드 추가
    private LocalDate travelDateEnd;  // 새 필드 추가
    private String location;

    @ManyToMany
    @JoinTable(
            name = "trip_post_tags",
            joinColumns = @JoinColumn(name = "trippost_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private Set<TagEntity> tags = new HashSet<>();

    @Enumerated(EnumType.STRING)
    private TopicEntity topic;

    @ElementCollection
    private List<String> photos;

//    // Getters 및 Setters 추가
//    public Long getId() {
//        return id;
//    }
//
//    public void setId(Long id) {
//        this.id = id;
//    }
//
//    public String getTitle() {
//        return title;
//    }
//
//    public void setTitle(String title) {
//        this.title = title;
//    }
//
//    public String getDescription() {
//        return description;
//    }
//
//    public void setDescription(String description) {
//        this.description = description;
//    }
//
//    public String getCountry() {
//        return country;
//    }
//
//    public void setCountry(String country) {
//        this.country = country;
//    }
//
//    public LocalDate getTravelDateStart() {
//        return travelDateStart;
//    }
//
//    public void setTravelDateStart(LocalDate travelDateStart) {
//        this.travelDateStart = travelDateStart;
//    }
//
//    public LocalDate getTravelDateEnd() {
//        return travelDateEnd;
//    }
//
//    public void setTravelDateEnd(LocalDate travelDateEnd) {
//        this.travelDateEnd = travelDateEnd;
//    }
//
//    public String getLocation() {
//        return location;
//    }
//
//    public void setLocation(String location) {
//        this.location = location;
//    }
//
//    public Set<TagEntity> getTags() {
//        return tags;
//    }
//
//    public void setTags(Set<TagEntity> tags) {
//        this.tags = tags;
//    }
//
//    public TopicEntity getTopic() {
//        return topic;
//    }
//
//    public void setTopic(TopicEntity topic) {
//        this.topic = topic;
//    }
//
//    public List<String> getPhotos() {
//        return photos;
//    }
//
//    public void setPhotos(List<String> photos) {
//        this.photos = photos;
//    }
}
