package org.example.technical.api.controller;


import org.example.technical.api.model.ImageDetailsResponse;
import org.example.technical.api.model.SignupUserResponse;
import org.example.technical.service.business.AdminService;
import org.example.technical.service.entity.ImageEntity;
import org.example.technical.service.exception.ImageNotFoundException;
import org.example.technical.service.exception.UnauthorizedException;
import org.example.technical.service.exception.UserNotSignedInException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
