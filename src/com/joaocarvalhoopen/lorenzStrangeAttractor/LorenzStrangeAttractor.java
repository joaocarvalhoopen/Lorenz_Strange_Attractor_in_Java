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

public class LorenzStrangeAttractor {
    final int maxNumSteps = 1500;
    Point3D curPoint;

    public LorenzStrangeAttractor(){
        curPoint = new Point3D(0.1,0,0);
    }

    public void reset( ){
        curPoint.x = 0.1;
        curPoint.y = 0;
        curPoint.z = 0;
    }

    /*
       Lorenz strange attractor differential equations.

       Fixed parameters:
       sigma = 10
       rho   = 28
       beta  = 8/3

       Equations:
       dx / dt = sigma * ( y - x)
       dy / dt = x * (rho - z) - y
       dz / dt = x * y - beta * z

    */
    public Point3D calcNextPoint(){
        // Attractor constants.
        final double sigma = 10.0;
        final double rho   = 28.0;
        final double beta  = 8.0/3.0;
        // Delta t / small interval of t (time) / step in t (time).
        final double dt = 0.01;

        double dxdt = sigma * (curPoint.y - curPoint.x);
        double dydt = curPoint.x * (rho - curPoint.z) - curPoint.y;
        double dzdt = curPoint.x * curPoint.y - beta * curPoint.z;
        curPoint.x += dt * dxdt;
        curPoint.y += dt * dydt;
        curPoint.z += dt * dzdt;
        return new Point3D(curPoint);
    }

    public Point3D[] generateLorenzPoints3D(){
        Point3D[] ptsArray = new Point3D[maxNumSteps];
        for (int i=0; i < maxNumSteps; i++){
            ptsArray[i] = calcNextPoint();
            System.out.println(ptsArray[i]);
        }
        return ptsArray;
    }

}
