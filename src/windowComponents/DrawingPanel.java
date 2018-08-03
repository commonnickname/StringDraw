package windowComponents;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.border.EtchedBorder;

import algorithms.DrawingAlgorithm;
import data.AlgorithmData;
import data.CONST;
import utilDatatypes.CLine;
import utilDatatypes.Pin;
import utilDatatypes.Point;
import utils.ParameterPackage;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class DrawingPanel extends JPanel{
	private DrawingAlgorithm algorithm;
	private AlgorithmData algData;
	private ParameterPackage params;
	private BufferedImage bufferedImage;
	public ArrayList<Point> drawingPoints;
	private int hOffset, vOffset;
	public boolean imageProcessed, algSuccess;
	private RenderingHints renderingHints;
	
	public DrawingPanel(ParameterPackage params) {
		this.params = params;
		setPreferredSize(new Dimension(CONST.FRAME_W, CONST.FRAME_H));
		renderingHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING, 
											RenderingHints.VALUE_ANTIALIAS_ON);
		reset();
	}
	
	public void initialize(float[][] imageVals) {
		algSuccess = imageProcessed = false;
		this.algData = new AlgorithmData(imageVals);
		
		drawingPoints = new ArrayList<Point>();
		hOffset = (CONST.FRAME_W - algData.width)/2 - CONST.PINSIZE/2;
		vOffset = (CONST.FRAME_H - algData.height)/2 - CONST.PINSIZE/2;
		for (Pin p: algData.pinList) drawingPoints.add(new Point(p.x + hOffset, p.y + vOffset));
		
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
			algorithm = (DrawingAlgorithm)Class.forName(CONST.algorithmName).newInstance();
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
	
	public void drawLines() {
		prepareCanvas();
		setupAlgorithm();
		if (!algSuccess) return;

		Graphics2D g2d = (Graphics2D)bufferedImage.getGraphics();
		Graphics g = this.getGraphics();
		g2d.setColor(Color.BLACK);
		g2d.setRenderingHints(renderingHints);
		
		hOffset = (CONST.FRAME_W - algData.width)/2;
		vOffset = (CONST.FRAME_H - algData.height)/2;
		int scalar = CONST.SCALAR;
		
	    CLine line = algorithm.getLine();
	    //int x1, y1, x2, y2;
	    g2d.scale(1.0/scalar, 1.0/scalar);
	    int skip = 0;
	    
		while (line != null) {
			/*
			x1 = (line.from.x + hOffset) * scalar;
			y1 = (line.from.y + vOffset) * scalar;
			x2 = (line.to.x + hOffset) * scalar;
			y2 = (line.to.y + vOffset) * scalar;*/
			g2d.drawLine( (line.from.x + hOffset) * scalar, (line.from.y + vOffset) * scalar, 
						  (line.to.x + hOffset) * scalar,   (line.to.y + vOffset) * scalar );
			if (skip++ % CONST.SKIP == 0) g.drawImage(bufferedImage, 0, 0, null);
			
			line = algorithm.getLine();
		}
		repaint();
		g2d.dispose();
		g.dispose();
	}
	
	
	public void paint(Graphics g) {
		g.drawImage(bufferedImage, 0, 0, null);
	} 
	

}
