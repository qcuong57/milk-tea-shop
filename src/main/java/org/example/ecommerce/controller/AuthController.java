//package org.example.ecommerce.controller;
//
//import lombok.RequiredArgsConstructor;
//import org.example.ecommerce.dto.LoginRequest;
//import org.example.ecommerce.respone.APIResponse;
//import org.example.ecommerce.util.JWTUtil;
//import org.springframework.http.ResponseEntity;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//@RequestMapping("/api/auth")
//@RequiredArgsConstructor
//public class AuthController {
//    private final JWTUtil jwtUtil;
//    private final AuthenticationManager authenticationManager;
//
//    @PostMapping("/login")
//    public ResponseEntity<APIResponse<String>> login(@RequestBody LoginRequest request) {
//        authenticationManager.authenticate(
//                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword())
//        );
//
//        String token = jwtUtil.generateToken(request.getUsername());
//        return ResponseEntity.ok(new APIResponse<>(true, "Login successful", token));
//    }
//}
