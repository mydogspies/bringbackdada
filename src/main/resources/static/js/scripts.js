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

    /* this we load ofr the main index page */
    if (page === "") {
        loadIndexGallery();
    }

    /* this we load for the projects page with multiple galleries on it */
    if (page === "photo-projects") {
        var macy = new Array(galleryCount);
        for (let i = 1; i <= galleryCount; i++) {
            loadProjectGallery(macy, i);
        }
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

/* MASONRY GALLERY */

/* This is the masonry func for the home/index page */
function loadIndexGallery() {

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

/* This is the masonry func for the project page */

function loadProjectGallery(macy,id) {

    macy[id] = Macy({
        container: '#macy-container' + id,
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
            x: 10,
            y: 15
        }

    });

    window.addEventListener("resize", function(){
        macy[id].recalculate(true);
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
