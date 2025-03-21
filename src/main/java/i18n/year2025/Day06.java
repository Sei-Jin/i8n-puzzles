package i18n.year2025;

import i18n.Solver;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.IntStream;

public class Day06 implements Solver<Integer> {
    
    @Override
    public Integer solve(String input) {
        final var parsed = parse(input);
        
        // Range is 1-based instead of 0-based for modulo calculations
        final var correctedWords = IntStream
            .range(1, parsed.words().size() + 1)
            .mapToObj(i -> {
                final var word = parsed.words().get(i - 1);
                
                if (i % 15 == 0) {
                    return correctEncoding(correctEncoding(word));
                } else if (i % 5 == 0 || i % 3 == 0) {
                    return correctEncoding(word);
                } else {
                    return word;
                }
            })
            .toList();
        
        final var crosswordLines = parsed.crossword()
            .stream()
            .map(String::stripLeading)
            .map(word ->
                IntStream
                    .range(0, word.length())
                    .filter(i -> word.charAt(i) != '.')
                    .mapToObj(i -> new CrosswordLine(word.charAt(i), i, word.length()))
                    .findFirst()
                    .orElseThrow()
            )
            .toList();

        return crosswordLines
            .stream()
            .mapToInt(line ->
                IntStream
                    .range(0, correctedWords.size())
                    .filter(i -> correctedWords.get(i).length() == line.length)
                    .filter(i ->
                        Character.toUpperCase(correctedWords.get(i).charAt(line.index)) ==
                            Character.toUpperCase(line.character)
                    )
                    .map(i -> i + 1)
                    .findFirst()
                    .orElseThrow()
            )
            .sum();
    }
    
    private static Input parse(String input) {
        final var lines = input.lines().toList();
        
        final var emptyLineIndex = IntStream
            .range(0, lines.size())
            .filter(i -> lines.get(i).isEmpty())
            .findFirst()
            .orElseThrow();
        
        final var words = lines.subList(0, emptyLineIndex);
        final var crossword = lines.subList(emptyLineIndex + 1, lines.size());
        
        return new Input(words, crossword);
    }
    
    private static String correctEncoding(String string) {
        final var bytes = string.getBytes(StandardCharsets.ISO_8859_1);
        return new String(bytes, StandardCharsets.UTF_8);
    }
    
    private record Input(List<String> words, List<String> crossword) {}
    private record CrosswordLine(char character, int index, int length) {}
}
