import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JApplet;

//Name: Rotem Levi
//ID: 205785959
public class HW3_RotemLevi extends JApplet{ // for applet

	private static final long serialVersionUID = 1L;
	private final int FRAME_SIZE = 700;
	private PaintPanel panel = new PaintPanel();
	public static void main(String[] args) {
		new PaintReconstructor();
	

	}
	public HW3_RotemLevi() {
		
		setSize(FRAME_SIZE, FRAME_SIZE);
		setFocusable(true);
		add(panel);
		
		setVisible(true);
		addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.isControlDown()&&e.getKeyCode()==KeyEvent.VK_SPACE)
				{
					new ControlPanel(panel);
					panel.stop();
					
				}
			}
		});
	}
	
}
