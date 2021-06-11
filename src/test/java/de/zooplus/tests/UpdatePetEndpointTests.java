package de.zooplus.tests;

import static de.zooplus.apimodel.ResponseSpecificationMock.SC_OK_JSON_NOT_NULL;
import static de.zooplus.staticdata.TestTag.REGRESSION;
import static de.zooplus.staticdata.TestTag.SMOKE;
import static org.assertj.core.api.Assertions.assertThat;

import de.zooplus.apimodel.Category;
import de.zooplus.apimodel.Pet;
import de.zooplus.apimodel.PetStatus;
import de.zooplus.services.PetStoreRestService;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author priyatham.bolli
 */
@DisplayName("Zoo-plus - PetStore PUT Endpoint Tests")
@Feature("PetStore - PUT Endpoint Feature")
@Epic("PetStore Api")
public class UpdatePetEndpointTests {

    @Description("Update a pet by Pet object via PetStore updatePet PUT Endpoint")
    @Story("PetStore updatePet PUT Endpoint User Story")
    @Tags({@Tag(REGRESSION), @Tag(SMOKE)})
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void updateExistingPetPositiveScenario() {
        Long petId = 1111L;
        String updatedName = "Alex updatedName";
        String categoryName = "Pomeranian1";
        String petTagName = "Pomeranian Tag1";
        Category petCategory = Category.categoryMock(221L, categoryName);
        de.zooplus.apimodel.Tag petTag = new de.zooplus.apimodel.Tag(231L, petTagName);
        List<de.zooplus.apimodel.Tag> petTags = List.of(petTag);
        List<String> photoUrls = List.of("https://media.istockphoto.com/photos/orange-pomeranian-dog-sleeping-smile-picture-id621356398");
        Pet petMock = new Pet(petId, petCategory, "Alex1", photoUrls, petTags, PetStatus.AVAILABLE.getParameterName());
        Pet pet = PetStoreRestService.addNewPet(petMock, SC_OK_JSON_NOT_NULL);

        Pet existingPet = PetStoreRestService.findPetById(pet.getId(), SC_OK_JSON_NOT_NULL);
        existingPet.setName(updatedName);
        existingPet.setStatus(PetStatus.SOLD.getParameterName());
        Pet updatedPet = PetStoreRestService.updatePet(existingPet, SC_OK_JSON_NOT_NULL);
        assertThat(updatedPet.getName()).isEqualTo(updatedName);
        assertThat(updatedPet.getStatus()).isEqualTo(PetStatus.SOLD.getParameterName());
    }

}
