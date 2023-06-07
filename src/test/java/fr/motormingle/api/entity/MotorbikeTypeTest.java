package fr.motormingle.api.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class MotorbikeTypeTest {

    private MotorbikeType motorbikeType1;
    private MotorbikeType motorbikeType2;

    @BeforeEach
    void setup() {
        motorbikeType1 = new MotorbikeType();
        motorbikeType1.setId(1);
        motorbikeType1.setName("Type1");

        motorbikeType2 = new MotorbikeType();
        motorbikeType2.setId(2);
        motorbikeType2.setName("Type2");
    }

    @Test
    void testEquality() {
        assertNotEquals(motorbikeType1, motorbikeType2);
    }

    @Test
    void testSetterGetter() {
        assertEquals(1, motorbikeType1.getId());
        assertEquals("Type1", motorbikeType1.getName());
        assertEquals(2, motorbikeType2.getId());
        assertEquals("Type2", motorbikeType2.getName());

        motorbikeType1.setName("Type3");
        motorbikeType1.setId(3);
        assertEquals(3, motorbikeType1.getId());
        assertEquals("Type3", motorbikeType1.getName());
    }
}
