package org.example.technical.api.controller;


import org.example.technical.api.model.*;
import org.example.technical.service.business.AdminService;
import org.example.technical.service.business.ImageUploadService;
import org.example.technical.service.entity.ImageEntity;
import org.example.technical.service.exception.ImageNotFoundException;
import org.example.technical.service.exception.UnauthorizedException;
import org.example.technical.service.exception.UserNotSignedInException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.ZonedDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class AdminController {


    @Autowired
    private AdminService adminService;

    @RequestMapping(method = RequestMethod.GET, path = "/images/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<ImageDetailsResponse> getImage(@PathVariable("id") final String imageUuid, @RequestHeader("authorization") final String authorization) throws ImageNotFoundException, UnauthorizedException, UserNotSignedInException {

        final ImageEntity imageEntity = adminService.getImage(imageUuid, authorization);

        ImageDetailsResponse imageDetailsResponse = new ImageDetailsResponse().image(imageEntity.getImage()).id((int) imageEntity.getId()).name(imageEntity.getName()).description(imageEntity.getDescription()).status(imageEntity.getStatus());

        return new ResponseEntity<ImageDetailsResponse>(imageDetailsResponse, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT, path = "/images/update/{image_id}", consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<UpdateImageResponse> updateImage(final UpdateImageRequest updateImageRequest, @PathVariable("image_id") final long image_id, @RequestHeader("authorization") final String authorization) throws ImageNotFoundException, UnauthorizedException, UserNotSignedInException {
        final ImageEntity updateImageEntity = new ImageEntity();

        updateImageEntity.setId(image_id);
        updateImageEntity.setImage(updateImageRequest.getImage());
        updateImageEntity.setName(updateImageRequest.getName());
        updateImageEntity.setDescription(updateImageRequest.getDescription());
        updateImageEntity.setStatus(updateImageRequest.getStatus());

        final ImageEntity updatedimageEntity = adminService.updateImage(updateImageEntity, authorization);
        UpdateImageResponse updateImageResponse = new UpdateImageResponse().id((int) updatedimageEntity.getId()).status(updatedimageEntity.getStatus());
        return new ResponseEntity<UpdateImageResponse>(updateImageResponse, HttpStatus.OK);
    }
}
