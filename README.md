# SimpleSPH
Simple educational fluid solver using Smoothed Particle Hydrodynamics method

###### https://www.youtube.com/watch?v=XIBEkGc8rSs
[![SimlpeSPH](http://img.youtube.com/vi/XIBEkGc8rSs/0.jpg)](http://www.youtube.com/watch?v=XIBEkGc8rSs)


You can start learning with this project in case other simulators seem to be too complex to grasp.
When you are done undestanding this, the more advanced material is
http://www.ligum.umontreal.ca/Clavet-2005-PVFS/pvfs.pdf

Made intentionally simple:
* No optimizations are implemented.
    No nearest neighbour search, no fancy root square operations hacks.
* Uses single CPU core.
* No surface tension, no predictions-relaxations, no gravity, simple viscosity, no complex collisions, no stickiness
* Uses <i>Processing</i> core library for graphics.

The only material used is Kelager.06
http://image.diku.dk/projects/media/kelager.06.pdf

Particle color represents relative density.

Smaller kernel <i>h</i> or larger space make simulation unstable.

Simple rule is implemented to make fluid swirl a little.

#### Other more advanced or nice simulators with source codes:
* Tetsuya Matsuno https://www.youtube.com/watch?v=4NU37rPOAsk http://www.openprocessing.org/sketch/160764
* GrantKot https://www.youtube.com/watch?v=iSEuwTnIcjQ&list=PL50F95812ECA1DFF6
* Rene Schulte - Flui°D°emo https://www.youtube.com/watch?v=0bL80G1HX9w
* Eli Davies - Copy and paste code into Processing application http://www.openprocessing.org/sketch/99799
