package reply.service;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import reply.dao.ReplyDAO;
import reply.model.ReplyDTO;

public class ReplyService {
    private ReplyDAO replyDAO;

    public ReplyService() {
        try {
            replyDAO = new ReplyDAO();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void addReply(ReplyDTO reply) throws SQLException {
        if (reply.getRcontent() == null || reply.getRcontent().isEmpty()) {
            throw new IllegalArgumentException("댓글 내용이 비어있습니다.");
        }
        
        reply.setDate(new Date());
        
        replyDAO.addReply(reply);
    }
    
    public List<ReplyDTO> getRepliesByBno(int bno) throws SQLException {
        return replyDAO.getRepliesByBno(bno);
    }
    
    public void removeReply(int rno) throws SQLException {
       
    	List<ReplyDTO> existingReplies = replyDAO.getRepliesByBno(rno);

        if (existingReplies == null) {
            throw new IllegalArgumentException("삭제할 댓글이 존재하지 않습니다.");
        }
        replyDAO.deleteReply(rno);
    }    
    
    public void updateReply(int rno, String rcontent) throws SQLException {
    	ReplyDTO reply = new ReplyDTO();
    	reply.setRcontent(rcontent);
    	reply.setRno(rno);
        if (reply.getRcontent() == null || reply.getRcontent().isEmpty()) {
            throw new IllegalArgumentException("댓글 내용이 비어있습니다.");
        }
        reply.setDate(new Date());
        
        replyDAO.updateReply(reply);
    }
    public List<ReplyDTO> getUpdatedReplies(int bno) throws SQLException {
        // 해당 게시글 번호에 해당하는 모든 댓글을 가져옴
        return replyDAO.getRepliesByBno(bno);
    }
}
