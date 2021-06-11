package de.zooplus.services;

import static de.zooplus.staticdata.RestUriPath.HOST_URL;
import static de.zooplus.staticdata.RestUriPath.PET_BASE_PATH;
import static io.restassured.RestAssured.given;

import de.zooplus.apimodel.Pet;
import de.zooplus.apimodel.PetResponse;
import de.zooplus.config.RestAssuredConfig;
import de.zooplus.staticdata.RestParameters;
import io.qameta.allure.Step;
import io.restassured.http.ContentType;
import io.restassured.specification.ResponseSpecification;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author priyatham.bolli
 */
public final class PetStoreRestService {

    private PetStoreRestService() {
    }

    @Step("Finds pet by given Id: '{petId}' ")
    public static Pet findPetById(Long petId, ResponseSpecification responseSpecification) {
        return
            given()
                .pathParam(RestParameters.PET_ID.getParameterName(), petId)
                .spec(RestAssuredConfig.getInstance().petApiRequestSpec())
                .contentType(ContentType.JSON)
                .when()
                .get(HOST_URL + PET_BASE_PATH + "/{petId}")
                .then()
                .spec(responseSpecification)
                .extract().as(Pet.class);
    }

    @Step("Finds Unknown pet by given Id: '{petId}' ")
    public static PetResponse findUnknownPet(Long petId, ResponseSpecification responseSpecification) {
        return
            given()
                .pathParam(RestParameters.PET_ID.getParameterName(), petId)
                .spec(RestAssuredConfig.getInstance().petApiRequestSpec())
                .contentType(ContentType.JSON)
                .when()
                .get(HOST_URL + PET_BASE_PATH + "/{petId}")
                .then()
                .spec(responseSpecification)
                .extract().as(PetResponse.class);
    }

    @Step("Finds pet by given Status: '{petStatus}' ")
    public static List<Pet> findPetByStatus(String petStatus, ResponseSpecification responseSpecification) {
        return
            given()
                .queryParam(RestParameters.STATUS.getParameterName(), petStatus == null ? "" : petStatus)
                .spec(RestAssuredConfig.getInstance().petApiRequestSpec())
                .contentType(ContentType.JSON)
                .when()
                .get(HOST_URL + PET_BASE_PATH + "/findByStatus")
                .then()
                .spec(responseSpecification)
                .extract().response().getBody().jsonPath().getList(".", Pet.class);
    }

    @Step("Add a new Pet: '{petBody}' to the pet store")
    public static Pet addNewPet(Pet petBody, ResponseSpecification responseSpecification) {
        return
            given()
                .spec(RestAssuredConfig.getInstance().petApiRequestSpec())
                .contentType(ContentType.JSON)
                .when()
                .body(petBody == null ? "" : petBody)
                .post(HOST_URL + PET_BASE_PATH)
                .then()
                .spec(responseSpecification)
                .extract().as(Pet.class);
    }

    @Step("Updates an existing Pet: '{pet}'")
    public static Pet updatePet(Pet pet, ResponseSpecification responseSpecification) {
        return
            given()
                .spec(RestAssuredConfig.getInstance().petApiRequestSpec())
                .contentType(ContentType.JSON)
                .when()
                .body(pet == null ? "" : pet)
                .put(HOST_URL + PET_BASE_PATH)
                .then()
                .spec(responseSpecification)
                .extract().as(Pet.class);
    }

    @Step("Updates an existing Pet(with form data): '{pet}'")
    public static PetResponse updatePetWithFormData(Pet pet, ResponseSpecification responseSpecification) {
        return
            given()
                .pathParam(RestParameters.PET_ID.getParameterName(), pet.getId())
                .spec(RestAssuredConfig.getInstance().petApiRequestSpec())
                .contentType(ContentType.URLENC)
                .formParam(pet.getName())
                .formParam(pet.getStatus())
                .when()
                .post(HOST_URL + PET_BASE_PATH + "/{petId}")
                .then()
                .spec(responseSpecification)
                .extract().as(PetResponse.class);
    }

    @Step("Deletes an existing by PetId: '{petId}'")
    public static void deletePet(Long petId, ResponseSpecification responseSpecification) {
        given()
            .pathParam(RestParameters.PET_ID.getParameterName(), petId)
            .spec(RestAssuredConfig.getInstance().petApiRequestSpec())
            .contentType(ContentType.JSON)
            .when()
            .delete(HOST_URL + PET_BASE_PATH + "/{petId}")
            .then()
            .spec(responseSpecification);
    }

    @Step("Uploads an image of a Pet by PetId: '{petId}'")
    public static PetResponse uploadImage(Long petId, String additionalMetadata, File file, ResponseSpecification responseSpecification) throws IOException {
        return
            given()
                .spec(RestAssuredConfig.getInstance().petApiRequestSpec())
                .pathParam(RestParameters.PET_ID.getParameterName(), petId)
                .contentType(ContentType.MULTIPART)
                .when()
                .multiPart(file)
                .formParam(additionalMetadata)
                .post(HOST_URL + PET_BASE_PATH + "/{petId}/uploadImage")
                .then()
                .spec(responseSpecification)
                .extract().as(PetResponse.class);
    }

}
