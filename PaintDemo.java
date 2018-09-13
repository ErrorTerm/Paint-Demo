package paintdemo;

import org.powerbot.script.PaintListener;
import org.powerbot.script.PollingScript;
import org.powerbot.script.Script;
import org.powerbot.script.rt4.ClientContext;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

@Script.Manifest(name = "Paint Demo", description = "Let's make an interactive paint!", properties = "author=Error Term; client=4;")
public class PaintDemo extends PollingScript<ClientContext> implements PaintListener, MouseListener, KeyListener
{
    private int paintTab = 0;
    private int foodId = -1;
    private String newFoodId = "";

    private int dropDownOne = 0;
    private int dropDownTwo = 0;

    private final Color SELECTED_TAB = new Color(105, 105, 105, 255);
    private final Color UNSELECTED_TAB = new Color(48, 48, 48, 255);
    private final Color UNSELECTED_DROPDOWN = new Color(180, 35, 54, 255);
    private final Color SELECTED_DROPDOWN = new Color(0, 180, 14, 255);
    private final Color TAB_SEPARATOR = new Color(180, 180, 180, 255);

    @Override
    public void repaint(Graphics graphics)
    {
        Graphics2D g = (Graphics2D) graphics;
        // Set the background color.
        g.setColor(new Color(0, 0, 0, 255));
        g.fillRect(552, 209, 180, 250);

        // Set the tab colors.
        g.setColor(UNSELECTED_TAB);
        g.fillRect(552, 209, 180, 30);
        g.setColor(SELECTED_TAB);
        g.fillRect(552 + 60 * paintTab, 209, 60, 30);

        // Lines in between tabs.
        g.setColor(TAB_SEPARATOR);
        g.fillRect(612, 209, 1, 30);
        g.fillRect(672, 209, 1, 30);

        if (paintTab == 0)
        {
            g.drawString("Food: " + foodId, 560, 260);
        }
        else if (paintTab == 1)
        {
            g.setColor(UNSELECTED_DROPDOWN);
            g.fillRect(565, 251, 72, 90);

            if (dropDownOne == 1)
                g.fillRect(647, 251, 72, 90);

            g.setColor(SELECTED_DROPDOWN);
            g.fillRect(565, 251 + 30 * dropDownOne, 72, 30);

            if (dropDownOne == 1)
                g.fillRect(647, 251 + 30 * dropDownTwo, 72, 30);

            g.setColor(TAB_SEPARATOR);
            g.fillRect(565, 281, 72, 1);
            g.fillRect(565, 311, 72, 1);
            if (dropDownOne == 1)
            {
                g.fillRect(647, 311, 72, 1);
                g.fillRect(647, 281, 72, 1);
            }
        }
        else if (paintTab == 2)
            g.drawString("Tab Three", 560, 260);
    }

    @Override
    public void poll() {

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int x = e.getX(), y = e.getY();
        if (x >= 552 && x <= 732 && y >= 209 && y <= 239)
            paintTab = (x - 552)/60;
        else if (paintTab == 1)
        {
            if (y >= 251 && y <= 341)
            {
                if (x >= 565 && x <= 637)
                    dropDownOne = 2 - (341 - y)/30;
                else if (dropDownOne == 1 && x >= 647 && x <= 714)
                    dropDownTwo = 2 - (341 - y)/30;
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (paintTab == 0)
        {
            if (e.getKeyChar() == '\n')
            {
                foodId = Integer.parseInt(newFoodId);
                newFoodId = "";
            }
            else if (Character.isDigit(e.getKeyChar()))
            {
                newFoodId += e.getKeyChar();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
