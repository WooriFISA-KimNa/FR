package service;

//서비스 인터페이스
public interface MyService {
 void orderByColumn(String col, String order);

 void findByAnonymousPropertyDTO(String col, String prop);
}

//실제 서비스 구현 클래스
public class ReadService implements MyService {
 private final ReadRepository readRepository;

 public MyServiceImpl(ReadRepository readRepository) {
     this.readRepository = readRepository;
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
}
