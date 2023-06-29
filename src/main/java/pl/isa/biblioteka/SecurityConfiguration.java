package pl.isa.biblioteka;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfiguration {


/*    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }*/

    @Bean
    @ConditionalOnMissingBean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(request -> request
                        .requestMatchers("", "/", "/images/**", "/css/**", "/static/font/**", "/font/**", "/searchText", "/searchByText", "/list", "/bookList/**", "/register").permitAll())
                .formLogin(form -> form
                        .permitAll()
                        .failureUrl("/")
                        .defaultSuccessUrl("/"))
                .logout(logout -> logout
                        .logoutUrl("/")
                        .logoutSuccessUrl("/"));
        return http.build();
    }
}

/*    @Bean
    public InMemoryUserDetailsManager userDetailsService(PasswordEncoder passwordEncoder) {

        List<Person> personList = PersonService.readUsers();

        List<UserDetails> admin = personList.stream().map(person -> User.withUsername(person.getLogin()).password(passwordEncoder.encode(person.getPassword())).roles(person.getLogin().equalsIgnoreCase("admin") || person.getLogin().equalsIgnoreCase("bibliotekarz") ? "ADMIN" : "USER").build()).collect(Collectors.toList());

        return new InMemoryUserDetailsManager(admin.stream().toArray(UserDetails[]::new));
    }*/


/*    @Bean      //KONFIGURACJA BEZ ZABEZPIECZENIA authorizeHttpRequests
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests(authorize -> authorize.requestMatchers("/", "/images/**", "/css/**", "/static/font/**", "/font/**", "/searchText", "/searchByText", "/list", "/bookList/**", "/register").permitAll().anyRequest().authenticated()).formLogin(login -> login.loginPage("/").defaultSuccessUrl("/", true).usernameParameter("user").passwordParameter("password")).logout(logout -> logout.logoutSuccessUrl("/").permitAll());
        return http.build();
    }*/

//    @Bean      //KONFIGURACJA BEZ ZABEZPIECZENIA authorizeHttpRequests
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable().authorizeHttpRequests(authorize -> authorize.requestMatchers("/", "/images/**", "/css/**", "/static/font/**", "/font/**", "/searchText", "/searchByText", "/list", "/bookList/**", "/register").permitAll().anyRequest().authenticated()).formLogin(login -> login.loginPage("/").defaultSuccessUrl("/", true).usernameParameter("user").passwordParameter("password")).logout(logout -> logout.logoutSuccessUrl("/").permitAll());
//        return http.build();
//    }



/*    @Bean      KONFIGURACJA Z ZABEZPIECZENIEM
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/", "/images/**", "/css/**", "/static/font/**", "/font/**",
                                "/searchText", "/searchByText", "/list", "/bookList/**", "/register")
                        .permitAll()
                        .anyRequest()
                        .authenticated())
                .formLogin(login -> login.loginPage("/")
                        .defaultSuccessUrl("/", true)
                        .usernameParameter("user").passwordParameter("password"))
                .logout(logout -> logout.logoutSuccessUrl("/logout").permitAll());
        return http.build();
    }*/


//todo skasować przed wgraniem na main

    /*    @Bean  metoda dopuszcza każdego bez logowania
    SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.authorizeHttpRequests()
                .anyRequest().permitAll();
                .and().formLogin().defaultSuccessUrl("index.html");
        return httpSecurity.build();
    }*/




/*@Bean    Dodać metodę AntMatchers   do wejścia dowolnego usera na daną stronę i logowanie dopiero z formularza
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.authorizeRequests()
            .anyRequest()
            .authenticated()
            .and().formLogin().loginPage("/");
    return http.build();
}*/


/*
    protected void logoutUser(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .logout()
                .logoutSuccessUrl("index")
                .permitAll()
                .and()
                .authorizeHttpRequests().
    }*/
