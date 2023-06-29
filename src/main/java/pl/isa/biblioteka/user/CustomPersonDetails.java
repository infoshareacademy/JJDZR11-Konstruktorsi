package pl.isa.biblioteka.user;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class CustomPersonDetails implements UserDetails {

    private PersonDTO personDTO;

    public CustomPersonDetails(PersonDTO personDTO) {
        this.personDTO = personDTO;
    }

    public PersonDTO getPersonDTO() {
        return personDTO;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = personDTO.getRole();
        GrantedAuthority authority = new SimpleGrantedAuthority(role);
        return Collections.singleton(authority);
    }

    @Override
    public String getPassword() {
        return personDTO.getPassword();
    }

    @Override
    public String getUsername() {
        return personDTO.getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
