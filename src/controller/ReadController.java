package controller;

import dto.RealDTO;
import repository.ReadRepository;

import java.util.List;

public class ReadController {

    private final ReadRepository readRepository;

    public ReadController(ReadRepository readRepository) {
        this.readRepository = readRepository;
    }

//
//    public List<RealDTO> getAllReal() {
//        return ;
//    }
}
