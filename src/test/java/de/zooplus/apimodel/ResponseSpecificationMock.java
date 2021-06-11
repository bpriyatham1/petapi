package de.zooplus.apimodel;

import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_METHOD_NOT_ALLOWED;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.apache.http.HttpStatus.SC_OK;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;
import org.hamcrest.Matcher;

/**
 * @author priyatham.bolli
 */
public class ResponseSpecificationMock {

    private ResponseSpecificationMock() {
    }

    public static ResponseSpecification expectedResponseForEmptyList(int expectedStatusCode, Matcher<?> expectedBody) {
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectStatusCode(expectedStatusCode);
        responseSpecBuilder.expectBody("", expectedBody);
        return responseSpecBuilder.build();
    }

    public static ResponseSpecification expectedResponse(int expectedStatusCode, ContentType expectedContentType, Matcher<?> expectedBody) {
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectStatusCode(expectedStatusCode);
        responseSpecBuilder.expectContentType(expectedContentType);
        responseSpecBuilder.expectBody(expectedBody);
        return responseSpecBuilder.build();
    }

    private static ResponseSpecification expectedResponse(int expectedStatusCode, ContentType contentType) {
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectStatusCode(expectedStatusCode);
        responseSpecBuilder.expectContentType(contentType);
        return responseSpecBuilder.build();
    }

    public static ResponseSpecification expectedErrorResponse(int expectedStatusCode, ContentType contentType,
        String jsonPathExpression, String jsonPathValue) {
        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder();
        responseSpecBuilder.expectBody(jsonPathExpression, is(jsonPathValue));
        responseSpecBuilder.expectStatusCode(expectedStatusCode);
        responseSpecBuilder.expectContentType(contentType);
        return responseSpecBuilder.build();
    }

    public static final ResponseSpecification SC_OK_EMPTY_LIST = expectedResponseForEmptyList(SC_OK, empty());
    public static final ResponseSpecification SC_OK_JSON_NOT_NULL = expectedResponse(SC_OK, JSON, notNullValue());
    public static final ResponseSpecification SC_METHOD_NOT_ALLOWED_JSON = expectedResponse(SC_METHOD_NOT_ALLOWED, JSON);
    public static final ResponseSpecification SC_NOT_FOUND_JSON_VALUE = expectedResponse(SC_NOT_FOUND, JSON);

}
