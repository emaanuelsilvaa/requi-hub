package br.com.imd.requihub.usecase.interfaces;

import br.com.imd.requihub.model.UserModel;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface IUserManager {
    Optional<UserModel> registerUser(final UserModel userModel);

    Optional<UserModel> updateUser(final UserModel userModel);

    Optional<UserModel> deleteUser(final UserModel userModel);

    Page<UserModel> getAllUsers(Pageable pageable);

    Optional<UserModel> getUserByEmail(final String userName);

}
