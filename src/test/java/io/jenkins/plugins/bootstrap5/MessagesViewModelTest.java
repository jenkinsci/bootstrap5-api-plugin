package io.jenkins.plugins.bootstrap5;

import java.util.List;

import org.junit.jupiter.api.Test;

import hudson.model.Run;

import static java.util.Arrays.*;
import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

/**
 * Tests the class {@link MessagesViewModel}.
 *
 * @author Ullrich Hafner
 */
class MessagesViewModelTest {
    private static final String TITLE = "Title";
    private static final String DISPLAY_NAME = TITLE + " - Messages";
    private static final List<String> INFO_MESSAGES = asList("One", "Two");
    private static final List<String> ERROR_MESSAGES = asList("Error One", "Error Two");

    @Test
    void shouldCreateOnlyMessages() {
        Run<?, ?> build = mock(Run.class);
        MessagesViewModel model = new MessagesViewModel(build, TITLE, INFO_MESSAGES);

        assertThat(model.getDisplayName()).isEqualTo(DISPLAY_NAME);
        assertThat(model.getOwner()).isSameAs(build);
        assertThat(model.getInfoMessages()).containsExactlyElementsOf(INFO_MESSAGES);
        assertThat(model.getErrorMessages()).isEmpty();
        assertThat(model.hasErrors()).isFalse();
    }

    @Test
    void shouldCreateMessagesAndErrors() {
        Run<?, ?> build = mock(Run.class);
        MessagesViewModel model = new MessagesViewModel(build, TITLE, INFO_MESSAGES, ERROR_MESSAGES);

        assertThat(model.getDisplayName()).isEqualTo(DISPLAY_NAME);
        assertThat(model.getOwner()).isSameAs(build);
        assertThat(model.getInfoMessages()).containsExactlyElementsOf(INFO_MESSAGES);
        assertThat(model.getErrorMessages()).containsExactlyElementsOf(ERROR_MESSAGES);
        assertThat(model.hasErrors()).isTrue();
    }
}
