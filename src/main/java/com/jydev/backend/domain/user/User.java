package com.jydev.backend.domain.user;

import com.jydev.backend.core.jpa.TimeAuditableEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "\"USER\"")
@Entity
public class User extends TimeAuditableEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "name", column = @Column(name = "NAME"))
    })
    private UserPrivacy userPrivacy;

    public User(UserPrivacy userPrivacy) {
        this.userPrivacy = Objects.requireNonNull(userPrivacy);
    }
}
