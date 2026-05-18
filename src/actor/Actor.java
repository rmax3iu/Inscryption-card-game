package actor;

public abstract class Actor {
    private int m_bonnes;

    public Actor(int bonnes){
        m_bonnes = bonnes;
    }

    public abstract String getType();
}
