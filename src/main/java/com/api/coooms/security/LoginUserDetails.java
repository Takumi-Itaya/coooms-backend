package com.api.coooms.security;

import com.api.coooms.Model.Users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collection;
import java.util.ArrayList;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@RequiredArgsConstructor
public class LoginUserDetails implements UserDetails {

	private Users user;

    public LoginUserDetails(Users user) {
		this.user = user;
	}

	// ユーザーが持つ権限を返す
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    // ユーザーアカウントが有効期限切れでないかを判定する
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // ユーザーアカウントがロックされていないかを判定する
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    // 認証情報が有効期限切れでないかを判定する
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
