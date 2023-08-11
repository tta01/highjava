package kr.or.ddit.member.vo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class BoardVO {
	
	private Long boardNo;
	private String title;
	private String writer;
	private Date date;
	private String content;
	
//	private Date regDt;
	
	public BoardVO(Long boardNo,String title, String writer, Date date, String content) {
		super();
		this.boardNo=boardNo;
		this.title = title;
		this.writer = writer;
		this.date = date;
		this.content = content;
	}
	
	public BoardVO() {
		
	}

	public Long getBoardNo() {
		return boardNo;
	}

	public void setBoardNo(Long boardNo) {
		this.boardNo = boardNo;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", title=" + title + ", writer=" + writer + ", date=" + date
				+ ", content=" + content + "]";
	}
	
	
//	public Date getRegDt() {
//		return regDt;
//	}
//
//	public void setRegDt(Date regDt) {
//		this.regDt = regDt;
//	}
//
//	public String getRegDtDisplay() {
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		return sdf.format(this.regDt);
//	}
	

}
