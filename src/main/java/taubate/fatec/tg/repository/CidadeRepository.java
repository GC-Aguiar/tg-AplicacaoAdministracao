package taubate.fatec.tg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import taubate.fatec.tg.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>{

}
