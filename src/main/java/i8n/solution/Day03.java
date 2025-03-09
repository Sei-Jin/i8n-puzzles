package i8n.solution;

import i8n.util.InputHandler;

import java.util.function.IntPredicate;
import java.util.function.Predicate;

public class Day03 {
    
    private static final String FILENAME = "input/day03.txt";
    
    private static final int MIN_LENGTH = 4;
    private static final int MAX_LENGTH = 12;
    
    private static final Predicate<String> IS_CORRECT_LENGTH = (s ->
        s.length() >= MIN_LENGTH && s.length() <= MAX_LENGTH
    );
    private static final IntPredicate IS_NOT_7_BIT_ASCII = (c -> c >= 128);
    
    public static void main(String[] args) {
        final var passwords = InputHandler.readAsLines(FILENAME);
        
        final var valid = passwords
            .stream()
            .filter(IS_CORRECT_LENGTH)
            .filter(p -> p.chars().anyMatch(Character::isDigit))
            .filter(p -> p.chars().anyMatch(Character::isUpperCase))
            .filter(p -> p.chars().anyMatch(Character::isLowerCase))
            .filter(p -> p.chars().anyMatch(IS_NOT_7_BIT_ASCII))
            .count();
        
        System.out.println(valid);
    }
}
