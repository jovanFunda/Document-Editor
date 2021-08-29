package gui.swing.view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import gui.swing.controller.DeleteAction;
import gui.swing.controller.NewDocumentAction;
import gui.swing.controller.NewPageAction;
import gui.swing.controller.NewProjectAction;
import gui.swing.controller.RenameAction;

public class MainMenuBar extends JMenuBar {
	
	private static final long serialVersionUID = 1L;
	
	public MainMenuBar(MainFrame frame) {
		
		JMenu menu = new JMenu("File");
		JMenu helpMenu = new JMenu("Pomoc");
		
		JMenuItem about = new JMenuItem("O nama");
		about.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				new AboutDialog();
			}
		});
		
	    JMenuItem dodajProjekat = new JMenuItem("Dodaj projekat");
	    dodajProjekat.addActionListener(new NewProjectAction());
	    
	    JMenuItem dodajDokument = new JMenuItem("Dodaj dokument selektovanom projektu"); 
	    dodajDokument.addActionListener(new NewDocumentAction());
	    
	    JMenuItem dodajStranicu = new JMenuItem("Kreiraj stranicu selektovanom dokumentu");
	    dodajStranicu.addActionListener(new NewPageAction());
	    
	    JMenuItem preimenujCvor = new JMenuItem("Preimenuj cvor");
	    preimenujCvor.addActionListener(new RenameAction());
	    
	    JMenuItem izbrisiCvor = new JMenuItem("Izbrisi cvor");
	    izbrisiCvor.addActionListener(new DeleteAction());

		helpMenu.add(about);
		menu.add(dodajProjekat);
		menu.add(dodajDokument);
		menu.add(dodajStranicu);
		menu.add(preimenujCvor);
		menu.add(izbrisiCvor);
		
		add(menu);
		add(helpMenu);
	}
}



