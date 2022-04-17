import java.awt.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.io.*;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerException;
import javax.xml.transform.Transformer;


import org.w3c.dom.*;
import org.xml.sax.SAXException;

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
                {"1" ,"Lamp", "189", "3132", "567"},
                {"2", "Mobile Phone", "18439", "3", "564127"},
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
             * @params event
             * Data load from file event processing
             * */
            public void actionPerformed (ActionEvent event)
            {
                Document doc = null;
                try {
                    DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                    doc = dBuilder.parse(new File("device.xml"));
                    doc.getDocumentElement().normalize();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                } catch (SAXException e) {
                    e.printStackTrace();
                }catch (IOException e) {
                    e.printStackTrace();
                }
                NodeList nldevis = doc.getElementsByTagName("device");
                model.removeRow(model.getRowCount()-1);
                for (int i = 0; i < nldevis.getLength()-1; i++) {
                    Node elem = nldevis.item(i);
                    NamedNodeMap attrs = elem.getAttributes();
                    String id = String.valueOf(model.getRowCount());
                    String name = attrs.getNamedItem("name").getNodeValue();
                    String price = attrs.getNamedItem("price").getNodeValue();
                    String CountSale = attrs.getNamedItem("CountSale").getNodeValue();
                    String TotalSumm = attrs.getNamedItem("TotalSumm").getNodeValue();
                    model.addRow(new String[]{id, name, price, CountSale, TotalSumm});
                }
                model.addRow(new String[]{" "," ", " ", " ", " "});
            }
        });

        neww.addActionListener(new ActionListener(){
            /**
             * @params event
             * function save new data to file
             * */
            public void actionPerformed (ActionEvent event) {
                Document doc = null;
                try {
                    DocumentBuilder builder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
                    doc = builder.newDocument();
                } catch (ParserConfigurationException e) {
                    e.printStackTrace();
                }
                Node devislist = doc.createElement("devicelist");
                doc.appendChild(devislist);
                for (int i = 1; i < model.getRowCount(); i++) {
                    Element device = doc.createElement("device");
                    devislist.appendChild(device);
                    device.setAttribute("name", (String) model.getValueAt(i, 1));
                    device.setAttribute("price", (String) model.getValueAt(i, 2));
                    device.setAttribute("CountSale", (String) model.getValueAt(i, 3));
                    device.setAttribute("TotalSumm", (String) model.getValueAt(i, 4));
                }
                try {
                    Transformer trans = TransformerFactory.newInstance().newTransformer();
                    java.io.FileWriter fw = new FileWriter("device.xml");
                    trans.transform(new DOMSource(doc), new StreamResult(fw));
                } catch (TransformerConfigurationException e) {
                    e.printStackTrace();
                } catch (TransformerException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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
