package my_tests;

import com.qaprosoft.apitools.validation.JsonCompareKeywords;
import com.qaprosoft.carina.core.foundation.IAbstractTest;
import com.qaprosoft.carina.core.foundation.api.http.HttpResponseStatusType;
import com.qaprosoft.carina.core.foundation.utils.ownership.MethodOwner;
import com.qaprosoft.carina.demo.utils.weather_api.GetWeatherReport;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.invoke.MethodHandles;

/**
 * This sample shows how create REST API tests.
 *
 * @author Blake
 */
public class WeatherAPITest implements IAbstractTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());

    @Test()
    @MethodOwner(owner = "Blake")
    public void testGetWeatherReport() {
        GetWeatherReport api = new GetWeatherReport();
        api.replaceUrlPlaceholder("api_request", "dt=1500000000&lat=10&lon=10");
        api.callAPIExpectSuccess();
        api.validateResponse(JSONCompareMode.STRICT, JsonCompareKeywords.ARRAY_CONTAINS.getKey());
        api.validateResponseAgainstSchema("api/weather_reports/_get/rs.schema");
    }

    @Test()
    @MethodOwner(owner = "Blake")
    public void testNoGeocode() {
        GetWeatherReport api = new GetWeatherReport();
        api.replaceUrlPlaceholder("api_request", "null");
        String rs = api.callAPI().asString();
        api.expectResponseStatus(HttpResponseStatusType.BAD_REQUEST_400);
        Assert.assertTrue(rs.contains("Nothing to geocode"), "should give message: Nothing to geocode");
    }

    @Test()
    @MethodOwner(owner = "Blake")
    public void testBadCoordinates() {
        GetWeatherReport api = new GetWeatherReport();
        api.replaceUrlPlaceholder("api_request", "dt=1500000000&lat=10000&lon=10000");
        String rs = api.callAPI().asString();
        api.expectResponseStatus(HttpResponseStatusType.BAD_REQUEST_400);
        Assert.assertTrue(rs.contains("wrong latitude"), "should give message: Wrong latitude");
    }

    @Test()
    @MethodOwner(owner = "Blake")
    public void testChangeLanguageResponse() {
        GetWeatherReport api = new GetWeatherReport();
        api.replaceUrlPlaceholder("api_request", "dt=1500000000&lat=39.31&lon=-74.5&lang=ru");
        String rs = api.callAPIExpectSuccess().asString();
        api.validateResponseAgainstSchema("api/weather_reports/_get/rs.schema");
        Assert.assertTrue(rs.matches("[а-яА-Я]"), "should include Cyrillic characters");
    }

    @Test()
    @MethodOwner(owner = "Blake")
    public void testChangeMeasurementUnits() {
        GetWeatherReport call1 = new GetWeatherReport();
        call1.replaceUrlPlaceholder("api_request", "dt=1500000000&lat=10&lon=10&units=imperial");
        String imperial = call1.callAPIExpectSuccess().asString();
        GetWeatherReport call2 = new GetWeatherReport();
        call2.replaceUrlPlaceholder("api_request", "lat=10&lon=10&units=metric");
        String metric = call2.callAPIExpectSuccess().asString();
        Assert.assertNotEquals(imperial, metric);
    }

    @Test()
    @MethodOwner(owner = "Blake")
    public void testChangeTimestamp() {
        GetWeatherReport call1 = new GetWeatherReport();
        call1.replaceUrlPlaceholder("api_request", "dt=1500000000&lat=10&lon=10");
        String time1 = call1.callAPIExpectSuccess().asString();
        GetWeatherReport call2 = new GetWeatherReport();
        call2.replaceUrlPlaceholder("api_request", "dt=1400000000&lat=10&lon=10");
        String time2 = call2.callAPIExpectSuccess().asString();
        Assert.assertNotEquals(time1, time2);
    }

}
