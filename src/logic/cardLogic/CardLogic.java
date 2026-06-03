package logic.cardLogic;

import logic.cardLogic.powers.Power;

import java.util.Optional;

public class CardLogic {
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

    public void takeDamage(int damage) {
        int surplus = 0;
        if (damage > m_hp) {
            surplus = m_hp - damage; // négatif = surplus
            m_hp = 0;
        } else {
            m_hp -= damage;
        }
    }

    public Optional<Power> getPower(){
        return Optional.empty();
    }

    public boolean hasPower() {
        return false;
    }

    public boolean canBeSacrify(){
        return false;
    }

    public Optional<CardLogic> sacrify(){
        return Optional.of(new CardLogic(m_name,m_hp));
    }

    public int attack(CardLogic card) {
        return 0;
    }

    public boolean isDead() {
        return m_hp <= 0;
    }

}
