package logic.cardLogic;

public abstract class CardLogic {
    private final String m_name;        //Le nom de la carte
    private int m_hp;                   //Les points de vie de la carte

    public CardLogic(String name, int hp) {
        m_name = name;
        m_hp   = hp;
    }

    //Renvoie son nom
    public String getName() {
        return m_name;
    }

    //Renvoie ses pv
    public int getHp() {
        return m_hp;
    }

    //On soustrait les dégâts reçus au pv et si les dégâts sont supérieurs on met les pv à 0 et on renvoie le surplus
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

    //Renvoie un boolean qui dit si la carte est morte (qu'elle a 0 pv ou moins)
    public boolean isDead() {
        return m_hp <= 0;
    }
}
