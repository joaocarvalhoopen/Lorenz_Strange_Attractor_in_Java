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
                double cx = transformX(pt3D.x);
                double cy = transformZ(pt3D.z);
                double r = 2;
                writer.print(genCircle(cx, cy, r));
            }
            writer.print(genPath(ptsArray));
            writer.print(getFooter());
        } catch(IOException ex) {
            System.out.println("Error while writting to SVG file!");
        } finally {
            writer.close();
        }
    }

    private double transformX(double x){
        return 250 + 8 * x;
    }

    private double transformZ(double z){
        return 35  + 8 * z;
    }

    /*
    <svg version="1.1"
         baseProfile="full"
         width="300" height="300"
         xmlns="http://www.w3.org/2000/svg"
         "xmlns:xlink=\"http://www.w3.org/1999/xlink\">
    */
    private String getHeader() {
        String header = "<svg version=\"1.1\"\n" +
                        "baseProfile=\"full\"\n" +
                        "width=\"500\" height=\"500\"\n" +
                        "xmlns=\"http://www.w3.org/2000/svg\"\n" +
                        "xmlns:xlink=\"http://www.w3.org/1999/xlink\">\n";

        return header;
    }

    // <rect width="100%" height="100%" fill="black" />
    private String getBackground() {
        String background = "<rect width=\"100%\" height=\"100%\" fill=\"black\" />\n";
        return background;
    }

    /*
        <circle id="circle" cx="0" cy="0" r="3" fill="yellow" />

        <animateMotion
                xlink:href="#circle"
                dur="3s"
                begin="0s"
                fill="freeze"
                repeatCount="indefinite">
            <mpath xlink:href="#motionPath" />
        </animateMotion>
    */
    // </svg>
    private String getFooter() {
        String footer = "\n" +
                        "    <animateMotion\n" +
                        "            xlink:href=\"#circle\"\n" +
                        "            dur=\"20s\"\n" +
                        "            begin=\"0s\"\n" +
                        "            fill=\"freeze\"\n" +
                        "            repeatCount=\"indefinite\">\n" +
                        "        <mpath xlink:href=\"#motionPath\" />\n" +
                        "    </animateMotion>" +
                        "</svg>\n";
        return footer;
    }

    // <circle cx="150" cy="100" r="80" fill="green" />
    private String genCircle(double cx, double cy, double r) {
        String circle = String.format(Locale.ROOT,
                "<circle cx=\"%.2f\" cy=\"%.2f\" r=\"%.2f\" fill=\"blue\" />\n", cx, cy, r);
        return circle;
    }

    /*
     <path id="motionPath" fill="none" stroke="#000000" d="M0,0L100,100L200,200" />
     */
    private String genPath(Point3D[] ptsArray) {
        double curX = transformX(ptsArray[0].x);
        double curZ = transformZ(ptsArray[0].z);
        StringBuffer sBuf = new StringBuffer(10000);
        // String pathBegin = "<path id=\"motionPath\" fill=\"none\" stroke=\"#000000\" d=\"M" +
        String pathBegin = "<path id=\"motionPath\" fill=\"none\" d=\"M" +
                String.format(Locale.ROOT, "%.2f,%.2f\n",
                curX,
                curZ);
        sBuf.append(pathBegin);
        for(int i = 0; i < ptsArray.length; i++ ){
            curX = transformX(ptsArray[i].x);
            curZ = transformZ(ptsArray[i].z);
            sBuf.append("L");
            sBuf.append(curX);
            sBuf.append(",");
            sBuf.append(curZ);   // it's translateY in the SVG path.
            sBuf.append("\n");
        }
        String pathEnd = "\" />\n";
        sBuf.append(pathEnd);
        sBuf.append(String.format(Locale.ROOT,
                "<circle id=\"circle\" cx=\"%.2f\" cy=\"%.2f\" r=\"3\" fill=\"yellow\" />\n",
                 0.0,
                        0.0));
        return sBuf.toString();
    }

    // <text x="150" y="125" font-size="60" text-anchor="middle" fill="white">SVG</text>

}
