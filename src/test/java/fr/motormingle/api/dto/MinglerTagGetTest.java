package fr.motormingle.api.dto;

import fr.motormingle.api.dto.mingler.get.MinglerTagGet;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MinglerTagGetTest {
    private MinglerTagGet minglerTagGet;

    @BeforeEach
    public void setUp() {
        minglerTagGet = new MinglerTagGet("enixo");
    }

    @Test
    public void testSetterAndGetter() {
        minglerTagGet.setTag("vixi9");
        assertEquals("vixi9", minglerTagGet.getTag());
    }
}
