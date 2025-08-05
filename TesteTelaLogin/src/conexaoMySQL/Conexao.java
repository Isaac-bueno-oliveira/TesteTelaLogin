package conexaoMySQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
	
	public static Connection conexao_servidor() throws SQLException {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			return DriverManager.getConnection("jdbc:mysql://localhost/db_senhas", "root", "@Thor145");
			
		} catch (ClassNotFoundException e) {
			throw new SQLException(e.getException());
		
		}
	}
	
	
}
