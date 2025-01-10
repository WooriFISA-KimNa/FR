package main;

import controller.ReadController;
import repository.ReadRepository;
import service.ReadService;
import util.DBUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class StartProgram {
    public StartProgram() throws SQLException {

        try (Connection connection = DBUtil.getConnection()) {
            // 의존성 주입
            ReadRepository readRepository = new ReadRepository(connection);
            ReadService readService = new ReadService(readRepository);
            ReadController readController = new ReadController(readService);
            // 데이터 출력
//            readController.displayAllEstates();
        }

    }
}
