package br.com.pizzaria.dao;

import br.com.pizzaria.jdbc.ConnectionFactory;
import br.com.pizzaria.model.Logar;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
