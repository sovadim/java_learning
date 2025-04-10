# FastFileMover

Moves a file from one directory to another directory.

## Running

```bash
$ gradle run --args="<file path> <new file path> <algorithm code>"
```

Move algorithms:
1. Using FileStreams
1. Using FileStreams with buffer 100 kb
1. Using FileChannel
1. Using NIO 2 File API

## Performance Report

Average time for moving files of different sizes across 1000 times with each algorithm:

|        |       Stream | Stream 100Kb buf |  FileChannel | NIO 2 File API |
| :----- | -----------: | ---------------: | -----------: | -------------: |
| 1 Kb   |      180 ms. |          161 ms. |      160 ms. |         73 ms. |
| 100 Kb |     1494 ms. |          194 ms. |      264 ms. |         71 ms. |
| 10 Mb  |   106275 ms. |         5299 ms. |     2992 ms. |         73 ms. |
| 1 Gb   | 12120237 ms. |       579135 ms. |  2424696 ms. |         78 ms. |
