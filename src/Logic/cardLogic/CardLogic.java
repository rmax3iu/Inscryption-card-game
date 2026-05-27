package Logic.cardLogic;

public abstract class CardLogic {
    private String m_name;
    private int m_hp;
    private String m_power = "Aucun";

    public CardLogic(String name, int hp){
        m_hp = hp;
        m_name = name;
    }

    public String getName(){
        return m_name;
    }

    public int getHp(){
        return m_hp;
    }

    public void takeDamage(int damage){
        if(damage > getHp()){
            m_hp = 0;
        }else{
            m_hp -= damage;
        }
    }

    public boolean isDead(){
        return getHp() <= 0;
    }

    public String getPower()
    {
        return m_power;
    }

    public void setPower(String power)
    {
        m_power = power;
    }
}
