package uk.co.datumedge.docscraper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.TableIterator;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

public class DocumentScraper {
	public static Iterable<Table> extractTablesFrom(InputStream inputStream) throws IOException {
		HWPFDocument document = new HWPFDocument(inputStream);
		Collection<org.apache.poi.hwpf.usermodel.Table> tables = getTablesFrom(document);

		// can't use diamond operator on anonymous classes?  aww.
		return Iterables.transform(tables, new Function<org.apache.poi.hwpf.usermodel.Table, Table>() {
			@Override
			public Table apply(org.apache.poi.hwpf.usermodel.Table table) {
				return new Table(table);
			}
		});
	}

	private static Collection<org.apache.poi.hwpf.usermodel.Table> getTablesFrom(
			HWPFDocument document) {
		TableIterator tableIterator = new TableIterator(document.getRange());
		Collection<org.apache.poi.hwpf.usermodel.Table> tables = new ArrayList<>();

		while (tableIterator.hasNext()) {
			tables.add(tableIterator.next());
		}

		return tables;
	}
}
