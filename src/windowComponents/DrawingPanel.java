package windowComponents;
import javax.swing.JPanel;
import algorithms.DrawingAlgorithm;
import data.CONST;
import utils.datatypes.CLine;
import utils.datatypes.Pin;
import utils.datatypes.Point;
import utils.helpers.AlgorithmData;
import utils.helpers.ParameterPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Map;

public class DrawingPanel extends JPanel{
	private DrawingAlgorithm algorithm;
	private AlgorithmData algData;
	private ParameterPackage params;
	private BufferedImage bufferedImage;
	public ArrayList<Point> drawingPoints;
	private int hOffset, vOffset;
	public boolean imageProcessed, algSuccess;
	private RenderingHints renderingHints;
	private Map<String, String> algNames;
	int counter = 0;
	
	public DrawingPanel() {
		this.params = null;
		setPreferredSize(new Dimension(CONST.FRAME_W, CONST.FRAME_H));
		renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, 
											RenderingHints.VALUE_ANTIALIAS_ON);
		reset();
	}
	
	public void initialize(float[][] imageVals) {
		algSuccess = imageProcessed = false;
		this.algData = new AlgorithmData(imageVals);
		algNames = CONST.algNames;
		
		drawingPoints = new ArrayList<Point>();
		hOffset = (CONST.FRAME_W - algData.width)/2 - CONST.PINSIZE/2;
		vOffset = (CONST.FRAME_H - algData.height)/2 - CONST.PINSIZE/2;
		for (Pin p: algData.pinList) drawingPoints.add(new Point(p.coords.x + hOffset, 
																 p.coords.y + vOffset));
		
		reset();
		drawPins();
	}
	
	private void prepareCanvas() {
		if (imageProcessed) {
			reset();
			drawPins();
		} else {
			imageProcessed = true;
		}
	}
	
	private void setupAlgorithm() {
		algSuccess = false;
		try {
			algorithm = null;
			algorithm = (DrawingAlgorithm)Class.forName(algNames.get(CONST.algKey)).newInstance();
			algorithm.setup(algData, params);
			algSuccess = true;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	
	
	public void reset() {
		bufferedImage = new BufferedImage( CONST.FRAME_W, CONST.FRAME_H, BufferedImage.TYPE_INT_ARGB );
	
		Graphics g = bufferedImage.getGraphics();
        g.setColor(Color.WHITE);
        g.fillRect(0,0, CONST.FRAME_W, CONST.FRAME_H);
        g.dispose();
	}
	

	public void drawPins() {
		Graphics2D g2d = (Graphics2D)bufferedImage.getGraphics();
		g2d.setColor(Color.RED);
		g2d.setRenderingHints(renderingHints);
		for (Point p: drawingPoints) g2d.fillOval(p.x, p.y, CONST.PINSIZE, CONST.PINSIZE);
		g2d.dispose();
	}
	
	public void drawLines(ParameterPackage params) {
		if (params == null || params.equals(this.params)) return;
		this.params = params;
		
		prepareCanvas();
		setupAlgorithm();
		if (!algSuccess) return;
		counter = 0;
		Graphics2D g2d = (Graphics2D)bufferedImage.getGraphics();
		Graphics g = this.getGraphics();
		g2d.setColor(Color.BLACK);
		g2d.setRenderingHints(renderingHints);
		
		hOffset = (CONST.FRAME_W - algData.width)/2;
		vOffset = (CONST.FRAME_H - algData.height)/2;
		int scalar = CONST.SCALAR;
		
	    CLine line = algorithm.getLine();
	    int x1, y1, x2, y2;
	    g2d.scale(1.0/scalar, 1.0/scalar);
	    int skip = 0;
	    
		while (line != null) {
			x1 = (line.from.coords.x + hOffset) * scalar;
			y1 = (line.from.coords.y + vOffset) * scalar;
			x2 = (line.to.coords.x + hOffset) * scalar;
			y2 = (line.to.coords.y + vOffset) * scalar;
			
			g2d.drawLine( x1, y1,  x2, y2 );
			if (skip++ % CONST.SKIP == 0) g.drawImage(bufferedImage, 0, 0, null);
			
			line = algorithm.getLine();
			//System.out.println(counter++);
		}
		repaint();
		g2d.dispose();
		g.dispose();
	}
	
	
	public void paint(Graphics g) {
		g.drawImage(bufferedImage, 0, 0, null);
	} 
	

}
