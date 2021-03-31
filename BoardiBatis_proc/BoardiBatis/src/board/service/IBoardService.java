package board.service;

import java.sql.SQLException;
import java.util.List;

import board.vo.BoardVO;

/**
 * 회원정보 처리를 수행하는 서비스
 *
 */
public interface IBoardService {
	
	/**
	 * 회원 등록 메서드
	 * @param mv DB에 insert할 자료가 저장된 BoardVO 객체
	 * @return DB작업이 성공하면 1이상의 값이 반환되고, 실패하면 0이 반환된다.
	 */
	public int insertBoard(BoardVO bv);
	
	/**
	 * 주어진 회원 ID가 존재하는지 여부를 알아내는 메서드
	 * @param boardNo 게시물번호
	 * @return 해당 게시물 번호가 존재하면 true, 존재하지 않으면 false
	 * @throws SQLException JDBC관련 예외객체 발생
	 */
	public boolean checkBoard(Integer boardNo);
	
	/**
	 * 회원정보 전체 목록을 가져오는 메서드
	 * @param conn 커넥션 객체
	 * @return 회원정보를 담고있는 List 객체
	 * @throws SQLException JDBC관련 예외객체 발생
	 */
	public List<BoardVO> getAllBoardList();
	
	/**
	 * 회원정보를 수정하는 메서드
	 * @param mv 회원정보 객체
	 * @return 작업성공 : 1, 작업 실패 : 0
	 */
	public int updateBoard(BoardVO bv);
	
	/**
	 * 회원정보를 삭제하는 메서드
	 * @param boardNo 게시물 번호
	 * @return 작업성공 : 1, 작업 실패 : 0
	 */
	public int deleteBoard(Integer boardNo);
	
	/**
	 * BoardVO 객체에 담긴 자료를 이용하여 회원을 검색하는 메서드
	 * @param mv 검색한 자료가 들어있는 BoardVO 객체
	 * @return 검색된 결과를 담은 List
	 */
	public List<BoardVO> getSearchBoard(BoardVO bv);

}
