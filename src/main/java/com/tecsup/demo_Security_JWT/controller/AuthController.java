package com.tecsup.demo_Security_JWT.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import com.tecsup.demo_Security_JWT.dto.JwtResponse;
import com.tecsup.demo_Security_JWT.dto.LoginRequest;
import com.tecsup.demo_Security_JWT.dto.MessageResponse;
import com.tecsup.demo_Security_JWT.dto.SignupRequest;
import com.tecsup.demo_Security_JWT.entity.Role;
import com.tecsup.demo_Security_JWT.entity.User;
import com.tecsup.demo_Security_JWT.jwt.JwtUtils;
import com.tecsup.demo_Security_JWT.repository.RoleRepository;
import com.tecsup.demo_Security_JWT.repository.UserRepository;
import com.tecsup.demo_Security_JWT.service.UserDetailsImpl;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired UserRepository userRepository;
    @Autowired RoleRepository roleRepository;
    @Autowired PasswordEncoder encoder;
    @Autowired AuthenticationManager authenticationManager;
    @Autowired JwtUtils jwtUtils;

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest req) {

        if (userRepository.existsByUsername(req.getUsername()))
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Username ya existe"));

        if (userRepository.existsByEmail(req.getEmail()))
            return ResponseEntity.badRequest()
                    .body(new MessageResponse("Error: Email ya existe"));

        User user = new User();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPassword(encoder.encode(req.getPassword()));

        Set<Role> roles = new HashSet<>();

        if (req.getRoles() == null || req.getRoles().isEmpty()) {

            Role role = roleRepository.findByName("USER")
                    .orElseThrow(() -> new RuntimeException("Rol USER no encontrado"));

            roles.add(role);

        } else {

            req.getRoles().forEach(r -> {
                Role role = roleRepository.findByName(r.toUpperCase())
                        .orElseThrow(() -> new RuntimeException("Rol no encontrado"));
                roles.add(role);
            });
        }

        user.setRoles(roles);
        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("Usuario registrado correctamente"));
    }

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody LoginRequest req) {

        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        req.getEmail(),
                        req.getPassword()
                )
        );

        SecurityContextHolder.getContext().setAuthentication(auth);

        String jwt = jwtUtils.generateJwtToken(auth);

        UserDetailsImpl userDetails = (UserDetailsImpl) auth.getPrincipal();

        List<String> roles = userDetails.getAuthorities()
                .stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(
                new JwtResponse(
                        userDetails.getId(),
                        userDetails.getUsername(),
                        userDetails.getEmail(),
                        jwt,
                        roles
                )
        );
    }
}