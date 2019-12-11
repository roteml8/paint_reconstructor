
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;

public class PaintReconstructor extends JFrame{
	private static final long serialVersionUID = 1L;
	private final int FRAME_SIZE = 700; 
	private PaintPanel panel = new PaintPanel();
	public PaintReconstructor ()
	{
		// general properties
		super("HW3");
		setSize(FRAME_SIZE, FRAME_SIZE);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		setAlwaysOnTop(true);
		setFocusable(true);
		
		//adding the paint panel 
		add(panel);
		
		setVisible(true);
		addKeyListener(new KeyAdapter() { // displaying control panel when needed
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.isControlDown()&&e
						.getKeyCode()==KeyEvent.VK_SPACE)
				{
					
					new ControlPanel(panel);
					panel.stop();
					
				}
			}
		});
	}
	
	public static void main(String[]args){
		new PaintReconstructor();
	}

}
