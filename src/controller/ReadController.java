package controller;

import domain.Estate;
import dto.RealDTO;
import repository.ReadRepository;
import view.EndView;

import java.util.List;

public class ReadController {

    private final ReadRepository readRepository;

    public ReadController(ReadRepository readRepository) {
        this.readRepository = readRepository;
    }

    public void readAll() {
        List<RealDTO> estates = readRepository.findAllDTO();
        EndView.displayAsTable(estates);
    }

    public void readAllDTO() {
        List<RealDTO> estates = readRepository.findAllDTO();
        EndView.displayAsTable(estates);
    }

    public void findByProperty(String col, String prop){
        List<Estate> estates = readRepository.findByProperty(col, prop);
        //EndView.displayAsTable(estates);
    }

//
//    public List<RealDTO> getAllReal() {
//        return ;
//    }
}
