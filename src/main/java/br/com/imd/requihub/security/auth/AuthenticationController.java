package br.com.imd.requihub.security.auth;

import br.com.imd.requihub.model.UserModel;
import br.com.imd.requihub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@RestController
@RequestMapping("api/v1/public/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final Logger LOG = LoggerFactory.getLogger(AuthenticationController.class);


    private final AuthenticationService service;
    private final UserRepository userRepository;
    private final UserPasswordService userPasswordService;
    private final EmailSenderService emailSenderService;


    @PostMapping("/register")
    //@CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<AuthenticationResponse> register(
        @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(service.register(request));
    }

    @PostMapping("/authenticate")
    //@CrossOrigin(origins = "http://localhost:3000")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody RegisterRequest request
    ){
        return ResponseEntity.ok(service.authenticate(request));
    }

    @PostMapping("/forgot-password")
    public void forgotPassword(@RequestBody PasswordResetInput input){
        Optional<UserModel> optionalUserModel = userRepository.findByEmail(input.getEmail());

        if (!optionalUserModel.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Email nao Encontrado");
        }
        optionalUserModel.ifPresent(userModel -> {
            String token = userPasswordService.generateToken(userModel);
            // enviar email email
            emailSenderService.sendEmail(userModel.getEmail(),"RECUPERAÇAO DE SENHA"
            , "Olá segue o link para redefiniçao de senha: " +
                            "https://requihub.com.br/mudar-senha?token="+ token);
            System.out.println(token);
        });

    }

    @PostMapping("/change-password")
    public void changePassword(@RequestBody PasswordUpdateWithTokenInput input){
       try{
           userPasswordService.changePassword(input.getPassword(), input.getToken());
       }catch (Exception e) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
       }
    }

    @GetMapping
    public String initial(){
        return "welcome";
    }

}
