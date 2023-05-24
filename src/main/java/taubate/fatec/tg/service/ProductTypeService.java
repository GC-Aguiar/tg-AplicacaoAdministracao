package taubate.fatec.tg.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import taubate.fatec.tg.model.ProductType;
import taubate.fatec.tg.repository.IProductRepository;
import taubate.fatec.tg.repository.IProductTypeRepository;

import java.util.List;

@Service
@Transactional
public class ProductTypeService {

    @Autowired
    private IProductTypeRepository repo;

    public List<ProductType> listAll() {
        return (List<ProductType>) repo.findAll();
    }

    public void save(ProductType product) {
        repo.save(product);
    }

    public ProductType get(long  id) {
        return repo.findById(id).get();
    }

    public void delete(long id) {
        repo.deleteById(id);
    }
}