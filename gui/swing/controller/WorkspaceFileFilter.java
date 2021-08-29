package gui.swing.controller;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class WorkspaceFileFilter extends FileFilter{

	@Override
	public String getDescription() {
		return "Text file (*.txt)";
	}

	@Override
	public boolean accept(File f) {
		return (f.isDirectory() || 
                f.getName().toLowerCase().endsWith(".txt"));
	}

}