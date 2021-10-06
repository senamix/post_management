package com.example.su.security;

import org.springframework.security.core.context.SecurityContextHolder;


public class SecurityUtil {
    public static CustomUserDetails getCustomUser(){
        try {
            CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            if (customUserDetails != null) {
                return customUserDetails;
            }
        }catch (Exception e) {
            e.getMessage();
        }
        return null;
    }
}
