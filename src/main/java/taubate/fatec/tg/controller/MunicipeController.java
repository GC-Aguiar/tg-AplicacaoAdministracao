package taubate.fatec.tg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import taubate.fatec.tg.model.Municipe;
import taubate.fatec.tg.service.MunicipeService;

@Controller
@RequestMapping("/municipe")
public class MunicipeController {

    @Autowired
    private MunicipeService municipeService;

    private String add_edit_template="/admin/municipe/add-edit-municipe";
    private String list_template="/admin/municipe/list-municipe";
    private String list_redirect="redirect:/admin/municipe/list-municipe";


    @GetMapping("/add")
    public String addProduct(){
    	//public String addProduct(Product product, Model model){
        /*
    	model.addAttribute("product", product);
        List<ProductType> productTypes = productTypeService.listAll();
        model.addAttribute("productTypes",productTypes);
*/
        return add_edit_template;
    }

/*
    @GetMapping("/edit/{id}")
    public String editProduct(@PathVariable("id") long id, Model model){
        Product product = productService.get(id);
        model.addAttribute("product", product);

        List<ProductType> productTypes = productTypeService.listAll();
        model.addAttribute("productTypes",productTypes);

        return add_edit_template;
    }

    @PostMapping("/save")
    public String saveProduct(@Valid @ModelAttribute("product") Product product, BindingResult result, Model model){
        model.addAttribute("product", product);
        List<ProductType> productTypes = productTypeService.listAll();
        model.addAttribute("productTypes",productTypes);

        if(result.hasErrors()){
            return add_edit_template;
        }

        productService.save(product);
        return list_redirect;
    }



    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable("id") long id, Model model) {
        productService.delete(id);

        return list_redirect;
    }
*/
    @GetMapping("/list")
    public ModelAndView listarMunicipes() {
        /*
    	List<ProductType> productTypes = productTypeService.listAll();
        model.addAttribute("productTypes",productTypes);
*/
        List<Municipe> listMunicipes = (List<Municipe>) municipeService.listAllMunicipes();
        //System.out.println(listMunicipes);
        ModelAndView modelAndView = new ModelAndView("/admin/municipe/list-municipe");
        modelAndView.addObject("listMunicipes", listMunicipes);
       
    	
    	
        //Chamar o método do service para receber os dados da API. Colocar esses dados em uma list.
    	
    	//System.out.println(municipeService.listAllMunicipes());
        
        //Adicionar a list de municipes em um Model and View
        
        //Retornar a Model and View

        return modelAndView;
    }
    /*@GetMapping("/list")
    public String listProduct(Model model
            ,@RequestParam("search") Optional<String> search
            ,@RequestParam("page") Optional<Integer> page
            ,@RequestParam("size") Optional<Integer> size)
    {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(5);
        String criteria = search.orElse("");

        List<ProductType> productTypes = productTypeService.listAll();
        model.addAttribute("productTypes",productTypes);

        Page<Product> listProducts = productService.findPaginated(criteria, PageRequest.of(currentPage - 1, pageSize));
        model.addAttribute("listProducts", listProducts);

        int totalPages = listProducts.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return list_template;
    }*/
}
