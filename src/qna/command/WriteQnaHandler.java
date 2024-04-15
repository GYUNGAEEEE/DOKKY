package qna.command;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.HashMap;
import java.util.Map;

import auth.service.User;
import qna.model.Qna;
import qna.service.WriteQnaService;
import qna.service.WriteRequest;
import member.model.Member;
import mvc.command.CommandHandler;


public class WriteQnaHandler implements CommandHandler {
	private static final String FORM_VIEW = "/view/board/write.jsp";
	private WriteQnaService writeService = new WriteQnaService();
	
	@Override
	public String process(HttpServletRequest req, HttpServletResponse res) {
		if(req.getMethod().equalsIgnoreCase("GET")) {
			return processForm(req, res);
		} else if (req.getMethod().equalsIgnoreCase("POST")) {
			return processSubmit(req, res);
		} else {
			res.setStatus(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			return null;
		}
	}
	
	private String processForm(HttpServletRequest req, HttpServletResponse res) {
		return FORM_VIEW;
	}
	
	private String processSubmit(HttpServletRequest req, HttpServletResponse res) {
		Map<String, Boolean> errors = new HashMap<>();
		req.setAttribute("errors", errors);
		
		User user = (User)req.getSession(false).getAttribute("authUser");
		WriteRequest writeReq = createWriteRequest(user, req);
		writeReq.validate(errors);
		
		if(!errors.isEmpty()) {
			return FORM_VIEW;
		}
		int newBno = writeService.write(writeReq);
		req.setAttribute("newBno", newBno);
		
		return "/view/board/main/mainPage.jsp";
	}
	
	private WriteRequest createWriteRequest(User user, HttpServletRequest req) { //String memberId로 두던지, User를 사용해서 가져오던지 수정.
		return new WriteRequest(
				new Member(user.getId(), null, user.getName(), null, user.getGrade()), req.getParameter("title"), req.getParameter("content"));
	}
}
