package com.syxbruno.CrudClientRegistration.repository;

import com.syxbruno.CrudClientRegistration.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("clientRepository")
public interface ClientRepository extends JpaRepository<Client, Long> {

    boolean existsByCpf(String cpf);
}
