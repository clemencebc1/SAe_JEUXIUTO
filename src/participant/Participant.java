package participant;
import sport.*;

public interface Participant {
    /** fait participer un althète ou une equipe a une epreuve */
    public abstract double participer(Epreuve e);
}