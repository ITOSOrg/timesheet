/**
 * 
 */
package com.company.timesheet.profile.projectpersonlink.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.company.timesheet.core.audittrail.dao.CreateAuditTrailDAO;
import com.company.timesheet.core.audittrail.pojo.AuditTrailDetails;
import com.company.timesheet.core.util.CRUDConstants;
import com.company.timesheet.core.util.dataaccess.DBConnection;
import com.company.timesheet.profile.projectpersonlink.pojo.ProjectPersonLinkDetail;

/**
 * @author vaish
 *
 */
public class DeleteProjectEmployeeLinkDAO {
	
	Connection connection = null;
	ResultSet resultSet = null;
	
	public String DeleteProjectEmployeeLink(ProjectPersonLinkDetail projectPersonLinkDetail){
		
		String returnMassegeStr = "";
		
		try {
			/**
			 * get connected with database by calling getDBConnection() method
			 * of DBConnection class
			 */
			connection = DBConnection.getDBConnection();
			String projectSQLStr = "UPDATE	ProjectPersonLink SET	RECORDSTATUS='cancel'	WHERE	projectEmployeeLinkID=" + projectPersonLinkDetail.getProjectPersonLinkID();
			/**
			 * sending sql statement to the database
			 */
			PreparedStatement preparedStatement = connection.prepareStatement(projectSQLStr);
			preparedStatement.executeUpdate();
			
			//inserting data into AuditTrail Table for Person Table
			AuditTrailDetails auditTrailDetails = new AuditTrailDetails();
			
			auditTrailDetails.setTableName("ProjectPersonLink");
			auditTrailDetails.setOperationType("Delete");
			auditTrailDetails.setRelatedID(projectPersonLinkDetail.getProjectPersonLinkID());
			auditTrailDetails.setTransactionType("Online");
			
			CreateAuditTrailDAO createAuditTrailDAO = new CreateAuditTrailDAO();
			createAuditTrailDAO.createAuditTrail(auditTrailDetails);

			returnMassegeStr = CRUDConstants.RETURN_MESSAGE_SUCCESS;

		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return returnMassegeStr = CRUDConstants.RETURN_MESSAGE_SUCCESS;
	}

	}


