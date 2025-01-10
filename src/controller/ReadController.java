package controller;

import domain.Estate;
import dto.RealDTO;
import repository.ReadRepository;

import java.util.List;

public class ReadController {

    private final ReadRepository readRepository;

    public ReadController(ReadRepository readRepository) {
        this.readRepository = readRepository;
    }

    public List<Estate> readAll() {
        return readRepository.findAll();
    }

//
//    public List<RealDTO> getAllReal() {
//        return ;
//    }
}
