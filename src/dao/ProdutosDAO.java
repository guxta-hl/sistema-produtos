package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Produtos;

public class ProdutosDAO {
	
    
    public static void inserir(Produtos p) {
        String sql = "INSERT INTO produto (nome, preco) VALUES (?, ?)";

        try (Connection conn = ConnectDAO.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao inserir: " + e.getMessage());
        }
    }

    
    public static List<Produtos> listar() {
        List<Produtos> lista = new ArrayList<>();
        String sql = "SELECT * FROM produto";

        try (Connection conn = ConnectDAO.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Produtos p = new Produtos();

                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getDouble("preco"));

                lista.add(p);
            }

        } catch (SQLException e) {
            System.out.println("Erro ao listar: " + e.getMessage());
        }

        return lista;
    }

  
    public static Produtos buscarPorId(int id) {
        String sql = "SELECT * FROM produto WHERE id = ?";
        Produtos p = null;

        try (Connection conn = ConnectDAO.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                p = new Produtos();
                p.setId(rs.getInt("id"));
                p.setNome(rs.getString("nome"));
                p.setPreco(rs.getDouble("preco"));
            }

        } catch (SQLException e) {
            System.out.println("Erro ao buscar: " + e.getMessage());
        }

        return p;
    }

   
    public static void atualizar(Produtos p) {
        String sql = "UPDATE produto SET nome = ?, preco = ? WHERE id = ?";

        try (Connection conn = ConnectDAO.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, p.getNome());
            stmt.setDouble(2, p.getPreco());
            stmt.setInt(3, p.getId());

            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao atualizar: " + e.getMessage());
        }
    }

   
    public static void deletar(int id) {
        String sql = "DELETE FROM produto WHERE id = ?";

        try (Connection conn = ConnectDAO.connectDB();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Erro ao deletar: " + e.getMessage());
        }
    }
}
