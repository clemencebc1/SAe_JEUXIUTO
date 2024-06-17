package bd;
import sport.*;
import participant.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AthleteBD {
	ConnexionMySQL laConnexion;
	Statement st;
	void Athlete(ConnexionMySQL laConnexion){
		this.laConnexion = laConnexion;
	}
	int maxNumAthlete() throws SQLException{
		this.st = this.laConnexion.createStatement();
		ResultSet rs = this.st.executeQuery("select max(id_A) from ATHLETE");
		if (rs.next()){
			int maxnum = rs.getInt(1);
			/** int macnum.rs.get("max(numJoueur)") */
			return maxnum;
		}
		return -1;
	}

	/** C'est mieux que insererJoueur_old */
	int insererAthlete( Athlete j) throws  SQLException{
		PreparedStatement ps = this.laConnexion.prepareStatement("insert into ATHLETE(id_A,nom_A,prenom_A,sexe_A,id_P,force_A,endurance,agilite,id_E) values (?,?,?,?,?,?,?,?,?)");
		int nouvNum = maxNumAthlete()+1;
		ps.setInt(1, nouvNum);
		ps.setString(2, j.getNom());
		ps.setString(3, j.getPrenom());
		ps.setString(4, j.getSexe());
		ps.setInt(5, j.getPays().getNum());
		ps.setInt(6, j.getForce());
		ps.setInt(7, j.getEndurance());
		ps.setInt(8, j.getAgilite());
		ps.setInt(9, j.getEquipe().getNum());
		ps.executeUpdate();
		return nouvNum;
	}

	void insererParticipation(Athlete a, Epreuve e)throws SQLException{
		PreparedStatement ps = this.laConnexion.prepareStatement("insert into PARTICIPER(id_A,id_Ep) values (?,?)");
		ps.setInt(1, getNumAthlete(a));
		ps.setInt(2, getNumEpreuve(e));
		ps.executeUpdate();
	}

	int getNumAthlete(Athlete a) throws SQLException{
		this.st = this.laConnexion.createStatement();
		ResultSet rs = this.st.executeQuery("SELECT id_A FROM ATHLETE WHERE nom_A = "+ a.getNom() +"AND prenom_A = "+ a.getPrenom()+"AND sexe_A =" + a.getSexe());
			if (rs.next()){
				int maxnum = rs.getInt(1);

				return maxnum;
			}
			return -1;
	}

	int getNumEpreuve(Epreuve e)throws SQLException{

		PreparedStatement ps = this.laConnexion.prepareStatement("SELECT id_Ep FROM EPREUVE WHERE nom = "+  e.getNom()+"AND categorie = "+ e.getCategorie()+ "AND id_S = " + e.getSport());
		ResultSet rs = ps.executeQuery();
			if (rs.next()){
				int maxnum = rs.getInt(1);

				return maxnum;
			}
			return -1;
	}

	int maxNumPays() throws SQLException{
		this.st = this.laConnexion.createStatement();
		ResultSet rs = this.st.executeQuery("select max(id_P) from PAYS");
		if (rs.next()){
			int maxnum = rs.getInt(1);
			/** int macnum.rs.get("max(numJoueur)") */
			return maxnum;
		}
		return -1;
	}

	List<Pays> classement() throws SQLException{
		ArrayList<Pays> listePays = new ArrayList<>();
		this.st = this.laConnexion.createStatement();
		ResultSet rs = this.st.executeQuery("select nom_P,nb_Or,nb_Argent,nb_Bronze from PAYS order by nb_Or, nb_Argent , nb_Bronze");
		while (rs.next()){
			Integer num = maxNumPays()+1;
			String nom = rs.getString("nom_P");
			Integer nbArgent = rs.getInt("nb_Argent");
			Integer nbBronze = rs.getInt("nb_Bronze");
			Integer nbOr = rs.getInt("nb_Or");
			Pays pays = new Pays( nom,num);
			pays.setNbOr(nbOr);
			pays.setNbArgent(nbArgent);
			pays.setNbBronze(nbBronze);
			listePays.add(pays);
		}
		rs.close();
		return listePays;
	}


}


