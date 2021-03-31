package Homework;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
import kr.or.ddit.util.JDBCUtil3;

public class Board_Homework {
   
   private Connection conn;
   private Statement stmt;
   private PreparedStatement pstmt;
   private ResultSet rs;
   
   private Scanner scan = new Scanner(System.in); 
   
   /**
    * 메뉴를 출력하는 메서드
    */
   public void displayMenu(){
      System.out.println();
      System.out.println("------------------------------------------------------------------");
      System.out.println("==================================================================");
      System.out.println("  1. 전체 목록 출력");
      System.out.println("  2. 새글 작성");
      System.out.println("  3. 게시글 수정");
      System.out.println("  4. 게시글 삭제");
      System.out.println("  5. 게시글 검색");
      System.out.println("  6. 종료");
      System.out.println("==================================================================");
      System.out.println("------------------------------------------------------------------");
      System.out.print("원하는 작업 선택 >> ");
   }
   
   /**
    * 프로그램 시작메서드
    */
   public void start(){
      int choice;
      do{
         displayMenu(); //메뉴 출력
         choice = scan.nextInt();; // 메뉴번호 입력받기
         switch(choice){
            case 1 :  // 전체 목록 출력
                viewAll();
               break;
            case 2 :  // 새글 작성
            	writeBoard();
               break;
            case 3 :  // 게시글 수정
            	updateBoard();
               break;
            case 4 :  // 게시글 삭제
            	deleteBoard();
               break;
            case 5 :  // 게시글 검색
            	searchBoard();
            	break;
            case 6 :  // 종료
               System.out.println("작업을 마칩니다.");
               break;
            default :
               System.out.println("번호를 잘못 입력했습니다. 다시입력하세요");
         }
      }while(choice!=6);
   }


