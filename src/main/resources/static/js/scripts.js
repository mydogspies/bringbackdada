window.onload = function() {
    console.log("scripts loaded")
    var loadFunc = onLoadFuncs();
}

/* FUNCTIONS TO BE CALLED ON EVERY LOAD */
function onLoadFuncs() {

    var path = window.location.pathname;
    var page = path.split("/").pop();
    styleCurrentPageLink(page);

    console.log(page);

    /* must define what pages we load the masonry scripts... */
    if (page === "" || page === "photo-projects") {
        loadMasonry();
    }
}

/* Swap link styling depending on page loaded */
function styleCurrentPageLink(page) {

    if (page === "") {
        page = "home"

        /* for the special case 'home' link in the footer */
        const footerElement = document.getElementById('afooter')
        footerElement.classList.add('onCurrentPage')
    }

    /* and all the cases in the header */
    const element = document.getElementById(page);
    element.classList.add('onCurrentPage');
}

function loadMasonry() {

    var macy = Macy({
        container: '#macy-container',
        trueOrder: true,
        waitForImages: false,
        columns: 5,
        mobileFirst: true,
        breakAt: {
            1890: 5,
            1490: 4,
            1140: 3,
            600: 2,
            300: 1
        },
        margin: {
            x: 5,
            y: 5
        }

    });

    window.addEventListener("resize", function(){
        macy.recalculate(true);
        console.log("resize");
    }, true);
}

/* FORM SCRIPTS */

function submitAddContentForm() {
    $.ajax(
        {
            type: "POST",
            data: $("#content_input_form").serialize(),
            cache: false,
            url: "/admin/save-new-content",
            success: function(data)
            {
                alert("Data saved");
            },
            error: function()
            {
                alert("Error - Data not saved");
            }

        });
}
