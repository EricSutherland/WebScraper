package org.webscraper.input;

abstract class UserInput {
    InputFormatter inputFormatter = new InputFormatter();

    public String run(){
        String domain = gatherInput();

        return inputFormatter.format(domain);
    }

    abstract String gatherInput();
}
