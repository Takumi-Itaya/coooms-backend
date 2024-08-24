package com.api.coooms.security;

import com.api.coooms.Model.Users;
import com.api.coooms.Repository.UsersRepository;

import lombok.RequiredArgsConstructor;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginUserDetailsService implements UserDetailsService {

	@Autowired
    UsersRepository usersRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
    	Optional<Users> maybeUser = Optional.of(usersRepository.findByEmail(email));
        return maybeUser.map(user -> new LoginUserDetails(user))
                .orElseThrow(() -> new UsernameNotFoundException("email not found."));
    }
}
