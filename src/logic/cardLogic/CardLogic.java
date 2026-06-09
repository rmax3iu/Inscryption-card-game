package logic.cardLogic;

import logic.cardLogic.powers.Power;

import java.util.Optional;

public class CardLogic
{
    private final String m_name;
    private int m_hp;

    public CardLogic(String name, int hp)
    {
        m_name = name;
        m_hp   = hp;
    }

    public CardLogic(CardLogic card)
    {
        m_name = card.getName();
        m_hp = card.getHp();
    }

    public String getName()
    {
        return m_name;
    }

    protected void setHp(int hp)
    {
        m_hp = hp;
    }

    public int getHp()
    {
        return m_hp;
    }

    public void takeDamage(int damage)
    {
        if (damage > m_hp)
        {
            m_hp = 0;
        }
        else
        {
            m_hp -= damage;
        }
    }

    public CardLogic copy()
    {
        return new CardLogic(this);
    }

    public void kill()
    {
    }

    public Optional<Power> getPower()
    {
        return Optional.empty();
    }

    public boolean hasPower()
    {
        return false;
    }

    public boolean canBeSacrify()
    {
        return false;
    }

    public Optional<CardLogic> sacrify()
    {
        return Optional.of(new CardLogic(m_name, m_hp));
    }

    public int attack(Optional<CardLogic> card)
    {
        return 0;
    }

    public boolean isDead()
    {
        return m_hp <= 0;
    }

    @Override
    public String toString()
    {
        return "Carte { Nom : " + m_name + ", PV : " + m_hp + " }";
    }
}