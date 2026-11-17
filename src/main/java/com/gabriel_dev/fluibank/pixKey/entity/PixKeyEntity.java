package com.gabriel_dev.fluibank.pixKey.entity;

import com.gabriel_dev.fluibank.account.entity.AccountEntity;
import com.gabriel_dev.fluibank.pixKey.enums.PixKeyType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "pix_key")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PixKeyEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String key;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PixKeyType type;

    @ManyToOne
    @JoinColumn(name = "account_id", nullable = false)
    private AccountEntity account;

    @CreationTimestamp
    private LocalDateTime createdAt;
}
