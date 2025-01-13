//package main;
//
//import controller.ReadController;
//import repository.ReadRepository;
//import util.DBUtil;
//import util.DataSource;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.SQLException;
//
//public class StartProgram {
//    public StartProgram() throws SQLException {
//        try {
//            // 의존성 주입
//            ReadRepository readRepository = new ReadRepository();
//            ReadController readController = new ReadController(readRepository);
//            // 데이터 출력
////            readController.displayAllEstates();
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//    }
//}
