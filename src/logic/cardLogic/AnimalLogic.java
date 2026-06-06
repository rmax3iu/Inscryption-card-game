package logic.cardLogic;

import logic.cardLogic.powers.ManyLife;
import logic.cardLogic.powers.Power;

import java.util.Optional;

public class AnimalLogic extends CardLogic {
    private final int m_attack;
    private final SummonCostLogic m_summonCostLogic;

    // On utilise un seul pouvoir au lieu d'une liste pour des raisons de cohérence d'affichage graphique de la carte
    // Si une carte aurait pu avoir les 6 pouvoirs la carte aurait du faire 11 lignes
    private Optional<Power> m_power;
    
    public AnimalLogic(String name, int hp, int attack, SummonCostLogic cost) {
        super(name, hp);
        m_attack = attack;
        m_summonCostLogic = cost;
        m_power = Optional.empty();
    }

    public AnimalLogic(String name, int hp, int attack, SummonCostLogic cost, Power power) {
        super(name, hp);
        m_attack = attack;
        m_summonCostLogic = cost;
        m_power = Optional.of(power);
    }

    public AnimalLogic(AnimalLogic animal)
    {
        super(animal.getName(), animal.getHp());
        m_attack = animal.getAttack();
        // On n'effectue pas de copie profonde du coût puisque les valeurs de sang et os ne changent jamais
        m_summonCostLogic = animal.getSummonCost();
        Optional<Power> power = animal.getPower();
        if(power.isPresent())
        {
            m_power = Optional.of(power.get().copy());
        }
        else
        {
            m_power = Optional.empty();
        }
    }

    public int getAttack() {
        return m_attack;
    }

    public SummonCostLogic getSummonCost() {
        return m_summonCostLogic;
    }

    @Override
    public AnimalLogic copy() {
        return new AnimalLogic(this);
    }

    @Override
    public Optional<Power> getPower() {
        return m_power;
    }

    @Override
    public Optional<CardLogic> sacrify()
    {
        // La carte reste en vie sur le plateau avec ses PV actuels si elle possède un pouvoir empêchant sa mort
        if (m_power.isPresent() && !m_power.get().canDeath())
        {
            return Optional.of(this);
        }
        return Optional.empty();
    }

    @Override
    public boolean canBeSacrify(){
        return true;
    }

    @Override
    public boolean hasPower() {
        return m_power.isPresent();
    }

    public void setPower(Power power) {
        m_power = Optional.of(power);
    }

    // Gère l'attaque contre une cible ou renvoie les dégâts directs si l'emplacement adverse est vide
    @Override
    public int attack(Optional<CardLogic> target)
    {
        if (target.isPresent())
        {
            Optional<Power> targetPower = target.get().getPower();
            // Le pouvoir Contact Mortel élimine instantanément les animaux mais n'affecte pas les obstacles
            if(m_power.isPresent() && m_power.get().killsOnHit() && target.get().canBeSacrify())
            {
                target.get().kill();
            }
            else
            {
                int degat = getAttack();
                if(targetPower.isPresent())
                {
                    degat -= targetPower.get().attackModifierOnFacing();
                }
                target.get().takeDamage(degat);
            }
            if(targetPower.isPresent())
            {
                targetPower.get().onDamageReceived(this);
            }
            return 0;
        }
        return getAttack();
    }

    @Override
    public void kill() {
        super.setHp(0);
    }

    @Override
    public String toString()
    {
        String nomPouvoir;
        if (m_power.isPresent())
        {
            nomPouvoir = m_power.get().getName();
        }
        else
        {
            nomPouvoir = "Aucun";
        }
        return "Animal : " + getName() + " | Attaque : " + m_attack + " | PV : " + getHp() + " | Pouvoir : " + nomPouvoir + " | Coût : " + m_summonCostLogic;
    }
}