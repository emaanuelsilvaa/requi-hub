package br.com.imd.requihub.security.auth;

import br.com.imd.requihub.model.UserModel;
import br.com.imd.requihub.repository.UserRepository;
import br.com.imd.requihub.security.config.JwtService;
import br.com.imd.requihub.security.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {
        final UserModel userModel = UserModel.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        repository.save(userModel);
        String jwttoken = jwtService.generateToken(userModel);
        return AuthenticationResponse.builder()
                .token(jwttoken)
                .build();
    }
    @Transactional
    public AuthenticationResponse authenticate(RegisterRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        Optional<UserModel> usermod = repository.findByEmail(request.getEmail());
        if (usermod.isPresent()){
            String jwtToken = jwtService.generateToken(usermod.get());
            return AuthenticationResponse.builder()
                    .token(jwtToken)
                    .email(request.getEmail())
                    .userId(usermod.get().getId().toString())
                    .build();
        }else
        throw new ResponseStatusException(
                HttpStatus.UNPROCESSABLE_ENTITY, "user not found");
    }
}
