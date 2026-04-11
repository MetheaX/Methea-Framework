package com.metheax.sena.repository;

import com.metheax.sena.domain.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

/**
 * Author : Kuylim Tith
 * Date : 21/08/2019
 */
public interface AccountRepository extends JpaRepository<Account, UUID> {
}
