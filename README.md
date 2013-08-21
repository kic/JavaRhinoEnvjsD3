JavaRhinoEnvjsD3
================

Render SVG in a Headless Java Environment with Rhino + Envjs + D3.

I ever wanted to know if it is possible to do so. And yes, here is the proof. You can render SVG in a complete headless java environment.

The main task is to get your rhino and evjs setup, see my post on stackoverflow: http://stackoverflow.com/a/18016155/1298461

All the next steps are pretty strait forward, simply compile the singe java fille and check it out.

Sadly JavaFX webengine is not running on a complete headless server, hopefully it is with JavaFX 8. So I hope this may help someone in the mean time!

I got almost everything to work, but note there are still some problems - i.e. https://github.com/mbostock/d3/issues/1471

Cheers 
KIC



