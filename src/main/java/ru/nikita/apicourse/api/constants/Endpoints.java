package ru.nikita.apicourse.api.constants;

public interface Endpoints {
    //* TARGET ID *//
    String TARGET_ID = "/{id}";

    //* AUTH *//
    String AUTH = "/auth";
    String REGISTER = "/register";
    String AUTHENTICATE = "/authenticate";
    String REFRESH_ACCESS_TOKEN = "/refresh";

    //* BLOGS *//
    String BLOGS = "/blogs";
    String BLOG_POSTS = TARGET_ID + "/posts";
    String BLOG_POST = "/posts" + TARGET_ID;

    //* USERS *//
    String USERS = "/users";
    String USER_BLOGS = TARGET_ID + "/blogs";
}
