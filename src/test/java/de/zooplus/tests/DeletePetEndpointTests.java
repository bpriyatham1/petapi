package de.zooplus.tests;

import static de.zooplus.apimodel.ResponseSpecificationMock.SC_NOT_FOUND_JSON_VALUE;
import static de.zooplus.apimodel.ResponseSpecificationMock.SC_OK_JSON_NOT_NULL;
import static de.zooplus.staticdata.ErrorMessages.PET_NOT_FOUND;
import static de.zooplus.staticdata.TestTag.REGRESSION;
import static de.zooplus.staticdata.TestTag.SMOKE;
import static org.assertj.core.api.Assertions.assertThat;

import de.zooplus.apimodel.Category;
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
import io.qameta.allure.TmsLink;
import org.assertj.core.api.junit.jupiter.SoftAssertionsExtension;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

/**
 * @author priyatham.bolli
 */
@DisplayName("Zoo-plus - PetStore deletePet DELETE Endpoint Tests")
@Feature("PetStore - deletePet DELETE Endpoint Feature")
@Epic("PetStore Api")
@TmsLink("JiraLink")
@ExtendWith(SoftAssertionsExtension.class)
public class DeletePetEndpointTests {

    @Description("Delete a pet by Pet object via PetStore DELETE Endpoint")
    @Story("PetStore DELETE Endpoint User Story")
    @Tags({@Tag(REGRESSION), @Tag(SMOKE)})
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void deleteExistingPetPositiveScenario() {
        Long petId = 1001L;
        String petName = "Alex";
        String categoryName = "Pomeranian";
        String petTagName = "Pomeranian Tag";
        Category petCategory = Category.categoryMock(1001L, categoryName);
        de.zooplus.apimodel.Tag petTag = new de.zooplus.apimodel.Tag(1001L, petTagName);
        List<de.zooplus.apimodel.Tag> petTags = List.of(petTag);
        List<String> photoUrls = List.of("https://media.istockphoto.com/photos/orange-pomeranian-dog-sleeping-smile-picture-id621356398");
        Pet petMock = new Pet(petId, petCategory, petName, photoUrls, petTags, PetStatus.AVAILABLE.getParameterName());
        Pet pet = PetStoreRestService.addNewPet(petMock, SC_OK_JSON_NOT_NULL);
        PetStoreRestService.deletePet(pet.getId(), SC_OK_JSON_NOT_NULL);
        PetResponse deletedPet = PetStoreRestService.findUnknownPet(pet.getId(), SC_NOT_FOUND_JSON_VALUE);
        assertThat(deletedPet.getMessage()).as("message").isEqualTo(PET_NOT_FOUND);
    }

}
