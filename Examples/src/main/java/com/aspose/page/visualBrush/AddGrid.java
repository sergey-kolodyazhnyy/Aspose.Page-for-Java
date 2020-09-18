/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aspose.page.visualBrush;

import com.aspose.page.utilities.Utils;
import com.aspose.xps.XpsCanvas;
import com.aspose.xps.XpsDocument;
import com.aspose.xps.XpsPath;
import com.aspose.xps.XpsPathGeometry;
import com.aspose.xps.XpsTileMode;
import com.aspose.xps.XpsVisualBrush;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class AddGrid {
    public static void main(String[] args) throws Exception
    {
        //ExStart:AddGrid
        // The path to the documents directory.
        String dataDir = Utils.getDataDir();

        // Create new XPS Document
        XpsDocument doc = new XpsDocument();

        // Geometry for magenta grid VisualBrush
        XpsPathGeometry pathGeometry = doc.createPathGeometry();
        pathGeometry.addSegment(doc.createPolyLineSegment(
            new Point.Float[] { new Point.Float(240f, 5f), new Point.Float(240f, 310f), new Point.Float(0f, 310f) }));
        pathGeometry.get(0).setStartPoint(new Point.Float(0f, 5f));
        
        // Canvas for magenta grid VisualBrush
        XpsCanvas visualCanvas = doc.createCanvas();

        XpsPath visualPath = visualCanvas.addPath(
            doc.createPathGeometry("M 0,4 L 4,4 4,0 6,0 6,4 10,4 10,6 6,6 6,10 4,10 4,6 0,6 Z"));        
        visualPath.setFill(doc.createSolidColorBrush(doc.createColor(1f, .61f, 0.1f, 0.61f)));

        XpsPath gridPath = doc.createPath(pathGeometry);
        //Create Visual Brush, it is specified by some XPS fragment (vector graphics and glyphs)
        gridPath.setFill(doc.createVisualBrush(visualCanvas,
                        new Rectangle2D.Float(0f, 0f, 10f, 10f), new Rectangle2D.Float(0f, 0f, 10f, 10f)));
        ((XpsVisualBrush)gridPath.getFill()).setTileMode(XpsTileMode.Tile);

        // New canvas
        XpsCanvas canvas = doc.addCanvas();
        canvas.setRenderTransform(doc.createMatrix(1f, 0f, 0f, 1f, 268f, 70f));
        // Add grid
        canvas.addPath(pathGeometry);
        // Red transparent rectangle in the middle top
        XpsPath path = canvas.addPath(doc.createPathGeometry("M 30,20 l 258.24,0 0,56.64 -258.24,0 Z"));
        path = canvas.addPath(doc.createPathGeometry("M 10,10 L 228,10 228,100 10,100"));
        path.setFill(doc.createSolidColorBrush(doc.createColor(1.0f, 0.0f, 0.0f)));
        path.setOpacity(0.7f);
        // Save resultant XPS document
        doc.save(dataDir + "AddGrid_out.xps");
        //ExEnd:AddGrid
    }    
}
