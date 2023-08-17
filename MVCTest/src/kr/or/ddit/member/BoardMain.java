package kr.or.ddit.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import kr.or.ddit.Util.JDBCUtil3;
import kr.or.ddit.member.service.BoardServiceImpl;
import kr.or.ddit.member.service.IBoardService;
import kr.or.ddit.member.vo.BoardVO;

public class BoardMain {

	private Scanner scan;

	private IBoardService brdService;

	public BoardMain() {
		scan = new Scanner(System.in);
		brdService = BoardServiceImpl.getInstance();
	}

	public static void main(String[] args) {
		new BoardMain().start();
	}

	/**
	 * 메뉴 출력
	 */
	public void displayMenu() {
		System.out.println();
		System.out.println("----------------------");
		System.out.println("  === 메  뉴  선 택 ===");
		System.out.println("  1. 새글 작성");
		System.out.println("  2. 글 수정");
		System.out.println("  3. 글 삭제");
		System.out.println("  4. 전체목록 출력");
		System.out.println("  5. 검색");
		System.out.println("  6. 게시판 종료");
		System.out.println("----------------------");
		System.out.print("원하는 메뉴 선택 >> ");
	}

	public void start() {
		int menu;

		do {
			displayMenu();
			menu = scan.nextInt();
			switch (menu) {
			case 1: // 새글작성
				writing();
				break;
			case 2: // 글 수정
				updataText();
				break;
			case 3: // 글 삭제
				deleteText();
				break;
			case 4: // 전체 목록 출력
				selectAll();
				break;
			case 5: // 검색
				searchText();
				break;
			case 6: // 종료
				System.out.println("게시판을 종료합니당당");
				break;
			default:
				System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
			}

		} while (menu != 6);
	}

	private void searchText(BoardVO paramBv) {

		Date date = null;

		scan.nextLine();

		System.out.println();
		System.out.println("검색할 정보를 입력하세요.");
		System.out.println("작성 번호  : ");
		Long boardNo = scan.nextLong(); // 공백제거

		System.out.println("작성 제목  : ");
		String title = scan.next().trim(); // 공백제거

		System.out.println("작성자  : ");
		String writer = scan.next().trim(); // 공백제거

		System.out.println("작성내용  : ");
		String content = scan.nextLine().trim(); // 공백제거

		BoardVO bv = new BoardVO(boardNo, title, writer, date, content);
		List<BoardVO> brdList = brdService.searchText(paramBv);

		System.out.println("-------------------------------------");
		System.out.println("작성 번호\t제목\t작성자\t작성 날짜\t작성 내용");
		System.out.println("-------------------------------------");

		if (brdList.size() == 0) {
			System.out.println("작성된 글이 없습니다.");
		} else {
			for (BoardVO bvo : brdList) {

				System.out.println(bv.getBoardNo() + "\t" + bv.getTitle() + "\t" + bv.getWriter() + "\t" + bv.getDate()
						+ "\t" + bv.getContent());

				System.out.println("--------------------------------------");
				System.out.println("출력 작업 완료");
			}
		}
	}

	private void selectAll() {

		System.out.println("-------------------------------------");
		System.out.println("작성 번호\t제목\t작성자\t작성 날짜\t작성 내용");
		System.out.println("-------------------------------------");

		List<BoardVO> brdList = brdService.selectAll();

		if (brdList.size() == 0) {
			System.out.println("작성된 글이 없습니다.");
		} else {

			for (BoardVO bv : brdList) {

				System.out.println(bv.getBoardNo() + "\t" + bv.getTitle() + "\t" + bv.getWriter() + "\t" + bv.getDate()
						+ "\t" + bv.getContent());

			}

			System.out.println("--------------------------------------");
			System.out.println("출력 작업 완료");

		}
	}

	private void deleteText() {

		System.out.println();
		System.out.println("삭제할 작성 번호를 입력해 주세요.");
		System.out.println("작성 번호 : ");

		long boardNo = scan.nextLong();

		int cnt = brdService.deleteText(boardNo);

		if (cnt > 0) {
			System.out.println("작성번호" + boardNo + "번 글을 삭제했습니다.");
		} else {
			System.out.println("작성번호" + boardNo + "번 글 삭제 실패ㅜㅜ");
		}
	}

	private void updataText() {

		boolean isExist = false;

		long boardNo = 0L;
		String writer = "";
		Date date = null;

		do {

			System.out.println("수정할 글의 작성번호를 입력해주세요 : ");
			System.out.println("작성 번호 : ");
			boardNo = scan.nextLong();

			isExist = checkInfo(boardNo);

			if (!isExist) {
				System.out.println("작성번호가" + boardNo + "번인 글은  " + " 존재하지 않습니다.\n다시 입력해 주세요.");
			}
		} while (!isExist);

		System.out.println("제목 입력 : ");
		String title = scan.next();

		scan.nextLine();

		System.out.println("내용 입력 : ");
		String content = scan.nextLine();

		BoardVO bv = new BoardVO(boardNo, title, writer, date, content);

		int cnt = brdService.modifyText(bv);

		if (cnt > 0) {
			System.out.println("수정 성공");
		} else {
			System.out.println("글 수정 실패!!");
		}
	}

	private void writing() {

		Long boardNo = 0L;
		Date date = null;

		System.out.println("작성할 제목을 입력해주세요 : ");
		String title = scan.nextLine();

		System.out.println("작성자를 입력해주세요 : ");
		String writer = scan.next();

		scan.nextLine();

		System.out.println("내용을 입력해주세요 : ");
		String content = scan.nextLine();

		BoardVO bv = new BoardVO(boardNo, title, writer, date, content);

		int cnt = brdService.savePost(bv);

		if (cnt > 0) {
			System.out.println("글 작성 완료");
		} else {
			System.out.println("글 작성 실패");
		}
	}

	private boolean checkInfo(long boardNo) {

		boolean isExist = false;

		int cnt = 0;

		if (cnt > 0) {
			isExist = true;
		}

		return isExist;
	}

//	public String getRegDtDisplay(Date regDt) {
//		
//		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		// MM => 달(월)  /  mm => 분(시간) 으로 출력됨
//		
//		return sdf.format(regDt);
//	}
}
