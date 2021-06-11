package de.zooplus.tests;

import static de.zooplus.apimodel.ResponseSpecificationMock.SC_OK_EMPTY_LIST;
import static de.zooplus.apimodel.ResponseSpecificationMock.SC_OK_JSON_NOT_NULL;
import static de.zooplus.staticdata.TestTag.REGRESSION;
import static de.zooplus.staticdata.TestTag.SMOKE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.params.ParameterizedTest.ARGUMENTS_PLACEHOLDER;

import de.zooplus.apimodel.Pet;
import de.zooplus.apimodel.PetStatus;
import de.zooplus.services.PetStoreRestService;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.qameta.allure.TmsLink;
import org.assertj.core.api.SoftAssertions;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;

/**
 * @author priyatham.bolli
 */
@DisplayName("Zoo-plus - PetStore FindByStatus GET Endpoint Tests")
@Feature("PetStore - FindByStatus GET Endpoint Feature")
@Epic("PetStore Api")
@TmsLink("JiraLink")
@ExtendWith(SoftAssertionsExtension.class)
public class FindPetsByStatusGetEndPointsTests {

    @ParameterizedTest(name = "Find the pet via findPetByStatus Endpoint by Valid status :: " + ARGUMENTS_PLACEHOLDER)
    @Description("Find pets by findPetByStatus GET Endpoint should be successful")
    @Story("PetStore findPetByStatus GET Endpoint User Story")
    @Tags({@Tag(SMOKE), @Tag(REGRESSION)})
    @EnumSource(value = PetStatus.class)
    @Severity(SeverityLevel.CRITICAL)
    void findPetByStatusPositiveScenario(PetStatus petStatus) {
        List<Pet> pets = PetStoreRestService.findPetByStatus(petStatus.getParameterName(), SC_OK_JSON_NOT_NULL);
        assertThat(pets.size()).as("size").isGreaterThanOrEqualTo(1);
        for (Pet pet : pets) {
            assertThat(pet.getStatus()).as("petStatus").isEqualTo(petStatus.getParameterName());
        }
    }

    @ParameterizedTest(name = "Find the pet via findPetByStatus Endpoint by InValid status :: " + ARGUMENTS_PLACEHOLDER)
    @ValueSource(strings = {"1234567", "unknown", "null", "0", "äöüß", "@#!$%^&*^(&)_-=?'"})
    @Description("Find pets by findPetByStatus GET Endpoint with Invalid value should return empty list")
    @Story("PetStore findPetByStatus GET Endpoint User Story")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag(REGRESSION)})
    void findPetByStatusNegativeScenario(String invalidStatus) {
        PetStoreRestService.findPetByStatus(invalidStatus, SC_OK_EMPTY_LIST);
    }

    @Description("Find pets by findPetByStatus GET Endpoint with null value should return list with no results")
    @Story("PetStore findPetByStatus GET Endpoint User Story")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag(REGRESSION)})
    @Test
    void findPetByNullStatusNegativeScenario(SoftAssertions softly) {
        List<Pet> pets = PetStoreRestService.findPetByStatus(null, SC_OK_JSON_NOT_NULL);
        Pet pet = pets.stream().findFirst().orElseThrow(() -> new RuntimeException("Pet not found"));
        softly.assertThat(pet.getName()).as("name").isEqualTo("teroSr");
        softly.assertThat(pet.getStatus()).as("status").isEmpty();
    }

}
