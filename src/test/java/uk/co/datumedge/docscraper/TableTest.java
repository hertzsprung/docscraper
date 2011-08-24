package uk.co.datumedge.docscraper;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;

import org.junit.Test;

public class TableTest {
	@Test
	public void getCellFromTable() {
		Table table = new Table(new String[][]{{"foo", "bar"}, {"baz", "qux"}});

		assertThat(table.getCells()[1][0], is(equalTo("baz")));
	}
}
