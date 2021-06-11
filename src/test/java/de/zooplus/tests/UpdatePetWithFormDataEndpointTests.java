package de.zooplus.tests;

import static de.zooplus.apimodel.ResponseSpecificationMock.SC_OK_JSON_NOT_NULL;
import static de.zooplus.staticdata.TestTag.REGRESSION;
import static de.zooplus.staticdata.TestTag.SMOKE;
import static org.assertj.core.api.Assertions.assertThat;

import de.zooplus.apimodel.Pet;
import de.zooplus.apimodel.PetResponse;
import de.zooplus.apimodel.PetStatus;
import de.zooplus.services.PetStoreRestService;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

/**
 * @author priyatham.bolli
 */
@DisplayName("Zoo-plus - PetStore updatePetWithFormData POST Endpoint Tests")
@Feature("PetStore - updatePetWithFormData POST Endpoint Feature")
@Epic("PetStore Api")
public class UpdatePetWithFormDataEndpointTests {

    @Description("Update a pet by Pet object via PetStore POST Endpoint")
    @Story("PetStore POST Endpoint User Story")
    @Tags({@Tag(REGRESSION), @Tag(SMOKE)})
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void updatePetWithFormDataPositiveScenario() {
        Long petId = 11011L;
        String updatedName = "Alex updatedName";
        Pet existingPet = PetStoreRestService.findPetById(petId, SC_OK_JSON_NOT_NULL);
        existingPet.setName(updatedName);
        existingPet.setStatus(PetStatus.SOLD.getParameterName());
        PetResponse petResponse = PetStoreRestService.updatePetWithFormData(existingPet, SC_OK_JSON_NOT_NULL);
        assertThat(petResponse.getCode()).as("code").isEqualTo(HttpStatus.SC_OK);
        assertThat(petResponse.getMessage()).isEqualTo(String.valueOf(petId));
    }

}
