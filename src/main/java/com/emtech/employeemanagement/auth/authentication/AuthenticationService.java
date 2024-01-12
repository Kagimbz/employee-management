package com.emtech.employeemanagement.auth.authentication;

import com.emtech.employeemanagement.auth.authentication.dto.AuthResponse;
import com.emtech.employeemanagement.auth.authentication.dto.AuthenticationRequest;
import com.emtech.employeemanagement.auth.authentication.dto.RegistrationRequest;
import com.emtech.employeemanagement.auth.jwt.JwtService;
import com.emtech.employeemanagement.auth.roles.Role;
import com.emtech.employeemanagement.auth.users.User;
import com.emtech.employeemanagement.auth.users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthResponse register(RegistrationRequest request) {
        var user = User.builder()
                        .firstName(request.getFirstName())
                        .lastName(request.getLastName())
                        .email(request.getEmail())
                        .password(passwordEncoder.encode(request.getEmail()))
                        .role(Role.ADMIN)
                .build();
        repository.save(user);
        var jwt = jwtService.generateToken(user);
        return AuthResponse.builder()
                .jwt(jwt)
                .build();

    }

    public AuthResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new UsernameNotFoundException("User Does Not Exist!!"));
        var jwt = jwtService.generateToken(user);
        return AuthResponse.builder()
                .jwt(jwt)
                .build();

    }
}
