package nchl.fellow.client.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

        http.oauth2Login(Customizer.withDefaults());
        http.authorizeHttpRequests(c->c.anyRequest().authenticated());
        return  http.build();
    }
}
