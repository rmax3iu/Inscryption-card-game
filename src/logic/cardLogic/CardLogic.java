package logic.cardLogic;

public abstract class CardLogic {
    private final String m_name;
    private int m_hp;

    public CardLogic(String name, int hp) {
        m_name = name;
        m_hp   = hp;
    }

    public String getName() {
        return m_name;
    }

    public int getHp() {
        return m_hp;
    }

    public int takeDamage(int damage) {
        int surplus = 0;
        if (damage > m_hp) {
            surplus = m_hp - damage; // négatif = surplus
            m_hp = 0;
        } else {
            m_hp -= damage;
        }
        return surplus;
    }

    public boolean isDead() {
        return m_hp <= 0;
    }

    public CardLogic copie(){
        return null;
    }
}
