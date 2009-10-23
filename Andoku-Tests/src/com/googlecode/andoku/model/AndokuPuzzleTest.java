package com.googlecode.andoku.model;

import java.io.Serializable;

import junit.framework.TestCase;

import com.googlecode.andoku.util.MockPuzzleSource;
import com.googlecode.andoku.util.SerializableUtil;

public class AndokuPuzzleTest extends TestCase {
	public void testSaveAndRestoreMemento() throws Exception {
		// "9..1........9.23.612.....89.............6.............71.....252.96.7........5..7|112222334112222334111552334116553334666654444677755488677955888677999988677999988|H";
		// "987153264574982316123576489461829753395468172642731598718394625259647831836215947";

		AndokuPuzzle p1 = MockPuzzleSource.createPuzzle(0);
		// set correct values
		p1.setValues(0, 1, ValueSet.single(7));
		p1.setValues(0, 2, ValueSet.single(6));
		p1.setValues(0, 4, ValueSet.of(3, 4));
		// set incorrect values
		p1.setValues(0, 5, ValueSet.single(8));
		p1.checkForErrors();

		assertEquals(2, p1.getRegionErrors().size());
		assertEquals(3, p1.getErrorPositions().size());

		Serializable memento = p1.saveToMemento();

		memento = SerializableUtil.roundTrip(memento);

		AndokuPuzzle p2 = MockPuzzleSource.createPuzzle(0);

		assertEquals(ValueSet.none(), p2.getValues(0, 1));
		assertEquals(ValueSet.none(), p2.getValues(0, 2));
		assertEquals(ValueSet.none(), p2.getValues(0, 4));
		assertEquals(ValueSet.none(), p2.getValues(0, 5));

		assertTrue(p2.getRegionErrors().isEmpty());
		assertTrue(p2.getErrorPositions().isEmpty());

		p2.restoreFromMemento(memento);

		assertEquals(ValueSet.single(7), p2.getValues(0, 1));
		assertEquals(ValueSet.single(6), p2.getValues(0, 2));
		assertEquals(ValueSet.of(3, 4), p2.getValues(0, 4));
		assertEquals(ValueSet.single(8), p2.getValues(0, 5));

		assertEquals(2, p2.getRegionErrors().size());
		assertEquals(3, p2.getErrorPositions().size());

		assertEquals(p1.getRegionErrors(), p2.getRegionErrors());
		assertEquals(p1.getErrorPositions(), p2.getErrorPositions());
	}
}
