package main;

import static org.junit.Assert.*;
import logic.World;

import org.junit.Test;

public class LogicTest {
	
	@Test
	public void test1() {
		World world = new World(25);
		for(int i = 0; i < 25; i++) world.makeCell(i, 0);
		for(int i = 0; i < 25; i++) world.makeCell(0, i);
		System.out.println(world.toString());
		System.out.println(world.numberOfNeighbors(0, 0));
		for(int i = 20; i <= 22; i++){
			for(int j = 10; j <= 12; j++){
				world.makeCell(i, j);
			}
		}
		System.out.println(world.toString());
		System.out.println(world.numberOfNeighbors(20, 10));
		System.out.println(world.numberOfNeighbors(21, 11));
		System.out.println(world.numberOfNeighbors(22, 12));
		world.nextIteration();
		int numIterations = 10;
		for(int i = 1; i <= numIterations; i++){
			world.nextIteration();
			System.out.println("Iteration #"+i);
			world.printWorld();
		}
		
	}
	
	@Test
	public void test2() {
		System.out.println("---TEST2---");
		World world = new World(5);
		for(int i = 0; i <= 2; i++){
			world.makeCell(i, 2);
		} 
		System.out.println(world.numberOfNeighbors(2, 1));
		System.out.println(world.numberOfNeighbors(2, 2));
		System.out.println(world.numberOfNeighbors(0,2));
		int numIterations = 5;
		for(int i = 0; i < numIterations; i++){
			world.printWorld();
			world.nextIteration();
		}
		
	}
	
	@Test
	public void testOscillators(){
		//Test some common Oscillators.
		System.out.println("---TEST3---");
		World world = new World(5);
		for(int i = 0; i <= 2; i++){
			world.makeCell(i, 2);
		} 
		world.printWorld();
		assertEquals(2, world.numberOfNeighbors(2, 1));
		assertEquals(2, world.numberOfNeighbors(1, 2));
		assertEquals(1, world.numberOfNeighbors(0, 2));
		assertEquals(1, world.numberOfNeighbors(2, 2));
		assertEquals(3, world.numberOfNeighbors(1, 1));
		assertEquals(3, world.numberOfNeighbors(1, 3));
		world.nextIteration();
		world.printWorld();
		World toadWorld = new World(6);
		toadWorld.makeCell(2, 2);
		toadWorld.makeCell(3, 2);
		toadWorld.makeCell(4, 2);
		toadWorld.makeCell(1, 3);
		toadWorld.makeCell(2, 3);
		toadWorld.makeCell(3, 3);
		System.out.println("TOAD-WORLD");
		toadWorld.printWorld();
		for(int i = 1; i <= 10; i++){
			System.out.println("Iteration"+i);
			toadWorld.nextIteration();
			toadWorld.printWorld();
		}
	}

}
