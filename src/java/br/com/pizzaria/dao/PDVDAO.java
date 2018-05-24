package br.com.pizzaria.dao;

import br.com.pizzaria.jdbc.ConnectionFactory;
import br.com.pizzaria.model.Logar;
import br.com.pizzaria.model.Produto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PDVDAO {
    
    //Método que verifica se o usuário está cadastrado no banco
    public boolean pesquisa(Logar user) throws SQLException{
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        boolean check = false;
        
        try{
            stmt = con.prepareStatement("SELECT * FROM  logar");
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Logar logar = new Logar();
                
                logar.setUser(rs.getString("user"));
                logar.setPassword(rs.getString("pass"));
                
                if(user.getUser().equals(logar.getUser()) && user.getPassword().equals(logar.getPassword())){
                    check = true;
                }
            }
        }catch(SQLException ex){
            System.out.println("Erro: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
     
        return check;
    }
    
    public List<Produto> findAll() throws SQLException{
        String sql = "SELECT * FROM produto";
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        ResultSet rs = null;
        
        List<Produto> listaProdutos = new ArrayList<Produto>();
        
        try{
            stmt = con.prepareStatement(sql);
            rs = stmt.executeQuery();
            
            while(rs.next()){
                Produto produto = new Produto();
                produto.setNome(rs.getString("nome"));
                produto.setQuantidade(rs.getInt("quantidade"));
                produto.setPreco(rs.getDouble("preco"));
                
                listaProdutos.add(produto);
            }
        }catch(Exception ex){
            System.out.println("Erro de busca no banco: "+ex);
        }finally{
            ConnectionFactory.closeConnection(con, stmt, rs);
        }
        
        return listaProdutos;
    }
    
    
    //Metodo inserir
    public boolean inserir(Produto produto) throws SQLException{
        
        String sql = "INSERT INTO produto (nome, quantidade, preco) values(?,?,?)";
        
        Connection con = ConnectionFactory.getConnection();
        PreparedStatement stmt = null;
        
        try{
        //Preparando a minha string para ser executada
        stmt = con.prepareStatement(sql);
        
        //Setando as interrogações com valores salvados no objeto
        stmt.setString(1, produto.getNome());
        stmt.setInt(2, produto.getQuantidade());
        stmt.setDouble(3, produto.getPreco());
        
        //Executando a query
        stmt.executeUpdate();
        
        return true;
        }catch(Exception ex){
            System.out.println("Erro na inserção no banco: "+ex);
            return false;
        }finally{
           //Sempre vai passar aqui dentro. (Fechando conexões)
           ConnectionFactory.closeConnection(con, stmt);
        }
    }
}
