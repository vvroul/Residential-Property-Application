package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import entities.Recommendation;


public class RecommendDAOImpl implements RecommendDAO {
	
	private static final String SQL_INSERT_ENTRY = "INSERT INTO Recommend VALUES (?,?,?)";
	
	private static final String SQL_DELETE_ALL = "DELETE FROM Recommend WHERE 1 = 1";
	
	
	
	
	private ConnectionFactory factory;
	
	
	public RecommendDAOImpl(boolean pool) {
    	factory = ConnectionFactory.getInstance(pool);
    }
	
	

	@Override
	public void insert_listings(Recommendation[] recommendations) {
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQL_INSERT_ENTRY);
			
			for (int i=0; i<recommendations.length; i++) {
				
				pstmt.setInt(1, recommendations[i].getUser_id());
				pstmt.setInt(2, recommendations[i].getListing_id());
				pstmt.setInt(3, recommendations[i].getPosition());
				
				pstmt.addBatch();
				
				
				if (i % 1000 == 0) {
					System.out.println("Execute batch " + (i / 1000));
					pstmt.executeBatch();
				}
				
			}
			
			pstmt.executeBatch();
			
		} 
		catch (SQLException e) {
			System.err.println(e.getMessage() + "   insert_listings");
		}
		catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	
	@Override
	public void delete_all() {
		
		try {
			Connection con = factory.getConnection();
			PreparedStatement pstmt = con.prepareStatement(SQL_DELETE_ALL);
			pstmt.executeUpdate();
		}
		catch (SQLException e) {
			System.err.println(e.getMessage() + "    delete_all");
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
