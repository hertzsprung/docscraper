package uk.co.datumedge.docscraper;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.collection.IsIterableContainingInOrder.contains;

import java.io.IOException;
import java.io.InputStream;

import org.junit.Test;

public class DocumentScraperTest {
	@Test
	public void extractsTableFromDocument() throws IOException {
		Iterable<Table> tablesInDocument = DocumentScraper.extractTablesFrom(inputStreamFor("uk/co/datumedge/docscraper/with-table.doc"));
		assertThat(tablesInDocument, contains(expectedTable()));
	}

	private InputStream inputStreamFor(String name) {
		return Thread.currentThread().getContextClassLoader().getResourceAsStream(name);
	}

	private Table expectedTable() {
		return new Table(new String[][]{{"Foo", "Bar", "Baz"}, {"Qux", "Quux", "corge"}});
	}
}
