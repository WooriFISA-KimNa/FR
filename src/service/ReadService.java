package service;

import repository.ReadRepository;

public class ReadService {
    private final ReadRepository readRepository;

    public ReadService(ReadRepository readRepository) {
        this.readRepository = readRepository;
    }



}
