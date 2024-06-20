package bd;
import sport.*;
import participant.*;
import java.sql.*;
import bd.*;
import autre.*;
import comparateur.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;


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

public List<?> voirResEpreuve(CompareMedailleOr comp, Tri tri, Epreuve e)throws SQLException{
	PreparedStatement ps = this.laConnexion.prepareStatement("SELECT id_A,nom_A,prenom_A,sexe_A,id_P,force_A,endurance,agilite,id_E from ATHLETE natural join PARTICIPER natural join EPREUVE where id_Ep =" + e.getNum());
	ResultSet rs = ps.executeQuery();
	Epreuve ep = new Epreuve(e.getNom(), e.getCategorie(), e.getSport(), e.getNum());
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
		return ep.classementMedaille(comp, tri);
		
}
	
	

	public int maxNumPays() throws SQLException{
		this.st = this.laConnexion.createStatement();
		ResultSet rs = this.st.executeQuery("select max(id_P) from PAYS");
		if (rs.next()){
			int maxnum = rs.getInt(1);
			/** int macnum.rs.get("max(numJoueur)") */
			return maxnum;
		}
		return -1;
	}

	public List<Pays> classement() throws SQLException{
		ArrayList<Pays> listePays = new ArrayList<>();
		this.st = this.laConnexion.createStatement();
		ResultSet rs = this.st.executeQuery("select nom_P,nb_Or,nb_Argent,nb_Bronze from PAYS order by nb_Or DESC, nb_Argent DESC, nb_Bronze");
		while (rs.next()){
			Integer num = maxNumPays()+1;
			String nom = rs.getString("nom_P");
			Integer nbArgent = rs.getInt("nb_Argent");
			Integer nbBronze = rs.getInt("nb_Bronze");
			Integer nbOr = rs.getInt("nb_Or");
			Pays pays = new Pays(nom,num);
			pays.setNbOr(nbOr);
			pays.setNbArgent(nbArgent);
			pays.setNbBronze(nbBronze);
			listePays.add(pays);
		}
		rs.close();
		return listePays;
	}

	public List<Pays> classementArgent() throws SQLException{
		ArrayList<Pays> listePays = new ArrayList<>();
		this.st = this.laConnexion.createStatement();
		ResultSet rs = this.st.executeQuery("select nom_P,nb_Or,nb_Argent,nb_Bronze from PAYS order by nb_Argent DESC, nb_Or DESC, nb_Bronze");
		while (rs.next()){
			Integer num = maxNumPays()+1;
			String nom = rs.getString("nom_P");
			Integer nbArgent = rs.getInt("nb_Argent");
			Integer nbBronze = rs.getInt("nb_Bronze");
			Integer nbOr = rs.getInt("nb_Or");
			Pays pays = new Pays(nom,num);
			pays.setNbOr(nbOr);
			pays.setNbArgent(nbArgent);
			pays.setNbBronze(nbBronze);
			listePays.add(pays);
		}
		rs.close();
		return listePays;
	}
	public List<Pays> classementBronze() throws SQLException{
		ArrayList<Pays> listePays = new ArrayList<>();
		this.st = this.laConnexion.createStatement();
		ResultSet rs = this.st.executeQuery("select nom_P,nb_Or,nb_Argent,nb_Bronze from PAYS order by nb_Bronze DESC, nb_Or DESC ,nb_Argent ");
		while (rs.next()){
			Integer num = maxNumPays()+1;
			String nom = rs.getString("nom_P");
			Integer nbArgent = rs.getInt("nb_Argent");
			Integer nbBronze = rs.getInt("nb_Bronze");
			Integer nbOr = rs.getInt("nb_Or");
			Pays pays = new Pays(nom,num);
			pays.setNbOr(nbOr);
			pays.setNbArgent(nbArgent);
			pays.setNbBronze(nbBronze);
			listePays.add(pays);
		}
		rs.close();
		return listePays;
	}
