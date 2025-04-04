package test.java;

import main.java.DataTransforming.CsvCreate;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import test.java.DataTransforming.CsvCreateTest;
import test.java.DataTransforming.SheetXlsxTest;
import test.java.WebScraper.AnexoTest;
import test.java.WebScraper.ZipperTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        AnexoTest.class,
        ZipperTest.class,
        CsvCreateTest.class,
        SheetXlsxTest.class
})
public class AllTests {
}
