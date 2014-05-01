package com.clearlyspam23.hexlogic;

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
 * An enum representing the hexagon directions used by this library.
 * 
 * @author clearlyspam23
 *
 */
public enum HexDirection {
	
	east(1, 0), southeast(1, -1), southwest(0, -1), west(-1, 0), northwest(-1, 1), northeast(0, 1);
	
	private final HexLocation offset;
	
	private HexDirection(int x, int y){
		offset = new HexLocation(x, y);
	}
	
	/**
	 * gets the horizontal component of this HexDirection
	 * The coordinate system is an axial one, with east representing positive x, and west representing negative x
	 * @return the horizontal component of this HexDirection
	 */
	public int offsetX(){
		return offset.x();
	}
	
	/**
	 * gets the vertical component of this HexDirection
	 * the coordinate system is an axial one, with northeast representing positive y, and southwest as negative y
	 * @return the vertical component of this HexDirection
	 */
	public int offsetY(){
		return offset.y();
	}
	
	/**
	 * returns the opposite direction of this direction.
	 * For example, if called on HexDirection.west, will return HexDirection.east
	 * @return the opposite direction of this direction
	 */
	public HexDirection getOpposite(){
		switch(this){
		case east:
			return west;
		case west:
			return east;
		case southwest:
			return northeast;
		case southeast:
			return northwest;
		case northeast:
			return southwest;
		case northwest:
			return southeast;
		}
		return null;
	}
	
	/**
	 * gets a number based on how different these two hex directions are
	 * 0 if they are the same, 3 if they are opposites, 1 if they are off by 1, 2 if they are off by 2
	 * @param other the other HexDirection to test against
	 * @return the offset factor
	 */
	public int getOffsetFactor(HexDirection other)
	{
		int sum = getMagSum(other);
		switch(sum){
		case 0:
			return 3;
		case 1:
		case 2:
			return 2;
		case 3:
		case 4:
			if(this==other)
				return 0;
			return 1;
		}
		return 0;
	}
	
	/**
	 * The sum of the magnitudes of all the offsets of the two directions, used to calculate the offset factor
	 * This value is smaller the more different the two directions are, if they are opposites, this value is 0
	 * @param other the direction to compare against
	 * @return 0 if they are opposite directions, 2 if they are almost opposites, 4 if they are almost the same/the same
	 */
	public int getMagSum(HexDirection other){
		int xVal = Math.abs(offsetX()+other.offsetX());
		int yVal = Math.abs(offsetY()+other.offsetY());
		int zVal = Math.abs(offset.z()+other.offset.z());
		return xVal+yVal+zVal;
	}

}
