package br.edu.ifsuldeminas.mch.springbootcrud.controller;


import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Supplier;
import br.edu.ifsuldeminas.mch.springbootcrud.model.repository.SupplierRepository;

@Controller
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierRepository supplierRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("suppliers", supplierRepository.findAll());
        return "supplier/list";
    }

    @GetMapping("/new")
    public String create(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "supplier/form";
    }

    @PostMapping("/save")
    public String save(@Valid @ModelAttribute("supplier") Supplier supplier, BindingResult result) {
        if (result.hasErrors()) {
            return "supplier/form";
        }
        supplierRepository.save(supplier);
        return "redirect:/suppliers";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, Model model) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Fornecedor inválido:" + id));
        model.addAttribute("supplier", supplier);
        return "supplier/form";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Fornecedor inválido:" + id));
        supplierRepository.delete(supplier);
        return "redirect:/suppliers";
    }
}