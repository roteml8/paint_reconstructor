import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JSlider;

public class ControlPanel extends JFrame {
	
	private JComboBox<String> comboBox = new JComboBox<>(new String[]{"Default thickness", "Thick", "Thin"}); // Combo Box of thickness levels
	private JPanel colors = new JPanel(); // Colors Panel
	private JPanel speed = new JPanel(); // Speed Panel 
	private JPanel settings  = new JPanel(new FlowLayout(FlowLayout.LEFT,30,30)); // Panel of all settings 
	private JPanel okPanel = new JPanel(); // OK Button Panel
	private ButtonGroup bg = new ButtonGroup(); // Button Group of radio buttons
	private JSlider slider = new JSlider(1,3); // Slider of speed levels 
	private final int drawWidth=10; // default width of drawing

	
	
	public ControlPanel(final PaintPanel p){
		super("Control Panel");
		
		//general properties
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
		setAlwaysOnTop(true);
		setFocusable(true);
		setSize(p.getHeight()/2,p.getHeight()/2);
		setResizable(false);
		setLocationRelativeTo(p);
		setLayout(new BorderLayout());
		
		// creating buttons of colors
		JRadioButton red = new JRadioButton("RED");
		JRadioButton black = new JRadioButton("BLACK");
	 	JRadioButton blue = new JRadioButton("BLUE");
	    JRadioButton green = new JRadioButton("GREEN");
	    
		colors.setBorder(BorderFactory.createTitledBorder("Colors"));
		
		//setting mnemonics to buttons
		red.setMnemonic('R');
		black.setMnemonic('B');
		blue.setMnemonic('L');	
		green.setMnemonic('G');
		
		// setting action commands to buttons
		red.setActionCommand("RED");
		black.setActionCommand("BLACK");
		blue.setActionCommand("BLUE");
		green.setActionCommand("GREEN");
		
		//adding buttons to the colors panel
		colors.add(black);
		colors.add(red);
		colors.add(blue);
		colors.add(green);
		
		//adding buttons to the button group
		bg.add(red);
		bg.add(blue);
		bg.add(black);
		bg.add(green);
		
		comboBox.setToolTipText("Please select thickness level");
		speed.setBorder(BorderFactory.createTitledBorder("Speed"));
		
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
	    slider.setLabelTable(slider.createStandardLabels(1));
	    speed.add(slider); 
	    
	    //adding the panels to the main settings panel 
	    settings.add(colors);
	    settings.add(comboBox);
	    settings.add(speed);
	    
		JButton button = new JButton("OK"); 
		button.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) { // setting user's choices to the PaintPanel
				
					p.setCurrentColorOfPaint(getColorFromButtons(bg));  
					p.setThickness(getThicknessFromBox(comboBox)); 
					p.setDelay((slider.getMaximum()-slider.getValue()+1)*10);
					p.startRestoring(); // start the restoring process
					
					dispose();
					
			}
		});
		
		
		okPanel.add(button, JPanel.CENTER_ALIGNMENT);
		add(settings,BorderLayout.CENTER);
		add(okPanel,BorderLayout.SOUTH);
		setVisible(true);
		
		


	}
	
	public Color getColorFromButtons(ButtonGroup bg) // returns chosen color from button group
	{
		switch (bg.getSelection().getActionCommand())
		{
		case "BLACK":
			return Color.BLACK;
		case "RED":
			return Color.RED;
		case "BLUE":
			return Color.BLUE;
		case "GREEN":
			return Color.GREEN;
		default:
			return Color.BLACK;
		}
	}
	
	public int getThicknessFromBox(JComboBox<String> box) // returns width according to chosen thickness level
	{
		switch (box.getSelectedItem().toString())
		{
		case "Default thickness":
			return drawWidth;
		case "Thick":
			return drawWidth+5;
		case "Thin":
			return drawWidth-5;
		default:
			return drawWidth;
		}
	}
	


}
