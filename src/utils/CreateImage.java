package utils;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

import data.CONST;
import utilDatatypes.Point;
import utilDatatypes.PointPair;

public class CreateImage{
	
	private BufferedImage image;
	private ArrayList<PointPair> linesList;
	private ArrayList<Point> pointList;
	private Graphics2D g2d;
	Color color;
	
	public CreateImage(ArrayList<Point> pointList, ArrayList<PointPair> linesList) {
		image = new BufferedImage(CONST.CANVAS_W, CONST.CANVAS_H, BufferedImage.TYPE_INT_RGB);
		g2d = (Graphics2D)image.getGraphics();
		this.pointList = pointList;
		this.linesList = linesList;
		reset();
		drawPins();
		drawLines();
		
		File f = new File("result.bmp");
		try {
			ImageIO.write(image, "bmp", f);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void reset() {
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, CONST.FRAME_W, CONST.FRAME_H);
	}
	
	private void drawPins() {
		g2d.setColor(Color.RED);
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, 
				   							   RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHints(rh);

		for (Point p: pointList) {
			g2d.fillOval(p.x, p.y, CONST.PINSIZE, CONST.PINSIZE);
		}
	}
	
	private void drawLines() {
		g2d.setColor(Color.BLACK);
		RenderingHints rh = new RenderingHints(RenderingHints.KEY_ANTIALIASING, 
				   							   RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setRenderingHints(rh);
		g2d.scale(1.0/CONST.SCALAR, 1.0/CONST.SCALAR);
		
		for (PointPair pp: linesList) {
			g2d.drawLine( pp.p1.x, pp.p1.y, pp.p2.x, pp.p2.y );
		}
	}
}
