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
 * A simple 2d intege vector, used largely for ease of conversion between HexLocations and array locations
 * @author clearlyspam23
 *
 */
public class Location {
	
	private int x;
	private int y;
	
	/**
	 * constructs a new Location at <0, 0>
	 */
	public Location(){
		
	}
	
	/**
	 * constructs a new Location at <x, y>
	 * @param x
	 * @param y
	 */
	public Location(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * constructs a new Location to be a copy of the given Location
	 * @param other the Location to copy
	 */
	public Location(Location other){
		x = other.x;
		y = other.y;
	}
	
	/**
	 * sets this Location to <x, y>
	 * @param x
	 * @param y
	 */
	public void set(int x, int y){
		this.x = x;
		this.y = y;
	}
	
	/**
	 * sets this Location to the coordinates of the other Location
	 * @param loc the Location to set to
	 */
	public void set(Location loc){
		x = loc.x;
		y = loc.y;
	}
	
	/**
	 * @return the x component of this Location
	 */
	public int x(){
		return x;
	}
	
	/**
	 * @return the y component of this Location
	 */
	public int y(){
		return y;
	}
	
	/**
	 * translates this Location by the given coordinates
	 * @param x
	 * @param y
	 */
	public void translate(int x, int y){
		this.x+=x;
		this.y+=y;
	}
	
	/**
	 * translates this Location by the given other Location
	 * @param location
	 */
	public void translate(Location location){
		translate(location.x, location.y);
	}
	
	/**
	 * creates and returns a copy of this Location
	 * @return a copy of this Location
	 */
	public Location copy(){
		return new Location(x, y);
	}
	
	public String toString(){
		return "[" + x +", " + y + "]";
	}

}
