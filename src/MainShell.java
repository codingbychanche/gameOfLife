import org.berthold.life.*;

/**
 * Convay's Game Of Life
 * </p>
 * 
 * What else do I have to say?
 * </p>
 * 
 * This is a basic example showing how to implement the package.
 * </p>
 * 
 * @author Berthold
 *
 */

public class MainShell {

	public static void main(String args[]) {
		PetriDish dish1 = new PetriDish(50, 50);

		// A glider to check if the algorithm works.....
		dish1.setCell(35, 5);
		dish1.setCell(34, 6);
		dish1.setCell(34, 7);
		dish1.setCell(35, 7);
		dish1.setCell(36, 7);
	
		
		// A shape, reaching a stable state after the first generation
		/*
		dish1.setCell(5, 5);
		dish1.setCell(6, 5);
		dish1.setCell(5, 6);
		*/
		
		PetriDish nextGenerationOfDish1 = new PetriDish(dish1.getDimX(), dish1.getDimY());

		// Repeat, as long as the following generation is not equal to the
				// preeceding generation.
				int generation=0;
				while (dish1.compareToSecondDish(nextGenerationOfDish1) != 0) {
					
					showContentsOfDish(dish1);
					
					nextGenerationOfDish1 = NextGeneration.generateFrom(dish1);
					showContentsOfDish(nextGenerationOfDish1);
					System.out.println("SUM: Born:"+nextGenerationOfDish1.getCellsBorn()+" Died:"+nextGenerationOfDish1.getCellsDied()+" Survived:"+nextGenerationOfDish1.getCellsSurvived());
					generation++;

					dish1.clear();
					dish1 = NextGeneration.generateFrom(nextGenerationOfDish1);
					generation++;
					
					System.out.println();
					System.out.println("SUM: Born:"+dish1.getCellsBorn()+" Died:"+dish1.getCellsDied()+" Survived:"+dish1.getCellsSurvived());
					
				}
				System.out.println("Petridish reached stable state after "+generation+" generations");
	}

	/*
	 * Shows the contents of a dish
	 */

	public static void showContentsOfDish(PetriDish dish) {
		int dimX = dish.getDimX();
		int dimY = dish.getDimY();

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
