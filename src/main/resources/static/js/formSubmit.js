/*! Common.js | (GNU v3) Peter A. Mankowski) | github.com/mydogspies */
/* This script is the ajax call for form submission from /site/contact-mydogspies */
$(document).ready(function () {

    document.getElementById("col").style.display = 'none';

    var form = $("#contactForm");
    form.submit(function (event) {
        // event.preventDefault();
        validateForm();
    });

    function validateForm() {

        let emailFieldContent = document.getElementsByTagName('input')[4].value;
        if (validateEmailFormat(emailFieldContent)) {
            sendMailDataToServer();
        } else {
            alert('Email address does not have valid format!')
            return false;
        }
    }

    function sendMailDataToServer() {
        $.ajax({
            type: "POST",
            url: form.attr("action"),
            data: form.serialize(),
            success: function (response) {
                // TODO implement
            },
            error: function(jqXHR, textStatus, errorThrown) {
                // TODO send error logs to site admin - not console!
                console.log(textStatus);
                console.log(errorThrown);
            }
        });
    }

    function validateEmailFormat(emailAddress) {
        const re = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
        return re.test(String(emailAddress).toLowerCase());
    }

});

