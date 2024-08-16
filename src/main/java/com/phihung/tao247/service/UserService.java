package com.phihung.tao247.service;

import com.phihung.tao247.exception.UserException;
import com.phihung.tao247.model.User;

public interface UserService {
    public User findUserById(Long userId) throws UserException;

    public User findUserProfileByJwt(String jwt) throws UserException;
}
