package com.nickmillward.eventinviteconcept.model;

import com.nickmillward.eventinviteconcept.entity.Avatar;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by nmillward on 6/10/16.
 */
public class AvatarUserData {

    List<Avatar> avatars;

    public List<Avatar> getAvatars() {

        avatars = new ArrayList<>();

        // Mock Data
        avatars.add(new Avatar("http://pbs.twimg.com/profile_images/603269306026106880/42CwEF4n.jpg", "Tom"));
        avatars.add(new Avatar("http://pbs.twimg.com/profile_images/651445595081605121/0B67DmRf.png", "Oprah"));
        avatars.add(new Avatar("http://pbs.twimg.com/profile_images/668328458519384064/FSAIjKRl.jpg", "Jack"));
        avatars.add(new Avatar("http://pbs.twimg.com/profile_images/585565077207678977/N_eNSBXi.jpg", "Kanye"));
        avatars.add(new Avatar("http://pbs.twimg.com/profile_images/668902554957316096/IpjBGyjC.jpg", "Chris"));
        avatars.add(new Avatar("http://pbs.twimg.com/profile_images/738744285101580288/OUoCVEXG.jpg", "Barack"));
        avatars.add(new Avatar("http://pbs.twimg.com/profile_images/710185851498729473/80w95ZyZ.jpg", "Nick"));
        avatars.add(new Avatar("http://pbs.twimg.com/profile_images/1269252993/HeidiKlum.jpg", "Heidi"));

        return avatars;
    }
}
