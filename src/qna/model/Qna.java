package qna.model;
import java.util.Date;
import member.Member;

public class Qna {
	
	private int bno;
	private Member memId;
	private String title;
	private Date regDate;
	private int hit;
	
	//Qna��ü ���� �� ������ �ڸ��� ���� �Ű�������(������)
	public Qna(int bno, Member memId, String title, Date regDate, int hit) {
		this.bno = bno;
		this.memId = memId;
		this.title = title;
		this.regDate = regDate;
		this.hit = hit;
	}

	//�Һ��� ������ ���� getter �޼��常 ����. ��ü�� ���°� ������� �����Ƿ� �ٸ� �κп��� ��ü�� ���� ���� ������ ���� �Һ� ��ü.
	public int getBno() {
		return bno;
	}

	public Member getMemId() {
		return memId;
	}

	public String getTitle() {
		return title;
	}

	public Date getRegDate() {
		return regDate;
	}

	public int getHit() {
		return hit;
	}
	
}
