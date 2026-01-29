package com.example.pz19;

import java.util.ArrayList;
import java.util.List;

public class UserStaticInfo {
    public final static String USERS_SIGN_IN_INFO = "UsersSignInInfo";
    public final static String USERS_PROFILE_INFO = "UsersProfileInfo";
    public final static String PASSWORD = "password";
    public final static String PROFILE_ID = "profileId";
    public final static String NAME = "name";
    public final static String AGE = "age";
    public final static String STATE = "state";
    public static String profileId;

    public static List<User> users = new ArrayList<>();
    public final static String POSITION = "position";
    public UserStaticInfo(){
        if (users.size()==0)
            AddUsersInList();
    }
    private void AddUsersInList() {
        users.add(new User("hhh", "hhh", 19, 1));
        users.add(new User(",lmjk", "nhbg", 19,0));
        users.add(new User("gfcx", "jkh", 19,2));
        users.add(new User("gf", "gdexsr", 19,2));
        users.add(new User("gdxswe", "uih", 19,1));
    }
}
