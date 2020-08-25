function test() {
    console.log('THIS IS MY JS FILE!');
}

/* FUNCTIONS TO BE CALLED ON EVERY LOAD */
function onLoadFuncs() {
    styleCurrentPageLink();
}


function styleCurrentPageLink() {
    var path = window.location.pathname;
    var page = path.split("/").pop();

    if (page === "") {
        page = "home"
        const footerElement = document.getElementById('afooter')
        console.log(footerElement)
        footerElement.classList.add('onCurrentPage')
    }

    const element = document.getElementById(page);
    element.classList.add('onCurrentPage');

}
