package taubate.fatec.tg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import taubate.fatec.tg.model.Municipe;


@Repository
public interface MunicipeRepository extends JpaRepository<Municipe, Integer> {

}
