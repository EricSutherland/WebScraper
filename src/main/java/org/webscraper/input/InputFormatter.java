package org.webscraper.input;

public class InputFormatter {
    String format(String domain) {
        domain = stripWww(domain);

        return formatProtocal(domain);
    }

    private String stripWww(String domain) {
        return domain.replace("www.", "");
    }

    private String formatProtocal(String domain) {
        if (domain.startsWith("https://")) {
            return domain;
        } else if (domain.startsWith("http://")) {
            return domain.replace("http://", "https://");
        }

        return "https://".concat(domain);
    }
}
