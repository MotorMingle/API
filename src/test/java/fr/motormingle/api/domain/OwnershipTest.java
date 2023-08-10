package fr.motormingle.api.domain;

import static org.assertj.core.api.Assertions.assertThat;

import fr.motormingle.api.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class OwnershipTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Ownership.class);
        Ownership ownership1 = new Ownership();
        ownership1.setId(1L);
        Ownership ownership2 = new Ownership();
        ownership2.setId(ownership1.getId());
        assertThat(ownership1).isEqualTo(ownership2);
        ownership2.setId(2L);
        assertThat(ownership1).isNotEqualTo(ownership2);
        ownership1.setId(null);
        assertThat(ownership1).isNotEqualTo(ownership2);
    }
}
