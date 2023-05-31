package br.com.imd.requihub.security.auth;


import br.com.imd.requihub.model.UserModel;
import br.com.imd.requihub.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.token.KeyBasedPersistenceTokenService;
import org.springframework.security.core.token.SecureRandomFactoryBean;
import org.springframework.security.core.token.Token;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.Duration;
import java.time.Instant;
import java.util.Base64;
import java.util.Date;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserPasswordService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @SneakyThrows
    public String generateToken(UserModel user ){
        KeyBasedPersistenceTokenService tokenService = getInstanceFor(user);

        Token token = tokenService.allocateToken(user.getEmail());

        return token.getKey();
    }

    @SneakyThrows
    public void changePassword(String newPassword, String rawToken){
        PasswordTokenPublicData publicData = readPublicData(rawToken);
        Optional<UserModel> userModel = userRepository.findByEmail(publicData.getEmail());

        if(isExpired(publicData)){
            throw new RuntimeException("Token Expirado");
        }

        if (!userModel.isPresent()){
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Error");
        }

        KeyBasedPersistenceTokenService tokenService = this.getInstanceFor(userModel.get());
        tokenService.verifyToken(rawToken);

        userModel.get().setPassword(this.passwordEncoder.encode(newPassword));
        userRepository.save(userModel.get());
    }

    private boolean isExpired(PasswordTokenPublicData publicData) {
        Instant createdAt = new Date(publicData.getCreateAtTimestamp()).toInstant();
        Instant now = new Date().toInstant();
        return createdAt.plus(Duration.ofMinutes(10)).isBefore(now);
    }

    private PasswordTokenPublicData readPublicData(String rawToken) {
        byte[] bytes = Base64.getDecoder().decode(rawToken);
        String rawTokenDecoded = new String(bytes);

        String[] tokenParts = rawTokenDecoded.split(":");

        Long timestamp = Long.parseLong(tokenParts[0]);
        String email = tokenParts[2];

        return new PasswordTokenPublicData(email,timestamp);

    }


    private KeyBasedPersistenceTokenService getInstanceFor(UserModel user) throws Exception {
        KeyBasedPersistenceTokenService tokenService = new KeyBasedPersistenceTokenService();
        tokenService.setServerSecret(user.getPassword());
        tokenService.setServerInteger(16);
        tokenService.setSecureRandom(new SecureRandomFactoryBean().getObject());
        return tokenService;
    }
}
