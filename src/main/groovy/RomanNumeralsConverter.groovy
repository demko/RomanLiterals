class RomanNumeralsConverter {
    static def romanNumerals = [
            from1To9 :     [ '', 'I', 'II', 'III', 'IV', 'V', 'VI', 'VII', 'VIII', 'IX' ],
            from10To90 :   [ '', 'X', 'XX', 'XXX', 'XL', 'L', 'LX', 'LXX', 'LXXX', 'XC' ],
            from100To900 : [ '', 'C', 'CC', 'CCC', 'CD', 'D', 'DC', 'DCC', 'DCCC', 'CM' ],
            from1000 :     [ '', 'M', 'MM', 'MMM' ]
    ]

    static def decimalToRoman(int decimal) {
        ensureValidDecimalNumber(decimal)
        def roman1To9 = romanNumerals.from1To9[decimal % 10]
        def roman10To90 = romanNumerals.from10To90[(decimal / 10 as int) % 10]
        def roman100To900 = romanNumerals.from100To900[(decimal / 100 as int) % 10]
        def romanMillennia = romanNumerals.from1000[(decimal / 1000 as int) % 10]
        romanMillennia + roman100To900 + roman10To90 + roman1To9
    }

    static def romanToDecimal(String roman) {
        roman = roman.toUpperCase()
        def resultMillennia = convertRomanToDecimal(roman, romanNumerals.from1000, 1000)
        def result100To900 = convertRomanToDecimal(resultMillennia.remainder, romanNumerals.from100To900, 100)
        def result10To90 = convertRomanToDecimal(result100To900.remainder, romanNumerals.from10To90, 10)
        def result1To9 = convertRomanToDecimal(result10To90.remainder, romanNumerals.from1To9, 1)
        if (result1To9.remainder) {
            throw new IllegalArgumentException("roman ${roman} has invalid ${result1To9.remainder} value")
        }
        resultMillennia.decimal + result100To900.decimal + result10To90.decimal + result1To9.decimal
    }

    private static def convertRomanToDecimal(roman, conversionTable, multiplier) {
        def decimal = 0
        int index = conversionTable.size() - 1
        conversionTable.reverse().each { numeral ->
            if (numeral && roman.startsWith(numeral)) {
                roman -= numeral
                decimal = index * multiplier
            }
            index--
        }
        [decimal: decimal, remainder: roman]
    }

    private static def ensureValidDecimalNumber(decimal) {
        if (decimal < 1 || decimal > 3000) {
            throw new IllegalArgumentException("invald decimal ${decimal}")
        }
    }

}
