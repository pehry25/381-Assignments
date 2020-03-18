CMPT 381 - Assignment 3
Peter Ehry | pfe930 | 11153336

===============================================
================= READ ME FILE ================
===============================================

TESTING
===============================================
This application was tested with the Pixel 2 Emulator, running API 26 (Oreo). It's is VERY important that this application is tested with the Sample Chart provided to us in the assignment spec as some values had to be hard coded to get proper coordinates (as I understoon, this was okay as per P. Gutwin's forum posts).

Known Bugs / Unfinished Features / Assumptions
===============================================
1. Proper Scaling: The detail view does not properly scale when an existing Chart Point has been selected. The view scales in, however, the view is not centered on the selected point.

2. Axis View XY Values: The XY values in the Axis View do not get set properly when the Bounds Rectangle is created. I chose to leave this feature to the end to focus on other aspect, and ran out of time.

3. The Export feature exports the list of Chart Points in the following format (assumption):
X,Y
xCord1,yCord1
xCord2,yCord2
......

4. Fat Finger Effect (assumption): I gave the bound rectangle handles, as well as the Chart Points a decent MOE (margin of error) to reduce the fat finger effect. This is mostly evident on the Chart Points as the circles are much larger compared to the handles on the Bound Rectangle.
