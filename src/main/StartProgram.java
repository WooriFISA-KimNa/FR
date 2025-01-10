package main;


import repository.DeleteRepository;

import util.DBUtil;
import util.DataSource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import controller.DeleteController;

public class StartProgram {
    public StartProgram() throws SQLException {
        try {
          
            DeleteRepository deleteRepository = new DeleteRepository();
            DeleteController deleteController = new DeleteController();
            // 데이터 삭제
            deleteController.deleteEstate("103", "50");
            System.out.println("성공");
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
