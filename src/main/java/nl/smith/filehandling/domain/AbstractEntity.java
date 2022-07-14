package nl.smith.filehandling.domain;

/**
 * This method <description of functionality>
 *
 * @author m.smithhva.nl
 */
public abstract class AbstractEntity {

    private Long id;

    public Long getId() {
        return id;
    }

    public boolean isNew() {
        return id == null;
    }
}
