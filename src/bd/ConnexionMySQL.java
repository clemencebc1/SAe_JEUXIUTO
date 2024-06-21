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

	public void connecter(String nomServeur, String nomBase, String nomLogin, String motDePasse) throws SQLException {
			this.mysql = DriverManager.getConnection("jdbc:mysql://"+nomServeur+":3306/"+nomBase, nomLogin, motDePasse);
			// si tout c'est bien passé la connexion n'est plus nulle

			this.connecte=this.mysql!=null;
		}
	
	public void close() throws SQLException {
		// fermer la connexion
		this.connecte=false;
	}

    public boolean isConnecte(String log, String mdp)  throws SQLException{ 
		if (this.connecte){
			this.st = this.createStatement();
			log = "'"+log+"'";
			mdp = "'"+mdp+"'";
			ResultSet rs = this.st.executeQuery("SELECT nom_U, mdp_U, role_u FROM USER where nom_U ="+log+"and mdp_U="+mdp);
			if (rs.next()){
				return true;}
			}
		return false;
}
	public Statement createStatement() throws SQLException {
		return this.mysql.createStatement();
	}

	public PreparedStatement prepareStatement(String requete) throws SQLException{
		return this.mysql.prepareStatement(requete);
	}
	
}
