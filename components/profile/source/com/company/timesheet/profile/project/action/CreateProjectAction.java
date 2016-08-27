/**
 * 
 */
package com.company.timesheet.profile.project.action;

import com.company.timesheet.core.util.CRUDConstants;
import com.company.timesheet.profile.employee.dao.RegisterEmployeeDAO;
import com.company.timesheet.profile.project.dao.CreateProjectDAO;
import com.company.timesheet.profile.project.pojo.ProjectDetail;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author vaish
 *
 */
public class CreateProjectAction extends ActionSupport {
	
	private static final long serialVersionUID = 1L;
	
	private ProjectDetail projectDetail;
	
	public ProjectDetail getProjectDetail() {
		return projectDetail;
	}

	public void setProjectDetail(ProjectDetail projectDetail) {
		this.projectDetail = projectDetail;
	}

	@Override
	public String execute() throws Exception {
		
		String pageForwardStr = "";
		
		CreateProjectDAO createProjectDAO = new CreateProjectDAO();
		String returnMassegeStr = createProjectDAO.createProject(projectDetail);
		
		if (returnMassegeStr == CRUDConstants.RETURN_MESSAGE_SUCCESS) {

			pageForwardStr = SUCCESS;

		} else {

			pageForwardStr = ERROR;

		}
		
		return pageForwardStr;
	}

}
