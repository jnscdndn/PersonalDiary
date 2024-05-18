import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
class Page implements ActionListener,MouseListener{
    JFrame frame;
    JPanel leftPanel, rightPanel;
    JLabel revisitQuotLabel, newPageQuotLabel;
    JButton writeButton, readButton;
    Page(){
        // ADDING FRAME
        frame = new JFrame();
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(60,91,111));
        frame.setSize(1000, 700);
        frame.setLocation(300, 50);
        

        //ADDING A NOTEBOOK SPIRAL
        ImageIcon getSpiralImage = new ImageIcon(ClassLoader.getSystemResource("Spiral.png"));
        Image scaleSpiralImage = getSpiralImage.getImage().getScaledInstance(490,700, Image.SCALE_DEFAULT);
        ImageIcon spiralImage = new ImageIcon(scaleSpiralImage);
        JLabel spiralLabel = new JLabel(spiralImage);
        spiralLabel.setBounds(450,0,100,700);
        frame.add(spiralLabel);


        //LEFT PANEL FOR REVISIT YOUR MEMORIES
        leftPanel = new JPanel();
        leftPanel.setBackground(new Color(248,199,148));
        leftPanel.setBounds(0, 0, 490,700);
        leftPanel.setLayout(null);
        frame.add(leftPanel);
        
        
        ImageIcon getBackArrowImage = new ImageIcon(ClassLoader.getSystemResource("back_arrow.png"));
        Image scaleBackArrowImage = getBackArrowImage.getImage().getScaledInstance(30, 20, Image.SCALE_DEFAULT);
        ImageIcon backArrowImage = new ImageIcon(scaleBackArrowImage);
        JLabel backArrowLabel = new JLabel(backArrowImage);
        backArrowLabel.setBounds(20, 20, 30, 20);
        backArrowLabel.addMouseListener(this); // Add mouse listener to the image label
        leftPanel.add(backArrowLabel);




        

        // RIGHT PANEL FOR WRITING A NEW PAGE INTO DIARY
        rightPanel = new JPanel();
        rightPanel.setBackground(new Color(255, 224, 181));
        rightPanel.setBounds(510, 0, 490,700);
        rightPanel.setLayout(null);
        frame.add(rightPanel);
        
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
       
    }
    public void mouseClicked(MouseEvent e) {
       new Options();
    }
    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public static void main(String[] args) {
        new Page();
    }
}