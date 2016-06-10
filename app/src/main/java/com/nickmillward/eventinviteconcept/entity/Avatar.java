package com.nickmillward.eventinviteconcept.entity;

/**
 * Created by nmillward on 6/10/16.
 */
public class Avatar {

    private int avatarImage;
    private String avatarName;

    public Avatar(int avatarImage, String avatarName) {
        this.avatarImage = avatarImage;
        this.avatarName = avatarName;
    }

    public int getAvatarImage() {
        return avatarImage;
    }

    public String getAvatarName() {
        return avatarName;
    }
}
