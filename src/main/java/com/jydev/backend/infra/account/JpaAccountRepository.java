package com.jydev.backend.infra.account;

import com.jydev.backend.domain.auth.Account;
import com.jydev.backend.domain.auth.AccountRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaAccountRepository extends JpaRepository<Account, Long> ,AccountRepository {
}
