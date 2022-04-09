import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
/** @author Alexey */

/**
 * Interface creating class
 * */
class ManagerHelper {
    /**Main interface window*/
    private JFrame ManagHelper;
    /**Main table window*/
    private JTable info;
    /**Instrument panel*/
    private JToolBar toolBar;
    /**Scrool*/
    private JScrollBar Scroll;
    /**Button in instrument panel - file */
    private JButton file;
    /** Label for scroll event*/
    private Label statusLabel;
    /** Button in instrument panel to edit*/
    private JButton Edit;
    /** Button in instrument panel to view*/
    private JButton View;
    /** Button in instrument panel to help*/
    private JButton Help;
    /**Secondary panel for right element*/
    private JPanel panelright;
    /**Secondary panel for left element*/
    private JPanel panelleft;
    /**List for object*/
    private JList <String> dataObjList;
    /**Field for search*/
    private TextField text;
    /**Third panel for right bottom elements*/
    private JPanel rightSouth;
    /**FileDialog for add new data*/
    private FileDialog newdata;
    /**FileDialog for load data*/
    private FileDialog loadData;
    /** Button for add new data*/
    private JButton neww;
    /**Button for load new data*/
    private JButton load;
    /**Button for delete data*/
    private JButton delete;
    /**Create table*/
    private DefaultTableModel model;
    /**Text field*/
    private JLabel label;
    /**Radio button for select data to delete*/
    private JRadioButton[] radioButton;

