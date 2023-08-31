package kr.or.ddit.comm.service;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Part;

import kr.or.ddit.comm.dao.AtchFileDaoImpl;
import kr.or.ddit.comm.dao.IAtchFileDao;
import kr.or.ddit.comm.vo.AtchFileVO;

public class AtchFileServiceImpl implements IAtchFileService {
	
	private static IAtchFileService fileService;
	
	private IAtchFileDao fileDao;
	
	private AtchFileServiceImpl() {
 
		fileDao = AtchFileDaoImpl.getInstance();
	}
	
	public static IAtchFileService getInstance() {
		if(fileService == null) {
			fileService = new AtchFileServiceImpl();
		}
		
		return fileService;
	}
	
	
	@Override
	public AtchFileVO saveAtchFileList(Collection<Part> parts) throws Exception, Exception {

		String uploadPath = "e:/D_Other/upload_files"; // 업로드 경로
		
		File uploadDir = new File(uploadPath); // 파일 유무 확인하고
		if(uploadDir.exists()) { // 없음면 만들어주기
			uploadDir.mkdir();
		}
		
		AtchFileVO atchFileVO = null;
		
		boolean isFirstFile = true; // 첫 번째 파일 여부
		// 첫 번째 파일이면 atch_file & atch_file_detail에 저장됨 / 이후로 저장되는 파일은 detail 파일에만 들어가게 만듦
		
			for(Part part : parts) {
				
				// 파일명 추출
				String fileName = part.getSubmittedFileName();
				
				if(fileName != null && !fileName.equals("")) { // 파일인 경우
					
					if(isFirstFile) {
						isFirstFile = false;
						
						// ATCH_FILE에 저장
						atchFileVO = new AtchFileVO();
						fileDao.insertAtchFile(atchFileVO);
					}
					
					String orignFileName = fileName;// 파일명
					long fileSize = part.getSize();	// 파일 크기
					String saveFileName = "";		// 저장 파일명
					String saveFilePath = "";		// 저장 파일 경로
 					
					saveFileName = UUID.randomUUID().toString().replace("-", ""); // replace = -를 공백으로 변경
					saveFilePath = uploadPath + File.separator + saveFileName; // 이 경로만 알면 언제든 다운 받을 수 있음
					
					// 확장자명 추출
					String fileExtension =
					orignFileName.lastIndexOf(".") < 0 ? "" : 	// 마지막 위치부터 찾음 마지막 인덱스의 .부터
					orignFileName.substring(orignFileName.lastIndexOf(".") +1);
					// 마지막  점 뒤를 잘라냄 => a.jpg => jpg를 확장자로 저장한다는 의미
					
					// 업로드 파일(원본파일) 저장하기
					part.write(saveFilePath);
						
					atchFileVO.setFileStreCours(saveFilePath);
					atchFileVO.setStreFileNm(saveFileName);
					atchFileVO.setFileSize(fileSize);
					atchFileVO.setFileExtsn(fileExtension);
//					atchFileVO.setFileCn("");
					atchFileVO.setOrignlFileNm(orignFileName);
					
					// ATCH_FILE_DETAIL에 저장
					fileDao.insertAtchFileDetail(atchFileVO);
					
					part.delete(); // 임시 업로드 파일 삭제하기
					
				}
		}
			
		return atchFileVO;
	}

	@Override
	public List<AtchFileVO> getAtchFileList(AtchFileVO atchFileVO) {

		return fileDao.getAtchFileList(atchFileVO);
	}

	@Override
	public AtchFileVO getAtchFileDetail(AtchFileVO atchFileVO) {
		
		return fileDao.getAtchFileDetail(atchFileVO);
	} 
	
	public static void main(String[] args) {
		
		System.out.println(UUID.randomUUID().toString().replace("-", ""));
	}
	
	

}
