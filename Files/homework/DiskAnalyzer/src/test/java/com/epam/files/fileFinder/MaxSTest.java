package com.epam.files.fileFinder;

import com.epam.files.BaseTest;
import com.epam.files.FileFinder;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Tag("UnitTests")
public class MaxSTest extends BaseTest {
    @Test
    @DisplayName("Returns empty result if directory is empty")
    void returnsEmptyResultIfDirIsEmpty() {
        var res = FileFinder.findFileWithMaxS(tmpDir);
        assertTrue(res.isEmpty());
    }

    @Test
    @DisplayName("Returns empty result if file name does not contain 's'")
    void returnsEmptyIfFileNameDoesNotContainS() throws IOException {
        addFiles("a", "b", "c");
        var res = FileFinder.findFileWithMaxS(tmpDir);
        assertTrue(res.isEmpty());
    }

    @Test
    @DisplayName("Returns same file if input is file with s")
    void returnsSameFileIfInputIsFileWithS() throws IOException {
        addFiles("a", "b", "s");
        var res = FileFinder.findFileWithMaxS(tmpDir.resolve("s"));
        assertTrue(res.isPresent());
        assertEquals("s", res.get().getFileName().toString());
    }

    @Test
    @DisplayName("Returns empty if input is file with no 's'")
    void returnsEmptyIfInputIsFileWithNoS() throws IOException {
        addFiles("a", "b", "s");
        var res = FileFinder.findFileWithMaxS(tmpDir.resolve("b"));
        assertTrue(res.isEmpty());
    }

    static Stream<Arguments> testFilesProvider() {
        return Stream.of(
                Arguments.of(
                        new String[]{"abcsdef"},
                        "abcsdef"
                ),
                Arguments.of(
                        new String[]{"dir1/some.txt", "dir2/dir3/ssss.txt", "dir4/file.txt"},
                        "ssss.txt"
                )
        );
    }

    @ParameterizedTest
    @MethodSource("testFilesProvider")
    @DisplayName("Returns file name with max 's'")
    void returnsFileNameWithMaxS(String[] files, String expectedFilename) throws IOException {
        addFiles(files);
        var res = FileFinder.findFileWithMaxS(tmpDir);
        assertTrue(res.isPresent());
        assertEquals(expectedFilename, res.get().getFileName().toString());
    }

    @Test
    @DisplayName("Directories are not considered in search")
    void directoriesAreNotConsideredInSearch() throws IOException {
        addDirs("sss", "SSS", "abc/def/Sss");
        var res = FileFinder.findFileWithMaxS(tmpDir);
        assertTrue(res.isEmpty());
    }

    @Test
    @DisplayName("File extensions are considered in search")
    void fileExtensionsAreConsideredInSearch() throws IOException {
        addFiles("abc.s", "files.s.txt", "s12.SSS");
        var res = FileFinder.findFileWithMaxS(tmpDir);
        assertTrue(res.isPresent());
        assertEquals("s12.SSS", res.get().getFileName().toString());
    }
}
