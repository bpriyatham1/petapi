package de.zooplus.config;

import static de.zooplus.staticdata.RestUriPath.HOST_URL;
import static de.zooplus.staticdata.RestUriPath.PET_BASE_PATH;

import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author priyatham.bolli
 */
public class RestAssuredConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestAssuredConfig.class);
    private static final RestAssuredConfig INSTANCE = new RestAssuredConfig();

    private RestAssuredConfig() {
        LOGGER.info("Configuring Rest Assured");
        RestAssured.reset();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
        RestAssured.replaceFiltersWith(new AllureRestAssured());
    }

    public RequestSpecification petApiRequestSpec() {
        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setBaseUri(HOST_URL);
        builder.setBasePath(PET_BASE_PATH);
        builder.addHeader("api_key", "special-key");
        return builder.build();
    }

    public static RestAssuredConfig getInstance() {
        return INSTANCE;
    }

}
