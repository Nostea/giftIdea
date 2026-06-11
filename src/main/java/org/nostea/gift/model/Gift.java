package org.nostea.gift.model;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
public class Gift {
    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String giftName;
    private String thumbnail;
    private double price;
    private String externalLink;
    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private User createdBy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Gift(long id, String giftName, String thumbnail, double price, String externalLink, User createdBy) {
        this.id = id;
        this.giftName = giftName;
        this.thumbnail = thumbnail;
        this.price = price;
        this.externalLink = externalLink;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = null;
    }

    public Gift() {}

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getGiftName() {
        return giftName;
    }

    public void setGiftName(String giftName) {
        this.giftName = giftName;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getExternalLink() {
        return externalLink;
    }

    public void setExternalLink(String externalLink) {
        this.externalLink = externalLink;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
