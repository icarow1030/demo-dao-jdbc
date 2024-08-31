package model.dao.implementation;

import db.DB;
import db.DbException;
import model.dao.DAOFactory;
import model.dao.DepartmentDAO;
import model.dao.SellerDAO;
import model.entities.Department;
import model.entities.Seller;

import java.sql.*;
import java.util.Date;
import java.util.List;

public class SellerDaoJDBC implements SellerDAO {

    private final Connection conn;

    public SellerDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Seller obj) {

    }

    @Override
    public void update(Seller obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Seller findById(Integer id) {
        ResultSet rs;
        PreparedStatement pst;
        String query = "SELECT s.*, department.Name AS DepName FROM seller AS s\n" +
                "INNER JOIN department ON s.DepartmentId = department.Id\n" +
                "WHERE s.Id = ?;";

        try {
            pst = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, id);
            rs = pst.executeQuery();
            if(!rs.next()) {
                throw new DbException("Seller with ID " + id + " not found");
            } else {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columnCount = rsmd.getColumnCount();

                Integer r_id = null;
                String r_name = null;
                String r_email = null;
                Date r_birthDate = null;
                Double r_baseSalary = null;
                Integer r_departmentId = null;

                for (int i = 1; i <= columnCount; i++) {
                    String columnLabel = rsmd.getColumnLabel(i);
                    if ("id".equalsIgnoreCase(columnLabel)) {
                        r_id = rs.getInt(i);
                    } else if ("name".equalsIgnoreCase(columnLabel)) {
                        r_name = rs.getString(i);
                    } else if ("email".equalsIgnoreCase(columnLabel)) {
                        r_email = rs.getString(i);
                    } else if ("birthdate".equalsIgnoreCase(columnLabel)) {
                        r_birthDate = rs.getDate(i);
                    } else if ("basesalary".equalsIgnoreCase(columnLabel)) {
                        r_baseSalary = rs.getDouble(i);
                    } else if ("departmentid".equalsIgnoreCase(columnLabel)) {
                        r_departmentId = rs.getInt(i);
                    }
                }

                System.out.println("VALOR ID DP: " + r_departmentId);

                DepartmentDAO departmentDAO = DAOFactory.createDepartmentDao(conn);
                Department r_department = departmentDAO.findById(r_departmentId);

                Seller foundSeller = new Seller(r_name, r_email, r_birthDate, r_baseSalary, r_department);
                foundSeller.setId(r_id);
                return foundSeller;
            }
        } catch(SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Seller> findAll() {
        return null;
    }
}
