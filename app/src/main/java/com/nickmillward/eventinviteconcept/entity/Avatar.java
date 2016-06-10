package com.nickmillward.eventinviteconcept.entity;

/**
 * Created by nmillward on 6/10/16.
 */
public class Avatar {

    private String avatarImage;
    private String avatarName;

    public Avatar(String avatarImage, String avatarName) {
        this.avatarImage = avatarImage;
        this.avatarName = avatarName;
    }

    public String getAvatarImage() {
        return avatarImage;
    }

    public String getAvatarName() {
        return avatarName;
    }
}
