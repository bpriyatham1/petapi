package de.zooplus.tests;

import static de.zooplus.apimodel.ResponseSpecificationMock.SC_OK_JSON_NOT_NULL;
import static de.zooplus.apimodel.ResponseSpecificationMock.expectedErrorResponse;
import static de.zooplus.staticdata.ErrorMessages.PET_NOT_FOUND;
import static de.zooplus.staticdata.TestTag.REGRESSION;
import static de.zooplus.staticdata.TestTag.SMOKE;
import static io.restassured.http.ContentType.JSON;
import static org.apache.http.HttpStatus.SC_NOT_FOUND;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_PLACEHOLDER;

import de.zooplus.apimodel.Pet;
import de.zooplus.services.PetStoreRestService;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.stream.LongStream;
import java.util.stream.Stream;

/**
 * @author priyatham.bolli
 */
@DisplayName("Zoo-plus - PetStore findPetById GET Endpoint Tests")
@Feature("PetStore - findPetById Get Endpoint Feature")
@Epic("PetStore Api")
@TmsLink("JiraLink")
public final class FindPetByIdGetEndPointTests {

    @ParameterizedTest(name = "Find the pet via findPetById Endpoint by Valid petId Test :: " + ARGUMENTS_PLACEHOLDER)
    @Description("Find the pet by PetId via PetStore Endpoint")
    @Story("PetStore GET Endpoint User Story")
    @Tags({@Tag(REGRESSION), @Tag(SMOKE)})
    @Severity(SeverityLevel.CRITICAL)
    @MethodSource("getValidPetIds")
    @TmsLink("JiraLink")
    void findPetByIdPositiveScenario(Long petId) {
        Pet pet = PetStoreRestService.findPetById(petId, SC_OK_JSON_NOT_NULL);
        assertThat(pet).as("petObject").isNotNull()
            .extracting(Pet::getId).as("petId").isEqualTo(petId);
    }

    @ValueSource(longs = {1234567L, Long.MIN_VALUE, Long.MAX_VALUE, 56L, 333L, 666L, 0L, -1L, -100L, 8999L,})
    @ParameterizedTest(name = "Find the pet via findPetById by Invalid petId Test :: " + ARGUMENTS_PLACEHOLDER)
    @Description("Find the pet via findPetById with invalid id should not be successful")
    @Story("PetStore Endpoint User Story")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag(REGRESSION)})
    @TmsLink("JiraLink")
    void findPetByIdNegativeScenario(Long petId) {
        ResponseSpecification expectedResponse = expectedErrorResponse(SC_NOT_FOUND, JSON, "message", PET_NOT_FOUND);
        PetStoreRestService.findUnknownPet(petId, expectedResponse);
    }

    /**
     * Test data for List of Pet Ids for Positive Scenario
     * @return {@link Stream<Integer>}
     */
    private static Stream<Long> getValidPetIds() {
        // 133, 654, 777, 789, 991, 992, 999
        return LongStream.of(1, 3, 12, 33, 99).boxed();
    }

}