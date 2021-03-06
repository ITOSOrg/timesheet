/**
 * 
 */
package com.company.timesheet.profile.projectpersonlink.action;

import com.company.timesheet.core.util.CRUDConstants;
import com.company.timesheet.profile.projectpersonlink.dao.UpdateProjectEmployeeLinkDAO;
import com.company.timesheet.profile.projectpersonlink.pojo.ProjectPersonLinkDetail;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author vaish
 *
 */
public class UpdateProjectEmployeeLinkAction extends ActionSupport {
			
	ProjectPersonLinkDetail projectPersonLinkDetail;		
	
	public ProjectPersonLinkDetail getProjectPersonLinkDetail() {
		return projectPersonLinkDetail;
	}

	public void setProjectPersonLinkDetail(
			ProjectPersonLinkDetail projectPersonLinkDetail) {
		this.projectPersonLinkDetail = projectPersonLinkDetail;
	}

	@Override
	public String execute() throws Exception {
		
		String pageForwardStr ="";
		
		UpdateProjectEmployeeLinkDAO updateProjectEmployeeLinkDAO = new UpdateProjectEmployeeLinkDAO();
		String returnMassegeStr = updateProjectEmployeeLinkDAO.updateProjectEmployeeLink(projectPersonLinkDetail);
		
		if (returnMassegeStr == CRUDConstants.RETURN_MESSAGE_SUCCESS) {

			pageForwardStr = SUCCESS;

		} else {
			pageForwardStr = ERROR;
		}
		return pageForwardStr;
	
	}

}
