package gui.swing.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	public AboutDialog() {
		
		setSize(new Dimension(530, 300));
        setLocationRelativeTo(null);
        setTitle("O nama");
        setResizable(false);
        
        BufferedImage slika1 = null;
        BufferedImage slika2 = null;
        
        
		try {
			slika1 = ImageIO.read(new File("RuDok/src/slike/slika studenta.png"));
			slika2 = ImageIO.read(new File("RuDok/src/slike/slika studenta.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        JLabel picLabel1 = new JLabel(new ImageIcon(slika1));
        JLabel picLabel2 = new JLabel(new ImageIcon(slika2));
        
        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        
        panel1.setLayout(new FlowLayout());
        panel2.setLayout(new FlowLayout());
        
        JLabel ime1 = new JLabel("Jovan Lazic RN 114/2020");
        JLabel ime2 = new JLabel("Petar Mitrovic RN 140/2019");
        
        panel1.add(ime1);
        panel2.add(ime2);
        
        panel1.add(picLabel1);
        panel2.add(picLabel2);
        
        GridLayout bl = new GridLayout(1, 2);
        
        setLayout(bl);
        add(panel1, bl);
        add(panel2, bl);
        
        setVisible(true);
	}
}