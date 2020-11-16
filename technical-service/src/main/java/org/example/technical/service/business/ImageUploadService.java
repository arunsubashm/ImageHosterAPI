package org.example.technical.service.business;

import org.example.technical.service.dao.ImageDao;
import org.example.technical.service.dao.UserDao;
import org.example.technical.service.entity.ImageEntity;
import org.example.technical.service.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ImageUploadService {

    @Autowired
    private ImageDao imageDao;

    @Transactional(propagation = Propagation.REQUIRED)
    public ImageEntity upload(ImageEntity imageEntity) {
        return imageDao.createImage(imageEntity);
    }
}
