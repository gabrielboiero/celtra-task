package com.celtra;

import com.celtra.objects.Modal;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.assertj.core.api.Assertions.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ModalTest extends Main {

    private Modal modal = new Modal(appiumDriver);

    @BeforeAll
    public void setup() {
        modal.mainView();
    }

    @AfterAll
    public void end() {
        appiumDriver.close();
    }

    @Test
    public void modalValidation() {
        // Validate 'Modal Unit' and Celtra logo are visible
        modal.switchTo(1);
        assertThat(modal.isModalVisible()).isTrue();
        assertThat(modal.text()).isEqualTo("Modal Unit");
        assertThat(modal.isCeltraLogoVisible()).isTrue();

        // Tap on Celtra log and validate it disappeared
        modal.tapLogo();
        assertThat(modal.isCeltraLogoVisible()).isFalse();

        // Close the page and validate it has successfully closed
        modal.close();
        assertThat(modal.isModalVisible()).isFalse();
    }

}
