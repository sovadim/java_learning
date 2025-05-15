package com.epam.files.fileFinder;

import com.epam.files.BaseTest;
import com.epam.files.FileFinder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Tag("UnitTests")
public class AverageFileSizeTest extends BaseTest {
    @Test
    @DisplayName("Returns 0.0 if directory is empty")
    void returnsZeroIfDirIsEmpty() {
        var res = FileFinder.averageFileSize(tmpDir);
        assertEquals(0.0, res);
    }

    @Test
    @DisplayName("Returns size of file if input is file")
    void returnsSizeOfFileIfInputIsFile() throws Exception {
        addFileWithLoad("f", 10);
        var res = FileFinder.averageFileSize(tmpDir.resolve("f"));
        assertEquals(10.0, res);
    }

    @Test
    @DisplayName("Recursively calculates average file size")
    void recursivelyCalculatesAverageFileSize() throws Exception {
        addFileWithLoad("a", 1);
        addFileWithLoad("dir/b", 2);
        addFileWithLoad("d2/d3", 3);
        addFileWithLoad("d4/d5/d6/d7/d8", 4);
        var res = FileFinder.averageFileSize(tmpDir);
        assertEquals(2.5, res);
    }
}
