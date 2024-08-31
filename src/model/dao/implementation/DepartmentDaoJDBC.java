package model.dao.implementation;

import db.DbException;
import model.dao.DepartmentDAO;
import model.entities.Department;

import java.sql.*;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDAO {

    private final Connection conn;

    public DepartmentDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Department obj) {

    }

    @Override
    public void update(Department obj) {

    }

    @Override
    public void deleteById(Integer id) {

    }

    @Override
    public Department findById(Integer id) {
        ResultSet rs;
        PreparedStatement pst;
        String query = "SELECT * FROM department as dp\n" +
                "WHERE dp.Id = ?;";

        try {
            pst = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            pst.setInt(1, id);
            rs = pst.executeQuery();

            if(!rs.next()) {
                throw new DbException("Department with ID " + id + " not found");
            } else {
                ResultSetMetaData rsmd = rs.getMetaData();
                int columns = rsmd.getColumnCount();

                Integer r_id = null;
                String r_name = null;

                for(int i = 1; i <= columns; i++) {
                    String columnLabel = rsmd.getColumnLabel(i);
                    if("id".equalsIgnoreCase(columnLabel)) {
                        r_id = rs.getInt(i);
                    } else if ("name".equalsIgnoreCase(columnLabel)){
                        r_name = rs.getString(i);
                    }
                }

                Department foundDepartment = new Department(r_name);
                foundDepartment.setId(r_id);
                return foundDepartment;
            }
        } catch(SQLException e) {
            throw new DbException(e.getMessage());
        }
    }

    @Override
    public List<Department> findAll() {
        return null;
    }
}
