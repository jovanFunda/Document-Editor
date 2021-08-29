package gui.swing.controller;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class SlotFileFilter extends FileFilter{

	@Override
	public String getDescription() {
		return "RuDok Project Files (*.ruf)";
	}

	@Override
	public boolean accept(File f) {
		return (f.isDirectory() || 
                f.getName().toLowerCase().endsWith(".ruf"));
	}

}
