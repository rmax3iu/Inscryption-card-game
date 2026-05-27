package logic.cardLogic.powers;

public abstract class Power
{
    private String m_name;

    public Power()
    {
        m_name = "Aucun";
    }

    public Power(String name)
    {
        m_name = name;
    }

    public String getName()
    {
        return m_name;
    }
}