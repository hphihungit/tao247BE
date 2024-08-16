package com.phihung.tao247.service;

import com.phihung.tao247.config.JwtProvider;
import com.phihung.tao247.exception.UserException;
import com.phihung.tao247.model.User;
import com.phihung.tao247.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserServiceImplementation implements UserService{
    private UserRepository userRepository;
    private JwtProvider jwtProvider;

    public UserServiceImplementation(UserRepository userRepository, JwtProvider jwtProvider) {
        this.userRepository = userRepository;
        this.jwtProvider = jwtProvider;
    }
    @Override
    public User findUserById(Long userId) throws UserException {
        Optional<User> user = userRepository.findById(userId);

        if (user.isPresent()) {
            return user.get();
        }

        throw new UserException("Người dùng không tương thích với id - " + userId);
    }

    @Override
    public User findUserProfileByJwt(String jwt) throws UserException {
        String email = jwtProvider.getEmailFromToken(jwt);

        User user = userRepository.findByEmail(email);

        if (user== null) {
            throw new UserException("Người dùng không tương thích với email - " + email);
        }
        return user;
    }
}
