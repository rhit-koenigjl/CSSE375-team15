ISSUE: currently the levels are very nonstandard and hardcoded to different characters. This makes it intuitive for beginners to create new levels, and the filetypes and storage of the levels are not functional or maintainable.

We aim to product a new format for the storage of levels that is contained in a .json file.
This will allow for easier transfer and manipulation of data in the long run, and also making it easier to interface with third party applications (like the python level builder)

The .json object should contain the following:

- level name
- a info stream
- a level size
- a map of data->block type

The info stream is the complex object within the system.
We will maintain a standard set of character keys that represent each block:

|  key  |          block           |
| :---: | :----------------------: |
|   e   |       empty space        |
|   b   |          brick           |
|   m   |       mossy-brick        |
|   c   |           coin           |
|   P   | player starting location |
|   ^   |       upward-spike       |
|   v   |      downward-spike      |
|   >   |     rightward-spike      |
|   <   |      leftward-spike      |
|   @   |      tracking enemy      |
|   &   |      generic enemy       |
Others will be added in the future

the info stream will be a string representing a list of pairs in the following format

|a1-b1|a2-b2|a3-b3|

the a's represent block types and the b's represent quantity in a row (including wrapping)

This will still be human readable, which is not ideal, but far less so than the original draft.

due to generic enemy (and possible future features) need to have a starting direction/velocity, additional information will need to be added to the data stream. This will be implemented with the '#' character. Hashtag character will be implemented with the following format.

| #Data |  meaning   |
| :---: | :--------: |
|  #U   |     up     |
|  #UR  |  up-right  |
|  #R   |   right    |
|  #DR  | down-right |
|  #D   |    down    |
|  #DL  | down-left  |
|  #L   |    left    |
|  #UL  |  up-left   |

To give an example, a generic enemy who's starting direction is down left will have a data segment of |&#DL-1|