/* Generated from Java with JSweet 2.3.0-SNAPSHOT - http://www.jsweet.org */
var toTranspile;
(function (toTranspile) {
    /**
     * Costruttore di Signup. Inizializza gli elementi e i loro attributi e aggiunge gli event
     * listeners
     * @class
     */
    class Signup {
        constructor() {
            if (this.__main === undefined)
                this.__main = null;
            if (this.form === undefined)
                this.form = null;
            if (this.emailLabel === undefined)
                this.emailLabel = null;
            if (this.emailField === undefined)
                this.emailField = null;
            if (this.passLabel === undefined)
                this.passLabel = null;
            if (this.passField === undefined)
                this.passField = null;
            if (this.confirmPassLabel === undefined)
                this.confirmPassLabel = null;
            if (this.confirmPassField === undefined)
                this.confirmPassField = null;
            if (this.firstLanguageLabel === undefined)
                this.firstLanguageLabel = null;
            if (this.ITBox === undefined)
                this.ITBox = null;
            if (this.ITBoxLabel === undefined)
                this.ITBoxLabel = null;
            if (this.ENBox === undefined)
                this.ENBox = null;
            if (this.ENBoxLabel === undefined)
                this.ENBoxLabel = null;
            if (this.firstLangsContainer === undefined)
                this.firstLangsContainer = null;
            if (this.secondLanguagesLabel === undefined)
                this.secondLanguagesLabel = null;
            if (this.secondLangsContainer === undefined)
                this.secondLangsContainer = null;
            if (this.signupButton === undefined)
                this.signupButton = null;
            if (this.loginLink === undefined)
                this.loginLink = null;
            if (this.errMsgContainer === undefined)
                this.errMsgContainer = null;
            this.__main = toTranspile.Utilities.createMain();
            this.form = toTranspile.Utilities.createPOSTForm("signup.jsp");
            this.form.addEventListener("submit", (event) => {
                if (!((o1, o2) => { if (o1 && o1.equals) {
                    return o1.equals(o2);
                }
                else {
                    return o1 === o2;
                } })(this.passField.value, this.confirmPassField.value)) {
                    this.errMsgContainer.textContent = "Passwords don\'t match!";
                    event.preventDefault();
                }
                if ($(".first-langs-container input:checked").length === 0) {
                    this.errMsgContainer.textContent = "At least one checkbox must be checked!";
                    event.preventDefault();
                }
            });
            this.emailLabel = toTranspile.Utilities.createLabel("Email*");
            this.emailField = toTranspile.Utilities.createEmailField();
            this.passLabel = toTranspile.Utilities.createLabel("Password*");
            this.passField = toTranspile.Utilities.createPassField("pwd");
            this.passField.pattern = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$";
            this.passField.title = "Minimum eight characters, at least one letter and one number";
            this.confirmPassLabel = toTranspile.Utilities.createLabel("Confirm password*");
            this.confirmPassField = toTranspile.Utilities.createPassField("confirmPwd");
            this.firstLanguageLabel = toTranspile.Utilities.createLabel("First language*");
            this.ITBox = toTranspile.Utilities.createCheckBox("itCheckbox", "on");
            this.ITBoxLabel = toTranspile.Utilities.createLabel("IT");
            this.ENBox = toTranspile.Utilities.createCheckBox("enCheckbox", "on");
            this.ENBoxLabel = toTranspile.Utilities.createLabel("EN");
            this.firstLangsContainer = document.createElement("div");
            this.firstLangsContainer.className = "first-langs-container";
            this.secondLanguagesLabel = toTranspile.Utilities.createLabel("Second languages");
            this.secondLanguagesLabel.className = "second-langs-label";
            this.secondLangsContainer = document.createElement("div");
            this.secondLangsContainer.className = "second-langs-container";
            for (let i = 0; i < 2; ++i) {
                {
                    let secondLangs = toTranspile.Utilities.createSelect("secondLang");
                    let chooseLangOption = toTranspile.Utilities.createOption("Language", "");
                    chooseLangOption.selected = true;
                    let itA1 = toTranspile.Utilities.createOption("IT-A1", "it-a1");
                    itA1.className = "IT";
                    let itA2 = toTranspile.Utilities.createOption("IT-A1", "it-a2");
                    itA2.className = "IT";
                    let itB1 = toTranspile.Utilities.createOption("IT-B1", "it-b1");
                    itB1.className = "IT";
                    let itB2 = toTranspile.Utilities.createOption("IT-B2", "it-b2");
                    itB2.className = "IT";
                    let itC1 = toTranspile.Utilities.createOption("IT-C1", "it-c1");
                    itC1.className = "IT";
                    let itC2 = toTranspile.Utilities.createOption("IT-C2", "it-c2");
                    itC2.className = "IT";
                    let enA1 = toTranspile.Utilities.createOption("EN-A1", "en-a1");
                    enA1.className = "EN";
                    let enA2 = toTranspile.Utilities.createOption("EN-A2", "en-a2");
                    enA2.className = "EN";
                    let enB1 = toTranspile.Utilities.createOption("EN-B1", "en-b1");
                    enB1.className = "EN";
                    let enB2 = toTranspile.Utilities.createOption("EN-B2", "en-b2");
                    enB2.className = "EN";
                    let enC1 = toTranspile.Utilities.createOption("EN-C1", "ec-c1");
                    enC1.className = "EN";
                    let enC2 = toTranspile.Utilities.createOption("EN-C2", "en-c2");
                    enC2.className = "EN";
                    $(this.secondLangsContainer).append($(secondLangs).append(chooseLangOption, itA1, itA2, itB1, itB2, itC1, itC2, enA1, enA2, enB1, enB2, enC1, enC2));
                    secondLangs.addEventListener("change", ((secondLangs) => {
                        return (event) => {
                            let optSelected = $(secondLangs).children("option:selected");
                            let optSelectedClass = $(optSelected).attr("class");
                            let selects = document.querySelectorAll("[name|=\'secondLang\']");
                            for (let index122 = 0; index122 < selects.length; index122++) {
                                let select = selects[index122];
                                {
                                    if (!((o1, o2) => { if (o1 && o1.equals) {
                                        return o1.equals(o2);
                                    }
                                    else {
                                        return o1 === o2;
                                    } })(select, secondLangs)) {
                                        $(select).children().removeAttr("disabled");
                                        $(select).children("." + optSelectedClass).attr("disabled", "true");
                                    }
                                }
                            }
                        };
                    })(secondLangs));
                }
                ;
            }
            this.signupButton = toTranspile.Utilities.createButton("submit", "Sign up");
            this.signupButton.className = "btn btn-primary signup-btn";
            this.loginLink = document.createElement("a");
            this.loginLink.className = "login-link";
            this.loginLink.href = "login.html";
            this.loginLink.textContent = "Log in instead";
            this.errMsgContainer = document.createElement("div");
            this.errMsgContainer.className = "err-msg-container";
        }
        static main(args) {
            new Signup().buildPage();
        }
        /**
         *
         * @return {*}
         */
        buildPage() {
            $("body").append($(this.__main).append($(this.form).append(this.emailLabel, this.emailField, this.passLabel, this.passField, this.confirmPassLabel, this.confirmPassField, this.firstLanguageLabel, $(this.firstLangsContainer).append(this.ITBox, this.ITBoxLabel, this.ENBox, this.ENBoxLabel), this.secondLanguagesLabel, this.secondLangsContainer, this.signupButton, this.loginLink, this.errMsgContainer)));
            return $("body");
        }
    }
    toTranspile.Signup = Signup;
    Signup["__class"] = "toTranspile.Signup";
    Signup["__interfaces"] = ["toTranspile.BuildablePage"];
})(toTranspile || (toTranspile = {}));
toTranspile.Signup.main(null);
