//package com.example.localmarket.controller;
//
//import com.example.localmarket.dto.UserDTO;
//import com.example.localmarket.service.AuthService;
//import jakarta.validation.Valid;
//import lombok.AllArgsConstructor;
//import lombok.NoArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@AllArgsConstructor
//public class AuthController {
//    private final AuthService authService;
//
//    public ResponseEntity<UserDTO> signup(@Valid @RequestBody SignUpDTO singUpDTO) {
//        UserDTO createUser = authService.signup(signUpDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).body(createUser);
//    }
//}
