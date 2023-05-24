package taubate.fatec.tg.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import taubate.fatec.tg.model.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Integer>{
	

}
