
/* FUNCTIONS TO BE CALLED ON EVERY LOAD */
function onLoadFuncs() {
    styleCurrentPageLink();
    loadMasonry();
}

/* Swap link styling depending on page loaded */
function styleCurrentPageLink() {
    var path = window.location.pathname;
    var page = path.split("/").pop();

    if (page === "") {
        page = "home"

        /* for the special case 'home' link in the footer */
        const footerElement = document.getElementById('afooter')
        console.log(footerElement)
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
        columns: 1,
        mobileFirst: true,
        breakAt: {
            1200: 5,
            940: 4,
            520: 3,
            450: 2,
        },
        margin: 4
    });
}