    /**
     * My exception class
     * */
    private class MyException extends Exception {
        public MyException() {
            super ("Вы не ввели параметры поиска");
        }
    }
    /**
     * Error processing function
     * @throws MyException
     * @throws NullPointerException
     * */
    private void checkName(TextField bName) throws MyException, NullPointerException {
        String sName = bName.getText();
        if (sName.contains("Тест")) throw new MyException();
        if (sName.length() == 0) throw new NullPointerException();
    }
    /**
     *  Interface creating function
     *  */
    public void show(){
        ManagHelper = new JFrame("Helper");
        ManagHelper.setLocation(430,150);
        ManagHelper.setSize(700,500);
        ManagHelper.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        file = new JButton("File");
        Edit = new JButton("Edit");
        View = new JButton("View");
        Help = new JButton("Help");

        toolBar = new JToolBar("Панель инструментов");
        toolBar.add(file);
        toolBar.add(Edit);
        toolBar.add(View);
        toolBar.add(Help);


        ManagHelper.setLayout(new BorderLayout());
        ManagHelper.add(toolBar, BorderLayout.NORTH);

        panelleft = new JPanel();
        panelleft.setLayout(new BorderLayout());
        String [] columns = {"№","Name", "Price", "Count Sale", "Total Summ"};
        String [][] data = {{"№" ,"Name", "Price", "Count Sale", "Total Summ"},
                {"1" ,"Lamp", "189", "3", "567"},
                {"2", "Mobile Phone", "189", "3", "567"},
                {"","", "", ""}};
        model = new DefaultTableModel(data, columns);
        info = new JTable(model);
        info.setRowHeight(20);

        JPanel lefteast = new JPanel();
        lefteast.setLayout(new BorderLayout());
        Scroll = new JScrollBar();

        lefteast.add(Scroll, BorderLayout.EAST);
        statusLabel = new Label();
        statusLabel.setAlignment(Label.CENTER);
        statusLabel.setSize(1,4);
        lefteast.add(statusLabel, BorderLayout.WEST);

        Scroll.addAdjustmentListener(new AdjustmentListener (){
            /**
             * Scrolling event processing
             * */
            public void adjustmentValueChanged(AdjustmentEvent event){
                statusLabel.setText(Integer.toString(event.getValue()));
            }
        });
        delete = new JButton("Delete");
        delete.addActionListener (new ActionListener()
        {
            /**
             * Delete button event processing
             * */
            public void actionPerformed (ActionEvent event)
            {
                JOptionPane.showMessageDialog(ManagHelper, "Подтвердите удаление");
            }
        });
        label = new JLabel("Select");
        JPanel df = new JPanel();
        df.setLayout(new BoxLayout(df,BoxLayout.Y_AXIS));
        radioButton = new JRadioButton[3];
        for(int i = 0; i < 3; i++){
            radioButton[i] = new JRadioButton("");
            df.add(radioButton[i]);
        }
        lefteast.add(df, BorderLayout.CENTER);
        lefteast.add(label, BorderLayout.NORTH);
        lefteast.add(delete, BorderLayout.SOUTH);
        panelleft.add(lefteast,BorderLayout.EAST);

        panelleft.add(info, BorderLayout.CENTER);


        ManagHelper.add(panelleft, BorderLayout.CENTER);

        panelright = new JPanel();
        panelright.setLayout(new BorderLayout());
        text = new TextField("");
        text.setPreferredSize(new Dimension(15,30));
        text.addActionListener(new ActionListener() {
            /**
             * Text error processing
             * */
            public void actionPerformed(ActionEvent arg0) {
                try {
                    checkName(text);
                }
                catch(NullPointerException ex) {
                    JOptionPane.showMessageDialog(ManagHelper, ex.toString());
                }
                catch(MyException myEx) {
                    JOptionPane.showMessageDialog(null, myEx.getMessage());
                }
            }
        });
        String [] dataObj = {"Clients", "Contract", "Device", "First Contact"};
        dataObjList = new JList<String>(dataObj);
        dataObjList.setFixedCellHeight(40);
        dataObjList.setBackground(Color.lightGray);
        dataObjList.addMouseListener(new MouseAdapter() {
            /**
             * Mouse click event processing
             * */
            public void mouseClicked(MouseEvent e) {
                int selected = dataObjList.locationToIndex(e.getPoint());
                switch(selected){
                    case 0:  JOptionPane.showMessageDialog(ManagHelper, "Выбран каталог: Clients");
                        break;
                    case 1:  JOptionPane.showMessageDialog(ManagHelper, "Выбран каталог: Contract");
                        break;
                    case 2:  JOptionPane.showMessageDialog(ManagHelper, "Выбран каталог: Device");
                        break;
                    case 3:  JOptionPane.showMessageDialog(ManagHelper, "Выбран каталог: First contract");
                        break;
                };
            }
        });
        panelright.add(dataObjList, BorderLayout.CENTER);
        panelright.add(text, BorderLayout.NORTH);

        rightSouth = new JPanel();
        rightSouth.setLayout(new BorderLayout());
        load = new JButton("Load Data");
        neww = new JButton("New");
        rightSouth.add(load, BorderLayout.WEST);
        rightSouth.add(neww, BorderLayout.EAST);
        panelright.add(rightSouth, BorderLayout.SOUTH);
        load.addActionListener (new ActionListener()
        {
            /**
             * Data load from file event processing
             * */
            public void actionPerformed (ActionEvent event)
            {
                loadData = new FileDialog(ManagHelper, "Load data", loadData.LOAD);
                loadData.setFile("*.txt");
                loadData.setVisible(true);
                String fileName = loadData.getDirectory() + loadData.getFile();
                if (fileName == null) return;
            }
        });

        neww.addActionListener(new ActionListener(){
            public void actionPerformed (ActionEvent event)
            {
                newdata = new FileDialog(ManagHelper, "Load data", loadData.SAVE);
                newdata.setFile("*.txt");
                newdata.setVisible(true);
                String fileName = loadData.getDirectory() + loadData.getFile();
                if (fileName == null) return;
            }
        });
        ManagHelper.add(panelright, BorderLayout.WEST);


        ManagHelper.setVisible(true);
    }
}

/**
 * Create interface, main class
 * */
public class main {
    /**
     * Call interface create function
     * */
    public static void main(String[] args) {
        new ManagerHelper().show();
    }
}
