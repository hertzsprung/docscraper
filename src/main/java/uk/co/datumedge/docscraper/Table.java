package uk.co.datumedge.docscraper;

import java.util.Arrays;

import org.apache.poi.hwpf.usermodel.TableCell;
import org.apache.poi.hwpf.usermodel.TableRow;

// yes, this should have been TDD'd.  There's a bunch of corner cases I've chosen to ignore :-)
public class Table {
	private final String[][] cells;

	Table(org.apache.poi.hwpf.usermodel.Table table) {
		cells = new String[table.numRows()][];

		populateCellsUsing(table);
	}

	private void populateCellsUsing(org.apache.poi.hwpf.usermodel.Table table) {
		for (int y = 0; y < table.numRows(); y++) {
			TableRow row = table.getRow(y);
			for (int x = 0; x < row.numCells(); x++) {
				cells[y] = new String[row.numCells()];
				TableCell cell = row.getCell(x);
				assert cell.numParagraphs() > 0;
				cells[y][x] = cell.getParagraph(0).text().replaceAll("\\p{Cntrl}", "");
			}
		}
	}

	public Table(String[][] cells) {
		this.cells = cells;
	}

	public String[][] getCells() {
		String[][] copy = new String[cells.length][];

		for (int y = 0; y < cells.length; y++) {
			copy[y] = new String[cells[y].length];

			for (int x = 0; x < cells[y].length; x++) {
				copy[y][x] = cells[y][x];
			}
		}

		return copy;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(cells);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Table other = (Table) obj;
		// raised https://bugs.eclipse.org/bugs/show_bug.cgi?id=355773 -- should be deepEquals(), not equals()
		if (!Arrays.deepEquals(cells, other.cells))
			return false;
		return true;
	}
}
