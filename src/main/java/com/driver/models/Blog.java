package com.driver.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.annotations.CreationTimestamp;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.CreationTimestamp;

@Entity
public class Blog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int blogId;
    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    private String title;
    private String content;
    @CreationTimestamp
    private Date publishDate;
    @JoinColumn
    @ManyToOne
    private User user;
    @OneToMany(mappedBy = "blog", cascade = CascadeType.ALL)
    private List<Image> imageList = new ArrayList<Image>();

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

   

    public List<Image> getImageList() {
        return imageList;
    }

    public void setImageList(List<Image> imageList) {
        this.imageList = imageList;
    }

    public int getBlogId() {
        return blogId;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getPublishDate() {
        return publishDate;
    }

    public void setPublishDate(Date publishDate) {
        this.publishDate = publishDate;
    }

    public Blog(int blogId, String title, String content, Date publishDate, List<Image> imageList,User user) {
        this.blogId = blogId;
        this.title = title;
        this.content = content;
        this.publishDate = publishDate;
        this.imageList = imageList;
        this.user=user;
    }

    public Blog() {
        
    }

}