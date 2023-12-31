module.exports = {
    /*
     * Разрешается не указывать расширения файлов для указанных типов.
     * https://github.com/benmosher/eslint-plugin-import/blob/master/docs/rules/extensions.md
     */
    'import/extensions': [0, 'ignorePackages', { js: 'never', jsx: 'never', ts: 'never', tsx: 'never' }],

    /*
     * Разрешается использовать именованные экспорты (кроме асинхронных компонентов).
     * https://github.com/benmosher/eslint-plugin-import/blob/master/docs/rules/prefer-default-export.md
     */
    'import/prefer-default-export': 0,

    /*
     * Запрещены 'overhead' импорты, например, при обращении к 'index'.
     * https://github.com/benmosher/eslint-plugin-import/blob/master/docs/rules/no-useless-path-segments.md
     */
    'import/no-useless-path-segments': [2, { noUselessIndex: true }],

    /*
     * Проверка резолва происходит за счет сборщика, а не линта.
     * https://github.com/benmosher/eslint-plugin-import/blob/master/docs/rules/no-unresolved.md
     */
    'import/no-unresolved': 0,

    /*
     * Сортировка импортов по группам, а внутри них по алфавиту.
     * https://github.com/benmosher/eslint-plugin-import/blob/master/docs/rules/order.md
     */
    'import/order': [1, { alphabetize: { order: 'asc', caseInsensitive: true } }],

    /*
     * Отключение наблюдение за импортами зависимостей
     * https://github.com/benmosher/eslint-plugin-import/blob/master/docs/rules/no-extraneous-dependencies.md
     */
    'import/no-extraneous-dependencies': 'off',
};