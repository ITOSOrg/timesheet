package com.company.timesheet.profile.employee.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.company.timesheet.core.util.dataaccess.DBConnection;
import com.company.timesheet.profile.employee.pojo.EmployeeDetail;
import com.company.timesheet.profile.timesheet.pojo.TimeSheetDetail;

public class ListEmployeeDAO {
	
	public  List<EmployeeDetail> listEmployee(){
		
		 List<EmployeeDetail> employeeDetailList = new ArrayList<EmployeeDetail>();
		 
		 Connection connection = null;
			ResultSet resultSet;
			try {
				connection = DBConnection.getDBConnection();

				String employeeSQLStr = "SELECT * FROM EMPLOYEE WHERE	RECORDSTATUS='Active'";

				// statement = connection.createStatement();

				PreparedStatement preparedStatement = connection.prepareStatement(employeeSQLStr);

				resultSet = preparedStatement.executeQuery();

				while (resultSet.next()) {
					
					EmployeeDetail employeeDetail = new EmployeeDetail();
					
					employeeDetail.setTitle(resultSet.getString("title"));
					employeeDetail.setEmployeeID(resultSet.getLong("employeeID"));
					employeeDetail.setFirstName(resultSet.getString("firstName"));
					employeeDetail.setMiddleName(resultSet.getString("middleName"));
					employeeDetail.setLastName(resultSet.getString("lastName"));
					employeeDetail.setGender(resultSet.getString("gender"));
					employeeDetail.setDateOfBirth(resultSet.getDate("dateOfBirth"));
					employeeDetail.setRegistrationDate(resultSet.getTimestamp("registrationDate"));
					employeeDetail.setStartDate(resultSet.getDate("startDate"));
					employeeDetail.setEndDate(resultSet.getDate("endDate"));
					
					employeeDetailList.add(employeeDetail);
				}
			
			}catch (SQLException e) {

				e.printStackTrace();
			}
		
		 
		 
		 return employeeDetailList;
	}

}
