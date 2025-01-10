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

    public void findByPropertyDTO(String col, String prop){
        List<RealDTO> estates = readRepository.findByPropertyDTO(col, prop);
        EndView.displayAsTable(estates);
    }

    public void orderByColumn(String col, String order){
        long startTime = System.nanoTime();
        List<RealDTO> estates = readRepository.orderByColumn(col, order);
        long endTime = System.nanoTime(); // 실행 시간 측정 종료
        EndView.displayAsTable(estates);
        // 실행 시간 출력
        System.out.println("Query executed in " + ((endTime - startTime) / 1_000_000) + " milliseconds");
    }

    public void findByAnonymousPropertyDTO(String col, String prop){
        List<RealDTO> estates = readRepository.findByAnonymousPropertyDTO(col, prop);
        EndView.displayAsTable(estates);
    }


}
