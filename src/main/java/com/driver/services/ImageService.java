package com.driver.services;

import com.driver.models.*;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions) {
        Blog blog = blogRepository2.findById(blogId).get();
        // add an image to the blog
        Image image = new Image();
        image.setDescription(description);
        image.setDimensions(dimensions);
        image.setBlog(blog);
        blog.getImageList().add(image);
        blogRepository2.save(blog);
        return image;

    }

    public void deleteImage(Integer id) {
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        // Find the number of images of given dimensions that can fit in a screen having
        // `screenDimensions`
        Image image = imageRepository2.findById(id).get();
        String dimension = image.getDimensions();
        int length = (int) (dimension.charAt(0) - '0');
        int breadth = (int) (dimension.charAt(2) - '0');
        int givenLength = (int) (screenDimensions.charAt(0) - '0');
        int givenBreadth = (int) (screenDimensions.charAt(2) - '0');
        int givenArea = givenBreadth * givenLength;
        int imageArea = length * breadth;
        return givenArea / imageArea;
    }
}
