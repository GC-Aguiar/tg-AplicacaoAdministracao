package taubate.fatec.tg.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import taubate.fatec.tg.model.ProductType;

public interface IProductTypeRepository extends JpaRepository<ProductType, Long> {

}