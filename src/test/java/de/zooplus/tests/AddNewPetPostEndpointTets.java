package de.zooplus.tests;

import static de.zooplus.apimodel.ResponseSpecificationMock.SC_METHOD_NOT_ALLOWED_JSON;
import static de.zooplus.apimodel.ResponseSpecificationMock.SC_OK_JSON_NOT_NULL;
import static de.zooplus.staticdata.TestTag.NEGATIVE;
import static de.zooplus.staticdata.TestTag.REGRESSION;
import static de.zooplus.staticdata.TestTag.SMOKE;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

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
@DisplayName("Zoo-plus - PetStore POST Endpoint Tests")
@Feature("PetStore - Post Endpoint Feature")
@Epic("PetStore Api")
public class AddNewPetPostEndpointTets {

    @Description("Add a pet by Pet object via PetStore POST Endpoint")
    @Story("PetStore POST Endpoint User Story")
    @Tags({@Tag(REGRESSION), @Tag(SMOKE)})
    @Severity(SeverityLevel.CRITICAL)
    @Test
    void addPetToPetStorePositiveScenario() {
        Long petId = 111L;
        String petName = "Alex";
        String categoryName = "Pomeranian";
        String petTagName = "Pomeranian Tag";
        Category petCategory = Category.categoryMock(22L, categoryName);
        de.zooplus.apimodel.Tag petTag = new de.zooplus.apimodel.Tag(23L, petTagName);
        List<de.zooplus.apimodel.Tag> petTags = List.of(petTag);
        List<String> photoUrls = List.of("https://media.istockphoto.com/photos/orange-pomeranian-dog-sleeping-smile-picture-id621356398");
        Pet petMock = new Pet(petId, petCategory, petName, photoUrls, petTags, PetStatus.AVAILABLE.getParameterName());

        Pet pet = PetStoreRestService.addNewPet(petMock, SC_OK_JSON_NOT_NULL);
        assertThat(pet).as("petObject added new").isNotNull()
            .extracting(Pet::getId, Pet::getName, Pet::getPhotoUrls, Pet::getStatus).as("petObject")
            .containsExactly(petId, petName, photoUrls, PetStatus.AVAILABLE.getParameterName());
        assertThat(pet.getCategory()).as("petCategory").isNotNull()
            .extracting(Category::getId, Category::getName).as("category")
            .containsExactly(22, categoryName);
        assertThat(pet.getTags()).hasSize(1).as("petTag").isNotNull()
            .extracting(de.zooplus.apimodel.Tag::getId, de.zooplus.apimodel.Tag::getName).as("Tag")
            .containsOnly(tuple(23, petTagName));
    }


    @Description("Add null pet to PetStore POST Endpoint should not be successful")
    @Story("PetStore POST Endpoint User Story")
    @Severity(SeverityLevel.NORMAL)
    @Tags({@Tag(REGRESSION)})
    @Tag(NEGATIVE)
    @Test
    void addPetToPetStoreNegativeScenario() {
        PetStoreRestService.addNewPet(null, SC_METHOD_NOT_ALLOWED_JSON);
    }

}


