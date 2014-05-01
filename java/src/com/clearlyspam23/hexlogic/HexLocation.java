package com.clearlyspam23.hexlogic;

import java.util.ArrayList;
import java.util.List;

/**
The MIT License (MIT)

Copyright (c) 2014 John Ader

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in
all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
THE SOFTWARE.
 */

/**
 * 
 * A basic location in a Hexagon coordinate system
 * The hexagons are assumed to be pointy topped (left and right side sharing an edge), and the coordinate system is an axial one,
 * with east representing positive x, west representing negative x, northeast representing positive y, and southwest as negative y
 * 
 * @author clearlyspam23
 *
 */
public class HexLocation {
	
	private int x;
	private int y;
	private int z;
	
	/**
	 * constructs a HexLocation, at <0, 0>
	 */
	public HexLocation(){
		
	}
	
	/**
	 * constructs a HexLocation, at <x, y>
	 * @param x the horizontal component of this HexLocation
	 * @param y the diagonal component of this HexLocation
	 */
	public HexLocation(int x, int y){
		set(x, y);
	}
	
	/**
	 * constructs a HexLocation, using the location of the other HexLocation
	 * @param other the HexLocation to base this location off of.
	 */ 
	public HexLocation(HexLocation other){
		x = other.x;
		y = other.y;
		z = other.z;
	}
	
	/**
	 * sets this HexLocation
	 * @param x the horizontal component
	 * @param y the diagonal component
	 * @return this HexLocation for chaining
	 */
	public HexLocation set(int x, int y){
		this.x = x;
		this.y = y;
		z = -x-y;
		return this;
	}
	
	/**
	 * sets this HexLocation to be equivalent to another
	 * @param loc the HexLocation to set this to.
	 * @return this HexLocation for chaining
	 */
	public HexLocation set(HexLocation loc){
		return set(loc.x, loc.y);
	}
	
	/**
	 * gets the x component of this HexLocation
	 * @return the x component
	 */
	public int x(){
		return x;
	}
	
	/**
	 * gets the y component of this HexLocation
	 * @return the y component
	 */
	public int y(){
		return y;
	}
	
	protected int z(){
		return z;
	}
	
	/**
	 * moves this HexLocation 1 unit in the Direction of HexDirection
	 * @param direction the direction to translate towards
	 * @return this HexLocation for chaining
	 */
	public HexLocation translate(HexDirection direction){
		return translate(direction.offsetX(), direction.offsetY());
	}
	
	/**
	 * moves this HexLocation amount units in the Direction of HexDirection
	 * @param direction the direction to translate towards
	 * @param amount the amount to move
	 * @return this HexLocation for chaining
	 */
	public HexLocation translate(HexDirection direction, int amount){
		return translate(direction.offsetX()*amount, direction.offsetY()*amount);
	}
	
	/**
	 * translates this HexLocation by <x, y>
	 * @param x the horizontal direction to translate by
	 * @param y the diagonal direction to translate by
	 * @return this HexLocation for chaining
	 */
	public HexLocation translate(int x, int y){
		return set(this.x+x, this.y+y);
	}
	
	/**
	 * translates this HexLocation by the other location
	 * @param location the location to translate by
	 * @return this HexLocation for chaining
	 */
	public HexLocation translate(HexLocation location){
		return translate(location.x, location.y);
	}
	
	/**
	 * calculates the hexagon distance to the other HexLocation
	 * @param other the other hexagon
	 * @return the distance between this hexagon and the other
	 */
	public int distanceTo(HexLocation other){
		return (Math.abs(x-other.x) + Math.abs(y-other.y) + Math.abs(z - other.z))/2;
	}
	
	/**
	 * calculates the hexagon distance to the location described by <x, y>
	 * @param x the horizontal direction to translate by
	 * @param y the diagonal direction to translate by
	 * @return the distance between this hexagon and the other
	 */
	public int distanceTo(int x, int y){
		return (Math.abs(this.x-x) + Math.abs(this.y-y) + Math.abs(z - (-x-y)))/2;
	}
	
	/**
	 * static version of the translation function, returns a new HexLocation, rather than changing the given one
	 * @param location the HexLocation to start from
	 * @param dir the direction to translate in
	 * @return a new HexLocation translated 1 unit in direction dir from location
	 */
	public static HexLocation translate(HexLocation location, HexDirection dir){
		HexLocation l = new HexLocation(location);
		l.translate(dir);
		return l;
	}
	
