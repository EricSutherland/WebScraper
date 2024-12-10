package org.webscraper;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.webscraper.connector.ConnectionException;
import org.webscraper.connector.ConnectorImpl;

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
