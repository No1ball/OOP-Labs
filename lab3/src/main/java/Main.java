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
    /**Button for add new data*/
    private JButton newdata;
    /**Button for load data*/
    private JButton loadData;
    /**Button for delete data*/
    private JButton delete;
    /**Create table*/
    private DefaultTableModel model;
    /**Text field*/
    private JLabel label;
    /**Radio button for select data to delete*/
    private JRadioButton[] radioButton;

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
        String [][] data = {{"№","Name", "Price", "Count Sale", "Total Summ"},
                {"1","Lamp", "189", "3", "567"},
                {"2","Mobile Phone", "189", "3", "567"},
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
        statusLabel.setSize(15,15);
        lefteast.add(statusLabel, BorderLayout.WEST);


        delete = new JButton("Delete");

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
        String [] dataObj = {"Clients", "Contract", "Device", "First Contact"};
        dataObjList = new JList<String>(dataObj);
        dataObjList.setFixedCellHeight(40);
        dataObjList.setBackground(Color.lightGray);

        panelright.add(dataObjList, BorderLayout.CENTER);
        panelright.add(text, BorderLayout.NORTH);

        rightSouth = new JPanel();
        rightSouth.setLayout(new BorderLayout());
        loadData = new JButton("Load Data");
        newdata = new JButton("New");
        rightSouth.add(loadData, BorderLayout.WEST);
        rightSouth.add(newdata, BorderLayout.EAST);
        panelright.add(rightSouth, BorderLayout.SOUTH);

        ManagHelper.add(panelright, BorderLayout.WEST);


        ManagHelper.setVisible(true);
    }
}
/**
 * Create interface, main class
 * */
public class Main {
    /**
     * Call interface create function
     * */
    public static void main(String[] args) {
        new ManagerHelper().show();
    }
}
