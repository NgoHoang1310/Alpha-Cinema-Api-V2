package com.example.alpha_cinemas.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ApiErrorCode {
    //user
    USER_NOT_FOUND(404, "User not found !"),
    USER_EXISTED(409, "This email has already been used. Please try another email !"),
    PASSWORD_INCORRECT(409, "Password is incorrect !"),
    JWT_EXPIRED(401, "Jwt expired !"),
    NO_TOKEN_PROVIDE(404, "No token provide !"),
    INVALID_JWT_TOKEN(401, "Jwt token is invalid !"),

    //movie
    NO_MOVIE(204, "Not have any movies !"),
    MOVIE_NOT_FOUND(404, "Movie not found !"),

    //room
    ROOM_NOT_FOUND(404, "Room not found !"),
    //schedule
    SCHEDULE_EXISTED(409, "This schedule has already been existed !"),
    SCHEDULE_NOT_FOUND(404, "Schedule not found !"),

    //category
    CATEGORY_NOT_FOUND(404, "Category not found !"),

    //upload file
    UPLOAD_FAILED(500, "Upload file failed !"),
    ;

    private final int code;
    private final String message;


}
