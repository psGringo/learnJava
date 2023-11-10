module.exports = {
    /** Синтакс функции позволяет запустить tsc с tsconfig.json. */
    '*.{ts,tsx}': [() => 'tsc --noEmit'],
    '*.{ts,tsx,less}': ['npm run prettier:pre-commit'],
    'src/**/*.less': ['npm run stylelint:pre-commit'],
    '{src,__mocks__,__tests__}/**/*.{ts,tsx}': ['npm run eslint:pre-commit'],
};