const eslintRules = require('./tools/eslint/rules/1-eslint');
const importRules = require('./tools/eslint/rules/2-import');
const reactRules = require('./tools/eslint/rules/3-react');
const reactJSXRules = require('./tools/eslint/rules/4-react-jsx');
const lodashRules = require('./tools/eslint/rules/5-lodash');
const typescriptRules = require('./tools/eslint/rules/6-typescript');
const a11yRules = require('./tools/eslint/rules/7-jsx-a11y');

module.exports = {
    root: true,
    env: { browser: true, es6: true, jest: true },

    extends: [
        'eslint:recommended',
        'plugin:import/recommended',
        'plugin:react/recommended',
        'plugin:lodash/recommended',
        'plugin:@typescript-eslint/eslint-recommended',
        'plugin:@typescript-eslint/recommended',
        'airbnb',
        'airbnb/hooks',
        'plugin:jest-dom/recommended',
        'plugin:testing-library/react',
        'prettier',
    ],

    globals: {
        Atomics: 'readonly',
        SharedArrayBuffer: 'readonly',
        JSX: 'readonly',
        page: 'readonly',
        reporter: 'readonly',
        BUILD_VERSION: 'readonly',
        LATEST_COMMIT_HASH: 'readonly',
    },

    parser: '@typescript-eslint/parser',

    parserOptions: {
        ecmaFeatures: { jsx: true },
        ecmaVersion: 2020,
        sourceType: 'module',
    },

    settings: {
        'import/resolver': {
            node: { extensions: ['.js', '.jsx', '.ts', '.tsx'] },
        },
    },

    plugins: ['react', 'lodash', '@typescript-eslint', 'testing-library', 'jest-dom', 'jsx-a11y'],

    rules: {
        // убираем ругань на CRLF под виндой
        'linebreak-style': ['error', process.platform === 'win32' ? 'windows' : 'unix'],

        // Убираем проверку типов react/prop-types, эти функции выполняет TS
        'react/prop-types': 0,

        // Отключаем правила для пустого конструктора
        'no-useless-constructor': 'off',

        // Правила на пустые функции, они необходимы для задания начального значения в контексте, что бы не использовать null
        'lodash/prefer-noop': 'off',
        'no-empty-function': 'off',
        '@typescript-eslint/no-empty-function': 'off',

        // Правила ESLint.
        ...eslintRules,

        // Правила импортов.
        ...importRules,

        // Правила React.
        ...reactRules,

        // Правила React JSX.
        ...reactJSXRules,

        // Правила Lodash.
        ...lodashRules,

        // Правила TypeScript.
        ...typescriptRules,

        // Правила JSX a11y.
        ...a11yRules,
    },
    ignorePatterns: ['..eslintrc.js', 'tools/eslint/rules', 'node_modules', 'out'],
};