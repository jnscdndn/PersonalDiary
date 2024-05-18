import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import javax.swing.*;

public class Front implements MouseListener {
    JFrame frontFrame;
    JLabel openDiary;
    Front() {
        // HALF FRAME
        frontFrame = new JFrame();
        frontFrame.setUndecorated(true);
        frontFrame.setLayout(null);
        frontFrame.getContentPane().setBackground(new Color(60, 91, 111));
        frontFrame.setSize(510, 700);
        frontFrame.setShape(new RoundRectangle2D.Double(0, 0, 510, 700, 0, 30));
        frontFrame.setLocation(500, 50);

        // OPEN BUTTON USING IMAGE
        openDiary = new JLabel("Open");
        openDiary.setCursor(new Cursor(Cursor.HAND_CURSOR));
        openDiary.setFont(new Font("Comic Sans MS", Font.BOLD, 30));
        openDiary.setForeground(new Color(212, 174, 126));
        openDiary.addMouseListener(this);
        openDiary.setBounds(130, 520, 100, 50);
        frontFrame.add(openDiary);

        // Add images after the "Open" label to ensure it is on top
        ImageIcon getSpiralImage = new ImageIcon(ClassLoader.getSystemResource("Image/frontSpiral.png"));
        Image scaleSpiralImage = getSpiralImage.getImage().getScaledInstance(270, 700, Image.SCALE_DEFAULT);
        ImageIcon spiralImage = new ImageIcon(scaleSpiralImage);
        JLabel spiralLabel = new JLabel(spiralImage);
        spiralLabel.setBounds(0, 0, 270, 700);
        frontFrame.add(spiralLabel);

        ImageIcon getMainImage = new ImageIcon(ClassLoader.getSystemResource("Image/frontPage.jpeg"));
        Image scaleMainImage = getMainImage.getImage().getScaledInstance(510, 700, Image.SCALE_DEFAULT);
        ImageIcon mainImage = new ImageIcon(scaleMainImage);
        JLabel mainLabel = new JLabel(mainImage);
        mainLabel.setBounds(10, 0, 510, 700);
        frontFrame.add(mainLabel);

        frontFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frontFrame.setVisible(true);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        frontFrame.setVisible(false);
        new Login();
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        openDiary.setCursor(new Cursor(Cursor.HAND_CURSOR));
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    public static void main(String[] args) {
        new Front();
    }
}
