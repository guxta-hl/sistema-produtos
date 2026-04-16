package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConnectDAO {
	
	public static Connection connectDB() {
		try {
        return DriverManager.getConnection(
            "jdbc:mysql://localhost:3306/sistemapi_produtos?serverTimezone=UTC",
            "root",
            "0702234M$?!%%"
        );

    	} catch (SQLException erro) {
        JOptionPane.showMessageDialog(null, "Erro Conexao: " + erro.getMessage());
        throw new RuntimeException(erro); 
    	}
	}

}
