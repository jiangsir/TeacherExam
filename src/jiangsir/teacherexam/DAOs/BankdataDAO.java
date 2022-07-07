package jiangsir.teacherexam.DAOs;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;

import org.codehaus.jackson.map.ObjectMapper;
import jiangsir.teacherexam.Exceptions.DataException;
import jiangsir.teacherexam.Scopes.ApplicationScope;
import jiangsir.teacherexam.Tables.Bankdata;

public class BankdataDAO extends SuperDAO<Bankdata> {
	ObjectMapper mapper = new ObjectMapper();

	@Override
	public synchronized int insert(Bankdata bankdata) throws DataException {
		String sql = "INSERT INTO bankdatas (`bankname`, `bankaccount`, `filename`, `rowdata`, `receivetime`)"
				+ " VALUES (?,?,?,?,?);";
		int id = 0;
		PreparedStatement pstmt;
		try {
			pstmt = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
			pstmt.setString(1, bankdata.getBankname().toString());
			pstmt.setString(2, bankdata.getBankaccount());
			pstmt.setString(3, bankdata.getFilename());
			pstmt.setString(4, bankdata.getRowdata());
			pstmt.setTimestamp(5, bankdata.getReceivetime());
			executeUpdate(pstmt);
			ResultSet rs = pstmt.getGeneratedKeys();
			rs.next();
			id = rs.getInt(1);
			rs.close();
			pstmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new DataException(e.getLocalizedMessage());
		}
		return id;
	}

	@Override
	public synchronized int update(Bankdata bankdata) throws DataException {
		return 0;
	}

	@Override
	public synchronized boolean delete(int i) {
		return false;
	}

	public ArrayList<Bankdata> getBankdatas() {
		String sql = "SELECT * FROM bankdatas ORDER BY receivetime DESC;";
		return this.executeQuery(sql, Bankdata.class);
	}

	/**
	 * 取得某一段時間內，銀行資料。
	 * 
	 * @param begin
	 * @param end
	 * @return
	 */
	public ArrayList<Bankdata> getBankdatasByTimestamp(Timestamp begin, Timestamp end) {
		String sql = "SELECT * FROM bankdatas WHERE receivetime>='" + begin + "' AND receivetime<='" + end + "'";
		return this.executeQuery(sql, Bankdata.class);
	}

	/**
	 * 取得 bankdata, 透過 cache
	 * 
	 * @param bankaccount
	 * @return
	 */
	public ArrayList<Bankdata> getBankdatasByBankaccount(String bankaccount) {
		ArrayList<Bankdata> bankdatas = new ArrayList<>();
		// if (ApplicationScope.cacheBankdatas.containsKey(bankaccount)) {
		// bankdatas.add(ApplicationScope.cacheBankdatas.get(bankaccount));
		// return bankdatas;
		// }
		String sql = "SELECT * FROM bankdatas WHERE bankaccount=?";
		try {
			PreparedStatement pstmt = this.getConnection().prepareStatement(sql);
			pstmt.setString(1, bankaccount);
			return this.executeQuery(pstmt, Bankdata.class);
			// for (Bankdata bankdata : this.executeQuery(pstmt, Bankdata.class)) {
			// ApplicationScope.cacheBankdatas.put(bankaccount, bankdata);
			// return bankdata;
			// }
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return bankdatas;
	}
}
