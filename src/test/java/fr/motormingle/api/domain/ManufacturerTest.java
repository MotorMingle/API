package fr.motormingle.api.domain;

import static org.assertj.core.api.Assertions.assertThat;

import fr.motormingle.api.web.rest.TestUtil;
import org.junit.jupiter.api.Test;

class ManufacturerTest {

    @Test
    void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Manufacturer.class);
        Manufacturer manufacturer1 = new Manufacturer();
        manufacturer1.setId(1L);
        Manufacturer manufacturer2 = new Manufacturer();
        manufacturer2.setId(manufacturer1.getId());
        assertThat(manufacturer1).isEqualTo(manufacturer2);
        manufacturer2.setId(2L);
        assertThat(manufacturer1).isNotEqualTo(manufacturer2);
        manufacturer1.setId(null);
        assertThat(manufacturer1).isNotEqualTo(manufacturer2);
    }
}
