# FastFileMover

Moves a file from one directory to another directory.

**Running**

```bash
$ gradle run --args="<file path> <new file path> <algorithm code>"
```

Move algorithms:
1. Using FileStreams
1. Using FileStreams with buffer 100 kb
1. Using FileChannel
1. Using NIO 2 File API