////////////////////////////////////// ADMIN //////////////////////////////////////

	public void effacerAthlete(int num) throws SQLException {
		Statement st = this.laConnexion.createStatement();
		st.executeUpdate("delete from ATHLETE where id_A = " + num);
		st.executeUpdate("delete from PARTICIPER where id_A = " + num);
	}
	public void effacerEpreuve(int num) throws SQLException {
		Statement st = this.laConnexion.createStatement();
		st.executeUpdate("delete from EPREUVE where id_Ep = " + num);
		st.executeUpdate("delete from PARTICIPER where id_Ep = " + num);
	}

	public void effacerUser(String nom,String mdp) throws SQLException {
		Statement st = this.laConnexion.createStatement();
		st.executeUpdate("delete from USER where nom_U = " + nom + "and mdp_U = " + mdp );
		
	}

	public int maxNumAthlete() throws SQLException{
		this.st = this.laConnexion.createStatement();
		ResultSet rs = this.st.executeQuery("select max(id_A) from ATHLETE");
		if (rs.next()){
			int maxnum = rs.getInt(1);
			/** int macnum.rs.get("max(numJoueur)") */
			return maxnum;
		}
		return -1;
	}

	public int maxNumEpreuve() throws SQLException{
		this.st = this.laConnexion.createStatement();
		ResultSet rs = this.st.executeQuery("select max(id_Ep) from EPREUVE");
		if (rs.next()){
			int maxnum = rs.getInt(1);
			/** int macnum.rs.get("max(numJoueur)") */
			return maxnum;
		}
		return -1;
	}

	
	public int insererAthlete( participant.Athlete j) throws  SQLException{
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
		if(j.getEquipe() == null){
			ps.setInt(9, 0);
		}
		else{
			ps.setInt(9,j.getEquipe().getNum());
		}
		if (this.existeDeja(j)){return 0;}
		ps.executeUpdate();
		return nouvNum;
	}
	public boolean existeDeja(participant.Athlete j) throws SQLException{
		this.st = this.laConnexion.createStatement();
		String nom = "'"+j.getNom()+"'";
		String prenom = "'"+j.getPrenom()+"'";
		String sexe = "'"+j.getSexe()+"'";
		ResultSet rs = this.st.executeQuery("select * from ATHLETE where nom_A="+nom+" and prenom_A="+prenom+" and sexe_A="+sexe+ " and force_A="+j.getForce()+" and endurance="+j.getEndurance()+ " and agilite="+j.getAgilite());
		if (rs.next()){
			return true;
		}
		return false;
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

	public void insererEpreuve(Epreuve e)throws SQLException{
		PreparedStatement ps = this.laConnexion.prepareStatement("insert into EPREUVE(id_EP,nom,categorie,id_S) values (?,?,?,?)");
		Integer num = maxNumEpreuve() +1;
		ps.setInt(1, num);
		ps.setString(2, e.getNom());
		ps.setString(3, e.getCategorie());
		ps.setInt(4, e.getSport().getNum());
	}

	public int getNumAthlete(Athlete a) throws SQLException{
		this.st = this.laConnexion.createStatement();
		ResultSet rs = this.st.executeQuery("SELECT id_A FROM ATHLETE WHERE nom_A = "+ a.getNom() +"AND prenom_A = "+ a.getPrenom()+"AND sexe_A =" + a.getSexe());
			if (rs.next()){
				int maxnum = rs.getInt("id_A");

				return maxnum;
			}
			return -1;
	}

	public int getNumEpreuve(Epreuve e)throws SQLException{

		PreparedStatement ps = this.laConnexion.prepareStatement("SELECT id_Ep FROM EPREUVE WHERE nom = "+  e.getNom()+"AND categorie = "+ e.getCategorie()+ "AND id_S = " + e.getSport());
		ResultSet rs = ps.executeQuery();
			if (rs.next()){
				int maxnum = rs.getInt(1);

				return maxnum;
			}
			return -1;
	}

	public Athlete getAthlete(int id) throws SQLException{
		this.st = this.laConnexion.createStatement();
		ResultSet rs = this.st.executeQuery("SELECT nom_A,prenom_A,sexe_A,force_A,endurance,agilite,id_E,nom_P  from ATHLETE natural join PAYS where id_A =" +id);
		if (rs.next()){
			return new Athlete(rs.getString("nom_A"), rs.getString("prenom_A"), rs.getString("sexe_A"), rs.getInt("force_A"), rs.getInt("endurance"), rs.getInt("agilite"), new Pays(rs.getString("nom_P")), id);
		}
		return null;


	}

	
	public void majAthlete(Athlete a)throws SQLException{
		PreparedStatement ps = this.laConnexion.prepareStatement("UPDATE ATHLETE SET nom_A = ?,prenom_A = ?, sexe_A=?,id_P=?,force_A=?,endurance=?, agilite = ?, id_E = ? where nom_A = ? and prenom_A=? and sexe_A=? and id_P=?" );
		ps.setString(1, a.getNom());
		ps.setString(2, a.getPrenom());
		ps.setString(3, a.getSexe());
		ps.setInt(4, a.getPays().getNum());
		ps.setInt(5, a.getForce());
		ps.setInt(6, a.getEndurance());
		ps.setInt(7, a.getAgilite());
		if(a.getEquipe() == null){
			ps.setInt(8, 0);
		}
		else{
			ps.setInt(8,a.getEquipe().getNum());
		}
		ps.setString(9, a.getNom());
		ps.setString(10, a.getPrenom());
		ps.setString(11, a.getSexe());
		ps.setInt(12, a.getPays().getNum());
		ps.executeUpdate();
    	}

	

	public void majUser(Utilisateur user)throws SQLException{
		PreparedStatement ps = this.laConnexion.prepareStatement("UPDATE USER SET role_u = ? where nom_U = ? and mdp_U = ?" );
		ps.setString(1, user.getRole());
		ps.setString(2, user.getNom());
		ps.setString(3, user.getMdp());
		ps.executeUpdate();
    	}

	public Pays avoirPaysParNom(String nom) throws SQLException{
		this.st = this.laConnexion.createStatement();
		String nombis = "'"+nom+"'";
		ResultSet rs = this.st.executeQuery("select id_P from PAYS where nom_P =" + nombis);
		Pays pays = null;
		if (rs.next()){
			Integer num = rs.getInt("id_P");
			pays = new Pays( nom,num);
		}
		return pays;
	}
	public int avoirAthleteParNom(String nom, String prenom, String sexe) throws SQLException{
		this.st = this.laConnexion.createStatement();
		String nombis = "'"+nom+"'";
		String prenomBis = "'"+prenom+"'";
		String sexebis = "'"+sexe+"'";
		ResultSet rs = this.st.executeQuery("select id_A,nom_A,prenom_A,sexe_A,id_P,force_A,endurance,agilite,id_E from ATHLETE where nom_A=" + nombis + " and prenom_A =" + prenomBis + "and sexe_A =" + sexebis);
		int numA = -1;
		if (rs.next()){
			numA = rs.getInt("id_A");
		}
		return numA;
	}

	public Equipe avoirEquipeParNom(String nom) throws SQLException{
		this.st = this.laConnexion.createStatement();
		String nombis = "'"+nom+"'";
		ResultSet rs = this.st.executeQuery("select id_E from EQUIPE where nom_E =" + nombis);
		Equipe equipe = null;
		if (rs.next()){
			Integer num = rs.getInt("id_E");
			equipe = new Equipe(nom,num);
		}
		return equipe;
	}

	public List<Utilisateur> user() throws SQLException{
		List<Utilisateur> listeUser = new ArrayList<>();
		this.st = this.laConnexion.createStatement();
		ResultSet rs = this.st.executeQuery("select nom_U, mdp_U, role_U from USER");
		while (rs.next()){
			Utilisateur user = new Utilisateur(rs.getString("nom_U"), rs.getString("mdp_U"), rs.getString("role_U"));
			listeUser.add(user);
		}
		return listeUser;
	}

////////////////////////////////////// ORGANISATEUR //////////////////////////////////////

public void lancerEpreuve(Epreuve e)throws SQLException,PasDeParticipantException{
	String nomBis = "'"+e.getNom()+"'";
	PreparedStatement ps = this.laConnexion.prepareStatement("SELECT id_A,nom_A,prenom_A,sexe_A,id_P,force_A,endurance,agilite,id_E from ATHLETE natural join PARTICIPER natural join EPREUVE where nom =" + nomBis);
	ResultSet rs = ps.executeQuery();
	Epreuve ep = new Epreuve(e.getNom(), e.getCategorie(), e.getSport(), e.getNum());
		while (rs.next()){
			PreparedStatement psP = this.laConnexion.prepareStatement("SELECT nom_P,id_P from PAYS where id_P = " + rs.getInt("id_P"));
			ResultSet rsP = psP.executeQuery();

			PreparedStatement psE = this.laConnexion.prepareStatement("SELECT nom_E,id_E from EQUIPE where id_E = " + rs.getInt("id_E"));
			ResultSet rsE = psE.executeQuery();

			Equipe equipe = null;
			rsP.next();

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
		if (ep.getParticipants().size()==0){
			throw new PasDeParticipantException();
		}
		List<Map<Athlete,Double>> classement = ep.classementPoints();
		Map<Athlete,Double> numero1 = classement.get(0);
            	Map<Athlete,Double> numero2 = classement.get(1);
            	Map<Athlete,Double> numero3 = classement.get(2);
            	Set<Athlete> num1 = numero1.keySet();
            	Set<Athlete> num2 = numero2.keySet();
            	Set<Athlete> num3 = numero3.keySet();
            	for (Athlete a : num1){
                	Pays pays1 = a.getPays();
			
			PreparedStatement ps1 = this.laConnexion.prepareStatement("UPDATE PAYS SET nb_Or = ? where PAYS.id_P = ? and PAYS.nom_P = ?" );
			ps1.setInt(1, pays1.getNbOr() + 1);
			pays1.setNbOr(pays1.getNbOr()+1);
			ps1.setInt(2, pays1.getNum());
			ps1.setString(3, pays1.getNom());
			ps1.executeUpdate();
			
		}
            	for (Athlete a : num2){
                	Pays pays2 = a.getPays();
			PreparedStatement ps2 = this.laConnexion.prepareStatement("UPDATE PAYS SET nb_Argent = ? where PAYS.id_P = ? and PAYS.nom_P = ?" );
			ps2.setInt(1, pays2.getNbArgent() + 1);
			pays2.setNbArgent(pays2.getNbArgent()+1);
			ps2.setInt(2, pays2.getNum());
			ps2.setString(3, pays2.getNom());
			ps2.executeUpdate();
				
            	}
            	for (Athlete a : num3){
                	Pays pays3 = a.getPays();
			PreparedStatement ps3 = this.laConnexion.prepareStatement("UPDATE PAYS SET nb_Bronze = ? where PAYS.id_P = ? and PAYS.nom_P = ?" );
			ps3.setInt(1, pays3.getNbBronze() + 1);
			pays3.setNbBronze(pays3.getNbBronze()+1);
			ps3.setInt(2, pays3.getNum());
			ps3.setString(3, pays3.getNom());
			ps3.executeUpdate();
            	}
		 
}


public Epreuve avoirEpreuveParNom(String nom, String categorie, Sport sport) throws SQLException{
	this.st = this.laConnexion.createStatement();
	String nombis = "'"+nom+"'";
	String categoriebis = "'"+categorie+"'";
	ResultSet rs = this.st.executeQuery("select id_Ep from EPREUVE where nom =" + nombis+" and categorie =" + categoriebis);
	Epreuve epreuve = null;
	if (rs.next()){
		Integer num = rs.getInt("id_Ep");
		epreuve = new Epreuve(nom,categorie,sport,num);
	}
	return epreuve;
}

}


