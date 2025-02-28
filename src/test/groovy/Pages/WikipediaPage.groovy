package Pages

import geb.Page

class WikipediaPage extends Page {

    static url = "https://www.wikipedia.org/"

    static at = { driver.getCurrentUrl().contains("wikipedia.org") }

    static content = {
        textoWikipedia {$("body")}
    }
}