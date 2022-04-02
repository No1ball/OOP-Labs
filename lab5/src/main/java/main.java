import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
/** @author Alexey */
class ManagerHelper {
    private JFrame ManagHelper;
    private JTable info;
    private JToolBar toolBar;
    private JScrollBar Scroll;
    private JButton file;
    private Label statusLabel;
    private JButton Edit;
    private JButton View;
    private JButton Help;
    private JPanel panelright;
    private JPanel panelleft;
    private JList <String> dataObjList;
    private TextField text;
    private JPanel rightSouth;
    private JButton newdata;
    private JButton loadData;
    private JButton delete;
    private DefaultTableModel model;
    private JLabel label;
    private JRadioButton[] radioButton;


    /**
     *  Функция создания графического интерфейса
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
        String [] columns = {"Name", "Price", "Count Sale", "Total Summ"};
        String [][] data = {{"Name", "Price", "Count Sale", "Total Summ"},
                {"Lamp", "189", "3", "567"},
                {"Mobile Phone", "189", "3", "567"},
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

        Scroll.addAdjustmentListener(new AdjustmentListener (){
            /**
             * Обработка события прокрутки скролла
             * */
            public void adjustmentValueChanged(AdjustmentEvent event){
                statusLabel.setText(Integer.toString(event.getValue()));
            }
        });
        delete = new JButton("Delete");
        delete.addActionListener (new ActionListener()
        {
            /**
             * Обработка события нажатия кнопки для удаления
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
        String [] dataObj = {"Clients", "Contract", "Device", "First Contact"};
        dataObjList = new JList<String>(dataObj);
        dataObjList.setFixedCellHeight(40);
        dataObjList.setBackground(Color.lightGray);
        dataObjList.addMouseListener(new MouseAdapter() {
            /**
             * Обработка события клика мышью
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
        loadData = new JButton("Load Data");
        newdata = new JButton("New");
        rightSouth.add(loadData, BorderLayout.WEST);
        rightSouth.add(newdata, BorderLayout.EAST);
        panelright.add(rightSouth, BorderLayout.SOUTH);
        newdata.addActionListener (new ActionListener()
        {
            /**
             * Обработка события нажатия на кнопку загрузки данных из файла
             * */
            public void actionPerformed (ActionEvent event)
            {
                JOptionPane.showInputDialog (ManagHelper, "Укажите название файла: ");
            }
        });
        ManagHelper.add(panelright, BorderLayout.WEST);


        ManagHelper.setVisible(true);
    }
}
public class main {

    public static void main(String[] args) {
        new ManagerHelper().show();
    }
}
