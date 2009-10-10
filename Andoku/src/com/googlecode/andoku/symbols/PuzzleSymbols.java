/*
 * Andoku - a sudoku puzzle game for Android.
 * Copyright (C) 2009  Markus Wiederkehr
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.googlecode.andoku.symbols;

import com.googlecode.andoku.model.Puzzle;

public final class PuzzleSymbols {
	public static char UNDEFINED_SYMBOL = '?';
	public static int UNDEFINED_VALUE = Puzzle.UNDEFINED;

	private final String symbolSet;
	private final int size;

	public PuzzleSymbols(String symbolSet) {
		this.symbolSet = symbolSet;
		this.size = symbolSet.length();
	}

	public char getSymbol(int value) {
		if (value < 0 || value >= size)
			return UNDEFINED_SYMBOL;

		return symbolSet.charAt(value);
	}
}
