import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.RoundRectangle2D;
import java.sql.Connection;
// import java.sql.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.DayOfWeek;

public class ReadPage implements MouseListener{
    int backClicks =0;
    JFrame readPageFrame;
    JPanel leftPanel,rightPanel;
    JLabel leftDateLabel,rightDatLabel,leftDayLabel,rightDayLabel,leftYearLabel,
           rightYearLabel,leftWeekLabel,rightWeekLabel,leftMonthLabel,
           rightMonthLabel,letDiaryText,rightDiaryText,pageBackLabel,
           pageForwardLabel,backArrowLabel,closeLabel;
    JButton perrviousPageButton,nextPageButton;
    LocalDate leftDate,rightDate;
    String leftWeekDay,rightWeekDay,leftFormattedDate,rightFormattedDate,leftMemory,rightMemory;
    JTextArea leftDiraryTextArea,rightDiaryTextArea;
    JScrollPane leftViewScrollPane,rightViewScrollPane;
    Connection con;

    ReadPage(int backClicks,Connection con){
        this.con=con;
        this.backClicks = backClicks;
        // ADDING FRAME
        readPageFrame = new JFrame();
        readPageFrame.setUndecorated(true); 
        readPageFrame.setLayout(null);
        readPageFrame.getContentPane().setBackground(new Color(60,91,111));
        readPageFrame.setSize(1000, 700);
        readPageFrame.setLocation(300, 50);
        readPageFrame.setShape(new RoundRectangle2D.Double(0, 0, readPageFrame.getWidth(), readPageFrame.getHeight(), 30, 30));

         //ADDING A NOTEBOOK SPIRAL
        ImageIcon getSpiralImage = new ImageIcon(ClassLoader.getSystemResource("Image/Spiral.png"));
        Image scaleSpiralImage = getSpiralImage.getImage().getScaledInstance(490,700, Image.SCALE_DEFAULT);
        ImageIcon spiralImage = new ImageIcon(scaleSpiralImage);
        JLabel spiralLabel = new JLabel(spiralImage);
        spiralLabel.setBounds(450,0,100,700);
        readPageFrame.add(spiralLabel);

        //ADDING BACK ARROW

        ImageIcon getLineImage = new ImageIcon(ClassLoader.getSystemResource("Image/line.png"));
        Image scaleLineImage = getLineImage.getImage().getScaledInstance(450,50, Image.SCALE_SMOOTH);
        ImageIcon lineImage = new ImageIcon(scaleLineImage);
        JLabel rightLineLabel = new JLabel(lineImage);
        rightLineLabel.setBounds(20, 30, 450, 50);
        JLabel leftLineLabel = new JLabel(lineImage);
        leftLineLabel.setBounds(20, 30, 450, 50);
        
        //LEFT PANEL VIEWING
        leftPanel = new JPanel();
        leftPanel.setBackground(new Color(248,199,148));
        leftPanel.setBounds(0, 0, 490,700);
        leftPanel.setLayout(null);
        readPageFrame.add(leftPanel);

        ImageIcon getBackArrowImage = new ImageIcon(ClassLoader.getSystemResource("Image/back_arrow.png"));
        backArrowLabel = new JLabel(getBackArrowImage);
        backArrowLabel.setBounds(5, 5, 25, 25);
        backArrowLabel.addMouseListener(this); // Add mouse listener to the image label
        leftPanel.add(backArrowLabel);

        //GETTING THE DATE;
        leftDate = LocalDate.now().minusDays(this.backClicks+1);
        DateTimeFormatter leftFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DayOfWeek leftdayOfWeek = leftDate.getDayOfWeek();
        leftWeekDay=leftdayOfWeek.toString();
        leftFormattedDate = leftDate.format(leftFormatter);
        String leftDay=leftFormattedDate.substring(0,2);
        String leftMonth=leftFormattedDate.substring(3,5);
        String leftYear=leftFormattedDate.substring(6, 10);
        
        leftPanel.add(leftLineLabel);

        leftMonthLabel = new JLabel(leftMonth);
        leftMonthLabel.setFont(new Font("Ariel", Font.BOLD, 20));
        leftMonthLabel.setBounds(420,10,40,20);
        leftPanel.add(leftMonthLabel);
        
        leftYearLabel = new JLabel(leftYear);
        leftYearLabel.setFont(new Font("Ariel", Font.BOLD, 15));
        leftYearLabel.setBounds(410,35,40,20);
        leftPanel.add(leftYearLabel);

        leftDayLabel = new JLabel(leftDay);
        leftDayLabel.setFont(new Font("Ariel", Font.BOLD, 35));
        leftDayLabel.setBounds(40,15,60,40);
        leftPanel.add(leftDayLabel);

        leftWeekLabel = new JLabel(leftWeekDay);
        leftWeekLabel.setFont(new Font("Ariel", Font.BOLD, 15));
        leftWeekLabel.setBounds(90,30,100,20);
        leftPanel.add(leftWeekLabel);




        //GETTING RIGHT SIDE DATA
        leftMemory=new Database().getMemory(leftFormattedDate,this.con);
        leftDiraryTextArea = new JTextArea();
        leftDiraryTextArea.setOpaque(false);
        leftDiraryTextArea.setText(leftMemory);
        leftDiraryTextArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        leftDiraryTextArea.setEditable(false);
        leftDiraryTextArea.setLineWrap(true);
        leftDiraryTextArea.setWrapStyleWord(true);

        leftViewScrollPane = new JScrollPane(leftDiraryTextArea);
        leftViewScrollPane.getViewport().setOpaque(false);
        leftViewScrollPane.setBackground(new Color(248,199,148));
        leftViewScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        leftViewScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        leftViewScrollPane.setBorder(BorderFactory.createLineBorder(new Color(248,199,148), 2));
        leftViewScrollPane.setBounds(35, 74, 430, 580);
        leftPanel.add(leftViewScrollPane);

        // LEFT ARROW
        ImageIcon getPageBackImage = new ImageIcon(ClassLoader.getSystemResource("Image/PageBack.png"));
        Image scalePageBackImage = getPageBackImage.getImage().getScaledInstance(30,70, Image.SCALE_SMOOTH);
        ImageIcon leftTextImage = new ImageIcon(scalePageBackImage);
        pageBackLabel  = new JLabel(leftTextImage);
        pageBackLabel .setBounds(0, 50, 30, 650);
        pageBackLabel .addMouseListener(this); // Add mouse listener to the image label
        leftPanel.add(pageBackLabel );


        
        // RIGHT PANEL VIEWING
        rightPanel = new JPanel();
        rightPanel.setBackground(new Color(248,199,148));
        rightPanel.setBounds(510, 0, 490,700);
        rightPanel.setLayout(null);
        readPageFrame.add(rightPanel);
        rightPanel.add(rightLineLabel);

        ImageIcon closeImage = new ImageIcon(ClassLoader.getSystemResource("Image/close.png"));
        Image scaleCloseImage= closeImage.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        closeImage = new ImageIcon(scaleCloseImage);
        closeLabel = new JLabel(closeImage);
        closeLabel.setBounds(450, 5, 25, 25);
        closeLabel.addMouseListener(this); // Add mouse listener to the image label
        rightPanel.add(closeLabel);

        //GETTING THE DATE;
        rightDate = LocalDate.now().minusDays(this.backClicks);
        DateTimeFormatter rightFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DayOfWeek rightdayOfWeek = rightDate.getDayOfWeek();
        rightWeekDay=rightdayOfWeek.toString();
        rightFormattedDate = rightDate.format(rightFormatter);
        String rightDay=rightFormattedDate.substring(0,2);
        String rightMonth=rightFormattedDate.substring(3,5);
        String rightYear=rightFormattedDate.substring(6, 10);
        

         //DISPLAYING THE DATE
        rightMonthLabel = new JLabel(rightMonth);
        rightMonthLabel.setFont(new Font("Ariel", Font.BOLD, 20));
        rightMonthLabel.setBounds(400,10,40,20);
        rightPanel.add(rightMonthLabel);

        rightYearLabel = new JLabel(rightYear);
        rightYearLabel.setFont(new Font("Ariel", Font.BOLD, 15));
        rightYearLabel.setBounds(390,35,40,20);
        rightPanel.add(rightYearLabel);

        rightDayLabel = new JLabel(rightDay);
        rightDayLabel.setFont(new Font("Ariel", Font.BOLD, 35));
        rightDayLabel.setBounds(30,15,60,40);
        rightPanel.add(rightDayLabel);

        rightWeekLabel = new JLabel(rightWeekDay);
        rightWeekLabel.setFont(new Font("Ariel", Font.BOLD, 15));
        rightWeekLabel.setBounds(80,30,100,20);
        rightPanel.add(rightWeekLabel);

        //GETTING THE TEXT
        rightMemory=new Database().getMemory(rightFormattedDate,con);
        rightDiaryTextArea = new JTextArea();
        rightDiaryTextArea.setOpaque(false);
        rightDiaryTextArea.setText(rightMemory);
        rightDiaryTextArea.setFont(new Font("Comic Sans MS", Font.PLAIN, 25));
        rightDiaryTextArea.setEditable(false);
        rightDiaryTextArea.setLineWrap(true);
        rightDiaryTextArea.setWrapStyleWord(true);
       

        rightViewScrollPane = new JScrollPane(rightDiaryTextArea);
        rightViewScrollPane.getViewport().setOpaque(false);
        rightViewScrollPane.setBackground(new Color(248,199,148));
        rightViewScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
        rightViewScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        rightViewScrollPane.setBorder(BorderFactory.createLineBorder(new Color(248,199,148), 2));
        rightViewScrollPane.setBounds(35, 74, 430, 580);
        rightPanel.add(rightViewScrollPane);

        // ADDING FORWARD ARROW
        // LEFT ARROW
        ImageIcon getPageForwardImage = new ImageIcon(ClassLoader.getSystemResource("Image/PageForward.png"));
        Image scalePageForwardImage = getPageForwardImage.getImage().getScaledInstance(30,70, Image.SCALE_SMOOTH);
        ImageIcon rightTextImage = new ImageIcon(scalePageForwardImage);
        pageForwardLabel  = new JLabel(rightTextImage);
        pageForwardLabel.setBounds(460, 50, 30, 650);
        pageForwardLabel.addMouseListener(this); // Add mouse listener to the image label
        rightPanel.add(pageForwardLabel);


        readPageFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        readPageFrame.setVisible(true);

    }


    public void mouseClicked(MouseEvent e) {
        if(e.getSource().equals(pageForwardLabel)){
            if(this.backClicks == 0){
                readPageFrame.setVisible(false);
                new WritePage(con);
            }
            else{
                readPageFrame.setVisible(false);
                new ReadPage(this.backClicks-2,con);
            }
        }
        else if(e.getSource().equals(pageBackLabel)){
            // System.out.println("LEFT BTN CLICK");
            readPageFrame.dispose();
            new ReadPage(this.backClicks+2,con);
        }
        else if(e.getSource().equals(backArrowLabel)){
            new Database().closeConnection(con);
            readPageFrame.dispose();
            new Options();
        }
        else if (e.getSource().equals(closeLabel)) {
            readPageFrame.dispose();
            new Back(con);
        }
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
        // new ReadPage(0,);
    }
}
