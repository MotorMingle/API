package fr.motormingle.api.domain;

import static org.assertj.core.api.Assertions.assertThat;

import fr.motormingle.api.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class InterestsTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Interests.class);
        Interests interests1 = new Interests();
        interests1.setId(1L);
        Interests interests2 = new Interests();
        interests2.setId(interests1.getId());
        assertThat(interests1).isEqualTo(interests2);
        interests2.setId(2L);
        assertThat(interests1).isNotEqualTo(interests2);
        interests1.setId(null);
        assertThat(interests1).isNotEqualTo(interests2);
    }
}
