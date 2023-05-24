package taubate.fatec.tg.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import taubate.fatec.tg.model.User;

public interface IUserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
}