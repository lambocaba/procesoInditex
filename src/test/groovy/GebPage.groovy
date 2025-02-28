import geb.Page

class GooglePage extends Page {

    static url = "https://www.google.com/"

        static at = {
        }

        static content = {
            heading { $("h1") }
            tableHeader { $("thead") }
            products { $("tbody > tr") }
            ManualButton { $("a.manuals.item") }

        }
    }

