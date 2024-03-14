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
| ^ | upward-spike|
|v| downward-spike|
|>|rightward-spike|
|<|leftward-spike|

