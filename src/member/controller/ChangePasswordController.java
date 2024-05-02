package member.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import auth.service.User;
import member.service.ChangePasswordService;
import member.service.InvalidPasswordException;
import member.service.MemberNotFoundException;
import mvc.command.CommandHandler;

public class ChangePasswordController implements CommandHandler{
	
	private static final String FORM_VIEW = "/view/member/changePassword.jsp";
	private ChangePasswordService changePwdSvc = new ChangePasswordService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) throws Exception {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if(req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) throws Exception{
		User user = (User)req.getSession().getAttribute("authUser");
		
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		String curPwd = req.getParameter("curPwd");
		String newPwd = req.getParameter("newPwd");
		String verifyNewPwd = req.getParameter("verifyNewPwd");
		
		if(curPwd ==null || curPwd.trim().isEmpty() || curPwd.contains(" ") || curPwd.length() > 20) {
			errors.put("curPwd", Boolean.TRUE);
		}
		if(newPwd ==null || newPwd.trim().isEmpty() || newPwd.contains(" ") || newPwd.length() > 20) {
			errors.put("newPwd", Boolean.TRUE);
		}
		if(verifyNewPwd ==null || verifyNewPwd.trim().isEmpty() || verifyNewPwd.contains(" ") || verifyNewPwd.length() > 20) {
			errors.put("verifyNewPwd", Boolean.TRUE);
		}
		if(!newPwd.equals(verifyNewPwd)) {
			errors.put("notMatch", Boolean.TRUE);
		}
			
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		
		try {
			changePwdSvc.changePassword(user.getId(), curPwd, newPwd);
			return "view/member/successChange.jsp";
		}catch(InvalidPasswordException e) {
			errors.put("badCurPwd", Boolean.TRUE);
			return FORM_VIEW; 
		}catch(MemberNotFoundException e) {
			res.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return null;
		}
	}
}
