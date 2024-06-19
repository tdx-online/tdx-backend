package edu.hit.tdxbackend.config.authentication;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class JWTUtilTest {

    @Test
    void generateToken() {
    }

    @Test
    void getUsernameFromToken() {
        System.out.println(JWTUtil.getUsernameFromToken(JWTUtil.generateToken("test", "admin")));
    }

    @Test
    void getUserTypeFromToken() {
    }

    @Test
    void validateToken() {
    }
}