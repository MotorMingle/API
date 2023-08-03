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
    void testTagNull() {
        assertThrows(NullPointerException.class, () -> minglerTagGet.setTag(null));
    }

    @Test
    void testRequiredArgsConstructor() {
        String tag = "enixo";
        MinglerTagGet minglerTagGet = new MinglerTagGet(tag);
        assertNotNull(minglerTagGet);
        assertEquals(tag, minglerTagGet.getTag());
    }

    @Test
    void testEqualsAndHashCodeOk() {
        MinglerTagGet minglerTagGet1 = new MinglerTagGet("enixo");
        MinglerTagGet minglerTagGet2 = new MinglerTagGet("enixo");
        assertEquals(minglerTagGet1, minglerTagGet1);
        assertEquals(minglerTagGet1, minglerTagGet2);
        assertEquals(minglerTagGet1.hashCode(), minglerTagGet2.hashCode());
    }

    @Test
    void testEqualsAndHashCodeNotOk() {
        MinglerTagGet minglerTagGet1 = new MinglerTagGet("enixo");
        MinglerTagGet minglerTagGet2 = new MinglerTagGet("vixi9");
        assertNotEquals(minglerTagGet1, new Object());
        assertNotEquals(minglerTagGet1, minglerTagGet2);
        assertNotEquals(minglerTagGet1, new MinglerTagGet(null));
        assertNotEquals(new MinglerTagGet(null), minglerTagGet1);
        assertNotEquals(minglerTagGet1.hashCode(), minglerTagGet2.hashCode());
    }

    @Test
    void testRequiredArgsConstructorNull() {
        assertThrows(NullPointerException.class, () -> new MinglerTagGet(null));
    }
}
