package fact.it.apigateway.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity
                .authorizeExchange(exchange ->
                        exchange
                                .pathMatchers(HttpMethod.GET, "/tasks", "/users", "/notifications").authenticated()
                                .pathMatchers(HttpMethod.POST, "/tasks", "/notifications").authenticated()
                                .pathMatchers(HttpMethod.GET, "/tasks/{id}", "/users/{userId}").authenticated()
                                .pathMatchers(HttpMethod.PUT, "/tasks/{id}").authenticated()
                                .pathMatchers(HttpMethod.DELETE, "/tasks/{id}").authenticated()
                                .anyExchange().permitAll()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(withDefaults())
                );
        return serverHttpSecurity.build();
    }
}
