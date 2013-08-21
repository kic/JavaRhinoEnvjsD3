var s = d3.scale.linear().domain([100,1000]).range([0,100]);
var a = d3.svg.axis().scale(s).orient("left");

print("scale test: " + s(150));

print("go for axis test");
d3.select("body").append("svg").append("g")
        .attr("class", "y axis")
        .attr("transform", "translate(" + 20 + ", " + 0 + ")")
        .call(a)
          .selectAll("text")
          .attr("font-size", ".70em");