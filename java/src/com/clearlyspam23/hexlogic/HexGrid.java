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
 * A Hexagon grid, generally contained as a square (although, this could be adapted to be whatever shape)
 * 
 * @author clearlyspam23
 *
 * @param <T> the Tile object to store in this grid
 */
public class HexGrid<T> {
	
	private Object[][] tiles;
	
	/**
	 * constructs a new HexGrid with the given dimensions
	 * @param width the horizontal area of the hexagon grid
	 * @param height the vertical area of the hexagon grid
	 */
	public HexGrid(int width, int height){
		tiles = new Object[width][height];
	}
	
	/**
	 * gets a tile at the given raw location
	 * raw location means the actual array position of the tile, not it's location in hex space
	 * @param x the 1st array's index
	 * @param y the 2nd array's index
	 * @return the tile at that location
	 */
	@SuppressWarnings("unchecked")
	public T getAtRawLocation(int x, int y)
	{
		return (T)tiles[x][y];
	}
	
	/**
	 * gets a tile at the given raw location
	 * raw location means the actual array position of the tile, not it's location in hex space
	 * @param loc the location in array space
	 * @return the tile at that location
	 */
	public T getAtRawLocation(Location loc)
	{
		return getAtRawLocation(loc.x(), loc.y());
	}
	
	/**
	 * sets the tile at the given raw location
	 * raw location means the actual array position of the tile, not it's location in hex space
	 * @param x the 1st array's index
	 * @param y the 2nd array's index
	 * @param t the tile to set that Location to
	 */
	public void setAtRawLocation(int x, int y, T t){
		tiles[x][y] = t;
	}
	
	/**
	 * sets the tile at the given raw location
	 * raw location means the actual array position of the tile, not it's location in hex space
	 * @param loc the location in array space
	 * @param t the tile at that location
	 */
	public void setAtRawLocation(Location loc, T t){
		setAtRawLocation(loc.x(), loc.y(), t);
	}
	
	/**
	 * takes a location, in global hexagon space, and converts it to local array space
	 * @param location a location in global hexagon space
	 * @return a location in local array space
	 */
	public Location convert(HexLocation location){
		return new Location(location.x() + (location.y())/2, location.y());
	}
	
	/**
	 * takes a location, in global hexagon space, and converts it to local array space
	 * similar to the other convert function, except this stores the answer in the passed in Location
	 * @param locIn a location in global hexagon space
	 * @param locOut the object to store the result in
	 * @return locOut, for chaining
	 */
	public Location convert(HexLocation locIn, Location locOut){
		locOut.set(locIn.x() + (locIn.y())/2, locIn.y());
		return locOut;
	}
	
	/**
	 * takes a location, in local array space, and converts it to global hexagon space
	 * @param location a location in local array space
	 * @return a location in global hexagon space
	 */
	public HexLocation convert(Location location){
		return new HexLocation(location.x() - location.y()/2, location.y());
	}
	
	/**
	 * takes a location, in local array space, and converts it to global hexagon space
	 * similar to the other convert function, except this stores the answer in the passed in HexLocation
	 * @param locIn a location in local array space
	 * @param locOut the object to store the result in
	 * @return locOut, for chaining
	 */
	public HexLocation convert(Location locIn, HexLocation locOut){
		locOut.set(locIn.x() - locIn.y()/2, locIn.y());
		return locOut;
	}
	
	/**
	 * checks if the given raw location is a valid index of the backing array
	 * @param loc the Location to check
	 * @return whether or not that location is valid
	 */
	public boolean isValidLocation(Location loc){
		return loc.x()>=0&&loc.x()<tiles.length&&loc.y()>=0&&loc.y()<tiles[0].length;
	}
	
	private Location cachedLocation = new Location();
	
	/**
	 * checks if the given raw location is a valid location for this HexGrid
	 * @param loc the HexLocation to check
	 * @return whether or not that location is valid
	 */
	public boolean isValidLocation(HexLocation loc){
		convert(loc, cachedLocation);
		return isValidLocation(cachedLocation);
	}
	
	/**
	 * gets the tile at the given HexLocation
	 * @param location the HexLocation to get
	 * @return the tile at the given HexLocation
	 */
	public T get(HexLocation location){
		convert(location, cachedLocation);
		return getAtRawLocation(cachedLocation.x(), cachedLocation.y());
	}
	
	private HexLocation cachedHexLocation = new HexLocation();
	
	/**
	 * gets the tile at the HexLocation represented by the given coordinates
	 * @param x
	 * @param y
	 * @return the tile at the given HexLocation
	 */
	public T get(int x, int y){
		convert(cachedHexLocation.set(x, y), cachedLocation);
		return getAtRawLocation(cachedLocation.x(), cachedLocation.y());
	}

	/**
	 * gets the height of this HexGrid
	 * @return this HexGrid's height
	 */
	public int getHeight() {
		return tiles[0].length;
	}

	/**
	 * gets the width of this HexGrid
	 * @return this HexGrid's width
	 */
	public int getWidth() {
		return tiles.length;
	}

}
