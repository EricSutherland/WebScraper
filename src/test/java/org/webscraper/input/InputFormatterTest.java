package org.webscraper.input;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class InputFormatterTest {

    InputFormatter testInputFormatter = new InputFormatter();

    @DisplayName("""
        when domain contains http
        returns formatted with https
        """)
    @Test
    void formatHttp() {
        String domain = testInputFormatter.format("http://monzo.com");

        assertThat(domain).isEqualTo("https://monzo.com");
    }

    @DisplayName("""
        when domain does not contain https
        returns formatted with https
        """)
    @Test
    void formatWithoutHttps() {
        String domain = testInputFormatter.format("monzo.com");

        assertThat(domain).isEqualTo("https://monzo.com");
    }

    @DisplayName("""
        when domain already contain https
        returns formatted with https
        """)
    @Test
    void formatAlreadyHttps() {
        String domain = testInputFormatter.format("https://monzo.com");

        assertThat(domain).isEqualTo("https://monzo.com");
    }

    @DisplayName("""
        when domain  contain www.
        returns formatted without www.
        """)
    @Test
    void stripWww() {
        String domain = testInputFormatter.format("https://www.monzo.com");

        assertThat(domain).isEqualTo("https://monzo.com");
    }
}