package controller;

import java.util.List;

import domain.Estate;
import dto.RealDTO;
import repository.ReadRepository;
import view.EndView;

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

    public void findByPropertyDTO(String col, String prop){
        List<RealDTO> estates = readRepository.findByPropertyDTO(col, prop);
        EndView.displayAsTable(estates);
    }


}
