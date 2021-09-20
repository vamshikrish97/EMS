package com.ems.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.RowMapperResultSetExtractor;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.ems.model.Employee;
@Repository
public class EmployeeDao1 {
  public static final String UPDATE_QUERY = "update employee_table set name=?,salary=?,age=?,address=?,role=? where id=?";
  public static final String POST_QUERY="insert into employee_table(name,salary,age,address,role) values (?,?,?,?,?)";
  public static final String GET_ALL_QUERY="select * from employee_table";
  public static final String DELETE_QUERY="delete from employee_table where id=?";
  public static final String GET_BY_ID_QUERY="select * from employee_table where id=?";


  @Autowired
  JdbcTemplate jdbctemplate;
@Autowired
    NamedParameterJdbcTemplate namedParameterJdbcTemplate;
  
 
    
public int updateEmployee( int id, Employee employee ) {
        return jdbctemplate.update(UPDATE_QUERY, new PreparedStatementSetter() {
           
        	@Override
            public void setValues(PreparedStatement ps) throws SQLException {
                 ps.setString(1,employee.getName());
                 ps.setInt(2, employee.getSalary());
                 ps.setInt(3, employee.getAge());
                 ps.setString(4,employee.getAddress());
                 ps.setString(5,employee.getRole());
                 ps.setInt(6,id);
               
            }

		
			
        });

}
public int createEmployee( Employee employee ) {
    return jdbctemplate.update(POST_QUERY, new PreparedStatementSetter() {
       
    	@Override
        public void setValues(PreparedStatement ps) throws SQLException {
    		
             ps.setString(1,employee.getName());
             ps.setInt(2, employee.getSalary());
             ps.setInt(3, employee.getAge());
             ps.setString(4,employee.getAddress());
             ps.setString(5,employee.getRole());
            
           
        }

	
		
    });

}
		
		
public List<Employee> getAllEmployees() {	 

    return jdbctemplate.query(GET_ALL_QUERY, new PreparedStatementSetter() {

        @Override
        public void setValues(PreparedStatement ps) throws SQLException {
            


        }
    }, new RowMapper<Employee>() {

    	

        @Override
        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        
	            	var employee = new Employee();
	              	 
	                employee.setId(rs.getInt(1));
	                employee.setName(rs.getString(2));
	                employee.setSalary(rs.getInt(3));
	                employee.setAge(rs.getInt(4));
	                employee.setAddress(rs.getString(5));
	                employee.setRole(rs.getString(6));
	                
	                return  employee ;
	            }
	        });

	 

	    }





public int deleteEmployee(int id) {
	   
    return jdbctemplate.update(DELETE_QUERY, new PreparedStatementSetter() {

		@Override
		public void setValues(PreparedStatement ps) throws SQLException {
			
		
			
            ps.setInt(1,id);
          
			
		}
    });
 }
public Employee getEmployeeById(int id) {
	   
	
Employee emp1 =null;
int i=0;
	List<Employee> emp =
 jdbctemplate.query(GET_BY_ID_QUERY, new PreparedStatementSetter() {

		@Override
		public void setValues(PreparedStatement ps) throws SQLException {
			ps.setInt(1, id);			
		}

},new RowMapper<Employee>() {

	@Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
    
            	var employee = new Employee();
                employee.setId(rs.getInt(1));
                employee.setName(rs.getString(2));
                employee.setSalary(rs.getInt(3));
                employee.setAge(rs.getInt(4));
                employee.setAddress(rs.getString(5));
                employee.setRole(rs.getString(6));
                
                
                return  employee ;
            }
	
	
        });
	for (Employee employee2 : emp) {
		if (employee2.getId() == id) {
			emp1=employee2;
			i++;
		}
	}
	return emp1;

}


}