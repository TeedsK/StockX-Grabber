import java.awt.Dimension;

import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.event.*;
import java.net.URL;
import java.awt.*;
public class panel extends JFrame {
    static JPanel body = new JPanel();
    static StockXGrabber Main;
    public panel() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500,500));
        setMinimumSize(new Dimension(500,500));
        setMaximumSize(new Dimension(500,500));
        setSize(500,500);
        JPanel controlArea = new JPanel();
        controlArea.setLayout(new BorderLayout());
        body.setLayout(new BoxLayout(body, BoxLayout.Y_AXIS));
        controlArea.add(body, BorderLayout.CENTER);
        JPanel top = new JPanel();
        top.setLayout(new BoxLayout(top, BoxLayout.X_AXIS));
        JTextField input = new JTextField();
        JButton button = new JButton("Search");
        top.add(input);
        top.add(button);
        button.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                Thread t = new Thread() {
                    public void run() {
                        body.removeAll();
                        body.revalidate();
                        body.repaint();
                        Main.getItems(input.getText());
                    }
                }; t.start();
                
            }
        });
        controlArea.add(top, BorderLayout.NORTH);
        add(controlArea);
        pack();
        setVisible(true);
    }   
    public static void main(String[] args) {
        try {
            new panel();
            Main = new StockXGrabber();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
    public static void addSearchItem(StockXItem item) {
        JPanel part = new JPanel();
        part.setMaximumSize(new Dimension(Integer.MAX_VALUE, 45));
        part.setMinimumSize(new Dimension(0, 45));
        part.setPreferredSize(new Dimension(100,45));
        part.setLayout(new BoxLayout(part, BoxLayout.X_AXIS));
        JLabel j = new JLabel(item.getName());
        try {
            String product_img_revamped = item.getImageUrl().substring(0, item.getImageUrl().indexOf("?"));
            URL imageURL = new URL(product_img_revamped + "?fit=clip&bg=FFFFFF&w=300&h=214&auto=format&trim=color");
            
            Image image = ImageIO.read(imageURL);
            Image p2 = image.getScaledInstance(55,42, Image.SCALE_SMOOTH);
            ImageIcon p3 = new ImageIcon(p2);
            JLabel img = new JLabel();
            img.setIcon(p3);
            part.add(img);
            part.add(Box.createRigidArea(new Dimension(10,10)));
            part.add(j);
            part.add(Box.createHorizontalGlue());
            body.add(part);
            body.revalidate();
            body.repaint();
        } catch(Exception e) {
            e.printStackTrace();
        }
        
    }
}
