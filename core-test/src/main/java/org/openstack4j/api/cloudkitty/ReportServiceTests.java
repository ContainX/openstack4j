package org.openstack4j.api.cloudkitty;

import org.openstack4j.api.AbstractTest;
import org.openstack4j.model.cloudkitty.Summary;
import org.testng.annotations.Test;

import javax.xml.bind.DatatypeConverter;
import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import static org.testng.Assert.*;

@Test(suiteName = "Rating/report", enabled = true)
public class ReportServiceTests extends AbstractTest {

    private final String JSON_REPORT_SUMMARY = "/cloudkitty/report-summary.json";
    private final String JSON_REPORT_TENANTS = "/cloudkitty/report-tenants.json";
    private final String REPORT_TOTAL = "/cloudkitty/report-total.json";

    public void testSummary() throws IOException, ParseException {
        respondWith(JSON_REPORT_SUMMARY);
        Date date = DatatypeConverter.parseDate("2018-02-28T14:25:53+00:00").getTime();
        List<? extends Summary> list = osv3().rating().reporting().summary(
                date, date, "tenantid", "serviceid", "groupby", true
        );

        assertEquals(list.size(), 1);

        Summary summary = list.get(0);

        assertEquals(summary.getBegin(), date);
        assertEquals(summary.getEnd(), date);
        assertEquals(summary.getRate(), 1f);
        assertEquals(summary.getResourceType(), "compute");
        assertEquals(summary.getTenantId(), "69d12143688f413cbf5c3cfe03ed0a12");
    }

    public void testTenants() throws IOException {
        respondWith(JSON_REPORT_TENANTS);

        List<String> list = osv3().rating().reporting().tenants(null, null);

        assertEquals(list.size(), 2);
        assertEquals(list.get(0), "tenant1");
    }

    public void testTotal() throws IOException {
        respondWith(REPORT_TOTAL);
        Date date = new Date();

        float total = osv3().rating().reporting().total(
                date, date, "tenantid", "serviceid", true);

        assertEquals(total, 12.10f);
    }

    @Override
    protected Service service() {
        return Service.RATING;
    }
}
