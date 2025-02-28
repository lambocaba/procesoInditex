package Pages

import geb.Page

class GooglePage extends Page {

    static url = "https://www.google.com/"

    static at = {  }

    static content = {
        heading { $("h1") }
        acceptButton {$("button", 3)}
        searchBarInput {$("textarea[name=q]")}
        searchButton {$("input[value='Buscar con Google']",1)}
        wikiLink {$("a[href*='wikipedia.org']",1)}
        tableHeader { $("thead") }
        products { $("tbody > tr") }
        manualButton { $("a.manuals.item") }  // La convención es usar camelCase
    }
}