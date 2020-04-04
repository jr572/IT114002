import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.WindowConstants;
import java.awt.Graphics2D;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class DrawGrid {
    private JFrame frame;

    public DrawGrid() {
        frame = new JFrame("DrawGrid");
        frame.setPreferredSize(frame.getSize());
        frame.add(new MultiDraw(frame.getSize()));
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);frame.pack();
        frame.setVisible(true);
    }

	public static void main (String[] args) {
        new DrawGrid();
    }

    public static class MultiDraw extends JPanel  implements ActionListener {
        int turn = 2; /* If it's going to be an even number, it will read it as one player's turn, if it is odd, it is the other player's turn*/
        int rows = 6;
        int cols = 7; int startX = 10; int startY = 10;
        int cellWidth = 40;
       

        Color[][] grid = new Color[rows][cols];

        public MultiDraw(Dimension dimension) {
            setSize(dimension);
            setPreferredSize(dimension);
            addActionListener(this);
            int x = 0;
            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                   grid[row][col] = new Color(255, 255, 255);
                }
            }
        }

        @Override
        public void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D)g;
            Dimension d = getSize();
            g2.setColor(new Color(0, 0, 0));
            g2.fillRect(0,0,d.width,d.height);
            startX = 0;
            startY = 0;

            for (int row = 0; row < grid.length; row++) {
                for (int col = 0; col < grid[0].length; col++) {
                  g2.setColor(grid[row][col]);
                  g2.fillOval(startX,  startY,  cellWidth,  cellWidth);
                  startX = startX + cellWidth;
                  
                }
                startX = 0;
                startY += cellWidth;
               
            }

            g2.setColor(new Color(255, 255, 255));
            if(turn%2==0) {
                g2.drawString("Red's Turn",400,20);
            }
            else {
            	g2.drawString("Yellow's Turn", 400, 20);
            }
          

        }

        public void ActionPressed(ActionEvent e) {

            int x = e.getX();
            int y = e.getY();
            int xLocation = x/cellWidth;
            int yLocation = y/cellWidth;
            
            if(turn%2==0) {
            	grid[yLocation][xLocation] = new Color(255, 0, 0);
            }
            else {
            	grid[yLocation][xLocation] = new Color(255, 255, 0);
            }
            System.out.println(x + " " + xSpot + " " + y + " " + ySpot);
            turn++;
            repaint();
        }

    }
}
