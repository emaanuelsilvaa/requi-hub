package br.com.imd.requihub.controller;

import br.com.imd.requihub.dtos.DtoTest;
import br.com.imd.requihub.model.UserModel;
import br.com.imd.requihub.usecase.UserManagerImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequestMapping("api/v1/users")
@RestController
@RequiredArgsConstructor
public class UsersController {

    private final UserManagerImpl userManagerImpl;

    @GetMapping
    public ResponseEntity<Page<UserModel>> getUsers(Pageable pageable){
        return ResponseEntity.ok(userManagerImpl.getAllUsers(pageable));
    }

    @GetMapping("/email")
    public Optional<UserModel> getUser(@RequestParam(value = "email", required = false) String email){
        return userManagerImpl.getUserByEmail(email);
    }

    @PutMapping("/update")
    public UserModel updateUser(@RequestBody UserModel currentUserModel){
        return userManagerImpl.updateUser(currentUserModel).get();
    }

    @DeleteMapping
    public UserModel deleteUser(@RequestBody UserModel currentUserModel){
        return userManagerImpl.deleteUser(currentUserModel).get();
    }

}
