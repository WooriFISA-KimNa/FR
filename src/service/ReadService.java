package service;

import dto.RealDTO;
import repository.ReadRepository;
import util.AOPUtil;
import view.EndView;

import java.util.List;

public class ReadService implements ReadServiceInterface{

    private final ReadRepository readRepository;

    public ReadService(ReadRepository readRepository) {
        this.readRepository = readRepository;
    }

//    private AOPUtil ExecutionTimeProxy;
//    ReadService proxyService = (ReadService) ExecutionTimeProxy.createProxy(ReadService.class);

    @Override
    public void findAllDTO() {
        List<RealDTO> estates = readRepository.findAllDTO();
        EndView.displayAsTable(estates);
    }

    @Override
    public void findByPropertyDTO(String col, String prop) {
        List<RealDTO> estates = readRepository.findByPropertyDTO(col, prop);
        EndView.displayAsTable(estates);
    }

    @Override
    public void orderByColumn(String col, String order) {
        List<RealDTO> estates = readRepository.orderByColumn(col, order);
        EndView.displayAsTable(estates);
    }

    @Override
    public void findByAnonymousPropertyDTO(String col, String prop) {
        List<RealDTO> estates = readRepository.findByAnonymousPropertyDTO(col, prop);
        EndView.displayAsTable(estates);
    }

    @Override
    public void selectSpecificColumns(String col) {
        String query = "SELECT " + col + " FROM real_estate_data";

        List<List<Object>> results = readRepository.selectSpecificColumns(query);
        EndView.displayObject(results);
    }
}
