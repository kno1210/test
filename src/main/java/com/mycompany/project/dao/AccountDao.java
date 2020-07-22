package com.mycompany.project.dao;

import java.sql.Connection;
//import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mycompany.project.datasource.ConnectionPool;
import com.mycompany.project.model.Account;

public class AccountDao {

	private static final Logger LOGGER = LoggerFactory.getLogger(AccountDao.class);	
	private DataSource dataSource = ConnectionPool.getDataSource();
	
	public int insert(Account account)
	{
		int rowNum = 0;
		Connection conn = null;
		
		try {
//			//JDBC 드라이버 로딩
//			Class.forName("com.mysql.jdbc.Driver");
//			
//			//DB와 연결하기
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web", "root", "12345");
//			LOGGER.info("연결됨");
			
			conn = dataSource.getConnection();
			
			//매개변수화된 SQL문
			String sql = "insert into account (aowner, abalance, adate) value(?, ?, now())";
			
			//SQL문을 실행시키는 객체 생성
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, account.getAowner());
			stmt.setInt(2, account.getAbalance());
			
			//SQL문을 실행하기
			rowNum = stmt.executeUpdate(); //create, insert, delete, update
			
			//사용한 자원 닫기
			stmt.close();
			
			LOGGER.info("저장 성공");
		} 
		catch (Exception e) {
			e.printStackTrace();
			//LOGGER.info("연결 안됨 : {}", e.toString());
		}
		finally {
			
			try { conn.close();	} 
			catch (SQLException e) {};
		}
		return rowNum;
	}
	
	public int delete(int ano)
	{
		int rowNum = 0;
		Connection conn = null;
		
		try {
//			//JDBC 드라이버 로딩
//			Class.forName("com.mysql.jdbc.Driver");
//			
//			//DB와 연결하기
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web", "root", "12345");
//			LOGGER.info("연결됨");
			
			conn = dataSource.getConnection();
			
			//매개변수화된 SQL문
			String sql = "delete from account where ano=?";
			
			//SQL문을 실행시키는 객체 생성
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ano);
			
			//SQL문을 실행하기
			rowNum = stmt.executeUpdate(); //create, insert, delete, update
			
			//사용한 자원 닫기
			stmt.close();
			LOGGER.info("삭제 성공");
		} 
		catch (Exception e) {
			e.printStackTrace();
			//LOGGER.info("연결 안됨 : {}", e.toString());
		}
		finally {
			
			try { conn.close();	} 
			catch (SQLException e) {};
		}
		return rowNum;
	}
	
	public int update(Account account)
	{
		int rowNum = 0;
		Connection conn = null;
		
		try {
//			//JDBC 드라이버 로딩
//			Class.forName("com.mysql.jdbc.Driver");
//			
//			//DB와 연결하기
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web", "root", "12345");
//			LOGGER.info("연결됨");
			
			conn = dataSource.getConnection();
			
			//매개변수화된 SQL문
			String sql = "update account set aowner=?, abalance=? where ano=?";
			
			//SQL문을 실행시키는 객체 생성
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setString(1, account.getAowner());
			stmt.setInt(2, account.getAbalance());
			stmt.setInt(3, account.getAno());
			
			//SQL문을 실행하기
			rowNum = stmt.executeUpdate(); //create, insert, delete, update
			
			//사용한 자원 닫기
			stmt.close();
			LOGGER.info("수정 성공");
		} 
		catch (Exception e) {
			e.printStackTrace();
			//LOGGER.info("연결 안됨 : {}", e.toString());
		}
		finally {
			
			try { conn.close();	} 
			catch (SQLException e) {};
		}
		return rowNum;
	}
	
	public Account selectByAno(int ano)
	{
		Account account = null;
		Connection conn = null;
		
		try {
//			//JDBC 드라이버 로딩
//			Class.forName("com.mysql.jdbc.Driver");
//			
//			//DB와 연결하기
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web", "root", "12345");
//			LOGGER.info("연결됨");
			
			conn = dataSource.getConnection();
			
			//매개변수화된 SQL문
			String sql = "select ano, aowner, abalance, adate from account where ano=?";
			
			//SQL문을 실행시키는 객체 생성
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, ano);
			
			//SQL문을 실행하기
			ResultSet rs = stmt.executeQuery(); //select
			if(rs.next()) 
			{
				account = new Account();
				account.setAno(rs.getInt("ano"));
				account.setAowner(rs.getString("aowner"));
				account.setAbalance(rs.getInt("abalance"));
				account.setAdate(rs.getDate("adate"));
			}
			
			//사용한 자원 닫기
			stmt.close();
			
			LOGGER.info("가져오기 성공");
		} 
		catch (Exception e) {
			e.printStackTrace();
			//LOGGER.info("연결 안됨 : {}", e.toString());
		}
		finally {
			
			try { conn.close();	} 
			catch (SQLException e) {};
		}
		return account;
	}
	
	public List<Account> selectAll()
	{
		List<Account> list = new ArrayList<>();
		Account account = null;
		Connection conn = null;
		
		try {
//			//JDBC 드라이버 로딩
//			Class.forName("com.mysql.jdbc.Driver");
//			
//			//DB와 연결하기
//			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/web", "root", "12345");
//			LOGGER.info("연결됨");
			
			conn = dataSource.getConnection();
			
			//매개변수화된 SQL문
			String sql = "select ano, aowner, abalance, adate from account";
			
			//SQL문을 실행시키는 객체 생성
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			//SQL문을 실행하기
			ResultSet rs = stmt.executeQuery(); //select
			while(rs.next()) 
			{
				account = new Account();
				account.setAno(rs.getInt("ano"));
				account.setAowner(rs.getString("aowner"));
				account.setAbalance(rs.getInt("abalance"));
				account.setAdate(rs.getDate("adate"));
				
				list.add(account);
			}
			
			//사용한 자원 닫기
			stmt.close();
			LOGGER.info("가져오기 성공");
		} 
		catch (Exception e) {
			e.printStackTrace();
			//LOGGER.info("연결 안됨 : {}", e.toString());
		}
		finally {
			
			try { conn.close();	} 
			catch (SQLException e) {};
		}
		return list;
	}
	
	public static void main(String[] args) {
		
		AccountDao dao = new AccountDao();
		
//		for(int i=1; i<=10; i++)
//		{
//			Account account = new Account();
//			
//			account.setAowner("홍길동" + i);
//			account.setAbalance(10000 + i);
//			
//			dao.insert(account);
//		}
		
//		dao.delete(3);
		
//		Account account = new Account();
//		account.setAno(5);
//		account.setAowner("감자바");
//		account.setAbalance(20000);
//		dao.update(account);
		
//		Account account = dao.selectByAno(7);
//		if(account != null)
//		{
//			LOGGER.info("ano:{}", account.getAno());
//			LOGGER.info("aowner:{}", account.getAowner());
//			LOGGER.info("abalance:{}", account.getAbalance());
//			LOGGER.info("adate:{}", account.getAdate());
//		}
		
		List<Account> list = dao.selectAll();
		for(Account account : list)
		{
			LOGGER.info("----------------------------------------");
			LOGGER.info("ano:{}", account.getAno());
			LOGGER.info("aowner:{}", account.getAowner());
			LOGGER.info("abalance:{}", account.getAbalance());
			LOGGER.info("adate:{}", account.getAdate());
		}
	}

	public void transfer(int fromAno, int toAno, int amount) 
	{
		Connection conn = null;
		try
		{
			conn = dataSource.getConnection();
			conn.setAutoCommit(false);
			
//			//출금 계좌 존재 여부
//			Account fromAccount = selectByAno(fromAno);
//			if(fromAccount == null) { throw new SQLException("출금 계좌 없음"); }

			//출금하기
			String sql = "update account set abalance=abalance-? where ano=?";
			PreparedStatement stmt = conn.prepareStatement(sql);
			stmt.setInt(1, amount);
			stmt.setInt(2, fromAno);
			int rows = stmt.executeUpdate(); //rows가 0이면 ano이 없다는 거니까 사실 위의 코드는 없어도 된다
			if(rows != 1) { throw new SQLException("출금 예외 : 출금 계좌가 존재하지 않음"); }
			
//			//입금 계좌 존재 여부
//			Account toAccount = selectByAno(toAno);
//			if(toAccount == null) { throw new SQLException("입금 계좌 없음"); }
			
			//입금하기
			sql = "update account set abalance=abalance+? where ano=?";
			stmt = conn.prepareStatement(sql);
			stmt.setInt(1, amount);
			stmt.setInt(2, toAno);
			rows = stmt.executeUpdate();
			if(rows != 1) { throw new SQLException("입금 예외 : 입금 계좌가 존재하지 않음"); }
			
			stmt.close();
			conn.commit();
		}
		catch(Exception e)
		{
			try { conn.rollback(); } 
			catch (SQLException e1) {}
			e.printStackTrace();
		}
		finally
		{
			try{ conn.close(); } 
			catch (SQLException e){ e.printStackTrace(); }
		}
	}
}
