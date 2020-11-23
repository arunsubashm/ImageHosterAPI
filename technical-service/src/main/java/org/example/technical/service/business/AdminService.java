package org.example.technical.service.business;


import org.example.technical.service.dao.ImageDao;
import org.example.technical.service.dao.UserDao;
import org.example.technical.service.entity.ImageEntity;
import org.example.technical.service.entity.UserAuthTokenEntity;
import org.example.technical.service.entity.UserEntity;
import org.example.technical.service.exception.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AdminService {

    @Autowired
    private ImageDao imageDao;

    public ImageEntity getImage(final String imageUuid, final String authorization) throws ImageNotFoundException, UnauthorizedException, UserNotSignedInException {

        UserAuthTokenEntity userAuthToken = imageDao.getUserAuthToken(authorization);
        if (userAuthToken == null) {
            throw new UserNotSignedInException("USR-001", "You are not Signed in, sign in first to get the image details");
        }

        UserEntity userEntity = userAuthToken.getUser();

        if (userEntity.getRole().equals("admin") != true) {
            throw new UnauthorizedException("UE-001", "You are not authorized to get the image details");
        }

        ImageEntity imageEntity = imageDao.getImage(imageUuid);

        if (imageEntity == null) {
            throw new ImageNotFoundException("IMG-001", "Image with given uuid not found");
        }
        return imageEntity;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public ImageEntity updateImage(final ImageEntity imageEntity, final String authorization) throws ImageNotFoundException, UnauthorizedException, UserNotSignedInException {

        UserAuthTokenEntity userAuthToken = imageDao.getUserAuthToken(authorization);
        if (userAuthToken == null) {
            throw new UserNotSignedInException("USR-001", "You are not Signed in, sign in first to get the image details");
        }

        UserEntity userEntity = userAuthToken.getUser();

        // check if he is an Admin User
        if (userEntity.getRole().equals("admin") != true) {
            throw new UnauthorizedException("UE-001", "You are not authorized to get the image details");
        }

        ImageEntity existingimageEntity = imageDao.getImageById(imageEntity.getId());

        if (existingimageEntity == null) {
            throw new ImageNotFoundException("IMG-001", "Image with given id not found");
        }
        imageEntity.setUuid(existingimageEntity.getUuid());
        imageEntity.setNo_of_likes(existingimageEntity.getNo_of_likes());
        imageEntity.setUser_id(existingimageEntity.getUser_id());
        imageEntity.setCreated_at(existingimageEntity.getCreated_at());

        return imageDao.updateImage(imageEntity);
    }

}
