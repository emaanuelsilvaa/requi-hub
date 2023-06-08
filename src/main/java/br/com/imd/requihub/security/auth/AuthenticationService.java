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
    private final UserRepository userRepository;

    public AuthenticationResponse register(RegisterRequest request) {
        final Optional<UserModel> userModel1 = userRepository.findByEmail(request.getEmail());
        if (userModel1.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Email informado ja está Cadastrado ! ");
        }
        if(request.getPassword().length() < 7){
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "A senha deve conter no minimo 7 caracteres");
        }
        if(request.getEmail().length() < 100 && !request.getEmail().contains("@")){
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Digite um email valido");
        }
        if(request.getFirstName().length() < 100 ){
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Primeiro nome muito longo");
        }
        if(request.getLastName().length() < 100 ){
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Ultimo nome muito longo");
        }
        if(request.getSobre().length() < 1000 ){
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Conteudo sobre muito longo");
        }
        final UserModel userModel = UserModel.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .about(request.getSobre())
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
                    .profilePhoto(usermod.get().getProfilePhoto())
                    .build();
        }else
        throw new ResponseStatusException(
                HttpStatus.UNPROCESSABLE_ENTITY, "user not found");
    }
}
