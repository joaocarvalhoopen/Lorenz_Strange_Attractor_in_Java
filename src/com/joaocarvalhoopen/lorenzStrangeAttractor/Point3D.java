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

import java.util.Locale;

public class Point3D {
    double x;
    double y;
    double z;

    public Point3D(double x, double y, double z){
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public Point3D(Point3D pt){
        // Calls the default constructor.
        this(pt.x, pt.y, pt.z);
    }

    @Override
    public String toString(){
       return String.format(Locale.ROOT, "x= %.2f y= %.2f z= %.2f", x, y, z);
    }
}
