package board.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.ibatis.sqlmap.client.SqlMapClient;

import board.dao.IBoardDao;
import board.dao.BoardDaoImpl;
import board.vo.BoardVO;
import util.SqlMapClientUtil;

public class BoardServiceImpl implements IBoardService {
	
//	사용할 DAO의 객체 변수를 선언한다.
//	나중에 implements된 다른 메서드로 편하게 바꾸기 위해 인터페이스 객체 생성함(다형성)
	private IBoardDao boardDao;
//	커넥션 객체 담기위한 메서드 객체 선언
	private SqlMapClient smc;
	
	private static IBoardService boardService;
	
	private BoardServiceImpl() {
		boardDao = BoardDaoImpl.getInstance();
		smc = SqlMapClientUtil.getInstance();
	}
	
	public static IBoardService getInstance() {
		if(boardService == null) {
			boardService = new BoardServiceImpl();
		}
		return boardService;
	}
	
	@Override
	public int insertBoard(BoardVO bv) {
		int cnt = 0;
		
		try {
			cnt = boardDao.insertBoard(smc, bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public boolean checkBoard(Integer boardNo) {
		boolean chk = false;
		
		try {
			boardDao.checkBoard(smc, boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return chk;
	}

	@Override
	public List<BoardVO> getAllBoardList() {
		List<BoardVO> boardList = new ArrayList<>();
		try {
			boardList = boardDao.getAllBoardList(smc);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boardList;
	}

	@Override
	public int updateBoard(BoardVO bv) {
		int cnt = 0;
		
		try {
			cnt = boardDao.updateBoard(smc, bv);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cnt;
	}

	@Override
	public int deleteBoard(Integer boardNo) {
		int cnt = 0;
		
		try {
			cnt = boardDao.deleteBoard(smc, boardNo);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cnt;
	}
	
	@Override
	public List<BoardVO> getSearchBoard(BoardVO bv) {
		
		List<BoardVO> boardList = new ArrayList<>();
		
		try {
			boardList = boardDao.getSearchBoard(smc, bv);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardList;
	}

}
