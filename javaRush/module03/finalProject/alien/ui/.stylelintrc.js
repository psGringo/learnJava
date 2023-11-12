module.exports = {
    extends: 'stylelint-config-standard',
    ignoreFiles: ['out/**/*.css'],
    rules: {
        identation: 4,
        'string-quotes': 'single',
        'selector-pseudo-class-no-unknown': [true],
        'no-descending-specificity': null,
        'property-no-unknown': true
    }
}