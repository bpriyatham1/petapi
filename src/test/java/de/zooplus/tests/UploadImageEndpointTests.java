package de.zooplus.tests;

import static de.zooplus.apimodel.ResponseSpecificationMock.SC_OK_JSON_NOT_NULL;
import static de.zooplus.staticdata.TestTag.REGRESSION;
import static de.zooplus.staticdata.TestTag.SMOKE;
import static org.assertj.core.api.Assertions.assertThat;

import de.zooplus.apimodel.PetResponse;
import de.zooplus.services.PetStoreRestService;
import io.qameta.allure.Description;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author priyatham.bolli
 */
@DisplayName("Zoo-plus - PetStore uploadImage POST Endpoint Tests")
@Feature("PetStore - uploadImage POST Endpoint Feature")
@Epic("PetStore Api")
public class UploadImageEndpointTests {

    @Tags({@org.junit.jupiter.api.Tag(REGRESSION), @org.junit.jupiter.api.Tag(SMOKE)})
    @Description("Upload a pet image via PetStore uploadImage POST Endpoint")
    @Story("PetStore uploadImage POST Endpoint User Story")
    @Severity(SeverityLevel.CRITICAL)
    @Test
    public void uploadFileTest() throws IOException {
        Long petId = 1111L;
        PetResponse response = PetStoreRestService.uploadImage(petId, "meta-data", fileToUpload(), SC_OK_JSON_NOT_NULL);
        assertThat(response.getCode()).as("code").isEqualTo(HttpStatus.SC_OK);
    }

    private static File fileToUpload() throws IOException {
        File file = new File("hello.txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write("Hello world!");
        writer.close();
        return file;
    }

}
