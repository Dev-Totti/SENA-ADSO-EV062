package com.tottidev.LotoUrban.controller;

import com.tottidev.LotoUrban.domain.user.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping
    @Transactional
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDataRegister userDataRegister, UriComponentsBuilder uriComponentsBuilder) {

        User user = userRepository.save(new User(userDataRegister, bCryptPasswordEncoder));
        UserDataResponse userDataResponse = new UserDataResponse(user);
        URI url = uriComponentsBuilder.path("/user/{id}").buildAndExpand(user.getId()).toUri();

        return ResponseEntity.created(url).body(userDataResponse);

    }

    @GetMapping
    public ResponseEntity<Page<UserDataDisplay>> getUsers(@PageableDefault(size=5) Pageable pageable) {
        return ResponseEntity.ok(userRepository.findByActiveTrue(pageable).map(UserDataDisplay::new));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<?> updateUser(@RequestBody @Valid UserDataUpdate userDataUpdate) {
        User user = userRepository.getReferenceById(userDataUpdate.id());
        user.update(userDataUpdate);
        return ResponseEntity.ok(new UserDataDisplay(user));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        User user = userRepository.getReferenceById(id);
        user.setActive(false);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDataDisplay> getUser(@PathVariable Long id) {
        User user = userRepository.getReferenceById(id);
        return ResponseEntity.ok(new UserDataDisplay(user));
    }

}
