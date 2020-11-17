import java.awt.*;
import java.time.*;
import java.applet.Applet;
import java.util.Random;
import java.util.Scanner;
import java.io.FileWriter;

public class main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.print("Please enter Number of columns: ");
		Scanner s = new Scanner(System.in);
		int size = s.nextInt();
		System.out.print("Please enter Percentage of Obstacles (30% = .3): ");
		double obstPercentage = s.nextDouble();
		int obstacles = (int) (size * size * obstPercentage);
		System.out.print("Please enter Starting location (x y): ");
		int startX = s.nextInt();
		int startY = s.nextInt();
		System.out.print("Please enter Goal Location (x y):");
		int goalX = s.nextInt();
		int goalY = s.nextInt();
		// ButtonGrid fieldGrid;
		int i;
		int j;
		double random;
		int obCounter = 0;
		int[][] field = new int[size][size];

		// Field Generation
		for (i = 0; i < size; i++) {
			for (j = 0; j < size; j++) {
				if ((i == startX && j == startY) || (i == goalX && j == goalY)) {
					if (i == startX && j == startY) {
						field[i][j] = 3;
					} else {
						field[i][j] = 4;
					}
				} else {
					if (obCounter++ % 3 == 0) {
						if (obstacles > 0) {
							random = (Math.random() * ((100 - 0) + 1)) + 0;
							;
							if (random > 70) {
								field[i][j] = 1;
								obstacles--;
							} else {
								field[i][j] = 0;
							}
						} // end if obstacle count
						else {
							field[i][j] = 0;
						} // end obstacle count
					} else {
						field[i][j] = 0;
					}
				} // end Start/Goal Check

			} // end j
		} // end i
			// for(i=0;i<size;i++) {
			// for(j=0;j<size;j++) {
			// System.out.print(field[i][j] + " ");
			// }
			// System.out.println();
			// }
		long start = System.nanoTime();
		field = aStarAlgorithm(field, startX, startY, goalX, goalY);
		long end = System.nanoTime();
		long time = (end - start);
		System.out.println("Time: " + Integer.toString((int) time) + " ns");
		for (j = 0; j < size; j++) {
			if (j == (size / 2 - (size * .1))) {
				System.out.print("Field Map");
			} else {
				System.out.print("  ");
			}
		}
		System.out.println();
		for (i = 0; i < 2; i++) {
			for (j = 0; j < size; j++) {
				System.out.print("--");
			}
			System.out.println();
		}

		for (i = 0; i < size; i++) {
			for (j = 0; j < size; j++) {
				if (field[i][j] != 0) {
					if (field[i][j] == 2) {
						System.out.print("* ");
					}
					else if (field[i][j] == 3) {
						System.out.print("@ ");
					}
					else if (field[i][j] == 4) {
						System.out.print("# ");
					} else {
						System.out.print(field[i][j] + " ");
					}
				} else {
					System.out.print("  ");
				}

			}
			System.out.println("||");
		}
		for (i = 0; i < 2; i++) {
			for (j = 0; j < size; j++) {
				System.out.print("--");
			}
			System.out.println();
		}

	}// end main

	private static int[][] aStarAlgorithm(int[][] field, int startX, int startY, int goalX, int goalY) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		int x1 = startX;
		int y1 = startY;
		int x2 = goalX;
		int y2 = goalY;
		int currentX = x1;
		int currentY = y1;
		int distanceTraveled = 1;
		double g = distanceToGoal(x1, y1, x2, y2);
		// Enable 2 lines below to allow user input of maximum cost
		// System.out.print("Please enter cost: ");
		// double cost = sc.nextDouble();

		// Disable line below to allow user input of maximum cost
		double cost = 1000000;
		int counter = 0;
		int action = 0;
		int previousAction = 0;
		while ((currentX != x2) && (currentY != y2)) {
			double[] distanceArray = new double[5];
			distanceArray[0] = -100000000; // currentPosition distance

			// Test for Obstacles
			if (field[currentX - 1][currentY] == 1 || currentX == 0) {
				distanceArray[1] = -10000000;
			} else {
				distanceArray[1] = (cost - distanceTraveled) / distanceToGoal(currentX - 1, currentY, x2, y2); // Left
																												// distance
			}
			if (field[currentX + 1][currentY] == 1 || currentX == 100) {
				distanceArray[2] = -10000000;
			} else {
				if (previousAction == 1) {
					distanceArray[2] = -1000000;
				} else {
					distanceArray[2] = (cost - distanceTraveled) / distanceToGoal(currentX + 1, currentY, x2, y2);// Right
																													// Distance
				}

			}
			if (field[currentX][currentY - 1] == 1 || currentY == 0) {
				distanceArray[3] = -10000000;
			} else {
				distanceArray[3] = (cost - distanceTraveled) / distanceToGoal(currentX, currentY - 1, x2, y2);// Up
																												// Distance
			}
			if (field[currentX][currentY + 1] == 1 || currentY == 100) {
				distanceArray[4] = -10000000;
			} else {
				if (previousAction == 3) {
					distanceArray[4] = -1000000;
				} else {
					distanceArray[4] = (cost - distanceTraveled) / distanceToGoal(currentX, currentY + 1, x2, y2);// Down
																													// Distance
				}

			}

			double min = distanceArray[0];
			// Finding the Min distance to goal from actions
			for (int i = 1; i < 5; i++) {
				if (min < distanceArray[i]) {
					min = distanceArray[i];
					action = i;
				}
			} // end if Min
			switch (action) {
			case (0): {
				field[currentX][currentY] = 2;
				break;
			}
			case (1): {
				field[currentX - 1][currentY] = 2;
				currentX--;
				distanceTraveled++;
				previousAction = 1;
				break;
			}
			case (2): {
				field[currentX + 1][currentY] = 2;
				currentX++;
				distanceTraveled++;
				previousAction = 2;
				break;
			}
			case (3): {
				field[currentX][currentY - 1] = 2;
				currentY--;
				distanceTraveled++;
				previousAction = 3;
				break;
			}
			case (4): {
				field[currentX][currentY + 1] = 2;
				currentY++;
				distanceTraveled++;
				previousAction = 4;
				break;
			}
			default:
				System.out.print("Error, action is out of bounds");
				break;
			}
			// counter++;
			if (counter++ > cost) {
				System.out.println("No Solution exists for Cost Bound");
				System.exit(action);
			}
		} // end While
		System.out.println("Actual Cost: " + Integer.toString(counter - 1));
		return field;
	}

	private static int distanceToGoal(int x1, int y1, int x2, int y2) {
		// TODO Auto-generated method stub
		return (int) Math.sqrt(((y2 - y1) * (y2 - y1) + (x2 - x1) * (x2 - x1)));
	}

}// end class main
