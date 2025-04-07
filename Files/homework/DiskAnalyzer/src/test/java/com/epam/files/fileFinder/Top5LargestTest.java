package com.epam.files.fileFinder;

import com.epam.files.BaseTest;
import com.epam.files.FileFinder;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("UnitTests")
public class Top5LargestTest extends BaseTest {
    @Test
    @DisplayName("Returns empty list if directory is empty")
    void returnsEmptyListIfDirIsEmpty() {
        var res = FileFinder.findTop5LargestFiles(tmpDir);
        assertTrue(res.isEmpty());
    }

    @Test
    @DisplayName("Returns list with file if input is file")
    void returnsListWithFileIfInputIsFile() throws Exception {
        addFiles("a", "b", "c");
        var res = FileFinder.findTop5LargestFiles(tmpDir.resolve("a"));
        assertEquals(1, res.size());
        assertEquals("a", res.getFirst().getFileName().toString());
    }

    @Test
    @DisplayName("Finds top 5 largest files")
    void findsTop5LargestFiles() throws Exception {
        addFileWithLoad("a", 1);
        addFileWithLoad("b", 2);
        addFileWithLoad("c", 3);
        addFileWithLoad("d", 4);
        addFileWithLoad("e", 5);
        addFileWithLoad("f", 6);
        addFileWithLoad("g", 7);
        addFileWithLoad("h", 8);
        addFileWithLoad("i", 9);
        addFileWithLoad("j", 10);

        var res = FileFinder.findTop5LargestFiles(tmpDir);

        assertEquals(5, res.size());
        assertEquals("j", res.get(0).getFileName().toString());
        assertEquals("i", res.get(1).getFileName().toString());
        assertEquals("h", res.get(2).getFileName().toString());
        assertEquals("g", res.get(3).getFileName().toString());
        assertEquals("f", res.get(4).getFileName().toString());
    }

    @Test
    @DisplayName("Finds files in underlying directories")
    void findsFilesInUnderlyingDirectories() throws Exception {
        addFileWithLoad("abc/def/a", 1);
        addFileWithLoad("abc/b", 2);
        addFileWithLoad("qwe/rty/yui/c", 3);

        var res = FileFinder.findTop5LargestFiles(tmpDir);

        assertEquals(3, res.size());
        assertEquals("c", res.get(0).getFileName().toString());
        assertEquals("b", res.get(1).getFileName().toString());
        assertEquals("a", res.get(2).getFileName().toString());
    }
}
