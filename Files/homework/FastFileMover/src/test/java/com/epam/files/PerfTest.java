package com.epam.files;

import com.epam.files.algorithms.ChannelFileMoveStrategy;
import com.epam.files.algorithms.NIOFileMoveStrategy;
import com.epam.files.algorithms.StreamFileMoveStrategy;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.io.TempDir;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.file.Path;
import java.util.stream.Stream;

@Tag("Perf")
public class PerfTest {
    @TempDir
    public Path tmpDir;

    private Path oldPath;
    private Path newPath;

    private FileMover fileMover;

    void initPaths(long size) throws IOException {
        oldPath = tmpDir.resolve("path1");
        newPath = tmpDir.resolve("path2");

        try (var file = new RandomAccessFile(oldPath.toString(), "rw")) {
            file.setLength(size);
        }
    }

    void swapSrcDst() {
        Path tmp = oldPath;
        oldPath = newPath;
        newPath = tmp;
    }

    long runBenchmark() {
        long cumulativeTimeNs = 0;
        for (int i = 0; i < 1000; ++i) {
            final long startTime = System.nanoTime();
            fileMover.move(oldPath, newPath);
            cumulativeTimeNs += System.nanoTime() - startTime;
            swapSrcDst();
        }
        // /--> 1 run /--> ms.
        return cumulativeTimeNs / 1000 / 1000;
    }

    static Stream<Long> fileSizes() {
        return Stream.of(
                1024L,          // 1 KB
                102_400L,       // 100 KB
                10_485_760L,    // 10 MB
                1_073_741_824L  // 1 GB
        );
    }

    @ParameterizedTest(name = "Move file of size {0} bytes")
    @MethodSource("fileSizes")
    void testStream(long size) throws IOException {
        // Default buffer size: 256 bytes
        fileMover = new FileMover(new StreamFileMoveStrategy());
        initPaths(size);
        System.out.println("Avg. move time: " + runBenchmark() + " ms.");
    }

    @ParameterizedTest(name = "Move file of size {0} bytes")
    @MethodSource("fileSizes")
    void testStreamWithBuffer(long size) throws IOException {
        // Buffer size: 100 Kb
        fileMover = new FileMover(new StreamFileMoveStrategy(102_400));
        initPaths(size);
        System.out.println("Avg. move time: " + runBenchmark() + " ms.");
    }

    @ParameterizedTest(name = "Move file of size {0} bytes")
    @MethodSource("fileSizes")
    void testChannel(long size) throws IOException {
        fileMover = new FileMover(new ChannelFileMoveStrategy());
        initPaths(size);
        System.out.println("Avg. move time: " + runBenchmark() + " ms.");
    }

    @ParameterizedTest(name = "Move file of size {0} bytes")
    @MethodSource("fileSizes")
    void testNIO(long size) throws IOException {
        fileMover = new FileMover(new NIOFileMoveStrategy());
        initPaths(size);
        System.out.println("Avg. move time: " + runBenchmark() + " ms.");
    }
}
