package com.company.timesheet.profile.person.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.company.timesheet.core.util.dataaccess.DBConnection;
import com.company.timesheet.profile.person.pojo.PersonDetail;
import com.company.timesheet.profile.person.pojo.PersonSearchCriteria;
import com.company.timesheet.profile.person.pojo.PersonSearchDetails;

public class PersonSearchDAO {

	Connection connection = null;

	public List<PersonDetail> searchEmployeeInfo(PersonSearchDetails employeeSearchDetails) {

		List<PersonDetail> employeeDetailList = new ArrayList<PersonDetail>();
		PersonSearchCriteria employeeSearchCriteria = employeeSearchDetails.getEmployeeSearchCriteria();
		PersonDetail employeeDetail = null;

		String whereStr = "";

		String emptyStr = "";

		if (employeeSearchCriteria.getFirstName() != null && !employeeSearchCriteria.getFirstName().equals(emptyStr)) {
			whereStr += (whereStr == "") ? " WHERE " : " AND ";
			whereStr += " firstName = '" + employeeSearchCriteria.getFirstName() + "'";
		}
		if (employeeSearchCriteria.getMiddleName() != null && !employeeSearchCriteria.getMiddleName().equals(emptyStr)) {
			whereStr += (whereStr == "") ? " WHERE " : " AND ";
			whereStr += " middleName = '" + employeeSearchCriteria.getMiddleName() + "'";
		}
		if (employeeSearchCriteria.getLastName() != null && !employeeSearchCriteria.getLastName().equals(emptyStr)) {
			whereStr += (whereStr == "") ? " WHERE " : " AND ";
			whereStr += " lastName = '" + employeeSearchCriteria.getLastName() + "'";
		}

		if (!whereStr.equals(emptyStr)) {

			try {
				connection = DBConnection.getDBConnection();
				String employeeSQLStr = "SELECT * " + " FROM Employee " + whereStr;

				PreparedStatement preparedstatement = connection.prepareStatement(employeeSQLStr);

				ResultSet resultSet = preparedstatement.executeQuery();

				while (resultSet.next()) {

					employeeDetail = new PersonDetail();
					employeeDetail.setEmployeeID(resultSet.getLong("employeeID"));
					employeeDetail.setTitle(resultSet.getString("title"));
					employeeDetail.setFirstName(resultSet.getString("firstName"));
					employeeDetail.setMiddleName(resultSet.getString("middleName"));
					employeeDetail.setLastName(resultSet.getString("lastName"));
					employeeDetail.setGender(resultSet.getString("gender"));
					employeeDetail.setDateOfBirth(resultSet.getDate("dateOfBirth"));
					employeeDetailList.add(employeeDetail);
				}

			} catch (Exception exception) {
				exception.printStackTrace();
			}

		} else {
			String errorMessage = "";

			errorMessage = "Please enter at least one attribute";
			employeeSearchDetails.setErrorMessage(errorMessage);
			;

		}

		return employeeDetailList;

	}
}
