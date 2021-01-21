package com.celtra;

import com.celtra.objects.Expandable;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.assertj.core.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ExpandableAndModalTest extends Main {

    private Expandable expandable = new Expandable(appiumDriver);

    @BeforeAll
    public void setup() {
        expandable.mainView();
    }

    @AfterAll
    public void end() {
        appiumDriver.close();
    }

    @Test
    public void bannerValidation() {
        // Verify 'Expandable' text and Celtra logo are visible on banner
        expandable.switchTo(1);
        assertThat(expandable.getBannerTitle()).isEqualTo("Expandable");
        assertThat(expandable.isLogoVisible()).isTrue();

        // Tap on banner to open modal box and verify it contains 'Modal Unit' text
        expandable.openModal();
        expandable.switchTo(2);
        assertThat(expandable.modalText()).isEqualTo("Modal Unit");

        // Touch on Celtra logo and verify it hides
        expandable.tapLogo();
        assertThat(expandable.isModalLogoVisible()).isFalse();

        // Close modal box and ensure it is not visible anymore
        expandable.closeModal();
        assertThat(expandable.isModalVisible()).isFalse();
        expandable.switchTo(1);

        // Validate after closing modal that banner reappeared
        assertThat(expandable.isBannerVisible()).isTrue();
    }

}
