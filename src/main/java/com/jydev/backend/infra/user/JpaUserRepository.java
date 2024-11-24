package com.jydev.backend.infra.user;

import com.jydev.backend.domain.user.User;
import com.jydev.backend.domain.user.UserRepository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaUserRepository extends JpaRepository<User, Long>, UserRepository {
}
