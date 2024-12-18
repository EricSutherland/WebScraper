package org.webscraper.connector;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;


public class ConnectorImplTest {

    @DisplayName("""
        When a exception occures
        raises ConnectionException
        """)
    @Test
    void connectionExceptionTest() {
        assertThatThrownBy(
            () -> new ConnectorImpl().connectToWebsite("bad website")
        ).isInstanceOf(ConnectionException.class)
        .hasCauseInstanceOf(IllegalArgumentException.class);
    }
}
