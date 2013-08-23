#!/bin/bash

# prereq: 
# apt-get install libxslt1.1 # libxslt for 64 bits 
# cp jfx-mb-rt.jar /usr/lib/jvm/java-7-oracle/jre/lib/


export LD_LIBRARY_PATH=/usr/lib/x86_64-linux-gnu/
xvfb-run -s "-screen 0 1280x1024x24" java -cp lib/js.jar:/usr/lib/jvm/java-7-oracle/jre/lib/jfx-mb-rt.jar:build/classes kic.engine.plot.svg.render.HeadlessWebEngine
