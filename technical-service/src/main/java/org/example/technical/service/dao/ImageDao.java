package org.example.technical.service.dao;

import org.example.technical.service.entity.ImageEntity;
import org.example.technical.service.entity.UserAuthTokenEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class ImageDao {


    @PersistenceContext
    private EntityManager entityManager;

    public ImageEntity createImage(ImageEntity imageEntity) {
        entityManager.persist(imageEntity);
        return imageEntity;
    }

    public UserAuthTokenEntity getUserAuthToken(final String accessToken) {
        try {
            return entityManager.createNamedQuery("userAuthTokenByAccessToken",
                    UserAuthTokenEntity.class).setParameter("accessToken", accessToken).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }

    public ImageEntity getImage(final String imageUuid) {
        try {
            return entityManager.createNamedQuery("ImageEntityByUuid", ImageEntity.class).setParameter("uuid", imageUuid).getSingleResult();
        } catch (NoResultException nre) {
            return null;
        }
    }
}