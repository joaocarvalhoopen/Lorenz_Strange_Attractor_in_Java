/******************************************************************************
 *                                                                            *
 *  Lorenz Strange Attractor in Java                                          *
 *                                                                            *
 ******************************************************************************
 * Author: Joao Nuno Carvalho                                                 *
 * Date: 2019.11.12                                                           *
 * License: MIT Open Source License                                           *
 * Description: This is a simple implementation in Java of a Lorenz Strange   *
 *              Attractor. It makes all the calculations for 1500 iteration   *
 *              steps and then generates an SVG file of the trajectory.       *
 *                                                                            *
 * From Wikipedia: The Lorenz system is a system of ordinary differential     *
 *                 equations first studied by Edward Lorenz. It is notable    *
 * for having chaotic solutions for certain parameter values and initial      *
 * conditions. In particular, the Lorenz attractor is a set of chaotic        *
 * solutions of the Lorenz system. In popular media the 'butterfly effect'    *
 * stems from the real-world implications of the Lorenz attractor, i.e. that  *
 * in any physical system, in the absence of perfect knowledge of the initial *
 * conditions (even the minuscule disturbance of the air due to a butterfly   *
 * flapping its wings), our ability to predict its future course will always  *
 * fail. This underscores that physical systems can be completely             *
 * deterministic and yet still be inherently unpredictable even in the        *
 * absence of quantum effects. The shape of the Lorenz attractor itself,      *
 * when plotted graphically, may also be seen to resemble a butterfly.        *
 *                                                                            *
 ******************************************************************************
 * References:                                                                *
 *   Lorenz System (Wikipedia)                                                *
 *   https://en.wikipedia.org/wiki/Lorenz_system                              *
 *   Programming the Lorenz Attractor                                         *
 *   https://www.algosome.com/articles/lorenz-attractor-programming-code.html *
 *                                                                            *
 ******************************************************************************
*/

package com.joaocarvalhoopen.lorenzStrangeAttractor;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Locale;

public class SVGFileGenerator {

    public SVGFileGenerator() {

    }

    public void generateSVG(String filename, Point3D[] ptsArray) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(filename, "UTF-8");
            writer.print(getHeader());
            writer.print(getBackground());
            for (Point3D pt3D : ptsArray) {
                double cx = 250 + 8 * pt3D.x;
                double cy = 35  + 8 * pt3D.z;
                double r = 2;
                writer.print(genCircle(cx, cy, r));
            }
            writer.print(getFooter());
        } catch(IOException ex) {
            System.out.println("Error while writting to SVG file!");
        } finally {
            writer.close();
        }
}

    /*
    <svg version="1.1"
         baseProfile="full"
         width="300" height="300"
         xmlns="http://www.w3.org/2000/svg">
    */
    private String getHeader() {
        String header = "<svg version=\"1.1\"\n" +
                        "baseProfile=\"full\"\n" +
                        "width=\"500\" height=\"500\"\n" +
                        "xmlns=\"http://www.w3.org/2000/svg\">\n";

        return header;
    }

    // <rect width="100%" height="100%" fill="black" />
    private String getBackground() {
        String background = "<rect width=\"100%\" height=\"100%\" fill=\"black\" />\n";
        return background;
    }

    // </svg>
    private String getFooter() {
        String footer = "</svg>\n";
        return footer;
    }

    // <circle cx="150" cy="100" r="80" fill="green" />
    private String genCircle(double cx, double cy, double r) {
        String circle = String.format(Locale.ROOT,
                "<circle cx=\"%.2f\" cy=\"%.2f\" r=\"%.2f\" fill=\"blue\" />\n", cx, cy, r);
        return circle;
    }

    // <text x="150" y="125" font-size="60" text-anchor="middle" fill="white">SVG</text>

}
