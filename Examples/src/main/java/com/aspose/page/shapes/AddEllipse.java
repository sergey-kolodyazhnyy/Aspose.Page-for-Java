/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.aspose.page.shapes;

import com.aspose.page.utilities.Utils;
import com.aspose.xps.XpsCanvas;
import com.aspose.xps.XpsDocument;
import com.aspose.xps.XpsGradientBrush;
import com.aspose.xps.XpsGradientStop;
import com.aspose.xps.XpsPath;
import com.aspose.xps.XpsSpreadMethod;
import java.awt.geom.Point2D;
import java.util.LinkedList;
import java.util.List;

public class AddEllipse {
    
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
            new Point2D.Float[] { new Point2D.Float(240f, 5f), new Point2D.Float(240f, 310f), new Point2D.Float(0f, 310f) }));
        pathGeometry.get(0).setStartPoint(new Point2D.Float(0f, 5f));

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
        canvas.add(gridPath);
        // Red transparent rectangle in the middle top
        XpsPath path = canvas.addPath(doc.createPathGeometry("M 10,10 L 228,10 228,100 10,100"));
        path.setFill(doc.createSolidColorBrush(doc.createColor(1.0f, 0.0f, 0.0f)));
        path.setOpacity(0.7f);
        // Save resultant XPS document
        doc.save(dataDir + "AddGrid_out.xps");
        // ExEnd:1
    }

    
}
