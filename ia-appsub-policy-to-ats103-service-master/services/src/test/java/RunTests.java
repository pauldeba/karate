
import com.intuit.karate.Results;
import com.intuit.karate.Runner;
import com.standard.test.ApiTestBase;

import net.masterthought.cucumber.Configuration;
import net.masterthought.cucumber.ReportBuilder;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class RunTests extends ApiTestBase {

    public static final String PROJECT_NAME = "ia-appsub-policy-to-ats103-service";
    private static final String REPORT_TITLE = PROJECT_NAME + " (karate) " + System.getProperty("test.env");
    private static final String GLOBAL_IGNORE_ALL = "~@ignore";
    private static final String MAVEN_OUTPUT = "target";
    private static String GLOBAL_INCLUDE = "@" + Optional.ofNullable(System.getProperty("test.type"))
        .filter(s -> !s.isEmpty())
        .orElse("reg").toLowerCase();

    @Test
    public void runAPITests() {
        Results results = Runner.parallel(1, "classpath:features", GLOBAL_IGNORE_ALL, GLOBAL_INCLUDE);

        Assertions.assertTrue(results.getFeatureCount() > 0, "Did not find any cucumber tests to execute.");

        generateReport(results.getReportDir());

        Assertions.assertEquals(0, results.getFailCount(), "Had at least one test failure.  See generated test report.");
    }

    static void generateReport(String karateOutputPath) {
        Collection<File> jsonFiles = FileUtils.listFiles(new File(karateOutputPath), new String[] {"json"}, true);

        List<String> jsonPaths = jsonFiles.stream().map(File::getAbsolutePath).collect(Collectors.toList());

        Configuration config = new Configuration(new File(MAVEN_OUTPUT), REPORT_TITLE);
        ReportBuilder reportBuilder = new ReportBuilder(jsonPaths, config);

        reportBuilder.generateReports();
    }

}
