package com.Oran.Makizone.Config;

import com.Oran.Makizone.Utilities.Hasher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. Disable CSRF (Standard for JWT/Stateless APIs)
                .csrf(AbstractHttpConfigurer::disable)

                // 2. Enable CORS (To allow Vue on port 5173 to connect)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))

                // 3. Define URL Rules
                .authorizeHttpRequests(auth -> auth
                        // Allow anyone to access Login and Register
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/garden/**").permitAll()
                        // Block everything else
                        .anyRequest().authenticated()
                ).sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));;

        return http.build();
    }

    /**
     * THE WRAPPER:
     * This tells Spring to use YOUR custom static Hasher class
     * whenever it needs to hash or check a password.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return Hasher.hashPassword(rawPassword.toString());
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return Hasher.verifyPassword(rawPassword.toString(), encodedPassword);
            }
        };
    }

    // CORS CONFIGURATION (The Bridge for Vue)
    @Bean
    public UrlBasedCorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();

        // Allow the frontend URL
        configuration.setAllowedOrigins(List.of("http://localhost:5173"));

        // Allow all HTTP methods (GET, POST, etc.)
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));

        // Allow all headers
        configuration.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}