import spock.lang.FailsWith
import static RomanNumeralsConverter.decimalToRoman
import static RomanNumeralsConverter.romanToDecimal

class RomanNumeralsConverterTests extends spock.lang.Specification {

    def "convert vanilla roman numerals"() {
        expect:
        decimalToRoman(decimal) == roman
        romanToDecimal(roman) == decimal

        where:
        decimal  | roman
        1        | 'I'
        5        | 'V'
        10       | 'X'
        50       | 'L'
        100      | 'C'
        1000     | 'M'
    }

    def "convert roman numerals"() {
        expect:
        decimalToRoman(decimal) == roman
        romanToDecimal(roman) == decimal

        where:
        decimal  | roman
        2        | 'II'
        3        | 'III'
        16       | 'XVI'
        85       | 'LXXXV'
        745      | 'DCCXLV'
        2342     | 'MMCCCXLII'
    }

    def "convert roman numerals edge cases"() {
        expect:
        decimalToRoman(decimal) == roman
        romanToDecimal(roman) == decimal

        where:
        decimal  | roman
        99       | 'XCIX'
        998      | 'CMXCVIII'
        998      | 'CMXCVIII'
        999      | 'CMXCIX'
        1999     | 'MCMXCIX'
    }

    @FailsWith(IllegalArgumentException.class)
    def "throws exception when invalid decimal"() {
        expect:
        decimalToRoman(decimal) == roman

        where:
        decimal  | roman
        -1       | 'exception expected'
        5000     | 'exception expected'
    }

    @FailsWith(IllegalArgumentException.class)
    def "throws exception when invalid roman numeral"() {
        expect:
        romanToDecimal(roman) == decimal

        where:
        decimal  | roman
        -1       | 'A'
        -1       | 'IIIC'
    }
}