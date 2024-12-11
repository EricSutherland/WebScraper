# Web Scraper

## Requirements

We'd like you to write a simple web crawler in a programming language you're familiar with. Given a starting URL, the crawler should visit each URL it finds on the same domain. It should print each URL visited, and a list of links found on that page. The crawler should be limited to one subdomain - so when you start with *https://monzo.com/*, it would crawl all pages on the monzo.com website, but not follow external links, for example to facebook.com or community.monzo.com.

We would like to see your own implementation of a web crawler. Please do not use frameworks like scrapy or go-colly which handle all the crawling behind the scenes or someone else's code. You are welcome to use libraries to handle things like HTML parsing.

## Future Improvements

- store vales and check if the site has been scraped before
- improve error handling to handle more specific types of errors returned by the scraper
- respect robots.txt rules set by websites
- replace system.out with level logging 
- remove query strings from found urls
- include user-agent to get around anti-scrapping
- switch to FlowApi for queue based concurrency
- improve validator to include check for different top level domain formats
- introduce user input from other sources

## Github

Please feel free to check out the application at https://github.com/EricSutherland/WebScraper