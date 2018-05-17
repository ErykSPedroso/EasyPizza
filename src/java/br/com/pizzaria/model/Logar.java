package br.com.pizzaria.model;

public class Logar {
    private String user;
    private String password;

    public Logar() {}
    
    public Logar(String user, String password) {
        this.user = user;
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Logar{" + "user=" + user + ", password=" + password + '}';
    }
}
