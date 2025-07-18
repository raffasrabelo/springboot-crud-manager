package br.edu.ifsuldeminas.mch.springbootcrud.model.repository;

import br.edu.ifsuldeminas.mch.springbootcrud.model.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Long> {
}