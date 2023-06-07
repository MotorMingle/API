package fr.motormingle.api.entity;

/**
 * The enum for the encounter status.
 */
public enum EncounterStatus {
    /**
     * The encounter has been accepted by both users.
     */
    ACCEPTED,
    /**
     * The encounter has been declined by one of the users.
     */
    DECLINED,
    /**
     * The encounter is pending.
     */
    PENDING
}
