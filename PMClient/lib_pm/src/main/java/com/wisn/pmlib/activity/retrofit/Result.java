package com.wisn.pmlib.activity.retrofit;

/**
 * Created by wisn on 2017/8/7.
 */

public class Result {
    public int code;
    public Object reason;
    public User content;

    @Override
    public String toString() {
        return "Result{" +
               "code=" + code +
               ", reason=" + reason +
               ", content=" + content +
               '}';
    }
}

class User{
    public int id;
    public String name;
    public String password;

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", name='" + name + '\'' +
               ", password='" + password + '\'' +
               '}';
    }
}