   private void searchBoard() {

       System.out.println();
       System.out.println("검색할 게시물에 포함된 단어를 입력하세요.");
       System.out.println("제목 or 내용 or 작성자");
       System.out.print(" >> ");
       String searchInput = scan.next();	   

       
       System.out.println();
	   System.out.println("------------------------------------------------------------------");
	   System.out.println("[번호]\t[제  목]\t[작 성 자]\t[작 성 일 자]\t       [내용]");
	   System.out.println("------------------------------------------------------------------");
	   
	   try {
	         conn = JDBCUtil3.getConnection();

	         
	         String sql = "select * from jdbc_board"
	         			+ " where board_title like '%' || ? || '%' "
	         			+ " or board_writer   like '%' || ? || '%' "
	         			+ " or board_content  like '%' || ? || '%' ";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setObject(1, searchInput);
	         pstmt.setObject(2, searchInput);
	         pstmt.setObject(3, searchInput);
	         
	         rs = pstmt.executeQuery();

	         
	         while(rs.next()) {
	        	 String boardNo = rs.getString("board_no");
	        	 String boardTitle = rs.getString("board_title");
	        	 String boardWriter = rs.getString("board_writer");
	        	 String boardDate = rs.getString("board_date");
	        	 String boardContents = rs.getString("board_content");
	        	 System.out.println(boardNo + "\t" + boardTitle + "\t"
	        	                    + boardWriter + "\t" + boardDate + "\t"
	        	                    + boardContents );
	         }
	         System.out.println("------------------------------------------------------------------");
	         System.out.println("출력 작업 끝...");
		   
	   }catch(SQLException ex) {
		   ex.printStackTrace();
	   }finally {
		   JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
	   }
	
}

private void deleteBoard() {
	   
       System.out.println();
       System.out.println("삭제할 게시물 번호를 입력하세요.");
       System.out.print("게시물 번호 >> ");
       String boardNo = scan.next();

	      try {
		         conn = JDBCUtil3.getConnection();
		         
		         String sql = "DELETE FROM jdbc_board WHERE board_no = ? ";
		         pstmt = conn.prepareStatement(sql);
		         pstmt.setString(1, boardNo);
		         
		         int cnt = pstmt.executeUpdate();
		         
		         if(cnt > 0) {
		        	 System.out.println(boardNo + "게시물 삭제 작업 성공");
		         }else {
		        	 System.out.println(boardNo + "게시물 삭제 작업 실패");
		         }
		         
		      } catch (SQLException ex) {

		      }finally {
		        JDBCUtil3.disConnect(conn, stmt, pstmt, rs);;
		      }	
}

private void updateBoard() {

	   boolean chk = false;
	   String boardNo = null;
	      
	   do {
	         System.out.println();
	         System.out.println("수정할 게시물 번호를 입력하세요.");
	         System.out.print("게시물 번호 >> ");
	         boardNo = scan.next();
	         
	         chk = checkBoardNo(boardNo);
	         
	         if (chk == false) {
	            System.out.println(boardNo + "번 게시물은 존재하지 않습니다.");
	            System.out.println("다시 입력해 주세요.");
	         }
	      } while(chk == false);
	      
	   	  System.out.println("수정할 게시글의 정보를 입력해주세요.");
	      System.out.print("게시글 제목 >> ");
	      String boardTitle = scan.next();
	      
	      scan.nextLine();
	      System.out.print("내  용 >> ");
	      String boardContents = scan.nextLine();
	      
	      try {

	         conn = JDBCUtil3.getConnection();	         
	         String sql = "UPDATE jdbc_board "
	         			+ " SET board_title = ? "
	         			+ "    ,board_date = sysdate "
	         			+ "    ,board_content = ? "
	         			+ " WHERE board_no = ? ";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, boardTitle);
	         pstmt.setString(2, boardContents);
	         pstmt.setString(3, boardNo);
	         
	         int cnt = pstmt.executeUpdate();
	         
	         if(cnt > 0) {
	        	 System.out.println(boardNo + "번 게시물 수정 작업 성공");
	         }else {
	        	 System.out.println(boardNo + "번 게시물 수정 작업 실패");
	         }
	         
	      } catch (SQLException ex) {

	      }finally {
	         JDBCUtil3.disConnect(conn, stmt, pstmt, rs);;
	      }
	   
	
}

   private void writeBoard() {

	   
	   	  System.out.println("작성할 게시글의 정보를 입력해주세요.");
	      System.out.print("게시글 제목 >> ");
	      String boardTitle = scan.next();
	      
	      System.out.print("작 성 자 >> ");
	      String boardWriter = scan.next();
	      
	      scan.nextLine(); // 입력버퍼 비우기
	      System.out.print("내  용 >> ");
	      String boardContents = scan.nextLine();
	      
	      try {
	         conn = JDBCUtil3.getConnection();	        
	         String sql = "INSERT INTO jdbc_board(board_no,  board_title, board_writer, board_date, board_content)"
	         		+ " VALUES(board_seq.nextVal, ?, ?, sysdate, ?)";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, boardTitle);
	         pstmt.setString(2, boardWriter);
	         pstmt.setString(3, boardContents);

	         
	         int cnt = pstmt.executeUpdate();
	         
	         if(cnt > 0) {
	        	 System.out.println("게시글 작성을 완료하였습니다.");
	         }else {
	        	 System.out.println("게시글 작성에 실패하였습니다. 다시 시도해주십시오.");
	         }
	         
	      } catch (SQLException ex) {

	      }finally {
	         JDBCUtil3.disConnect(conn, stmt, pstmt, rs);;
	      }

	
}

   private void viewAll() {
	   System.out.println();
	   System.out.println("------------------------------------------------------------------");
	   System.out.println("[번호]\t[제  목]\t[작 성 자]\t[작 성 일 자]\t       [내용]");
	   System.out.println("------------------------------------------------------------------");
	   
	   try {
	         conn = JDBCUtil3.getConnection();		         
	         stmt = conn.createStatement();
	         
	         rs = stmt.executeQuery("select * from jdbc_board");
	         
	         while(rs.next()) {
	        	 String boardNo = rs.getString("board_no");
	        	 String boardTitle = rs.getString("board_title");
	        	 String boardWriter = rs.getString("board_writer");
	        	 String boardDate = rs.getString("board_date");
	        	 String boardContents = rs.getString("board_content");
	        	 System.out.println(boardNo + "\t" + boardTitle + "\t"
	        	                    + boardWriter + "\t" + boardDate + "\t"
	        	                    + boardContents );
	         }
	         System.out.println("------------------------------------------------------------------");
	         System.out.println("출력 작업 끝...");
		   
	   }catch(SQLException ex) {
		   ex.printStackTrace();
	   }finally {
		   JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
	   }
	   
	
	}

   private boolean checkBoardNo(String boardNo) {

	      boolean chk = false;
	      
	      try {
	         conn = JDBCUtil3.getConnection();	        
	         String sql = "SELECT COUNT(*) CNT"
	               + " FROM jdbc_board"
	               + " WHERE board_no = ? ";
	         pstmt = conn.prepareStatement(sql);
	         pstmt.setString(1, boardNo);
	         
	         rs = pstmt.executeQuery();
	         
	         int cnt = 0;
	         while(rs.next()) {
	            cnt = rs.getInt("cnt");
	         }
	         
	         if (cnt > 0) {
	            chk = true;
	         }
	         
	      } catch (SQLException ex) {
	         ex.printStackTrace();
	      }finally {
	         JDBCUtil3.disConnect(conn, stmt, pstmt, rs);
	      }
	      
	      return chk;
	   }  
   
	public static void main(String[] args) {
		Board_Homework boardObj = new Board_Homework();
		boardObj.start();
		}

	}





