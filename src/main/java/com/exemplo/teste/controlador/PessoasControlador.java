package com.exemplo.teste.controlador;

import java.util.List;
import java.util.Optional;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.exemplo.teste.entidades.Pessoas;
import com.exemplo.teste.repositorio.PessoasRepositorio;

import javax.validation.Valid;


@RestController
public class PessoasControlador {

    @Autowired
    private PessoasRepositorio pessoasRepositorio;

    @RequestMapping (value = "/pessoas", method = RequestMethod.GET)
    public List<Pessoas> Get() {
        return pessoasRepositorio.findAll();
    }

    @RequestMapping (value = "/pessoas{id}",method = RequestMethod.GET)
    public ResponseEntity<Pessoas> GetById(@PathVariable(value = "id") long id) {
        Optional<Pessoas> pessoas = pessoasRepositorio.findById(id);
        if(pessoas.isPresent())
            return new ResponseEntity<Pessoas>(pessoas.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pessoas", method = RequestMethod.POST)
    public Pessoas Post(@Valid @RequestBody Pessoas pessoas) {
        return pessoasRepositorio.save(pessoas);
    }

    @RequestMapping(value = "/pessoas/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Pessoas> Put(@PathVariable(value = "id") long id, @Valid @RequestBody Pessoas newPessoa){
        Optional<Pessoas> oldPessoas = pessoasRepositorio.findById(id);
        if(oldPessoas.isPresent()){
            Pessoas pessoas = oldPessoas.get();
            pessoas.setNome(newPessoa.getNome());
            pessoasRepositorio.save(pessoas);
            return new ResponseEntity<Pessoas>(pessoas, HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/pessoas/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") long id){
        Optional<Pessoas> pessoas = pessoasRepositorio.findById(id);
        if(pessoas.isPresent()){
            pessoasRepositorio.delete(pessoas.get());
            return new ResponseEntity<>(HttpStatus.OK);
        }
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
