package fr.motormingle.api.entity;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class UserPairTest {

    private UUID userId1;
    private UUID userId2;
    private UserPair userPair1;
    private UserPair userPair2;

    @BeforeEach
    void setUp() {
        userId1 = UUID.randomUUID();
        userId2 = UUID.randomUUID();

        userPair1 = new UserPair();
        userPair1.setUserId1(userId1);
        userPair1.setUserId2(userId2);

        userPair2 = new UserPair();
        userPair2.setUserId1(userId2);
        userPair2.setUserId2(userId1);
    }

    @Test
    void testEquals() {
        assertEquals(userPair1, userPair1);

        assertNotEquals(userPair1, new Object());

        assertNotEquals(userPair1, userPair2);
        userPair2.setUserId1(this.userId1);
        assertNotEquals(userPair1, userPair2);
        userPair2.setUserId2(this.userId2);
        assertEquals(userPair1, userPair2);

        assertNotEquals(userPair1, new UserPairStats());
    }

    @Test
    void testHashCode() {
        assertEquals(userPair1.hashCode(), userPair1.hashCode());
        assertNotEquals(userPair1.hashCode(), new UserPair().hashCode());
    }
}
