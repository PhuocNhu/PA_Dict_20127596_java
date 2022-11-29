import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class HistoryUI extends JFrame implements ActionListener {
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    JTable table;
    DefaultTableModel tableModel;
    JButton cancel;
    HistoryUI() throws IOException {
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.add(new JLabel("text"));
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));
        JPanel Heading = new JPanel(new GridBagLayout());
        JLabel content = new JLabel("HISTORY");
        content.setFont(new Font("TimesRoman", Font.CENTER_BASELINE,30));
        Heading.add(content);
        JPanel Body = new JPanel();
//        String[] col = {"Slang Word","Date And Time"};
//        tableModel = new DefaultTableModel(col,0);
        HashMap<LocalDateTime, ArrayList<String>> h = FileInteract.readHistory("data/history.txt");
        ArrayList<String> slang = new ArrayList<>();
        ArrayList<String> datetime = new ArrayList<>();
//        for(Map.Entry<LocalDateTime, ArrayList<String>> i : h.entrySet()) {
//            ArrayList<String> value = i.getValue();
//            ArrayList<String> tmp = new ArrayList<>(Collections.nCopies(value.size(), i.getKey().format(formatter)));
//            datetime.addAll(tmp);
//            slang.addAll(value);
//        }
        DataInteract.sortHistory(h,slang,datetime);
        tableModel = new DefaultTableModel();
        tableModel.addColumn("Slang",slang.toArray());
        tableModel.addColumn("Date And Time", datetime.toArray());
        table = new JTable(tableModel);
        JScrollPane scroll = new JScrollPane(table);

        cancel = new JButton("Back to menu");
        cancel.addActionListener(this);

        panel.add(Heading);
        panel.add(Body);
        panel.add(scroll);
        panel.add(cancel);
        this.add(panel);
        this.pack();
        this.setLocationRelativeTo(null);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getActionCommand().equals("Back to menu")){
            this.dispose();
            new UI();
        }
    }
}
