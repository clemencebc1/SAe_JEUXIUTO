package bd;
import sport.*;
import participant.*;
import java.sql.*;

public class ConnexionMySQL {
	private Connection mysql=null;
	private boolean connecte=false;
	Statement st;
	public ConnexionMySQL() throws ClassNotFoundException{
		Class.forName("org.mariadb.jdbc.Driver");
	}

	public void connecter(String nomServeur, String nomBase, String nomLogin, String motDePasse, String mdp, String log) throws SQLException {
		this.st = this.createStatement();
		ResultSet rs = this.st.executeQuery("SELECT nom_U, mdp_U, role_U FROM USER where nom_u =" + log + " and mdp_u = " + mdp);
			if (rs.next()){
			this.mysql = DriverManager.getConnection("jdbc:mysql://" + "servinfo-maria" + ":3306/" + "DBlobjois" +"lobjois"+ "lobjois");
			// si tout c'est bien passé la connexion n'est plus nulle
			this.connecte=this.mysql!=null;
		}
	}
	public void close() throws SQLException {
		// fermer la connexion
		this.connecte=false;
	}

    	public boolean isConnecte() { return this.connecte;}
	public Statement createStatement() throws SQLException {
		return this.mysql.createStatement();
	}

	public PreparedStatement prepareStatement(String requete) throws SQLException{
		return this.mysql.prepareStatement(requete);
	}
	
}
