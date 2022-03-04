package com.nextsuntech.kdf1User.Model;

public class CategoriesDataModel {
    String id;
    String userId;
    String user;
    String title;
    String description;
    String image;
    String createAt;
    String status;

    public CategoriesDataModel(String id, String userId, String user,
                               String title, String description, String image, String createAt, String status) {
        this.id = id;
        this.userId = userId;
        this.user = user;
        this.title = title;
        this.description = description;
        this.image = image;
        this.createAt = createAt;
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
