/*! Common.js | (GNU v3) Peter A. Mankowski) | github.com/mydogspies */
/* This script is the ajax call for form submission from /site/model-for-bringbackdada */
$(document).ready(function () {

    let elems = document.getElementsByClassName('coll');
    for (let i = 0; i < elems.length; i++) {
        elems[i].style.display = 'none';
    }

    // init friendlycaptcha
    const element = document.querySelector("#captcha");
    const fWidget = new friendlyChallenge.WidgetInstance(element);

    // define the checkbox
    let checkbox = document.getElementById('yesiknow');

    // this is the main form event
    let form = $("#contactForm");
    getIP();


    form.submit(function (event) {
        if (fWidget.valid) {
            sendMailDataToServer();
        } else {
            alert("You must verify you are not a robot!");
            return false;
        }

    });

    function sendMailDataToServer() {
        $.ajax({
            type: "POST",
            url: form.attr("action"),
            data: form.serialize(),
            success: function (response) {
                console.log(response);
                fWidget.destroy();
            },
            error: function (jqXHR, textStatus, errorThrown) {
                // TODO send error to log
                fWidget.reset();
                console.log(textStatus);
                console.log(errorThrown);
            }
        });
    }

    function getIP() {
        const ipFormInput = document.getElementById('ip');
        fetch('https://api.ipify.org', {mode: 'cors'})
            .then((response) => response.text())
            .then(data => {
                ipFormInput.value = data;
            })
            .catch((err) => {
                // TODO send to log
                console.error(`Error getting IP Address: ${err}`)
            })
    }

    // deal with tags in the textarea
    let errorMessage = "Please match the requested format.";
    $(this).find("textarea").on("input change propertychange", function () {

        let pattern = $(this).attr("pattern");

        if (typeof pattern !== typeof undefined && pattern !== false) {
            let patternRegex = new RegExp("^" + pattern.replace(/^\^|\$$/g, '') + "$", "g");
            let hasError = !$(this).val().match(patternRegex);

            if (typeof this.setCustomValidity === "function") {
                this.setCustomValidity(hasError ? errorMessage : "");
            } else {
                $(this).toggleClass("error", !!hasError);
                $(this).toggleClass("ok", !hasError);

                if (hasError) {
                    $(this).attr("title", errorMessage);
                } else {
                    $(this).removeAttr("title");
                }
            }
        }
    });


});

