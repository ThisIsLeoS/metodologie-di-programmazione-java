/* Generated from Java with JSweet 2.3.0-SNAPSHOT - http://www.jsweet.org */
var toTranspile;
(function (toTranspile) {
    /**
     * Costruttore di BasicAnnotValidat.
     * <ul>
     * <li>Inizializza gli elementi e i loro attributi.</li>
     * <li>Appende il link alla home page e il link alla pagina di logout all'elemento header.</li>
     * <li>Appende il pulsante skip e next al loro contenitore.</li>
     * </ul>
     * @class
     */
    class BasicAnnotValidat {
        constructor() {
            if (this.homeLink === undefined)
                this.homeLink = null;
            if (this.logoutLink === undefined)
                this.logoutLink = null;
            if (this.header === undefined)
                this.header = null;
            if (this.mainEl === undefined)
                this.mainEl = null;
            if (this.form === undefined)
                this.form = null;
            if (this.skipBtn === undefined)
                this.skipBtn = null;
            if (this.nextBtn === undefined)
                this.nextBtn = null;
            if (this.skipAndNextBtnsContainer === undefined)
                this.skipAndNextBtnsContainer = null;
            this.homeLink = document.createElement("a");
            this.homeLink.href = "home.html";
            this.homeLink.textContent = "Home page";
            this.logoutLink = document.createElement("a");
            this.logoutLink.href = "logout.jsp";
            this.logoutLink.textContent = "Log out";
            this.header = document.createElement("div");
            this.header.id = "header";
            $(this.header).append(this.homeLink, this.logoutLink);
            this.mainEl = toTranspile.Utilities.createMain();
            this.skipBtn = toTranspile.Utilities.createButton("button", "Skip");
            this.skipBtn.name = "skipBtn";
            this.skipBtn.value = "clicked";
            this.skipBtn.className = "btn btn-primary";
            this.nextBtn = toTranspile.Utilities.createButton("submit", "Next");
            this.nextBtn.name = "nextBtn";
            this.nextBtn.value = "clicked";
            this.nextBtn.className = "btn btn-primary";
            this.skipAndNextBtnsContainer = document.createElement("div");
            this.skipAndNextBtnsContainer.className = "skip-and-next-btns-container";
            $(this.skipAndNextBtnsContainer).append(this.skipBtn, this.nextBtn);
        }
    }
    toTranspile.BasicAnnotValidat = BasicAnnotValidat;
    BasicAnnotValidat["__class"] = "toTranspile.BasicAnnotValidat";
})(toTranspile || (toTranspile = {}));
