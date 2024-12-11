package org.webscraper.input;

import java.util.Scanner;

public class ConsoleInput extends UserInput {
    Scanner scanner = new Scanner(System.in);

    @Override
    public String gatherInput() {
        System.out.println("Enter the domain you wish to scrape (eg monzo.com): ");
        return scanner.nextLine();
    }
}
