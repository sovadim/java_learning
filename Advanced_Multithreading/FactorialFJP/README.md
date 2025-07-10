# Factorial JFP

**How to run**

```bash
$ gradle run --args="<number> <number of threads>"
```

**Running time comparison**

Elapsed time

| number / threads  | 1         | 2     | 4     | 8     | 16    | 32    |
| ----------------- | ----------| ----- | ----- | ----- | ----- | ----- |
| 100               | 0.91s     | 0.98s | 0.92s | 0.90s | 0.91s | 0.92s |
| 1000              | 0.92s     | 0.91s | 0.93s | 0.92s | 0.93s | 0.91s |
| 10000             | 1.00s     | 0.97s | 0.95s | 0.97s | 0.95s | 0.98s |
| 100000            | 4.42s     | 1.32s | 1.35s | 1.36s | 1.32s | 1.33s |
| 1000000           | 385.73s   | 8.47s | 8.32s | 8.15s | 8.34s | 8.64s |
