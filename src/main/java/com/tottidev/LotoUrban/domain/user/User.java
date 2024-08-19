package com.tottidev.LotoUrban.domain.user;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;
import java.util.List;

@Table(name = "users")
@Entity(name = "User")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String username;

    private String password;

    private String name;

    private Boolean active;

    public User(UserDataRegister userDataRegister) {
        this.email = userDataRegister.email();
        this.username = userDataRegister.username();
        this.password = userDataRegister.password();
        this.name = userDataRegister.name();
        this.active = true;
    }

    public User(UserDataRegister userDataRegister, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.email = userDataRegister.email();
        this.username = userDataRegister.username();
        this.password = bCryptPasswordEncoder.encode(userDataRegister.password());
        this.name = userDataRegister.name();
        this.active = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return UserDetails.super.isAccountNonExpired();
    }

    @Override
    public boolean isAccountNonLocked() {
        return UserDetails.super.isAccountNonLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return UserDetails.super.isCredentialsNonExpired();
    }

    @Override
    public boolean isEnabled() {
        return UserDetails.super.isEnabled();
    }

    public void update(UserDataUpdate userDataUpdate) {
        this.name = userDataUpdate.name();

    }
}
