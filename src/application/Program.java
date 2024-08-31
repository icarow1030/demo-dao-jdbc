package application;

import db.DB;
import db.DbException;
import model.dao.DAOFactory;
import model.dao.implementation.DepartmentDaoJDBC;
import model.dao.implementation.SellerDaoJDBC;
import model.entities.Department;
import model.entities.Seller;

import java.sql.Connection;
import java.sql.SQLException;

public class Program {
    public static void main(String[] args) {
        Connection conn;

        try {
            conn = DB.getConnection();

            SellerDaoJDBC sellerJDBC = DAOFactory.createSellerDao(conn);
            Seller seller = sellerJDBC.findById(2);
            System.out.println("Seller found: " + seller.getName());

            System.out.println("Seller Department: " + seller.getDepartment().getName());

        } catch(DbException e) {
            e.printStackTrace();
        } finally {
            DB.closeConnection();
        }

    }
}
