package com.example.pz19;

import java.util.ArrayList;
import java.util.List;

public class UserStaticInfo {
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
