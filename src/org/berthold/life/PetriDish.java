package org.berthold.life;

/**
 * All methods needed to create and evaluate a petridish of cells.
 * 
 * @author Berthold Fritz
 *
 */
import java.util.Random;

public class PetriDish {

	private int dimX, dimY;
	private int lifeCells, bornCells, diedCells, survivedCells, generation;
	private int contents[][];

	public PetriDish(int dimX, int dimY) {
		super();
		this.dimX = dimX;
		this.dimY = dimY;
		contents = new int[dimX][dimY];
	}

	/**
	 * Set a cell.
	 * 
	 * A cells alive have an integer value of 1.
	 * 
	 * @param x
	 *            Cells x- ccordinate.
	 * @param y
	 *            Cells y- coordinate.
	 * @return true if cell is inside petridish, false if not.
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
	 * @param x
	 *            Cells x- ccordinate.
	 * @param y
	 *            Cells y- coordinate.
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
	 * @param x
	 *            Cells x- ccordinate.
	 * @param y
	 *            Cells y- coordinate.
	 * @return Number of cells which are alive. -1 if the given coordinates are
	 *         outside of this petridish.
	 */
	public int countLifeCellsSouroundingCellAt(int x, int y) {

		int lifeCells = 0;
		int xi;
		int yj;

		if (coordinatesAreInsideThisDish(x, y)) {

			for (int j = 0; j <= 2; j++) {
				for (int i = 0; i <= 2; i++) {
					xi = x - 1;
					yj = y - 1;

					if (xi + i > dimX - 1)
						xi = 0;

					if ((xi + i) < 0)
						xi = dimX - i - 1;

					if (yj + j > dimY - 1)
						yj = 0;

					if (yj + j < 0)
						yj = dimY - j - 1;

					if (contents[xi + i][yj + j] == 1)
						lifeCells++;

					/*
					 * System.out.println("i:" + i + "  j:" + j + "   x:" + x +
					 * "  y:" + y + "  xi:" + (xi + i) + "  yj:" + (yj + j) +
					 * "   Cell:" + contents[xi + i][yj + j]);
					 */
				}
			}

			if (lifeCells > 0)
				lifeCells = lifeCells - contents[x][y];

			return lifeCells;
		} else
			return -1;
	}

	/**
	 * Counts the cells which are not at equal positions.
	 * 
	 * Both petridishes must have equal dimensions. If not, return value is -1
	 * 
	 * @param secondPetriDish
	 *            The petridish to be compared to this petridish
	 * 
	 * @return Number of cells which are not at an equal position in both
	 *         petridishes.
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
	 * Generates a random set of cells
	 * 
	 * @param Density
	 *            of cell population. 100 means petridish is fully populated. 0
	 *            means no population.
	 */

	public void generateRandomSetOfCells(PetriDish dish, int density) {

		Random random = new Random();

		for (int y = 0; y <= dimY - 1; y++) {
			for (int x = 0; x <= dimX - 1; x++) {
				float value = random.nextInt(100);
				if (value > (100 - density))
					dish.setCell(x, y);
			}
		}
	}

	/**
	 * Check if the given coordinates are inside this petridish.
	 * 
	 * @return true if coordinates inside, false if not.
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
	 * @param x
	 *            Cells x- ccordinate.
	 * @param y
	 *            Cells y- coordinate.
	 * 
	 * @return 1 for a life cell, 0 for a death cell. -1 if coordinates are
	 *         outside of this petridish.
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
	 * @return Maxinum number of possible cells in a row of the petridish.
	 */
	public int getDimX() {
		return dimX;
	}

	/**
	 * Returns vertical size of this petridish.
	 * 
	 * @return Maxinum number of possible cells in a column of the petridish.
	 */
	public int getDimY() {
		return dimY;
	}

	/**
	 * Counts all living cells inside this petridish.
	 * 
	 * @return Number of cells alive.
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
	 * Counts the the number of cells that have died, compared to the preceding
	 * generation. This number is increased when creating the the next
	 * generation. See:{@link NextGeneration}
	 * 
	 * @return Number of cells died.
	 */
	public int getCellsDied() {
		return diedCells;
	}

	/**
	 * Counts the the number of cells born, relative to the preceding
	 * generation. This number is increased when creating the the next
	 * generation. See:{@link NextGeneration}
	 * 
	 * @return Number of cells died.
	 */
	public int getCellsBorn() {
		return bornCells;
	}

	/**
	 * Counts the number of cells that have survived, relative to the preceding
	 * generation. This number is increased when creating the the next
	 * generation. See:{@link NextGeneration}
	 * 
	 * @return Number of cells died.
	 */
	public int getCellsSurvived() {
		return survivedCells;
	}

	/**
	 * Returns the generation of this dish (=how many times
	 * {@link NextGeneration} has been applied on this peteridish).
	 * 
	 * @return generation of this petridish.
	 */
	public int getGeneration() {
		return generation;
	}
	
	/**
	 * Set generation
	 */
	public void setGeneration(int generation){
		this.generation=generation;
	}

	/**
	 * Increase generation.
	 */
	public void increaseGeneration() {
		generation++;
	}

	/**
	 * Increases the number of cells survived compared to the preceding
	 * generation. The valid condition (cell was already alive in preceding
	 * generation) is checked when creating the next generation. See:
	 * {@link NextGeneration}. When valid condition apply, the number is
	 * increased.
	 */
	public void cellSurvived() {
		survivedCells++;
	}

	/**
	 * Increases the number of cells died compared to the preceding generation.
	 * This number is increased when creating the the next generation. See:
	 * {@link NextGeneration}
	 */
	public void cellDied() {
		diedCells++;
	}

	/**
	 * Increases the number of cells born compared to the preceding generation.
	 * This number is increased when creating the the next generation. See:
	 * {@link NextGeneration}
	 */
	public void cellBorn() {
		bornCells++;
	}
}
