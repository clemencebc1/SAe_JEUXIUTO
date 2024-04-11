public class Natation extends Sport {
    public Natation(String nom, String milieu){
        super(nom, milieu);
        
    }
    @Override
    public int calculeRes(){
        List<Athlete> participants = this.epreuves.getParticipants();
        int points = 0;
        for (Athlete participant : participants){
            points += participant.getForce()*getAgilite()*getEndurance();
        }
        return points;
    }
}