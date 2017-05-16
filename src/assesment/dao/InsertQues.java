package assesment.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import assesment.Validator.InsertQuestion;
import assesment.Validator.QuestionType;

public class InsertQues {

	public static void main(String[] args) {
		int qid, aid, y;
		String ans = null;
		Scanner s=null;
		try {
			s = new Scanner(System.in);
			do {
				System.out.println("enter the question type");
				System.out.println("1.Choose the best answer 2.Fill in the blanks");
				int ch = s.nextInt();
				QuestionType.typeCheck(ch);
				s.nextLine();
				if (ch == 1) {
					System.out.println("Enter the question:");
					String q = s.nextLine();
					InsertQuestion.validateQuestion(q);
					System.out.println("Enter the question score:");
					int sc = s.nextInt();
					InsertQuestion.validateScore(sc);
					insertques(q, 1, sc);
					qid = find_qid(q);
					if (qid != 0) {
						System.out.println("Enter the four options");
						int i = 0;
						while (i < 4) {
							System.out.println("Enter option" + (i + 1) + "\n");
							String op = s.next();
							InsertQuestion.validateQuestion(op);
							insertoption(op, qid);
							i++;
						}
					} else
						System.out.println("no such a question");
					System.out.println("Enter the correct answer");
					ans = s.next();
					InsertQuestion.validateQuestion(ans);
					aid = find_aid(ans, qid);
					if (aid != 0) {
						insertans(qid, aid);
					} else
						System.out.println("error");
				} else {
					System.out.println("Enter the question for filling must be ----");
					String q = s.nextLine();
					InsertQuestion.validateQuestion(q);
					if (q.contains("----")) {
						System.out.println("Enter the question score");
						int sc = s.nextInt();
						InsertQuestion.validateScore(sc);
						insertques(q, 2, sc);
						qid = find_qid(q);
						if (qid != 0) {
							System.out.println("enter the answer");
							ans = s.next();
							insertoption(ans, qid);
						} else
							System.out.println("no such a question");
						aid = find_aid(ans, qid);
						if (aid != 0)
							insertans(qid, aid);
						else
							System.out.println("error");

					} else
						System.out.println("The question pattern is wrong. Try again");
				}
				System.out.println("Do you want to give another question give 1");
				y = s.nextInt();
			} while (y == 1);
		} catch (ClassNotFoundException e) {
			System.out.println(e);
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		} finally {
			s.close();
		}

	}
	// TODO Auto-generated method stub

	public static void insertques(String d, int i, int sc) throws Exception {
		Connection con = null;
		try {
			con = connection.myconnection();
			String sql = "insert into ques (qtext,qty,qscore) values (?,?,?)";
			PreparedStatement p = con.prepareStatement(sql);
			p.setString(1, d);
			p.setInt(2, i);
			p.setInt(3, sc);
			int rows = p.executeUpdate();
			System.out.println("no of rows inserted" + rows);
		} catch (ClassNotFoundException e) {
			throw new Exception("NOT Connected");
		} catch (SQLException e) {
			throw new Exception("Database server is not available");
		} finally {
			con.close();
		}
	}

	public static void insertoption(String d, int i) throws Exception {
		Connection con = null;
		try {
			con = connection.myconnection();
			String sql = "insert into aoption (anstext,qtext) values (?,?)";
			PreparedStatement p = con.prepareStatement(sql);
			p.setString(1, d);
			p.setInt(2, i);
			int rows = p.executeUpdate();
			System.out.println("no of rows inserted" + rows);
		} catch (ClassNotFoundException e) {
			throw new Exception("NOT Connected");
		} catch (SQLException e) {
			throw new Exception("Database server is not available");
		} finally {
			con.close();
		}

	}

	public static int find_aid(String a, int qno) throws Exception {
		Connection con = null;
		try {

			con = connection.myconnection();
			String sql = "select id from aoption where qtext=? and anstext=?";
			PreparedStatement p = con.prepareStatement(sql);
			p.setInt(1, qno);
			p.setString(2, a);
			ResultSet rs = p.executeQuery();
			if (rs.next())
				return rs.getInt("id");
			return 0;

		} catch (ClassNotFoundException e) {
			throw new Exception("NOT Connected");
		} catch (SQLException e) {
			throw new Exception("Database server is not available");
		} finally {
			con.close();
		}

	}

	public static void insertans(int d, int i) throws Exception {
		Connection con = null;
		try {
			con = connection.myconnection();
			String sql = "insert into canswer (qid,ansid) values (?,?)";
			PreparedStatement p = con.prepareStatement(sql);
			p.setInt(1, d);
			p.setInt(2, i);
			int rows = p.executeUpdate();
			System.out.println("no of rows inserted" + rows);
		} catch (ClassNotFoundException e) {
			throw new Exception("NOT Connected");
		} catch (SQLException e) {
			throw new Exception("Database server is not available");
		} finally {
			con.close();
		}

	}

	public static int find_qid(String a) throws Exception {
		Connection con = null;
		try {
			con = connection.myconnection();
			String sql = "select id from ques where qtext=?";
			PreparedStatement p = con.prepareStatement(sql);
			p.setString(1, a);
			ResultSet rs = p.executeQuery();
			if (rs.next())
				return rs.getInt("id");
			return 0;
		} catch (ClassNotFoundException e) {
			throw new Exception("NOT Connected");
		} catch (SQLException e) {
			throw new Exception("Database server is not available");
		} finally {
			con.close();
		}

	}
}
