package br.edu.ifsuldeminas.mch.springbootcrud.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Product;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.ProductRepository;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.SupplierRepository;

@Controller
@RequestMapping("/produtos")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("produtos", productRepository.findAll());
        return "produto/list";
    }

    @GetMapping("/novo")
    public String newProduct(Model model) {
        model.addAttribute("produto", new Product());
        model.addAttribute("fornecedores", supplierRepository.findAll());
        return "produto/form";
    }

    @PostMapping("/salvar")
    public String save(@Valid @ModelAttribute("produto") Product product, BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("fornecedores", supplierRepository.findAll());
            return "produto/form";
        }
        productRepository.save(product);
        return "redirect:/produtos";
    }

    @GetMapping("/editar/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto inválido:" + id));
        model.addAttribute("produto", product);
        model.addAttribute("fornecedores", supplierRepository.findAll());
        return "produto/form";
    }

    @GetMapping("/deletar/{id}")
    public String delete(@PathVariable("id") Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Produto inválido:" + id));
        productRepository.delete(product);
        return "redirect:/produtos";
    }
}
