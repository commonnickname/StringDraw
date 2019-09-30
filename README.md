# StringDraw
Original idea by Petros Vrellis.
Program that approximates an image by drawing a number of lines that start and end on the oval border + GUI.
Work in progress.

To test out, download the .java executable or copile the code. In the app select File -> Open File and select an image of your choice. Wait untile the image is loaded and click 'Run'. Experiment with parameters. 
You can also select Settings -> Select Mode -> Optimization Mode to generate two pictures independently in order to compare how different parameters affect the result.

There are several algorithms that you can select from, 'naive', 'delta' and 'delta log' - they perform differently well depending on the details of a particular image. 

There are two modes - local and global. In the local mode the algorithms try to select the best line starting from the previous selection line - thus they search only a subset of all possible line on each step. In the global mode the algorithms try to select the best line out of all possible lines and join the segments at the end such that the pattern could be produced with a single continuous thread. The global mode offers better accuracy for small details, but is coputationally more expensive and thus slover. 

Demo videos:
Bulldog Puppy: https://www.youtube.com/watch?v=9QEH-P27gXw
Mona Lisa: https://www.youtube.com/watch?v=3cXhJ34x8gA
Text 1: https://www.youtube.com/watch?v=kDUOt7F8zn8
Text 2: https://www.youtube.com/watch?v=HcDbAHp6Ydk&t=2s
