package ru.shasoka.dcis.prac_5.security;

import java.util.Collection;
import java.util.Collections;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import ru.shasoka.dcis.prac_5.models.User;

/**
 * Реализация интерфейса UserDetails для пользователя магазина.
 */
public class UserData implements UserDetails {

    private final User user;

    /**
     * Конструктор для ShopUserDetails.
     *
     * @param user Пользователь магазина.
     */
    public UserData(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(user.getRole()));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
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

    /**
     * Получает объект пользователя магазина.
     *
     * @return Объект пользователя магазина.
     */
    public User getUser() {
        return user;
    }
}
