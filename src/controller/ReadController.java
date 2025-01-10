package controller;

import dto.RealDTO;
import service.ReadService;

import java.util.List;

public class ReadController {

    private final ReadService readService;

    public ReadController(ReadService readService) {
        this.readService = readService;
    }

//
//    public List<RealDTO> getAllReal() {
//        return ;
//    }
}
