/*
 * va_Obstacles_r.java
 */

package EDU.gatech.cc.is.clay;

import java.lang.*;
import EDU.gatech.cc.is.abstractrobot.SimpleInterface;
import EDU.gatech.cc.is.util.Vec2;

/**
 * Report a list of Vec2s pointing to
 * obstacles detected by the robot.
 * <P>
 * For detailed information on how to configure behaviors, see the
 * <A HREF="../clay/docs/index.html">Clay page</A>.
 * <P>
 * <A HREF="../COPYRIGHT.html">Copyright</A>
 * (c)1998 Tucker Balch
 *
 * @author Tucker Balch
 * @version $Revision: 1.1 $
 */


public class va_Obstacles_r extends NodeVec2Array
	{
	/** 
	 * Turn debug printing on or off.
	 */
	public static final boolean DEBUG = Node.DEBUG;
	private SimpleInterface	abstract_robot;

	/**
	 * Instantiate a va_Obstacles_r schema.
	 * @param ar SimpleInterface, the abstract_robot object 
	 *           that provides hardware support.
	 */
	public va_Obstacles_r(SimpleInterface ar)
		{
		if (DEBUG) System.out.println("va_Obstacles_r: instantiated");
		abstract_robot = ar;
		}

	/**
	 * Return an array of Vec2s pointing from the
	 * center of the robot to the detected obstacles.
	 * @param timestamp long, only get new information 
	 *        if timestamp > than last call or timestamp == -1.
	 * @return the sensed obstacles
	 */
	public Vec2[] Value(long timestamp)
		{
		if (DEBUG) System.out.println("va_Obstacles_r: Value()");
		return(abstract_robot.getObstacles(timestamp));
		}
        }
