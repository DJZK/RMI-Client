package com.Bryan.Client;

public class User {

    private static String Username, Password, Type, Region;

    public static void setUsername(String username) {
        Username = username;
    }

    public static String getUsername() {
        return Username;
    }

    public static void setPassword(String password) {
        Password = password;
    }

    public static void setType(String type){
        Type = type;
    }

    public static String getType(){
        return Type;
    }

    public static void setRegion(String region) {Region = region;}

    public static String getRegion (){ return Region;}
}
