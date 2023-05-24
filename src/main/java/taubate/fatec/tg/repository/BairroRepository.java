package taubate.fatec.tg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import taubate.fatec.tg.model.Bairro;

@Repository
public interface BairroRepository extends JpaRepository<Bairro, Integer>{

}
