package fr.motormingle.api.dto;

import fr.motormingle.api.dto.mingler.get.MinglerTagGet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MinglerTagGetTest {
    private MinglerTagGet minglerTagGet;

    @BeforeEach
    public void setUp() {
        minglerTagGet = new MinglerTagGet("enixo");
    }

    @Test
    void testSetterAndGetter() {
        minglerTagGet.setTag("vixi9");
        assertEquals("vixi9", minglerTagGet.getTag());
    }

    @Test
    void testRequiredArgsConstructor() {
        String tag = "enixo";
        MinglerTagGet minglerTagGet = new MinglerTagGet(tag);
        assertNotNull(minglerTagGet);
        assertEquals(tag, minglerTagGet.getTag());
    }

    @Test
    void testEqualsAndHashCode() {
        MinglerTagGet minglerTagGet1 = new MinglerTagGet("enixo");
        MinglerTagGet minglerTagGet2 = new MinglerTagGet("enixo");
        assertEquals(minglerTagGet1, minglerTagGet2);
        assertEquals(minglerTagGet1.hashCode(), minglerTagGet2.hashCode());
    }

    @Test
    void testTagNull() {
        assertThrows(NullPointerException.class, () -> minglerTagGet.setTag(null));
    }

    @Test
    void testRequiredArgsConstructorNull() {
        assertThrows(NullPointerException.class, () -> new MinglerTagGet(null));
    }
}
