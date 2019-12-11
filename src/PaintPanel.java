import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

public class PaintPanel extends JPanel{
	
	private final Font LABEL_FONT = new Font("Arial",Font.BOLD|Font.ITALIC,20); // font of message
	private final Color RECORD_COLOR = Color.black; // color when recording
	private final Color RECORD_TEXT_C = Color.red; // color of recording message
	private final Color RESTORE_TEXT_C = Color.black; // color of restoring message
	private final int PAINT_WIDTH = 10; // width when recording 
	
	private int drawWidth; // width when restoring 
	private Color currentColorOfMessage; // color of the current message to display
	private Color currentColorOfPaint; // current color for restoring 
	private String message="Recording..."; // message to display, initiated to Recording
	private boolean record=true, restore=false; // determines the operation performed 
	private ArrayList<Point> points= new ArrayList<>(); // stores the points drawn
	private int delay; // indicates restoring speed
	private int index; // index to the point array
	private Timer time = new Timer(delay, new ActionListener() { 
		
		@Override
		public void actionPerformed(ActionEvent e) {
			if(index<points.size()) // restoring
			{
				repaint();
			}
			else // prepare for new record 
			{
				time.stop();
				restore=false;
				record=true;
				message="Recording...";
				currentColorOfMessage=RECORD_TEXT_C;
				points.clear();
			}
		}
	});
	
	public PaintPanel(){
		currentColorOfMessage=RECORD_TEXT_C; //initial message color
		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent e){
				if(record) // while recording, collect the points 
				{
					points.add(new Point(e.getX(),e.getY()));
					repaint();
				}
			}
			
			
		});
	}
	
	protected void paintComponent(Graphics g) {
		

		if (record&&points.size()>0) { // displaying drawing when recording
			if(points.size()==1)
				super.paintComponent(g);
			g.setColor(RECORD_COLOR);
			g.fillOval(points.get(points.size()-1).x, points.get(points.size()-1).y, PAINT_WIDTH, PAINT_WIDTH);
		}
		else if(!record&&restore){ //restoring 
			if(index==0)
				super.paintComponent(g);
			g.setColor(currentColorOfPaint);
			g.fillOval(points.get(index).x,points.get(index).y ,drawWidth, drawWidth);
			index++;

		}
		
		//  next message
		setFont(LABEL_FONT);
		g.setColor(currentColorOfMessage);
		g.drawString(message,20 ,20 );
		

}
	
	public void setCurrentColorOfPaint(Color color) // set color of restoring
	{
		this.currentColorOfPaint=color;
	}
	
	public void setThickness(int drawWidth) // set thickness of restoring
	{
		this.drawWidth=drawWidth;
	}
	
	
	public void setDelay(int delay ) // set speed of restoring
	{
		this.delay=delay;
		time.setDelay(delay);
	}
	public void stop() // stop operating
	{
		record=false;
		restore=false;
		time.stop();
	}
	
	public void startRestoring() // start restore process 
	{
		index=0;
		currentColorOfMessage=RESTORE_TEXT_C;
		message="Restoring...";
		restore=true;
		time.start();
	}
}
