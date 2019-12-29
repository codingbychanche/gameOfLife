import org.berthold.life.*;

/**
 * Convay's Game Of Life</p>
 *
 * This implementaion of Convay's famous game of life aims at easy integrationin in 
 * one's own projects. The package {@link org.berthold.life} contains all classes needed.
 * 
 * This is a minimal example to show how to use to library.
 * 
 * @author Berthold
 *
 */

public class MainShell {

	public static void main(String args[]) {

		// Start
		PetriDish dish1 = new PetriDish(50, 50);

		// A glider to check if the algorithm works.....
		dish1.setCell(35, 5);
		dish1.setCell(34, 6);
		dish1.setCell(34, 7);
		dish1.setCell(35, 7);
		dish1.setCell(36, 7);

		// dish1.generateRandomSetOfCells(dish1,30);

		// A shape, reaching a stable state after the first generation
		/*
		 * dish1.setCell(5, 5); dish1.setCell(6, 5); dish1.setCell(5, 6);
		 */
		// A Bomb...
		/*
		 * dish1.setCell(10, 10); dish1.setCell(11, 10); dish1.setCell(12, 10);
		 * dish1.setCell(10, 11); dish1.setCell(12, 11); dish1.setCell(10, 12);
		 * dish1.setCell(12, 12);
		 * 
		 * dish1.setCell(10, 16); dish1.setCell(12, 16); dish1.setCell(10, 17);
		 * dish1.setCell(12, 17); dish1.setCell(10, 18); dish1.setCell(11, 18);
		 * dish1.setCell(12, 18);
		 */

		PetriDish nextGenerationOfDish1 = new PetriDish(dish1.getDimX(), dish1.getDimY());
		while (dish1.compareToSecondDish(nextGenerationOfDish1) != 0) {

			showContentsOfDish(dish1);

			nextGenerationOfDish1 = NextGeneration.generateFrom(dish1);
			showContentsOfDish(nextGenerationOfDish1);

			dish1 = NextGeneration.generateFrom(nextGenerationOfDish1);
		}
		System.out.println("Petridish freezed after " + nextGenerationOfDish1.getGeneration() + " generations");

		// Get any of the preceding generations
		int storedGens = NextGeneration.getNumberOfPetridishesStored();
		System.out.println("Stored generations:" + storedGens);

		PetriDish olderDish = new PetriDish(50, 50);
		int olderGenerationNumber = 0;
		if (NextGeneration.getPeteriDishOfGeneration(olderGenerationNumber) != null) {
			olderDish = NextGeneration.getPeteriDishOfGeneration(olderGenerationNumber);
			showContentsOfDish(olderDish);
		} else
			System.out.println("Generation " + olderGenerationNumber + " Does not exist.");
	}

	/*
	 * Shows the contents of a dish
	 */
	public static void showContentsOfDish(PetriDish dish) {
		int dimX = dish.getDimX();
		int dimY = dish.getDimY();

		System.out.println();
		System.out.println("Generation:" + dish.getGeneration() + "  SUM: Born:" + dish.getCellsBorn() + " Died:"
				+ dish.getCellsDied() + " Survived:" + dish.getCellsSurvived());

		for (int y = 0; y <= dimY - 1; y++) {
			for (int x = 0; x <= dimX - 1; x++) {
				if (dish.getCellAt(x, y) == 1)
					System.out.print("*");
				else
					System.out.print(".");
			}
			System.out.println();
		}
	}
}
