package com.nickmillward.eventinviteconcept.model;

import com.nickmillward.eventinviteconcept.entity.Avatar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nmillward on 6/10/16.
 */
public class AvatarUserData {

    private List<Avatar> avatars;

    public List<Avatar> getAvatars() {

        avatars = new ArrayList<>();

        avatars.add(new Avatar("https://pbs.twimg.com/profile_images/710185851498729473/80w95ZyZ.jpg", "Nick"));
        avatars.add(new Avatar("https://pbs.twimg.com/profile_images/710185851498729473/80w95ZyZ.jpg", "Nick"));
        avatars.add(new Avatar("https://pbs.twimg.com/profile_images/710185851498729473/80w95ZyZ.jpg", "Nick"));
        avatars.add(new Avatar("https://pbs.twimg.com/profile_images/710185851498729473/80w95ZyZ.jpg", "Nick"));
        avatars.add(new Avatar("https://pbs.twimg.com/profile_images/710185851498729473/80w95ZyZ.jpg", "Nick"));
        avatars.add(new Avatar("https://pbs.twimg.com/profile_images/710185851498729473/80w95ZyZ.jpg", "Nick"));
        avatars.add(new Avatar("https://pbs.twimg.com/profile_images/710185851498729473/80w95ZyZ.jpg", "Nick"));
        avatars.add(new Avatar("https://pbs.twimg.com/profile_images/710185851498729473/80w95ZyZ.jpg", "Nick"));

        return avatars;
    }
}
