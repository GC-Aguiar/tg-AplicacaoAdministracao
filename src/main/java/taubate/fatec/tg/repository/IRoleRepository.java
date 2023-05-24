package taubate.fatec.tg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import taubate.fatec.tg.model.Role;

public interface IRoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}