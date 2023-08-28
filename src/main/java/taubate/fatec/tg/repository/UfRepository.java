package taubate.fatec.tg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import taubate.fatec.tg.model.Uf;

@Repository
public interface UfRepository extends JpaRepository<Uf, Integer> {

}
