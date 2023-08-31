package kr.or.ddit.comm.service;

import java.util.Collection;
import java.util.List;

import javax.servlet.http.Part;

import kr.or.ddit.comm.vo.AtchFileVO;

public interface IAtchFileService {
	
	/**
	 * 첨부파일 목록을 저장하기 위한 메서드 
	 * @param req Part정보를 꺼내기 위한 요청객체
	 * @return atchFileId를 담고 있는 AtchFileVO객체
	 */
	public AtchFileVO saveAtchFileList(Collection<Part> parts) throws Exception;
	
	/**
	 * 첨부파일 목록 조회하기
	 * @param atchFileVO
	 * @return 첨부파일 목록
	 */
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO); 
	
	/**
	 * 첨부파일 세부정보 조회하기
	 * @param atchFileVO
	 * @return 세부첨부파일 정보
	 */
	public AtchFileVO getAtchFileDetail(AtchFileVO atchFileVO); 

	
	
}
