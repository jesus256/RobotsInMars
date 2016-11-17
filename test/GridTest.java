
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class GridTest {

	Grid grid, grid2x2;

	@Before
	public void setUp() throws Exception {
		grid = new Grid(0, 0, 0, 0);
		grid2x2 = new Grid(2, 2, 0, 0);

	}

	@Test
	public void rectangular_grid_with_x0_and_y0() {

		assertNotNull(grid);
	}

	@Test
	public void set_scent_at_0_0() {

		Coord coord = new Coord(0, 0, 0);
		grid.setScent(coord);

		assertTrue("scent is at 0,0", grid.hasScent(coord));
	}

	@Test
	public void is_0_0_in_the_boundary() {

		assertTrue("0,0 is in the bounds", grid.isInBounds(new Coord(0, 0, 0)));
	}

	@Test
	public void is_0_1_in_the_boundary() {

		assertFalse("0,1 is not in the bounds", grid.isInBounds(new Coord(0, 1, 0)));
	}

	@Test
	public void set_scent_at_1_2() {

		Coord coord = new Coord(1, 2, 0);
		grid2x2.setScent(coord);

		assertTrue("scent is at 1,2", grid2x2.hasScent(coord));
	}

	@Test
	public void is_1_2_in_the_boundary() {

		assertTrue("1,2 is in the bounds", grid2x2.isInBounds(new Coord(1, 2, 0)));
	}

	@Test
	public void is_1_minus3_in_the_boundary() {

		assertFalse("1,-3 is not in the bounds", grid2x2.isInBounds(new Coord(1, -3, 0)));
	}

}

/*
 * 
 * 'use strict'; var assert = require('chai').assert, Grid =
 * require('../lib/grid.js').Grid, Coord = require('../lib/coord').Coord;
 * 
 * describe('Grid module.', function() {
 * 
 * it('has Grid', function() { assert.isDefined(Grid, 'is Function'); });
 * 
 * describe('In coord Grid :', function() {
 * 
 * // lets create a coord grid var grid = new Grid(0, 0);
 * 
 * it('rectangular grid with l=0 aand b=0', function() { assert.isObject(grid,
 * 'grid is created'); });
 * 
 * // has functions defined by API it('Verifying API', function() {
 * assert.isFunction(grid.hasScent, 'is available');
 * assert.isFunction(grid.setScent, 'is available');
 * assert.isFunction(grid.isInBounds, 'is available'); });
 * 
 * it('set scent at 0,0', function() { var coord = new Coord(0, 0);
 * grid.setScent(coord);
 * 
 * assert.isTrue(grid.hasScent(coord), 'scent is at 0,0'); });
 * 
 * it('is 0,0 in the boundary?', function() { assert.isTrue(grid.isInBounds(new
 * Coord(0, 0)), '0,0 is in the bounds'); });
 * 
 * it('is 0,1 in the boundary?', function() { assert.isFalse(grid.isInBounds(new
 * Coord(0, 1)), '0,1 is not in the bounds'); }); });
 * 
 * 
 * 
 * describe('In Grid 2 X 2 :', function() {
 * 
 * // lets create a 2 X 2 grid var grid = new Grid(2, 2);
 * 
 * it('rectangular grid with l=2 aand b=2', function() { assert.isObject(grid,
 * 'grid is created'); });
 * 
 * var point_1_2 = new Coord(1, 2);
 * 
 * it('set scent at 1,2', function() { grid.setScent(point_1_2);
 * 
 * assert.isTrue(grid.hasScent(point_1_2), 'scent is at 1,2'); });
 * 
 * it('is 1,2 in the boundary?', function() {
 * assert.isTrue(grid.isInBounds(point_1_2), '1,2 is in the bounds'); });
 * 
 * it('is 1,-3 in the boundary?', function() {
 * assert.isFalse(grid.isInBounds(new Coord(1, -3)), '1,-2 is not in the
 * bounds'); }); });
 * 
 * });
 */