import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Random;

public class UI extends JFrame implements ActionListener {
    UI(){
        this.setVisible(true);
        this.setSize(750,750);
        this.setTitle("Dictionary Project");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        JPanel Heading = new JPanel(new GridBagLayout());
        JLabel dict = new JLabel("MENU");
        dict.setFont(new Font("TimesRoman", Font.CENTER_BASELINE,30));
        Heading.add(dict);
        Heading.setMaximumSize(new Dimension(300,100));
        JButton Search = new JButton("Search");
        Search.setMaximumSize(new Dimension(300,40));
        Search.setAlignmentX(.5f);
        Search.addActionListener(this);
        JButton History = new JButton("History");
        History.setMaximumSize(new Dimension(300,40));
        History.setAlignmentX(.5f);
        History.addActionListener(this);
        JButton Add = new JButton("Add Slang Word");
        Add.setMaximumSize(new Dimension(300,40));
        Add.setAlignmentX(.5f);
        Add.addActionListener(this);
        JButton Edit = new JButton("Edit Slang Word");
        Edit.setMaximumSize(new Dimension(300,40));
        Edit.setAlignmentX(.5f);
        Edit.addActionListener(this);
        JButton Delete = new JButton("Delete Slang Word");
        Delete.setMaximumSize(new Dimension(300,40));
        Delete.setAlignmentX(.5f);
        Delete.addActionListener(this);
        JButton Reset = new JButton("Reset Slang File");
        Reset.setMaximumSize(new Dimension(300,40));
        Reset.setAlignmentX(.5f);
        Reset.addActionListener(this);
        JButton Random = new JButton("Random Slang Word");
        Random.setMaximumSize(new Dimension(300,40));
        Random.setAlignmentX(.5f);
        Random.addActionListener(this);
        JButton Quiz1 = new JButton("Quiz 1");
        Quiz1.setMaximumSize(new Dimension(300,40));
        Quiz1.setAlignmentX(.5f);
        Quiz1.addActionListener(this);
        JButton Quiz2 = new JButton("Quiz 2");
        Quiz2.setMaximumSize(new Dimension(300,40));
        Quiz2.setAlignmentX(.5f);
        Quiz2.addActionListener(this);
        panel.add(Heading);panel.add(Search); panel.add(History); panel.add(Add); panel.add(Edit); panel.add(Delete);panel.add(Reset); panel.add(Random); panel.add(Quiz1); panel.add(Quiz2);
        this.add(panel);
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Search")){
            this.dispose();
            new SearchUI();
        }
        if(e.getActionCommand().equals("History")){
            this.dispose();
            try {
                new HistoryUI();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getActionCommand().equals("Add Slang Word")){
            this.dispose();
            new AddUI();
        }
        if(e.getActionCommand().equals("Edit Slang Word")){
            this.dispose();
            new EditUI();
        }
        if(e.getActionCommand().equals("Delete Slang Word")){
            this.dispose();
            new DelUI();
        }
        if(e.getActionCommand().equals("Reset Slang File")){
            try {
                new ResetUI();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getActionCommand().equals("Random Slang Word")){
            this.dispose();
            try {
                new RandomUI();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getActionCommand().equals("Quiz 1")){
            this.dispose();
            try {
                new Quiz1UI();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
        if(e.getActionCommand().equals("Quiz 2")){
            this.dispose();
            try {
                new Quiz2UI();
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        }
    }
}
