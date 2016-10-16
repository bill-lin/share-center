package com.linguo.sharecenter.exception;

/**
 * Created by bin on 05/09/2016.
 */
public class AccessTokenError extends Throwable {
    public AccessTokenError(String message){
        super(message);
    }

    public AccessTokenError(String message, Throwable cause) {
        super(message, cause);
    }
}
