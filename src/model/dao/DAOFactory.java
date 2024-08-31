package model.dao;

import model.dao.implementation.DepartmentDaoJDBC;
import model.dao.implementation.SellerDaoJDBC;

import java.sql.Connection;

public class DAOFactory {

    public static SellerDaoJDBC createSellerDao(Connection conn) {
        System.out.println("SellerDaoJDBC created");
        return new SellerDaoJDBC(conn);
    }
    public static DepartmentDaoJDBC createDepartmentDao(Connection conn) {
        System.out.println("DepartmentDaoJDBC created");
        return new DepartmentDaoJDBC(conn);
    }

}
