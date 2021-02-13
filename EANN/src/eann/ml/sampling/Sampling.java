package eann.ml.sampling;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class Sampling {

    private Sampling() {
    }

    public static void write(List<Sample> samples, String path) throws IOException {
        write(samples, Sample::toString, path);
    }

    public static <T> void write(List<T> samples, Function<T, String> formatter, String path) throws IOException {
        try (var writer = Files.newBufferedWriter(Path.of(path))) {
            for (T sample : samples) {
                writer.write(formatter.apply(sample));
                writer.newLine();
            }
        }
    }

    public static List<Sample> load(String path) throws IOException {
        return load(path, Sample::parse);
    }

    public static <T> List<T> load(String path, Function<String, T> parser) throws IOException {
        return Files.lines(Path.of(path)).map(parser).collect(Collectors.toUnmodifiableList());
    }
}
