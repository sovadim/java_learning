package com.epam.files.fileFinder;

import com.epam.files.BaseTest;
import com.epam.files.FileFinder;
import com.epam.files.Pair;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("UnitTests")
public class CountPathsWithATest extends BaseTest {
    @Test
    @DisplayName("Returns {0, 0} if directory is empty")
    void returnsZerosIfDirIsEmpty() {
        var res = FileFinder.countFilesAndDirsStartingWithA(tmpDir);
        assertEquals(new Pair<>(0, 0), res);
    }

    @Test
    @DisplayName("Counts files and dirs starting with 'a'")
    void countsFilesAndDirsStartingWithA() throws Exception {
        addFiles(
                "a",
                "AAAAA",
                "ba",
                "ab",
                "d1/d2/d3/d4/d5/abc/Aaa/a.txt",
                "tmp/tmp/d/aaaa"
        );
        var res = FileFinder.countFilesAndDirsStartingWithA(tmpDir);
        assertEquals(new Pair<>(5, 2), res);
    }
}