	/**
	 * static version of the translation function, returns a new HexLocation, rather than changing the given one
	 * @param location the HexLocation to start from
	 * @param dir the direction to translate in
	 * @param amount the amount to translate by
	 * @return a new HexLocation translated amount units in direction dir from location
	 */
	public static HexLocation translate(HexLocation location, HexDirection dir, int amount){
		HexLocation l = new HexLocation(location);
		l.translate(dir, amount);
		return l;
	}
	
	/**
	 * rounds 3 float coordinates into a HexLocation, used generally with calculating what HexLocation a camera falls in
	 * @param x the horizontal component
	 * @param y the diagonal component
	 * @param z the other diagonal, usually given by -x-y
	 * @return the approximate HexLocation
	 */
	public static HexLocation round(float x, float y, float z)
	{
		return round(x, y, z, new HexLocation());
	}
	
	/**
	 * rounds 3 float coordinates into a HexLocation, used generally with calculating what HexLocation a camera falls in
	 * @param x the horizontal component
	 * @param y the diagonal component
	 * @param z the other diagonal, usually given by -x-y
	 * @param outLoc, the HexLocation to store the result in
	 * @return outLoc, for chaining
	 */
	public static HexLocation round(float x, float y, float z, HexLocation outLoc){
		int roundX = Math.round(x);
		int roundY = Math.round(y);
		int roundZ = Math.round(z);
		float diffX = Math.abs(x-roundX);
		float diffY = Math.abs(y-roundY);
		float diffZ = Math.abs(z-roundZ);
		if(diffX>diffY&&diffX>diffZ)
			roundX = -roundY-roundZ;
		else if(diffY>diffZ)
			roundY = -roundX-roundZ;
		outLoc.set(roundX, roundY);
		return outLoc;
	}
	
	/**
	 * calculates all HexLocations forming a line between two locations
	 * @param loc1 the first location
	 * @param loc2 the second location
	 * @return a line between the two locations
	 */
	public static List<HexLocation> getLine(HexLocation loc1, HexLocation loc2){
		
		//first, calculate the maximum steps away
		float max = loc1.distanceTo(loc2);
		//then, calculate steps
		float x = (loc2.x - loc1.x)/max;
		float y = (loc2.y - loc1.y)/max;
		float z = (loc2.z - loc1.z)/max;
		List<HexLocation> ans = new ArrayList<HexLocation>();
		ans.add(loc1);
		float lastX = loc1.x;
		float lastY = loc1.y;
		float lastZ = loc1.z;
		for(int i = 0; i < max; i++)
		{
			lastX+=x;
			lastY+=y;
			lastZ+=z;
			ans.add(round(lastX, lastY, lastZ));
		}
		return ans;
	}
	
	/**
	 * creates and returns a copy of this HexLocation
	 * @return a copy of this Hexlocation
	 */
	public HexLocation copy(){
		return new HexLocation(x, y);
	}
	
	public String toString(){
		return "<" + x +", " + y + ">";
	}
	
	public boolean equals(Object o){
		if(!(o instanceof HexLocation))
			return false;
		return equals((HexLocation)o);
	}
	
	public boolean equals(HexLocation o){
		return x==o.x&&y==o.y&&z==o.z;
	}
	
	/**
	 * gets the closest direction towards the other HexLocation
	 * @param other the HexLocation to get the direction towards
	 * @return the direction towards the other HexLocation
	 */
	public HexDirection getDirectionTowards(HexLocation other){
		HexLocation relative = new HexLocation(x-other.x, y-other.y);
		int magnitude = distanceTo(other);
		int difference = Integer.MAX_VALUE;
		HexDirection ans = null;
		for(HexDirection d : HexDirection.values())
		{
			HexLocation temp = new HexLocation(d.offsetX()*magnitude, d.offsetY()*magnitude);
			int tempDiff = Math.abs(temp.x-relative.x) + Math.abs(temp.y-relative.y) + Math.abs(temp.z-relative.z);
			if(tempDiff<difference){
				difference = tempDiff;
				ans = d;
			}
		}
		return ans;
	}

}
