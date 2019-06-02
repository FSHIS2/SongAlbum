package graphic;

import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

public class DialogoAlbum extends JDialog {
    private JButton addbutton;
    private JButton finalize;
    private JButton cancel;
    private JTextArea area;
    private boolean finalized;
    private DialogoCancion songdialog;
    
    public DialogoAlbum(JFrame f, Album album) {
        super(f, "Nuevo álbum");
        finalized = false;
        setModal(true);
        setLocationRelativeTo(f);
        Container cp = getContentPane();
        addbutton = new JButton("Añadir canción");
        finalize = new JButton("Finalizar álbum");
        cancel = new JButton("Cancelar");
        area = new JTextArea(album.toString(),15,15);
        //West Panel
        JPanel west = new JPanel();
        west.setLayout(new FlowLayout());
        west.add(addbutton);
        cp.add(west,BorderLayout.WEST);
        //East Panel
        area.setEditable(false);
        JScrollPane areapane = new JScrollPane(area);
        areapane.setLayout(new ScrollPaneLayout());
        cp.add(areapane,BorderLayout.EAST);
        //South Panel
        JPanel south = new JPanel();
        south.setLayout(new FlowLayout());
        south.add(finalize);
        south.add(cancel);
        cp.add(south,BorderLayout.SOUTH);
        cancel.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                setVisible(false);
            }
        });
        finalize.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e){
                finalized = true;
                setVisible(false);
            }
        });
        addbutton.addActionListener(new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent e) {
                songdialog = new DialogoCancion(DialogoAlbum.this);
                if(songdialog.isFinalized()) {
                    Cancion song = new Cancion(songdialog.getTitle(),
                    songdialog.getSinger(),songdialog.getLength());
                    album.añadeCanción(song);
                    area.setText(album.toString());
                }
            }
        });
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        pack();
        setVisible(true);
    }
    
    public boolean isfinalized() {
        return finalized;
    }
    
}
