package controller;

import java.util.List;

import domain.Estate;
import dto.RealDTO;
import repository.ReadRepository;
import service.ReadServiceInterface;
import view.EndView;

public class ReadController {

    private final ReadServiceInterface readService;

    public ReadController(ReadServiceInterface readService) {
        this.readService = readService;
    }

//    public void readAll() {
//        List<RealDTO> estates = readRepository.findAllDTO();
//        EndView.displayAsTable(estates);
//    }

    public void readAllDTO() {
        readService.findAllDTO();
    }

//    public void findByProperty(String col, String prop){
//        List<Estate> estates = readRepository.findByProperty(col, prop);
        //EndView.displayAsTable(estates);
//    }

    public void findByPropertyDTO(String col, String prop){
        readService.findByPropertyDTO(col, prop);
    }

    public void orderByColumn(String col, String order){
        readService.orderByColumn(col, order);
    }

    public void findByAnonymousPropertyDTO(String col, String prop){
        readService.findByAnonymousPropertyDTO(col, prop);
    }

    public void selectSpecificColumns(String col){
        readService.selectSpecificColumns(col);
    }
    
    public void selectLocationRank() {
//    	List<RealDTO> estates = readRepository
    }

}
