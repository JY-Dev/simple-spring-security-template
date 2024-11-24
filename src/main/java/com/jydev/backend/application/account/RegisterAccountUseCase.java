package com.jydev.backend.application.account;

import com.jydev.backend.application.account.error.DuplicateLoginIdException;
import com.jydev.backend.domain.auth.Account;
import com.jydev.backend.domain.auth.AccountRepository;
import com.jydev.backend.domain.user.PrivacyEncryptor;
import com.jydev.backend.domain.user.User;
import com.jydev.backend.domain.user.UserPrivacy;
import com.jydev.backend.domain.user.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegisterAccountUseCase {

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final PrivacyEncryptor privacyEncryptor;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public void register(
            String loginId,
            String password,
            String name
    ) {
        var loginIdExist = accountRepository.existsByLoginId(loginId);
        if (loginIdExist) {
            throw new DuplicateLoginIdException("login id already exist");
        }

        var userPrivacy = UserPrivacy.create(name, privacyEncryptor);
        var user = new User(userPrivacy);
        var savedUser = userRepository.save(user);

        var encodedPassword = passwordEncoder.encode(password);
        var account = Account.builder()
                .loginId(loginId)
                .password(encodedPassword)
                .userId(savedUser.getId())
                .build();
        accountRepository.save(account);
    }
}
