package gui.swing.view;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

import graphics.model.elements.SlotDevice;
import model.observer.IObserver;
import model.observer.helpClasses.ObserverHelperOne;
import model.repository.Document;
import model.repository.Page;
import model.repository.node.Node;

public class PageView extends JPanel implements IObserver{
	
	private static final long serialVersionUID = 1L;
	
	private JLabel path;
	private Page pageModel;
	private PageView thisPV = this;
	private List<Integer> mousePositions = new ArrayList<Integer>();
	private boolean selectRectangle = false;

	public PageView(Page pa) {
		
		this.pageModel = pa;
		this.pageModel.addSubscriber(this);
		
		Node selectedItem = MainFrame.getInstance().getTree().getSelectedNode().getNodeModel();
		String fullName = selectedItem.getParent().getName() + " - " + selectedItem.getName() + " - " + pageModel.getName();
		setLayout(new FlowLayout());
		path = new JLabel(fullName);
		setPreferredSize(new Dimension(800, 200));
		add(path);
		
		
		setCursor(new Cursor(Cursor.HAND_CURSOR));
		setBackground(Color.WHITE);
		setBorder(BorderFactory.createLineBorder(Color.black));
		MouseController m = new MouseController();
		addMouseListener(m);
		addMouseMotionListener(m);
	}

	public PageView() {}

	protected void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D) g;
		AffineTransform old = g2.getTransform();
		
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.8f));
		
		Iterator<SlotDevice> it = pageModel.getPageModel().getDeviceIterator();
			
		while(it.hasNext()){
			SlotDevice painter = it.next();
			g2.rotate(painter.getAngle()/45, painter.getPosition().getX() +painter.getSize().getWidth()/2 , 
					painter.getPosition().getY() + painter.getSize().getHeight()/2);
			
			painter.notifySubscribers(g2);
			g2.setTransform(old);
		}
		
		for (MouseListener listener : getMouseListeners()) {
			if(selectRectangle == true && !(listener.equals("move") || listener.equals("mouse"))) {
		
				g2.setColor(new Color(60, 130, 180));
				
				g2.fillRect(mousePositions.get(0), mousePositions.get(1), mousePositions.get(2), mousePositions.get(3));
				
				selectRectangle = false;
			}
		}
		
	}
	
	
	
	
	public void setPage(Page page) {
		this.pageModel = page;
		this.setName(page.getName());
		this.pageModel.getPageModel().addSubscriber(this);
	}
	
	int cntSlot = 1;
	
	private class MouseController extends MouseAdapter{

		public void mousePressed(MouseEvent event) {
			if (event.getButton()==MouseEvent.BUTTON1) {
				thisPV.setPage(pageModel);
				MainFrame.getInstance().setLastSelectedPageView(thisPV);
				if(pageModel.getStateManager().getCurrentState() != null) {
					pageModel.getStateManager().getCurrentState().mousePressed(event);
				}
				repaint();
			}
		} 
		
		public void mouseDragged(MouseEvent e) {
			if(pageModel.getStateManager().getCurrentState() != null) {
				pageModel.getStateManager().getCurrentState().mouseDragged(e);
				repaint();
			}
		}		
		
		public void mouseReleased(MouseEvent e) {
			if (e.getButton()==MouseEvent.BUTTON1) {
				if(pageModel.getStateManager().getCurrentState() != null) {
					pageModel.getStateManager().getCurrentState().mouseReleased(e);
				}
				repaint();
			}	
		}
			
		@Override
		public String toString() {
			return "mouse";
		}
	}
	

	public Page getPage() {
		return pageModel;
	}
	
	@SuppressWarnings("rawtypes")
	@Override
	public void update(Object notification) {
		
		Document doc = (Document)this.pageModel.getParent();
		
		if(notification.equals("Obrisan page")) {
			doc.notifySubscribers(new ObserverHelperOne(this, "Obrisan page"));
		} else if(notification instanceof Page) {
			doc.notifySubscribers(new ObserverHelperOne(this, this.pageModel.getName()));
		} else if(notification instanceof List) {
			mousePositions.add(0, (Integer) ((ArrayList)notification).get(0));
			mousePositions.add(1, (Integer) ((ArrayList)notification).get(1));
			mousePositions.add(2, (Integer) ((ArrayList)notification).get(2));
			mousePositions.add(3, (Integer) ((ArrayList)notification).get(3));
			selectRectangle = true;
			
		} else {
			doc.notifySubscribers(this);
		}
			
		String imeStranice = this.pageModel.getName();
		String imeDokumenta = this.pageModel.getParent().getName();
		String imeProjekta = this.pageModel.getParent().getParent().getName();
		path.setText(imeProjekta + " - " + imeDokumenta + " - " + imeStranice);
		
	}
}
