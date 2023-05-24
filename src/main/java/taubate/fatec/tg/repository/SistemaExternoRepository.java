package taubate.fatec.tg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import taubate.fatec.tg.model.SistemaExterno;

@Repository
public interface SistemaExternoRepository extends JpaRepository<SistemaExterno, Integer>{

}
