package com.kan.bglcc.simulation.core;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GridTest {
	
	private Grid grid;
	private int rows = 7;
	private int columns = 8;

	@BeforeEach
	void setUp() throws Exception {
		grid = new Grid(rows, columns);
	}

	@AfterEach
	void tearDown() throws Exception {
		grid = null;
	}
	
	//---------------------------------------------------------------------------

	@Test
	void testGetRows() {
		assertEquals(rows, grid.getRows());
	}

	@Test
	void testGetColumns() {
		assertEquals(columns, grid.getColumns());
	}
	
	//---------------------------------------------------------------------------

	@Test
	void testIsCellOnGrid() {
		assertTrue(grid.isCellOnGrid(columns-3, rows-2));
	}
	
	@Test
	void testIsCellOnGrid_SW_Corner() {
		assertTrue(grid.isCellOnGrid(0, 0));
	}

	@Test
	void testIsCellOnGrid_SW_Outside() {
		assertFalse(grid.isCellOnGrid(-1, -1));
	}
	
	@Test
	void testIsCellOnGrid_S_Outside() {
		assertFalse(grid.isCellOnGrid(0, -1));
	}
	
	@Test
	void testIsCellOnGrid_W_Outside() {
		assertFalse(grid.isCellOnGrid(-1, 0));
	}
	
	@Test
	void testIsCellOnGrid_SE_Corner() {
		assertTrue(grid.isCellOnGrid(columns-1, 0));
	}
	
	@Test
	void testIsCellOnGrid_SE_Outside() {
		assertFalse(grid.isCellOnGrid(columns, -1));
	}
	
	@Test
	void testIsCellOnGrid_NE_Corner() {
		assertTrue(grid.isCellOnGrid(columns-1, rows-1));
	}

	@Test
	void testIsCellOnGrid_NE_Outside() {
		assertFalse(grid.isCellOnGrid(columns, rows));
	}
	
	@Test
	void testIsCellOnGrid_N_Outside() {
		assertFalse(grid.isCellOnGrid(columns-1, rows));
	}
	
	@Test
	void testIsCellOnGrid_E_Outside() {
		assertFalse(grid.isCellOnGrid(columns, rows-1));
	}
	
	@Test
	void testIsCellOnGrid_NW_Corner() {
		assertTrue(grid.isCellOnGrid(0, rows-1));
	}

	@Test
	void testIsCellOnGrid_NW_Outside() {
		assertFalse(grid.isCellOnGrid(-1, rows));
	}
	
}
