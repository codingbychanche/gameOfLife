package org.berthold.life;

/**
 * All methods needed to create and evaluate a petridish of cells.
 * 
 * @author Berthold Fritz
 *
 */
public class PetriDish {

	private int dimX, dimY;
	private int lifeCells, bornCells, diedCells, survivedCells, generation;
	private int contents[][];

	public PetriDish(int dimX, int dimY) {
		super();
		this.dimX = dimX;
		this.dimY = dimY;

		generation = 0;
		contents = new int[dimX][dimY];
	}

	/**
	 * Set a cell.
	 * 
	 * A cells alive have an integer value of 1.
	 * 
	 * @param	x	Cells x- ccordinate.
	 * @param	y	Cells y- coordinate.
	 * @return 	true if cell is inside petridish, false if not.
	 */

	public boolean setCell(int x, int y) {
		if (coordinatesAreInsideThisDish(x, y)) {
			contents[x][y] = 1;
			return true;
		} else
			return false;
	}

	/**
	 * Delete a cell.
	 * 
	 * Death cells have an integer value of 0.
	 * 
	 * @param	x	Cells x- ccordinate.
	 * @param	y	Cells y- coordinate.
	 * @return true if coordinates are inside the petridish, false if not.
	 */

	public boolean killCell(int x, int y) {
		if (coordinatesAreInsideThisDish(x, y)) {
			contents[x][y] = 0;
			return true;
		} else
			return false;
	}

	/**
	 * Count cells which are alive surounding a cell at the given coordinates.
	 * 
	 * @param	x	Cells x- ccordinate.
	 * @param	y	Cells y- coordinate.
	 * @return Number of cells which are alive. -1 if the given coordinates are
	 *         outside of this petridish.
	 */

	public int countLifeCellsSouroundingCellAt(int x, int y) {

		int lifeCells = 0;

		if (coordinatesAreInsideThisDish(x, y)) {

			if (x >= 1 && y >= 1) {
				int xj = x - 1;
				int yi = y - 1;

				if (x <= this.getDimX() - 2 && y <= this.getDimY() - 2) {
					for (int i = 0; i <= 2; i++) {
						for (int j = 0; j <= 2; j++) {
							if (contents[xj + j][yi + i] == 1)
								lifeCells++;
						}
					}
				}
			}
			return lifeCells - getCellAt(x, y);
		} else
			return -1;
	}

	/**
	 * Counts the cells which are not at equal positions.
	 * 
	 * Both petridishes must have equal dimensions. If not, return value is -1
	 * 
	 * @param	secondPetriDish The petridish to be compared to this petridish
	 * @return 	Number of cells which are not at an equal position in bath
	 *         	petridishes.
	 */

	public int compareToSecondDish(PetriDish secondPetriDish) {
		int delta = 0;

		if (dimX != secondPetriDish.getDimX() || dimY != secondPetriDish.getDimY())
			return -1;
		else {
			for (int y = 0; y <= dimY - 1; y++) {
				for (int x = 0; x <= dimX - 1; x++) {
					if (contents[x][y] != secondPetriDish.contents[x][y])
						delta++;
				}
			}
			return delta;
		}
	}

	/**
	 * Clears this petridish.
	 * 
	 */
	public void clear() {
		for (int y = 0; y <= dimY - 1; y++) {
			for (int x = 0; x <= dimX - 1; x++) {
				contents[x][y] = 0;
			}
		}

	}

	/**
	 * Check if the given coordinates are inside this petridish.
	 * 
	 */

	private boolean coordinatesAreInsideThisDish(int x, int y) {
		if ((x >= 0) && (x <= dimX - 1) && (y >= 0) && (y <= dimY - 1))
			return true;
		else
			return false;
	}

	/**
	 * Get value of a cell at a given coordinate.
	 * 
	 * @param	x	Cells x- ccordinate.
	 * @param	y	Cells y- coordinate.
	 * @return 	1 for a life cell, 0 for a death cell. -1 if coordinates not
	 *         	equal to dimensions of dish.
	 */

	public int getCellAt(int x, int y) {

		if (coordinatesAreInsideThisDish(x, y))
			return contents[x][y];
		else
			return -1;
	}

	/**
	 * Returns horizontal size of this petridish.
	 * 
	 * @return	Maxinum number of possible cells in a row of the petridish.
	 */
	public int getDimX() {
		return dimX;
	}

	/**
	 * Returns vertical size of this petridish.
	 * 
	 * @return	Maxinum number of possible cells in a column of the petridish.
	 */
	public int getDimY() {
		return dimY;
	}

	/**
	 * Counts cells which are alive inside this petridish.
	 * 
	 * @return	Number of cells alive.
	 */
	public int getLifeCells() {
		for (int y = 0; y <= dimY - 1; y++) {
			for (int x = 0; x <= dimX - 1; x++)
				if (contents[x][y] == 1)
					lifeCells++;
		}
		return lifeCells;
	}
	
	/**
	 * Counts the the number of cells that have died, compared to the preceding generation.
	 * This number is increased when creating the the next generation. See:{@link NextGeneration}
	 * 
	 * @return	Number of cells died.
	 */
	public int getCellsDied() {
		return diedCells;
	}

	/**
	 * Counts the the reletively number of cells born, compared to the preceding generation.
	 * This number is increased when creating the the next generation. See:{@link NextGeneration}
	 * 
	 * @return	Number of cells died.
	 */
	public int getCellsBorn() {
		return bornCells;
	}

	/**
	 * Counts the number of cells that have survived, compared to the preceding generation.
	 * This number is increased when creating the the next generation. See:{@link NextGeneration}
	 * 
	 * @return	Number of cells died.
	 */
	public int getCellsSurvived() {
		return survivedCells;
	}

	/**
	 * Returns the number representing the generation of this dish (=how many times {@link NextGeneration} has been 
	 * applied on this peteridish).
	 * 
	 * @return	generation of this petridish.
	 */
	public int getGeneration() {
		return generation;
	}

	/**
	 * Increases the number of cells survived compared to the preceding generation.
	 * The valid condition (cell was already alive in preceding generation) is checked wehn creating the next generation. See:{@link NextGeneration}.
	 * When valid condition apply, the number is increased.
	 */
	public void cellSurvived() {
		survivedCells++;
	}

	/**
	 * Increases the number of cells died compared to the preceding generation.
	 * This number is increased when creating the the next generation. See:{@link NextGeneration}
	 */
	public void cellDied() {
		diedCells++;
	}

	/**
	 * Increases the number of cells born compared to the preceding generation.
	 * This number is increased when creating the the next generation. See:{@link NextGeneration}
	 */
	public void cellBorn() {
		bornCells++;
	}
}
