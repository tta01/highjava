package kr.or.ddit.comm.vo;
/**
 * 첨부파일 정보를 담기 위한 vo
 */

import java.util.Date;

public class AtchFileVO {
	
	private long atchFileId = -1;	// 첨부파일 ID
	private Date CreatDt;			// 파일생성일시
	private String useAt;
	private int fileSn = 1;				// 파일 순번
	private String fileStreCours;	// 파일 저장 경로
	private String streFileNm;		// 저장파일명
	private String orignlFileNm;	// 원본파일명
	private String fileExtsn;		// 파일확장자명
	private String fileCn;			// 파일내용
	private long fileSize;		// 파일크기
	
	
	public long getAtchFileId() {
		return atchFileId;
	}
	public void setAtchFileId(long atchFileId) {
		this.atchFileId = atchFileId;
	}
	public Date getCreatDt() {
		return CreatDt;
	}
	public void setCreatDt(Date creatDt) {
		CreatDt = creatDt;
	}
	public String getUseAt() {
		return useAt;
	}
	public void setUseAt(String useAt) {
		this.useAt = useAt;
	}
	public int getFileSn() {
		return fileSn;
	}
	public void setFileSn(int fileSn) {
		this.fileSn = fileSn;
	}
	public String getFileStreCours() {
		return fileStreCours;
	}
	public void setFileStreCours(String fileStreCours) {
		this.fileStreCours = fileStreCours;
	}
	public String getStreFileNm() {
		return streFileNm;
	}
	public void setStreFileNm(String streFileNm) {
		this.streFileNm = streFileNm;
	}
	public String getOrignlFileNm() {
		return orignlFileNm;
	}
	public void setOrignlFileNm(String orignlFileNm) {
		this.orignlFileNm = orignlFileNm;
	}
	public String getFileExtsn() {
		return fileExtsn;
	}
	public void setFileExtsn(String fileExtsn) {
		this.fileExtsn = fileExtsn;
	}
	public String getFileCn() {
		return fileCn;
	}
	public void setFileCn(String fileCn) {
		this.fileCn = fileCn;
	}
	public long getFileSize() {
		return fileSize;
	}
	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}
	

	
	
}
