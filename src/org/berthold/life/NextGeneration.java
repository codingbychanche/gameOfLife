package org.berthold.life;

/**
 * This class contains the basic rules of life.
 * 
 * @author Berthold
 *
 */
import java.util.List;
import java.util.ArrayList;

public class NextGeneration {

	private static final int MAX_NUMBER_OF_PRECEDING_GENERATIONS = 400;
	private static List<PetriDish> listOfPrecedingGenerations = new ArrayList<>();
	private static int numberOfPrecedingGenerationsStored=0;

	/**
	 * This method generates the next generation from a given {@link petriDish}.
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
		
		int generationOfPrecedingPetriDish=precedingPetriDish.getGeneration();

		savePetriDish(precedingPetriDish);
		
		// Life, check rules, create new dish....
		PetriDish petriDishContainingNextGeneration = new PetriDish(precedingPetriDish.getDimX(),
				precedingPetriDish.getDimY());
		
		for (int y = 0; y <= precedingPetriDish.getDimY() - 1; y++) {
			for (int x = 0; x <= precedingPetriDish.getDimX() - 1; x++) {
				int numberOfSuroundingCells = precedingPetriDish.countLifeCellsSouroundingCellAt(x, y);

				// Cell survives
				if (numberOfSuroundingCells == 2 || numberOfSuroundingCells == 3) {
					if (precedingPetriDish.getCellAt(x, y) == 1) {
						petriDishContainingNextGeneration.cellSurvived();
						petriDishContainingNextGeneration.setCell(x, y);
					} else
						petriDishContainingNextGeneration.killCell(x, y);
				}

				// Cell dies
				if (numberOfSuroundingCells > 3 || numberOfSuroundingCells < 2) {
					petriDishContainingNextGeneration.killCell(x, y);

					if (precedingPetriDish.getCellAt(x, y) == 1)
						petriDishContainingNextGeneration.cellDied();
				}

				// New cell is born
				if (numberOfSuroundingCells == 3) {
					petriDishContainingNextGeneration.setCell(x, y);

					if (precedingPetriDish.getCellAt(x, y) == 0)
						petriDishContainingNextGeneration.cellBorn();
				}
			}
		}
		petriDishContainingNextGeneration.setGeneration(generationOfPrecedingPetriDish);
		petriDishContainingNextGeneration.increaseGeneration();
	
		return petriDishContainingNextGeneration;
	}

	/**
	 * Stored preceding generations.
	 * 
	 * @return Number of preceding genertions stored.
	 */

	public static int getNumberOfPetridishesStored() {
		return numberOfPrecedingGenerationsStored;
	}

	/**
	 * Saves petridish.
	 * 
	 * @param dishToSave
	 *            {@link PetriDish} object to be saved.
	 * 
	 */
	private static void savePetriDish(PetriDish dishToSave) {
		if (numberOfPrecedingGenerationsStored == MAX_NUMBER_OF_PRECEDING_GENERATIONS) {
			numberOfPrecedingGenerationsStored = 0;
			listOfPrecedingGenerations.clear();
		} else {
			listOfPrecedingGenerations.add(dishToSave);
			numberOfPrecedingGenerationsStored++;
		}
	}

	/**
	 * Returns a petridish for the given generation.
	 * 
	 * @param generation,
	 *            generation of the current game to be returned.
	 * 
	 * @return {@link PetriDish} containing the given generation.Will return
	 *         null- object if this genertion does not exist. 
	 */
	public static PetriDish getPeteriDishOfGeneration(int generation) {

		if (generation <= numberOfPrecedingGenerationsStored)
			return listOfPrecedingGenerations.get(generation);
		else
			return null;
	}
}
