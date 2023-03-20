import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class invoiceSwing extends JFrame {
    public invoiceSwing(){
        super("Invoice Manager");
        // background elements
        ArrayList<lineItem> redHawk= new ArrayList<lineItem>();

        //UI elements
        JPanel buttonPanel = new JPanel();
        JPanel invoiceTextPanel = new JPanel();
        JPanel renderPanel = new JPanel();
        JPanel addressPanel = new JPanel();
        JPanel linePanel = new JPanel();
        JButton clear = new JButton("Clear Options");
        JButton additionalLine = new JButton("Add Line");
        JButton quit = new JButton("Quit");
        JButton render = new JButton("Render Invoice");
        JLabel lineLType = new JLabel("Item Type");
        JLabel lineLUnitPrice = new JLabel("Item Unit Price");
        JLabel lineLQuantity = new JLabel("Item Quantity");
        JTextArea lineIType = new JTextArea("", 1, 5);
        JTextArea lineIUnitPrice = new JTextArea("", 1, 8);
        JTextArea lineIQuantity = new JTextArea("", 1, 7);

        JLabel addLTitle = new JLabel("Name of Business");
        JLabel addLStreet = new JLabel("Street Address");
        JLabel addLCity = new JLabel("City, State, Zip");
        JTextArea addITitle = new JTextArea("", 1, 10);
        JTextArea addIStreet = new JTextArea("", 1, 10);
        JTextArea addICity = new JTextArea("", 1, 10);

        JRadioButton textRender = new JRadioButton("Render to Textbox");

        textRender.setSelected(true);
        JRadioButton consoleRender = new JRadioButton("Render to Console");
        ButtonGroup renderGroup = new ButtonGroup();
        renderGroup.add(textRender);
        renderGroup.add(consoleRender);
        GridBagConstraints c = new GridBagConstraints();
        JTextArea ta = new JTextArea("", 15, 50); // Text area
        ta.setLineWrap(true);
        JScrollPane sbrText = new JScrollPane(ta); // Scroll pane for text area
        sbrText.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //action listener
        quit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(new JFrame(),"Sure? You want to exit?", "Quit Confirm",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION){
                    System.exit(0);

                }else if (result == JOptionPane.NO_OPTION){

                }else {

                }
            }
        });
        additionalLine.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String type= lineIType.getText();
                int quantity= Integer.parseInt(lineIQuantity.getText());
                double unitPrice= Double.parseDouble(lineIUnitPrice.getText());
                redHawk.add(new lineItem(type,quantity,unitPrice));
                lineIType.setText("");
                lineIQuantity.setText("");
                lineIUnitPrice.setText("");
                System.out.println("Line Item #"+redHawk.size());

            }
        });
        render.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                double amount=0;
                String msg="";
                msg+= addITitle.getText()+"\n";
                msg+= addIStreet.getText()+"\n";
                msg+= addICity.getText()+"\n";
                msg+= "\n\n";
                msg+="=====================================================================\n";

                msg+=String.format("%-50s %-10s %-10s %-10s\n","Item","Qty","Price","Total");
                for(int x =0; x<redHawk.size();x++){
                    amount += redHawk.get(x).getLineTotal();
                    msg+=String.format("%-50s %-10s $%-10.2f $%-10.2f\n",redHawk.get(x).getType(),redHawk.get(x).getQuantity(),redHawk.get(x).getUnitPrice(),redHawk.get(x).getLineTotal());
                }




                msg+="\n \n";
                msg+="--------------------------------------------------------------------------------\n";
                msg+=String.format("%-55s $%-10.2f\n","Total:",amount);



                msg+="=====================================================================\n";
                if(textRender.isSelected()==true){

                    ta.setText(msg);
                }else{
                    System.out.print(msg);
                }

            }
        });

        //formatting the buttonPanel
        buttonPanel.setLayout(new GridBagLayout());
        buttonPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Actions", TitledBorder.LEFT, TitledBorder.TOP));
        c.weightx = 1;

        c.ipady = 0;

        c.ipady = 0;
        c.gridx = 1;
        c.gridy = 1;

        buttonPanel.add(quit,c);

        // formatting invoiceTextPanel
        invoiceTextPanel.setLayout(new GridBagLayout());
        invoiceTextPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Invoice Render", TitledBorder.LEFT, TitledBorder.TOP));
        c.weightx = 1;

        c.ipady = 0;
        c.gridx = 1;
        c.gridy = 1;
        invoiceTextPanel.add(sbrText, c);
        //formatting the linePanel
        linePanel.setLayout(new GridBagLayout());
        linePanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Invoice Line Items", TitledBorder.LEFT, TitledBorder.TOP));
        c.weightx = 1;
        c.ipady = 0;
        c.gridwidth=1;
        c.gridheight=1;
        c.anchor = GridBagConstraints.WEST;
        c.ipadx= 10;
        c.gridx = 0;
        c.gridy = 0;
        linePanel.add(lineLType,c);
        c.gridx = 1;
        c.gridy = 0;
        linePanel.add(lineLQuantity,c);
        c.gridx = 2;
        c.gridy = 0;
        linePanel.add(lineLUnitPrice,c);
        c.gridx = 0;
        c.gridy = 1;
        linePanel.add(lineIType,c);
        c.gridx = 1;
        c.gridy = 1;
        linePanel.add(lineIQuantity,c);
        c.gridx = 2;
        c.gridy = 1;
        linePanel.add(lineIUnitPrice,c);
        c.gridx = 3;
        c.gridy = 1;
        linePanel.add(additionalLine,c);
        c.ipadx= 0;

        //Formatting renderPanel
        renderPanel.setLayout(new GridBagLayout());
        renderPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Render Options", TitledBorder.LEFT, TitledBorder.TOP));
        c.gridwidth=1;

        c.gridwidth=2;
        c.gridx = 0;
        c.gridy = 1;
        renderPanel.add(textRender,c);
        c.gridx = 0;
        c.gridy = 2;
        renderPanel.add(consoleRender,c);
        c.gridx = 0;
        c.gridy = 3;
        renderPanel.add(render,c);

        // Formatting addressPanel
        addressPanel.setLayout(new GridBagLayout());
        addressPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createEtchedBorder(), "Address Info", TitledBorder.LEFT, TitledBorder.TOP));

        c.anchor = GridBagConstraints.WEST;
        c.gridwidth=1;
        c.ipadx= 10;
        c.gridx = 0;
        c.gridy = 0;
        addressPanel.add(addLTitle,c);
        c.gridx = 0;
        c.gridy = 1;
        addressPanel.add(addITitle,c);
        c.gridx = 0;
        c.gridy = 2;
        addressPanel.add(addLStreet,c);
        c.gridx = 0;
        c.gridy = 3;
        addressPanel.add(addIStreet,c);
        c.gridx = 0;
        c.gridy = 4;
        addressPanel.add(addLCity,c);
        c.gridx = 0;
        c.gridy = 5;
        addressPanel.add(addICity,c);

        c.ipadx= 0;



        //adding UI Elements
        setLayout(new GridBagLayout());
        c.anchor = GridBagConstraints.CENTER;
        c.weightx = 1;
        c.weighty=1;
        c.ipadx = 0;
        c.ipady = 0;
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        add(buttonPanel, c);
        c.gridx = 0;
        c.gridy = 2;
        add(invoiceTextPanel, c);
        c.gridx = 0;
        c.gridy = 1;
        add(linePanel, c);
        c.gridwidth = 1;
        c.gridx = 0;
        c.gridy = 0;
        add(addressPanel, c);
        c.gridx = 1;
        c.gridy = 0;
        add(renderPanel, c);



        // formatting the frame
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int screenHeight = screenSize.height;
        int screenWidth = screenSize.width;

        // center frame in screen

        setSize((int) (screenWidth / 1.25), (int) (screenHeight / 1.25));

        setLocation((int) (screenWidth / 9.5), (int) (screenHeight / 9));

        // Render the Frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.show();



    }
}
