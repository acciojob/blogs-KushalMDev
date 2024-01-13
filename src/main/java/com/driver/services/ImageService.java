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
        int Xindex=-1,i=0;
        for(char c:dimension.toCharArray()){
            if(c=='x' || c=='X'){
                Xindex=i;
                break;
            }
            i++;
        }

        int length = Integer.parseInt(dimension.substring(0, Xindex));
        int breadth =Integer.parseInt(dimension.substring(Xindex+1));
         Xindex=-1;i=0;
        for(char c:screenDimensions.toCharArray()){
            if(c=='x' || c=='X'){
                Xindex=i;
                break;
            }
            i++;
        }
        int givenLength = Integer.parseInt(screenDimensions.substring(0, Xindex));
        int givenBreadth = Integer.parseInt(screenDimensions.substring(Xindex+1));
        int givenArea = givenBreadth * givenLength;
        int imageArea = length * breadth;
        if(givenArea==0 || imageArea==0)return 0;
        return givenArea / imageArea;
    }
}
