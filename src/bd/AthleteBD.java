package bd;
import sport.*;
import participant.*;
import java.sql.*;
import bd.*;
import autre.*;
import comparateur.*;
import java.util.ArrayList;
import java.util.List;

// javac --module-path /usr/share/openjfx/lib/ --add-modules javafx.controls -d bin/ src/bd/*.java src/participant/*.java src/sport/*.java src/autre/*.java src/comparateur/*.java

public class AthleteBD {
	ConnexionMySQL laConnexion;
	Statement st;
	public void Athlete(ConnexionMySQL laConnexion){
		this.laConnexion = laConnexion;
	}

////////////////////////////////////// AUTRE //////////////////////////////////////
	public boolean estAdmin(String log, String mdp) throws SQLException{
		log = "'"+log+"'";
		mdp = "'"+mdp+"'";
		this.st = this.laConnexion.createStatement();
		ResultSet rs = this.st.executeQuery("SELECT role_u FROM USER where nom_U ="+log+"and mdp_U="+mdp);
		if (rs.next()){
			if (rs.getString("role_U").equals("admin")){
				return true;
			}
		}
		return false;
	}

	public boolean estOrga(String log, String mdp) throws SQLException{
		log = "'"+log+"'";
		mdp = "'"+mdp+"'";
		this.st = this.laConnexion.createStatement();
		ResultSet rs = this.st.executeQuery("SELECT role_u FROM USER where nom_U ="+log+"and mdp_U="+mdp);
		if (rs.next()){
			if (rs.getString("role_U").equals("organisateur")){
				return true;
			}
		}
		return false;
	}
	public boolean estJourna(String log, String mdp) throws SQLException{
		log = "'"+log+"'";
		mdp = "'"+mdp+"'";
		this.st = this.laConnexion.createStatement();
		ResultSet rs = this.st.executeQuery("SELECT role_u FROM USER where nom_U ="+log+"and mdp_U="+mdp);
		if (rs.next()){
			if (rs.getString("role_U").equals("journaliste")){
				return true;
			}
		}
		return false;
	}

////////////////////////////////////// JOURNALISTE //////////////////////////////////////

List<?> voirResEpreuve(CompareMedailleOr comp, Tri tri, Epreuve e)throws SQLException{
	PreparedStatement ps = this.laConnexion.prepareStatement("SELECT id_A,nom_A,prenom_A,sexe_A,id_P,force_A,endurance,agilite,id_E from ATHLETE natural join PARTICIPER natural join EPREUVE where id_Ep =" + e.getNum());
	ResultSet rs = ps.executeQuery();
	Epreuve ep = new Epreuve(e.getNom(), e.getCategorie(), e.getStyle(), e.getSport(), e.getNum());
		while (rs.next()){
			PreparedStatement psP = this.laConnexion.prepareStatement("SELECT nom_P from PAYS where id_P = " + rs.getInt("id_P"));
			ResultSet rsP = psP.executeQuery();

			PreparedStatement psE = this.laConnexion.prepareStatement("SELECT nom_E from PAYS where id_P = " + rs.getInt("id_E"));
			ResultSet rsE = psE.executeQuery();

			Equipe equipe = null;

			String nomP = rsP.getString("nom_P");
			Integer numP = rsP.getInt("id_P");
			Pays pays = new Pays(nomP, numP);

			if (rsE.next()){
				String nomE = rsE.getString("nom_E");
				Integer numE = rsE.getInt("id_E");
				equipe = new Equipe(nomE, numE);
			}
			Integer num = rs.getInt("id_A");
			String nom = rs.getString("nom_A");
			String prenom = rs.getString("prenom_A");
			String sexe = rs.getString("sexe_A");
			Integer force = rs.getInt("force_A");
			Integer endurance = rs.getInt("endurance");
			Integer agilite = rs.getInt("agilite");

			Athlete athlete = new Athlete(nom, prenom, sexe, force, agilite, endurance, pays, equipe, num);

			ep.ajoutParticipants(athlete);


		}
		return ep.classement(comp, tri);
		
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
////////////////////////////////////// ADMIN //////////////////////////////////////

	void effacerAthlete(int num) throws SQLException {
		Statement st = this.laConnexion.createStatement();
		st.executeUpdate("delete from ATHLETE where id_A = " + num);
		st.executeUpdate("delete from PARTICIPER where id_A = " + num);
	}
	void effacerEpreuve(int num) throws SQLException {
		Statement st = this.laConnexion.createStatement();
		st.executeUpdate("delete from EPREUVE where id_Ep = " + num);
		st.executeUpdate("delete from PARTICIPER where id_Ep = " + num);
	}

	void effacerUser(String nom,String mdp) throws SQLException {
		Statement st = this.laConnexion.createStatement();
		st.executeUpdate("delete from USER where nom_U = " + nom + "and mdp_U = " + mdp );
		
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

	int maxNumEpreuve() throws SQLException{
		this.st = this.laConnexion.createStatement();
		ResultSet rs = this.st.executeQuery("select max(id_Ep) from EPREUVE");
		if (rs.next()){
			int maxnum = rs.getInt(1);
			/** int macnum.rs.get("max(numJoueur)") */
			return maxnum;
		}
		return -1;
	}

	
	int insererAthlete( participant.Athlete j) throws  SQLException{
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

	public void insererParticipation(Athlete a, Epreuve e)throws SQLException{
		PreparedStatement ps = this.laConnexion.prepareStatement("insert into PARTICIPER(id_A,id_Ep) values (?,?)");
		ps.setInt(1, getNumAthlete(a));
		ps.setInt(2, getNumEpreuve(e));
		ps.executeUpdate();
	}

	public void insererUser(Utilisateur user) throws SQLException{
		PreparedStatement ps = this.laConnexion.prepareStatement("insert into USER(nom_U,mdp_U,role_u) values (?,?,?)");
		ps.setString(1, user.getNom());
		ps.setString(2, user.getMdp());
		ps.setString(3, "journaliste");
		ps.executeUpdate();

	}

	void insererEpreuve(Epreuve e)throws SQLException{
		PreparedStatement ps = this.laConnexion.prepareStatement("insert into EPREUVE(id_EP,nom,categorie,id_S) values (?,?,?,?)");
		Integer num = maxNumEpreuve() +1;
		ps.setInt(1, num);
		ps.setString(2, e.getNom());
		ps.setString(3, e.getCategorie());
		ps.setInt(4, e.getSport().getNum());
	}

	int getNumAthlete(Athlete a) throws SQLException{
		this.st = this.laConnexion.createStatement();
		ResultSet rs = this.st.executeQuery("SELECT id_A FROM ATHLETE WHERE nom_A = "+ a.getNom() +"AND prenom_A = "+ a.getPrenom()+"AND sexe_A =" + a.getSexe());
			if (rs.next()){
				int maxnum = rs.getInt("id_A");

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

	
	void majAthlete(Athlete a)throws SQLException{
		PreparedStatement ps = this.laConnexion.prepareStatement("UPDATE ATHLETE SET nom_A = ?,prenom_A = ?, sexe_A=?,id_P=?,force_A=?,endurance=?, agilite = ?, id_E = ? where id_A = ?" );
		ps.setString(1, a.getNom());
		ps.setString(2, a.getPrenom());
		ps.setString(3, a.getSexe());
		ps.setInt(4, a.getPays().getNum());
		ps.setInt(5, a.getForce());
		ps.setInt(6, a.getEndurance());
		ps.setInt(7, a.getAgilite());
		ps.setInt(8, a.getEquipe().getNum());
		ps.setInt(9, a.getNum());
		ps.executeUpdate();
    	}

	

	void majUser(Utilisateur user)throws SQLException{
		PreparedStatement ps = this.laConnexion.prepareStatement("UPDATE USER SET role_u = ? where nom_U = ? and mdp_U = ?" );
		ps.setString(1, user.getRole());
		ps.setString(2, user.getNom());
		ps.setString(3, user.getMdp());
		ps.executeUpdate();
    	}

	
	

////////////////////////////////////// ORGANISATEUR //////////////////////////////////////

void lancerEpreuve(CompareMedailleOr comp, Tri tri, Epreuve e)throws SQLException{
	PreparedStatement ps = this.laConnexion.prepareStatement("SELECT id_A,nom_A,prenom_A,sexe_A,id_P,force_A,endurance,agilite,id_E from ATHLETE natural join PARTICIPER natural join EPREUVE where id_Ep =" + e.getNum());
	ResultSet rs = ps.executeQuery();
	Epreuve ep = new Epreuve(e.getNom(), e.getCategorie(), e.getStyle(), e.getSport(), e.getNum());
		while (rs.next()){
			PreparedStatement psP = this.laConnexion.prepareStatement("SELECT nom_P from PAYS where id_P = " + rs.getInt("id_P"));
			ResultSet rsP = psP.executeQuery();

			PreparedStatement psE = this.laConnexion.prepareStatement("SELECT nom_E from PAYS where id_P = " + rs.getInt("id_E"));
			ResultSet rsE = psE.executeQuery();

			Equipe equipe = null;

			String nomP = rsP.getString("nom_P");
			Integer numP = rsP.getInt("id_P");
			Pays pays = new Pays(nomP, numP);

			if (rsE.next()){
				String nomE = rsE.getString("nom_E");
				Integer numE = rsE.getInt("id_E");
				equipe = new Equipe(nomE, numE);
			}
			Integer num = rs.getInt("id_A");
			String nom = rs.getString("nom_A");
			String prenom = rs.getString("prenom_A");
			String sexe = rs.getString("sexe_A");
			Integer force = rs.getInt("force_A");
			Integer endurance = rs.getInt("endurance");
			Integer agilite = rs.getInt("agilite");

			Athlete athlete = new Athlete(nom, prenom, sexe, force, agilite, endurance, pays, equipe, num);

			ep.ajoutParticipants(athlete);
			


		}
		ep.classementPoint();
}



}


