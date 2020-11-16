package org.example.technical.service.business;

import org.example.technical.service.dao.ImageDao;
import org.example.technical.service.dao.UserDao;
import org.example.technical.service.entity.ImageEntity;
import org.example.technical.service.entity.UserAuthTokenEntity;
import org.example.technical.service.entity.UserEntity;
import org.example.technical.service.exception.UploadFailedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImageUploadService {

    @Autowired
    private ImageDao imageDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ImageEntity upload(ImageEntity imageEntity, final String authorizationToken) throws UploadFailedException {
        UserAuthTokenEntity userAuthToken = imageDao.getUserAuthToken(authorizationToken);
        if (userAuthToken == null) {
            throw new UploadFailedException("UP-001", "User is not Signed in, sign in to upload an image");
        }
        imageEntity.setUser_id(userAuthToken.getUser());

        return imageDao.createImage(imageEntity);
    }
}
