package br.com.imd.requihub.usecase;

import br.com.imd.requihub.model.UserModel;
import br.com.imd.requihub.repository.UserRepository;
import br.com.imd.requihub.usecase.interfaces.IUserManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@SuppressWarnings("SameReturnValue")
@Service
@RequiredArgsConstructor
public class UserManagerImpl implements IUserManager, UserDetailsService {

    private final UserRepository userRepository;


    @Transactional(rollbackFor = Exception.class)
    @Override
    public Optional<UserModel> registerUser(UserModel userModel) {

        boolean userExists = this.existUser(userModel.getEmail());

        if(userExists)
            throw new ResponseStatusException(
                    HttpStatus.UNPROCESSABLE_ENTITY, "Email already registered");

        return Optional.of(userRepository.save(userModel));
    }

    @Override
    public Optional<UserModel> updateUser(UserModel userModel) {
        Optional<UserModel> currentUser = userRepository.findById(userModel.getId());
        return Optional.of(userRepository.save(userModel));
    }

    @Override
    public Optional<UserModel> deleteUser(UserModel userModel) {
        userRepository.delete(userModel);
        return Optional.of(userModel);
    }

    @Override
    public Page<UserModel> getAllUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Override
    public Optional<UserModel> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    private Boolean existUser(String email){
        return userRepository.findByEmail(email).isPresent();
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserModel> requiUser = userRepository.findByEmail(email);
        if (requiUser.isPresent())
            return requiUser.get();

        String USER_NOT_FOUND_MSG = "User with email %s not found";
        throw new UsernameNotFoundException(String.format(USER_NOT_FOUND_MSG, email));
    }
}
