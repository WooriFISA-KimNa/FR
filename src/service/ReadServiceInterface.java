package service;

//서비스 인터페이스
public interface ReadServiceInterface {

 void findAllDTO();

 void findByPropertyDTO(String col, String prop);

 void orderByColumn(String col, String order);

 void findByAnonymousPropertyDTO(String col, String prop);

 void selectSpecificColumns(String col);
}
