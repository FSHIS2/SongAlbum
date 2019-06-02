package graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DialogoCancion extends JDialog{
    private JTextField title;
    private JTextField singer;
    private JTextField length;
    private JLabel titlelabel;
    private JLabel singerlabel;
    private JLabel lengthlabel;
    private JButton accept;
    private JButton cancel;
    boolean finalized;
    
    public DialogoCancion(JDialog d) {
        setModal(true);
        setLocationRelativeTo(d);
        finalized = false;
        Container c = getContentPane();
        c.setLayout(new BoxLayout(c, BoxLayout.Y_AXIS));
        title = new JTextField(10);
        singer = new JTextField(10);
        length = new JTextField(10);
        titlelabel = new JLabel("Título: ");
        singerlabel = new JLabel("Intérprete: ");
        lengthlabel = new JLabel("Duración: ");
        accept = new JButton("Aceptar");
        cancel = new JButton("Cancelar");
        //Title Panel
        JPanel titlepanel = new JPanel();
        titlepanel.setLayout(new FlowLayout());
        titlepanel.add(titlelabel);
        titlepanel.add(title);
        c.add(titlepanel);
        //Singer Panel
        JPanel singerpanel = new JPanel(new FlowLayout());
        singerpanel.add(singerlabel);
        singerpanel.add(singer);
        c.add(singerpanel);
        //Length Panel
        JPanel lengthpanel = new JPanel(new FlowLayout());
        lengthpanel.add(lengthlabel);
        lengthpanel.add(length);
        c.add(lengthpanel);
        //Buttons panel
        JPanel buttonpanel = new JPanel(new FlowLayout());
        buttonpanel.add(accept);
        buttonpanel.add(cancel);
        c.add(buttonpanel);
        cancel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });
        accept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                if(title.getText().equals("") || singer.getText().equals("")
                        ||length.getText().equals("")){
                    JOptionPane.showMessageDialog(DialogoCancion.this, 
                            "Algún campo está vacío");
                } else {
                    finalized = true;
                    setVisible(false);
                }
            }
        });
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }
    
    public String getTitle() {
        return title.getText();
    }
    
    public String getSinger() {
        return singer.getText();
    }
    
    public int getLength() {
        return (int) Integer.parseInt(length.getText());
    }
    
    public boolean isFinalized() {
        return finalized;
    }
}
