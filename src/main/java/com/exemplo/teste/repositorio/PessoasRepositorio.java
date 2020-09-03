package com.exemplo.teste.repositorio;

import com.exemplo.teste.entidades.Pessoas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoasRepositorio extends JpaRepository<Pessoas, Long> {
}
