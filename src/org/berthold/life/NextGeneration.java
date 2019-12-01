package org.berthold.life;

/**
 * This class contains the basic rules of life.
 * 
 * @author Berthold
 *
 */

public class NextGeneration {

	/**
	 * This method generates the next generation from a given petridish.
	 * 
	 * @param precedingPetriDish
	 *            Contains the petridish which will be evaluated according to
	 *            Convay's rules for the Game of Life. This is the base for the
	 *            next generation.
	 * 
	 * @return Dish- object containing the next generation, based on then given
	 *         dish which represents the earlier generation.
	 */

	public static PetriDish generateFrom(PetriDish precedingPetriDish) {
		PetriDish petriDishContainingNextGeneration = new PetriDish(precedingPetriDish.getDimX(),
				precedingPetriDish.getDimY());

		for (int y = 0; y <= precedingPetriDish.getDimY() - 1; y++) {
			for (int x = 0; x <= precedingPetriDish.getDimX() - 1; x++) {
				int numberOfSuroundingCells = precedingPetriDish.countLifeCellsSouroundingCellAt(x, y);

				// Cell survives
				if (numberOfSuroundingCells == 2 || numberOfSuroundingCells == 3) {
					if (precedingPetriDish.getCellAt(x, y) == 1) {
						petriDishContainingNextGeneration.setCell(x, y);
						petriDishContainingNextGeneration.cellSurvived();
					}
				}

				// Cell dies
				if ((numberOfSuroundingCells > 4 || numberOfSuroundingCells < 1)
						&& precedingPetriDish.getCellAt(x, y) == 1) {
					precedingPetriDish.killCell(x, y);
					petriDishContainingNextGeneration.killCell(x, y);
					petriDishContainingNextGeneration.cellDied();
				}

				// New cell is born
				if (numberOfSuroundingCells == 3) {
					petriDishContainingNextGeneration.setCell(x, y);
					petriDishContainingNextGeneration.cellBorn();
				}
			}
		}
		return petriDishContainingNextGeneration;
	}
}
