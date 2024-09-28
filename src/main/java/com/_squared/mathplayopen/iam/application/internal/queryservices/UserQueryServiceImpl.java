package com._squared.mathplayopen.iam.application.internal.queryservices;

import com._squared.mathplayopen.iam.domain.model.aggregates.User;
import com._squared.mathplayopen.iam.domain.model.queries.GetAllUsersQuery;
import com._squared.mathplayopen.iam.domain.model.queries.GetUserByIdQuery;
import com._squared.mathplayopen.iam.domain.model.queries.GetUserByUsernameQuery;
import com._squared.mathplayopen.iam.domain.services.UserQueryService;
import com._squared.mathplayopen.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Implementation of {@link UserQueryService} interface.
 * @version 1.0
 */
@Service
public class UserQueryServiceImpl implements UserQueryService {


    private final UserRepository userRepository;


    public UserQueryServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    /**
     * This method is used to handle {@link GetAllUsersQuery} query.
     * @param query {@link GetAllUsersQuery} instance.
     * @return {@link List} of {@link User} instances.
     */
    @Override
    public List<User> handle(GetAllUsersQuery query) {
        return userRepository.findAll();
    }



    /**
     * This method is used to handle {@link GetUserByIdQuery} query.
     * @param query {@link GetUserByIdQuery} instance.
     * @return {@link Optional} of {@link User} instance.
     */
    @Override
    public Optional<User> handle(GetUserByIdQuery query) {
        return userRepository.findById(query.userId());
    }



    /**
     * This method is used to handle {@link GetUserByUsernameQuery} query.
     * @param query {@link GetUserByUsernameQuery} instance.
     * @return {@link Optional} of {@link User} instance.
     */
    @Override
    public Optional<User> handle(GetUserByUsernameQuery query) {
        return userRepository.findByUsername(query.username());
    }

}