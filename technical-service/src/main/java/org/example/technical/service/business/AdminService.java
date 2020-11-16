package org.example.technical.service.business;


import org.example.technical.service.dao.ImageDao;
import org.example.technical.service.dao.UserDao;
import org.example.technical.service.entity.ImageEntity;
import org.example.technical.service.exception.AuthenticationFailedException;
import org.example.technical.service.exception.ImageNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {

    @Autowired
    private ImageDao imageDao;

    public ImageEntity getImage(final String imageUuid) throws ImageNotFoundException {

        ImageEntity imageEntity = imageDao.getImage(imageUuid);

        if (imageEntity == null) {
            throw new ImageNotFoundException("IMG-001", "Image with given uuid not found");
        }
        return imageEntity;
    }
}
