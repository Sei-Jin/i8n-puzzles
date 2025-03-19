package i18n.year2025;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Day07Test {
    
    private static final Day07 SOLUTION = new Day07();
    
    @Test
    void solve() {
        final var input = """
            2012-11-05T09:39:00.000-04:00	969	3358
            2012-05-27T17:38:00.000-04:00	2771	246
            2001-01-15T22:27:00.000-03:00	2186	2222
            2017-05-15T07:23:00.000-04:00	2206	4169
            2005-09-02T06:15:00.000-04:00	1764	794
            2008-03-23T05:02:00.000-03:00	1139	491
            2016-03-11T00:31:00.000-04:00	4175	763
            2015-08-14T12:40:00.000-03:00	3697	568
            2013-11-03T07:56:00.000-04:00	402	3366
            2010-04-16T09:32:00.000-04:00	3344	2605
            """;
        assertEquals(866, SOLUTION.solve(input));
    }
}