// from https://github.com/jgraph/drawio/discussions/3430
function createMxGraphData(xml, idx = new Date().getTime()) {
    return {
        editable: false,
        highlight: "#0000ff",
        nav: false,
        toolbar: null,
        edit: null,
        resize: true,
        lightbox: "open",
        xml,
    };
}

window.addEventListener("load", function(){
    div = document.querySelector(".mxgraph") 
    xml = div.getAttribute("data-diagram-data")
    mxGraphData = createMxGraphData(xml); 
    json = JSON.stringify(mxGraphData) 
    div.setAttribute('data-mxgraph', json)
    window.GraphViewer.processElements();
})
