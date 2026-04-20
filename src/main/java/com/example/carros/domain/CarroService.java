package com.example.carros.domain;

import com.example.carros.domain.dto.CarroDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CarroService {
    @Autowired
    private CarroRepository rep;
    public List<CarroDTO> getCarros() {

        return rep.findAll().stream().map(CarroDTO::create).collect(Collectors.toList());

        //List<CarroDTO> carrosDTO = new ArrayList<>();
        //for (Carro c : carros) {
         //   list.add(new CarroDTO(c));
        //}
        //return list;
    }
    public Optional<CarroDTO> getCarroById(Long id) {
        return rep.findById(id).map(CarroDTO::create);
    }
    public List<CarroDTO> getCarrosByTipo(String tipo) {
        return rep.findByTipo(tipo).stream().map(CarroDTO::create).collect(Collectors.toList());
    }
    public List<Carro> getCarrosFake() {
        List<Carro> carros = new ArrayList<>();

        Carro c1 = new Carro();
        c1.setId(1L);
        c1.setNome("Fusca");

        Carro c2 = new Carro();
        c2.setId(2L);
        c2.setNome("Brasilia");

        Carro c3 = new Carro();
        c3.setId(3L);
        c3.setNome("Chevette");

        carros.add(c1);
        carros.add(c2);
        carros.add(c3);

        return carros;
    }

    public Carro save(Carro carro) {
        return rep.save(carro);
    }

    public CarroDTO insert(Carro carro) {
    Assert.isNull(carro.getId(), "Não foi possível inserir o registro.");
        return CarroDTO.create(rep.save(carro));
    }

    public CarroDTO update(Carro carro, Long id) {
        Assert.notNull(id, "Não foi possível atualizar o registro.");

        Optional<Carro> optional = rep.findById(id);

        if (optional.isPresent()) {
            Carro db = optional.get();

            if (carro.getNome() != null) {
                db.setNome(carro.getNome());
            }

            if (carro.getTipo() != null) {
                db.setTipo(carro.getTipo());
            }

            return CarroDTO.create(rep.save(db));
        } else {
            throw new RuntimeException("Não foi possível atualizar o registro.");
        }
    }

    public void delete(Long id) {
        if ( getCarroById(id).isPresent()) {
            rep.deleteById(id);
        }
    }
}
