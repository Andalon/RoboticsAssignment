windowsize 600 600

//left right bottom top
bounds 0 10 0 10

seed 3

time 10.0 

maxtimestep 100 

background xFFFFFF

//Grid values, setting it: grid xNum yNum
robot EDU.gatech.cc.is.abstractrobot.MultiForageN150Sim com.robotsagentshumans.assignment.Q2RobotController 0.5 0.5 0 x000000 xFF0000 2

//Setting up the Obstacles
object EDU.cmu.cs.coral.simulation.PolygonObstacleSim 2.5 9.5 0 0.5 xC0C0C0 x000000 2
object EDU.cmu.cs.coral.simulation.PolygonObstacleSim 3.5 9.5 0 0.5 xC0C0C0 x000000 2
object EDU.cmu.cs.coral.simulation.PolygonObstacleSim 4.5 9.5 0 0.5 xC0C0C0 x000000 2
object EDU.cmu.cs.coral.simulation.PolygonObstacleSim 2.5 8.5 0 0.5 xC0C0C0 x000000 2
object EDU.cmu.cs.coral.simulation.PolygonObstacleSim 3.5 8.5 0 0.5 xC0C0C0 x000000 2
object EDU.cmu.cs.coral.simulation.PolygonObstacleSim 4.5 8.5 0 0.5 xC0C0C0 x000000 2
object EDU.cmu.cs.coral.simulation.PolygonObstacleSim 2.5 7.5 0 0.5 xC0C0C0 x000000 2
object EDU.cmu.cs.coral.simulation.PolygonObstacleSim 3.5 7.5 0 0.5 xC0C0C0 x000000 2
object EDU.cmu.cs.coral.simulation.PolygonObstacleSim 4.5 7.5 0 0.5 xC0C0C0 x000000 2
object EDU.cmu.cs.coral.simulation.PolygonObstacleSim 2.5 2.5 0 0.5 xC0C0C0 x000000 2
object EDU.cmu.cs.coral.simulation.PolygonObstacleSim 3.5 2.5 0 0.5 xC0C0C0 x000000 2
object EDU.cmu.cs.coral.simulation.PolygonObstacleSim 4.5 2.5 0 0.5 xC0C0C0 x000000 2
object EDU.cmu.cs.coral.simulation.PolygonObstacleSim 2.5 1.5 0 0.5 xC0C0C0 x000000 2
object EDU.cmu.cs.coral.simulation.PolygonObstacleSim 3.5 1.5 0 0.5 xC0C0C0 x000000 2
object EDU.cmu.cs.coral.simulation.PolygonObstacleSim 4.5 1.5 0 0.5 xC0C0C0 x000000 2
object EDU.cmu.cs.coral.simulation.PolygonObstacleSim 2.5 0.5 0 0.5 xC0C0C0 x000000 2
object EDU.cmu.cs.coral.simulation.PolygonObstacleSim 3.5 0.5 0 0.5 xC0C0C0 x000000 2
object EDU.cmu.cs.coral.simulation.PolygonObstacleSim 4.5 0.5 0 0.5 xC0C0C0 x000000 2
object EDU.cmu.cs.coral.simulation.PolygonObstacleSim 7.5 9.5 0 0.5 xC0C0C0 x000000 2
object EDU.cmu.cs.coral.simulation.PolygonObstacleSim 7.5 8.5 0 0.5 xC0C0C0 x000000 2
object EDU.cmu.cs.coral.simulation.PolygonObstacleSim 7.5 7.5 0 0.5 xC0C0C0 x000000 2
object EDU.cmu.cs.coral.simulation.PolygonObstacleSim 7.5 6.5 0 0.5 xC0C0C0 x000000 2
object EDU.cmu.cs.coral.simulation.PolygonObstacleSim 7.5 5.5 0 0.5 xC0C0C0 x000000 2
object EDU.cmu.cs.coral.simulation.PolygonObstacleSim 7.5 4.5 0 0.5 xC0C0C0 x000000 2
object EDU.cmu.cs.coral.simulation.PolygonObstacleSim 7.5 3.5 0 0.5 xC0C0C0 x000000 2
object EDU.cmu.cs.coral.simulation.PolygonObstacleSim 7.5 2.5 0 0.5 xC0C0C0 x000000 2
object EDU.cmu.cs.coral.simulation.PolygonObstacleSim 7.5 1.5 0 0.5 xC0C0C0 x000000 2

//Setting the 'goal' location
object EDU.gatech.cc.is.simulation.BinSim 9.5 4.5 0 0.5 xFF0000 xFF0000 4

//Setting up the grid by using a BinClass that is invisible
object EDU.gatech.cc.is.simulation.BinSim 0.5 0.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 1.5 0.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 2.5 0.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 3.5 0.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 4.5 0.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 5.5 0.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 6.5 0.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 7.5 0.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 8.5 0.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 9.5 0.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 0.5 1.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 1.5 1.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 2.5 1.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 3.5 1.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 4.5 1.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 5.5 1.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 6.5 1.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 7.5 1.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 8.5 1.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 9.5 1.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 0.5 2.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 1.5 2.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 2.5 2.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 3.5 2.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 4.5 2.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 5.5 2.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 6.5 2.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 7.5 2.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 8.5 2.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 9.5 2.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 0.5 3.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 1.5 3.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 2.5 3.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 3.5 3.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 4.5 3.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 5.5 3.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 6.5 3.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 7.5 3.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 8.5 3.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 9.5 3.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 0.5 4.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 1.5 4.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 2.5 4.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 3.5 4.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 4.5 4.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 5.5 4.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 6.5 4.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 7.5 4.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 8.5 4.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 0.5 5.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 1.5 5.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 2.5 5.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 3.5 5.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 4.5 5.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 5.5 5.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 6.5 5.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 7.5 5.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 8.5 5.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 9.5 5.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 0.5 6.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 1.5 6.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 2.5 6.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 3.5 6.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 4.5 6.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 5.5 6.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 6.5 6.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 7.5 6.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 8.5 6.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 9.5 6.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 0.5 7.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 1.5 7.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 2.5 7.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 3.5 7.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 4.5 7.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 5.5 7.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 6.5 7.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 7.5 7.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 8.5 7.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 9.5 7.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 0.5 8.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 1.5 8.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 2.5 8.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 3.5 8.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 4.5 8.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 5.5 8.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 6.5 8.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 7.5 8.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 8.5 8.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 9.5 8.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 0.5 9.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 1.5 9.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 2.5 9.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 3.5 9.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 4.5 9.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 5.5 9.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 6.5 9.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 7.5 9.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 8.5 9.5 0 0.5 x0000BB x000000 -1
object EDU.gatech.cc.is.simulation.BinSim 9.5 9.5 0 0.5 x0000BB x000000 -1

