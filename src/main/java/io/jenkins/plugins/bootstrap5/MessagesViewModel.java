package io.jenkins.plugins.bootstrap5;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import hudson.model.ModelObject;
import hudson.model.Run;

/**
 * A view model to visualize logging messages and errors of a build step.
 *
 * @author Ullrich Hafner
 */
public class MessagesViewModel implements ModelObject {
    private final Run<?, ?> owner;
    private final List<String> errorMessages;
    private final List<String> infoMessages;
    private final String displayName;

    /**
     * Creates a new {@link MessagesViewModel} with info messages only.
     *
     * @param owner
     *         current build as owner of this view
     * @param displayName
     *         display name of the step
     * @param infoMessages
     *         all info messages that should be shown (should not be empty)
     */
    public MessagesViewModel(final Run<?, ?> owner, final String displayName,
            final List<String> infoMessages) {
        this(owner, displayName, infoMessages, Collections.emptyList());
    }

    /**
     * Creates a new {@link MessagesViewModel} with error and info messages.
     *
     * @param owner
     *         current build as owner of this view
     * @param displayName
     *         display name of the step
     * @param infoMessages
     *         all info messages that should be shown (should not be empty)
     * @param errorMessages
     *         all error messages that should be shown (might be empty)
     */
    public MessagesViewModel(final Run<?, ?> owner, final String displayName,
            final List<String> infoMessages, final List<String> errorMessages) {
        this.owner = owner;
        this.errorMessages = asImmutableList(errorMessages);
        this.infoMessages = asImmutableList(infoMessages);

        this.displayName = displayName + " - " + Messages.Messages_View_Name();
    }

    private List<String> asImmutableList(final List<String> elements) {
        return Collections.unmodifiableList(new ArrayList<>(elements));
    }

    /**
     * Returns the build as owner of this view model.
     *
     * @return the owner
     */
    public final Run<?, ?> getOwner() {
        return owner;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Returns the information messages of the step.
     *
     * @return the information messages
     */
    @SuppressWarnings("unused") // Called by jelly view
    public Collection<String> getInfoMessages() {
        return infoMessages;
    }

    /**
     * Returns the error messages of the step.
     *
     * @return the error messages
     */
    @SuppressWarnings("unused") // Called by jelly view
    public Collection<String> getErrorMessages() {
        return errorMessages;
    }
}

