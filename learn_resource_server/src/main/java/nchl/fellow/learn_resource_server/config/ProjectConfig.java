package nchl.fellow.learn_resource_server.config;

import nchl.fellow.learn_resource_server.converter.JwtAuthenticationConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
//@EnableMethodSecurity(prePostEnabled = true)
public class ProjectConfig {

    @Value("${introspectionUri}")
    private String introspectionUri;
    @Value("${resourceserver.clientID}")
    private String resourceServerClientID;
    @Value("${resourceserver.secret}")
    private String resourceServerSecret;


    private final JwtAuthenticationConverter converter;


    @Value("${keySetURI}")
    private String keySetUri;

    public ProjectConfig(JwtAuthenticationConverter converter) {
        this.converter = converter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.oauth2ResourceServer(
                c->c.jwt(j->j.jwkSetUri(keySetUri)
                        .jwtAuthenticationConverter(converter)));
        http.authorizeHttpRequests(
                c->c.anyRequest()
                                  .authenticated());

        return http.build();

    }
//}


// URL for the authorization code
// http://localhost:8080/oauth2/authorize?response_type=code&client_id=client&scope=openid&redirect_uri=https://www.manning.com/authorized&code_challenge=QYPAZ5NU8yvtlQ9erXrUYR-T5AGCjCF47vN-KsaI2A8&code_challenge_method=S256

// Url for openid datas
//http://localhost:8001/.well-known/openid-configuration

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http)
//            throws Exception {
//        http.oauth2ResourceServer(
//                c -> c.opaqueToken(
//                        o -> o.introspectionUri(introspectionUri)
//                                .introspectionClientCredentials(
//                                        resourceServerClientID,
//                                        resourceServerSecret)
//                )
//        );
//        http.authorizeHttpRequests(c->c.anyRequest().authenticated());
//        return http.build();
//    }
}
