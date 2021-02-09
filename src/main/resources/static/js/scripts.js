window.onload = function() {
    let loadFunc = onLoadFuncs();
}

/* FUNCTIONS TO BE CALLED ON EVERY LOAD */
function onLoadFuncs() {

    let path = window.location.pathname;
    let page = path.split("/").pop();
    styleCurrentPageLink(page);

    /* this we load ofr the main index page */
    if (page === "") {
        loadIndexGallery();
    }

    /* this we load for the projects page with multiple galleries on it */
    if (page === "photo-projects") {
        let macy = new Array(galleryCount);
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
    if (element) {
        element.classList.add('onCurrentPage');
    }

}

/* MASONRY GALLERY */

/* This is the masonry func for the home/index page */
function loadIndexGallery() {

    let macy = Macy({
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
            y: 12
        }

    });

    window.addEventListener("resize", function(){
        macy[id].recalculate(true);
    }, true);
}
