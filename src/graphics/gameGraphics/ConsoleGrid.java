package graphics.gameGraphics;

public class ConsoleGrid
{
    private char[][] m_grid;
    private int m_width;
    private int m_height;

    public ConsoleGrid(int width, int height)
    {
        m_grid = new char[width][height];
        m_width = width;
        m_height = height;
        clear();
    }

    public void writeString(String text, int x, int y) {

        for (int i = 0; i < text.length(); i++)
        {
            if (x + i < m_width)
            {
                m_grid[x + i][y] = text.charAt(i);
            }
        }
    }

    public void writeBox(int width, int height, int x, int y)
    {
        // Coins
        m_grid[x][y] = '+';
        m_grid[x + width - 1][y] = '+';
        m_grid[x][y + height - 1] = '+';
        m_grid[x + width - 1][y + height - 1] = '+';

        // Bordures horizontales
        for (int i = 1; i < width - 1; i++)
        {
            m_grid[x + i][y] = '-';
            m_grid[x + i][y + height - 1] = '-';
        }

        // Bordures verticales
        for (int j = 1; j < height - 1; j++)
        {
            m_grid[x][y + j] = '|';
            m_grid[x + width - 1][y + j] = '|';
        }
    }

    public void render()
    {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < m_height; j++)
        {
            for (int i = 0; i < m_width; i++)
            {
                sb.append(m_grid[i][j]);
            }
            sb.append('\n');
        }
        System.out.print(sb);
    }

    public void clear()
    {
        for (int i = 0; i < m_width; i++)
        {
            for (int j = 0; j < m_height; j++)
            {
                m_grid[i][j] = ' ';
            }
        }
    }
}