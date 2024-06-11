import java.sql.*;
import java.util.ArrayList;

public class AthleteBD {
	ConnexionMySQL laConnexion;
	Statement st;
	Athlete(ConnexionMySQL laConnexion){
		this.laConnexion=laConnexion;
	}
	int maxNumJoueur() throws SQLException{
		this.st = this.laConnexion.createStatement();
		ResultSet rs = this.st.executeQuery("select max(id_A) from ATHLETE");
		if (rs.next()){
			int maxnum = rs.getInt(1);
			/** int macnum.rs.get("max(numJoueur)") */
			return maxnum;
		}
		return -1;
	}

	/** Permet d'insérer un athlète dans la base de données*/
	int insererAthlete( Athlete j) throws  SQLException{
		PreparedStatement ps = this.laConnexion.prepareStatement("insert into ATHLETE(id_A,nom_A,prenom_A,sexe_A,id_P,force_A,endurance,agilite,id_E) values (?,?,?,?,?,?,?,?,?)");
		int nouvNum = maxNumJoueur()+1;
		ps.setInt(1, nouvNum);
		ps.setString(2, j.getNom());
		ps.setString(3, j.getPrenom());
		ps.setString(4, j.getSexe());
		ps.setInt(5, j.getPays());
		ps.setInt(6, j.getForce());
		ps.setInt(7, j.getEndurance());
		ps.setInt(8, j.getAgilite());
		ps.setInt(9, j.getEquipe());
		ps.executeUpdate();
		return nouvNum;
	}
	/** Permet d'insérer la participation d'un athlète à une épreuve dans la base de données */
	void insererParticipation(Athlete a, Epreuve e){
		PreparedStatement ps = this.laConnexion.prepareStatement("insert into PARTICIPER(id_A,id_Ep) values (?,?)");
		ps.setInt(1, getNumAthlete(a));
		ps.setInt(2, j.getNumEpreuve(e));
		ps.executeUpdate();
	}
	/** Permet d'avoir le numéro de l'athlète afin de l'insérer dans la table Participer*/
	int getNumAthlete(Athlete a){
		// Create a prepared statement with the SQL query
		PreparedStatement ps = this.laConnexion.prepareStatement("SELECT id_A FROM ATHLETE WHERE nom_A = ? AND prenom_A = ? AND sexe_A = ?");

		// Set the parameters for the prepared statement
		ps.setString(1, a.getNom());
		ps.setString(2, a.getPrenom());
		ps.setString(3, a.getSexe());

		// Execute the query
		ResultSet rs = ps.executeQuery();
			if (rs.next()){
				int maxnum = rs.getInt(1);
				/** int macnum.rs.get("max(numJoueur)") */
				return maxnum;
			}
			return -1;
	}
	/** Permet d'avoir le numéro de l'épreuve afin de l'insérer dans la table Participer*/
	int getNumEpreuve(Epreuve e){
		// Create a prepared statement with the SQL query
		PreparedStatement ps = this.laConnexion.prepareStatement("SELECT id_Ep FROM EPREUVE WHERE nom = ? AND categorie = ? AND id_S = ?");

		// Set the parameters for the prepared statement
		ps.setString(1, e.getNom());
		ps.setString(2, a.getCategorie());
		ps.setInt(3, a.getPays());

		// Execute the query
		ResultSet rs = ps.executeQuery();
			if (rs.next()){
				int maxnum = rs.getInt(1);
				/** int macnum.rs.get("max(numJoueur)") */
				return maxnum;
			}
			return -1;
	}


}


