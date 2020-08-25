
/* FUNCTIONS TO BE CALLED ON EVERY LOAD */
function onLoadFuncs() {
    styleCurrentPageLink();
}


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
