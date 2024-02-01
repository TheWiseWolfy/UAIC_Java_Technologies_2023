
var selectedNodes = [];  // Nodes to be submmited as part of the final path
function drawGraph(edges, path) {


    // Change edge.source and edge.target to point to the actual node objects and not their names
    var nodeIndex = {};
    edges.forEach(function(edge) {
        edge.source = nodeIndex[edge.node1] = nodeIndex[edge.node1] || {name: edge.node1};
        edge.target = nodeIndex[edge.node2] = nodeIndex[edge.node2] || {name: edge.node2};
    });

    // Set the dimensions
    var width = 800;
    var height = 800;

    // Create an SVG element inside provided div to contain the graph
    var svg = d3.select("#graph").append("svg").attr("width", width).attr("height", height);

    // Create force layout
    var simulation = d3.forceSimulation(Object.values(nodeIndex))
        .force("charge", d3.forceManyBody().strength(-200))
        .force("center", d3.forceCenter(width / 2, height / 2))
        .force("link", d3.forceLink(edges).id(function(d) { return d.name; }))
        .on("tick", ticked);

    // Add lines for every edge in the graph
    var link = svg.append("g")
        .attr("class", "links")
        .selectAll("line")
        .data(edges)
        .enter().append("line")
        .style("stroke", "black"); // Add a black stroke color to the lines (edges).

    // Add circles and text for every node in the graph
    var node = svg.append("g")
        .attr("class", "nodes")
        .selectAll("g")
        .data(Object.values(nodeIndex))
        .enter().append("g");


    var circles = node.append("circle")
        .attr("r", 5)
        .style("fill", function(d) {  // Adding the fill style
            if (d.name == path.startNode) {
                return "green";
            } else if (d.name == path.endNode) {
                return "red";
            } else {
                return "black";
            }
        });

    function nodeClickEvent() {
        // Get the circle and the data bound to the click
        var clickedCircle = d3.select(this);
        var clickedData = clickedCircle.datum();

        // If the clicked node is the start or end node, do nothing and return
        if (clickedData.name == path.startNode || clickedData.name == path.endNode) {
            return;
        }

        var index = selectedNodes.indexOf(clickedData.name);

        if (index === -1) { // The name is not in the list
            clickedCircle.style("fill", "blue");
            selectedNodes.push(clickedData.name);
        } else {
            selectedNodes.splice(index, 1);
            clickedCircle.style("fill", "black");
        }

        link.style("stroke", function (d) {

            var nodesToHighlight = selectedNodes.concat([path.startNode, path.endNode]);
            // we re checking if both the source node and the target node of an edge are in the selectedNodes list
            if (nodesToHighlight.indexOf(d.source.name) > -1 && nodesToHighlight.indexOf(d.target.name) > -1) {
                return "blue";
            } else {
                return "black";
            }
        });

        console.log(selectedNodes);
    }

// Attach the click event to the circles
    circles.on("click", nodeClickEvent);

    node.append("text")
        .attr("x", 12)
        .attr("dy", ".35em")
        .text(function(d) { return d.name; });

    function ticked() {
        link
            .attr("x1", function(d) { return d.source.x; })
            .attr("y1", function(d) { return d.source.y; })
            .attr("x2", function(d) { return d.target.x; })
            .attr("y2", function(d) { return d.target.y; });

        node
            .attr("transform", function(d) {
                return "translate(" + d.x + "," + d.y + ")";
            })
    }

}

document.addEventListener('DOMContentLoaded', function() {
    document.getElementById('submit-button').addEventListener('click', function() {
        var xhr = new XMLHttpRequest();
        xhr.open('POST', '/lab7-1.0-SNAPSHOT/graph-endpoint', true);
        xhr.setRequestHeader('Content-Type', 'application/json');
        xhr.send(JSON.stringify(selectedNodes));

        xhr.onload = function() {
            var status = xhr.status;

            if (status == 200) {
                console.log("Submit successful.");
                console.log("Response: " + xhr.responseText); //
            } else {
                console.log("Submit failed.");
            }
        };
    });
});