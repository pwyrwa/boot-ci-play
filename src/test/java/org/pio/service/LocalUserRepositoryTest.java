package org.pio.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.tuple;

import org.junit.Before;
import org.junit.Test;

/**
 * <Add description here>
 * <p/>
 * Created 2/21/16.
 */
public class LocalUserRepositoryTest {


    UserRepository userRepository;

    @Before
    public void setup() {
        userRepository = new LocalUserRepository();
    }

    @Test
    public void extracting() {

        assertThat(userRepository.getAll())
                .extracting("lastName")
                .contains("W", "C");
    }

    @Test
    public void extracting_tuple() {

        assertThat(userRepository.getAll())
                .extracting("firstName", "lastName")
                .contains(
                        tuple("Piotr", "W")
                        ,tuple("Aidan", "W")
                        ,tuple("Barbie", "C")
                );
    }

    @Test
    public void contains_only() {

        assertThat(userRepository.getAll())
                .extracting("firstName")
                .containsOnly(
                        "Piotr", "Barbie", "Vivienne", "Aidan");
    }

    @Test
    public void filtering_contains_exactly() {

        assertThat(userRepository.getAll())
                .filteredOn(u -> "W".equals(u.getLastName()))
                .extracting("firstName")
                .containsExactly(
                        "Piotr", "Aidan", "Vivienne");
    }


    @Test
    public void longString_finds() {

        String intro = "AssertJ has some great features that people don't always know, "
                + "the purpose of this page is to show them so that you take the most "
                + "of AssertJ.";


        assertThat(intro)
                .contains("AssertJ")
                .contains("great features")
                .doesNotContain("Heavy");
    }


}
