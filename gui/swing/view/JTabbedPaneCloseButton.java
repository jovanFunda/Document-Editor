package gui.swing.view;

import javax.swing.*;
import javax.swing.plaf.metal.MetalIconFactory;

//import main.BookTab;

import java.awt.*;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class JTabbedPaneCloseButton extends JTabbedPane {

	private static final long serialVersionUID = 1L;
	private String naziv;

	public JTabbedPaneCloseButton() {
        super();
    }

    @Override
    public void addTab(String title, Icon icon, Component component, String tip) {
    	
        super.addTab(title, icon, component, tip);
        int count = this.getTabCount() - 1;
        setTabComponentAt(count, new CloseButtonTab(component, title, icon));
        naziv = title;
    }

    @Override
    public void addTab(String title, Icon icon, Component component) {
        addTab(title, icon, component, null);
        naziv = title;
    }

    @Override
    public void addTab(String title, Component component) {
        addTab(title, null, component);
        naziv = title;
    }

    public void addTabNoExit(String title, Icon icon, Component component, String tip) {
        super.addTab(title, icon, component, tip);
        naziv = title;
    }

    public void addTabNoExit(String title, Icon icon, Component component) {
        addTabNoExit(title, icon, component, null);
        naziv = title;
    }

    public void addTabNoExit(String title, Component component) {
        addTabNoExit(title, null, component);
        naziv = title;
    }
    
     public class CloseButtonTab extends JPanel {
        
		private static final long serialVersionUID = 4141334065998335653L;
		
		private Component tab;

        public CloseButtonTab(final Component tab, String title, Icon icon) {
            this.tab = tab;
            setOpaque(false);
            FlowLayout flowLayout = new FlowLayout(FlowLayout.CENTER, 3, 3);
            setLayout(flowLayout);
            JLabel jLabel = new JLabel(title);
            jLabel.setIcon(icon);
            add(jLabel);
            JButton button = new JButton(MetalIconFactory.getInternalFrameCloseIcon(16));
            button.setMargin(new Insets(0, 0, 0, 0));
            button.addMouseListener(new CloseListener(tab));
            add(button);
        }
    }
    
    public class CloseListener implements MouseListener
    {
        private Component tab;

        public CloseListener(Component tab){
            this.tab=tab;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource() instanceof JButton){
                JButton clickedButton = (JButton) e.getSource();
                JTabbedPane tabbedPane = (JTabbedPane) clickedButton.getParent().getParent().getParent();
                if (tab instanceof DocumentView) {
                	((DocumentView)tab).saveDocumentState();
                }
                tabbedPane.remove(tab);
                //tabbedPane.setVisible(false);
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {}

        @Override
        public void mouseReleased(MouseEvent e) {}

        @Override
        public void mouseEntered(MouseEvent e) {
            if(e.getSource() instanceof JButton){
                JButton clickedButton = (JButton) e.getSource();
            }
        }

        @Override
        public void mouseExited(MouseEvent e) {
            if(e.getSource() instanceof JButton){
                JButton clickedButton = (JButton) e.getSource();
            }
        }
    }
    
    public String getNaziv() {
    	return naziv;
    }
}