package graphic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
/**
 * This class shows the library main frame
 * 
 * @version 21/12/2018
 */
public class BibliotecaGUI extends JFrame {
    private JTextArea lista;
    private JButton otro;
    private Biblioteca miBiblioteca;
    private DialogoAlbum d;
    
    private void initBiblioteca(){
        
        miBiblioteca = new Biblioteca();
        Cancion canción1 = new Cancion("song1","i1",90);
        Cancion canción2 = new Cancion("song2","i1",90);
        Cancion canción3 = new Cancion("song3","i2",120);
        Cancion canción4 = new Cancion("song1","i2",120);
        Cancion canción5 = new Cancion("song3","i2",120);
        Cancion canción6 = new Cancion("song4","i3",150);
        Cancion canción7 = new Cancion("song1","i3",150);

        Album álbum1 = new Album("Álbum 1");
        álbum1.añadeCanción(canción1);
        álbum1.añadeCanción(canción2);
        álbum1.añadeCanción(canción3);

        Album álbum2 = new Album("Álbum 2");
        álbum2.añadeCanción(canción4);
        álbum2.añadeCanción(canción5);
        álbum2.añadeCanción(canción6);
        álbum2.añadeCanción(canción7);
        
        miBiblioteca.añadeÁlbum(álbum1);
        miBiblioteca.añadeÁlbum(álbum2);
    }
    /**
     * Builds the library frame
    */
    public BibliotecaGUI(String t){
        super(t);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        initBiblioteca();
        Container p = getContentPane();
        lista = new JTextArea(20,25);
        otro = new JButton("Añadir álbum");
        p.add(new JScrollPane(lista),BorderLayout.CENTER);
        JPanel rellenoBotón = new JPanel();
        rellenoBotón.add(otro);
        p.add(rellenoBotón,BorderLayout.WEST);
        lista.setEditable(false);
        lista.setText(miBiblioteca.toString());
        otro.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String message = "Introduzca el nombre del álbum";
                String name = JOptionPane.showInputDialog(BibliotecaGUI.this,
                        message);
                while(name.equals("")){
                    JOptionPane.showMessageDialog(BibliotecaGUI.this, 
                            "Nombre no válido");
                    name = JOptionPane.showInputDialog(BibliotecaGUI.this,
                        message);
                }
                Album album = new Album(name);
                d = new DialogoAlbum(BibliotecaGUI.this, album);
                if(d.isfinalized()) {
                    miBiblioteca.añadeÁlbum(album);
                    lista.setText(miBiblioteca.toString());
                }
            }
        });
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    public static void main(String[] args){
        new BibliotecaGUI("Mi biblioteca");
    }
}
