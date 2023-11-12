module.exports = {
    /*
     * Отступы = 4 пробела.
     * Код должен быть опрятным и не сливаться в кучу.
     * https://eslint.org/docs/rules/indent
     */
    indent: ['error', 4, { SwitchCase: 1 }],

    /*
     * Запрещается использовать 'магические' числа.
     * Все числа должны быть указаны в виде констант.
     * https://eslint.org/docs/rules/no-magic-numbers
     */
    'no-magic-numbers': ['error', { ignore: [0, 1], detectObjects: false, enforceConst: true }],

    /*
     * Перевод строки после фигурной скобки в объектах.
     * Фигурные скобки должны быть согласованы между собой и переноситься синхронизованно.
     * https://eslint.org/docs/rules/object-curly-newline
     */
    'object-curly-newline': ['error', { consistent: true }],

    /*
     * Разрешается использование только одинарных кавычек.
     * https://eslint.org/docs/rules/quotes
     */
    quotes: ['error', 'single'],

    /*
     * В JSX-элементах разрешается использование только двойных кавычек.
     * https://eslint.org/docs/rules/jsx-quotes
     */
    'jsx-quotes': ['error', 'prefer-double'],

    /*
     * Разрешается использовать сущности до их объявления. TODO: Включить после поднятия версии реакта!
     * Сейчас существует баг с версией React https://stackoverflow.com/questions/63818415/react-was-used-before-it-was-defined
     * Поэтому вместо этого правила, сейчас включено @typescript-eslint/no-use-before-define.
     * https://eslint.org/docs/rules/no-use-before-define
     */
    'no-use-before-define': 'off',

    /*
     * Разрешается использовать имена переменных, как и в родительской области видимости.
     * https://eslint.org/docs/rules/no-shadow
     */
    'no-shadow': 'off',

    /*
     * Семантика стрелочных функций не регламентируется.
     * Возвращать значение сразу или явно указывать return - это непринципиальный кейс.
     * https://eslint.org/docs/rules/arrow-body-style
     */
    'arrow-body-style': 'off',

    /*
     * Максимальное количество символов в строке.
     * В .editorconfig поставляется аналогичное значение.
     * https://eslint.org/docs/rules/max-len
     */
    'max-len': ['error', { code: 120 }],

    /*
     * Максимальное кол-во строк в файле = 1000. TODO: Уменьшить до 500!
     * Файлы должны быть максимально компактными и легко читаемыми.
     * https://eslint.org/docs/rules/max-lines
     */
    'max-lines': ['error', 1000],

    /*
     * Запрещается использование класса console.
     * Единственное исключение = console.error().
     * https://eslint.org/docs/rules/no-console
     */
    'no-console': ['error', { allow: ['warn', 'error'] }],

    /*
     * Разрешается оставлять неиспользованные переменные.
     * Правило включается посредством '@typescript-eslint/no-unused-vars'.
     * Потому что только TS умеет работать с переменными в типизации.
     * https://eslint.org/docs/rules/no-unused-vars
     */
    'no-unused-vars': 'off',

    /*
     * Все файлы должны заканчиваться пустой строкой.
     * Это просто правило хорошего тона.
     * https://eslint.org/docs/rules/eol-last
     */
    'eol-last': ['error', 'always'],

    /*
     * Запрещается использовать стрелки там, где они могут быть спутаны со знаком сравнения.
     * https://eslint.org/docs/rules/no-confusing-arrow
     */
    'no-confusing-arrow': 'error',

    /*
     * Аргументы стрелочной функции всегда должны быть в скобках.
     * Оставление 'висячих' аргументов выглядит неаккуратно в большой массе кода.
     * https://eslint.org/docs/rules/arrow-parens
     */
    'arrow-parens': ['error', 'always'],

    /*
     * Запрещено изменять аргументы функции.
     * Это может привести к неявному изменению значения.
     * https://eslint.org/docs/rules/no-param-reassign
     */
    'no-param-reassign': 'off',

    /*
     * Разрешается использовать и шаблоны и конкатенацию.
     * Конкатенация зачастую выглядит аккуратней.
     * https://eslint.org/docs/rules/prefer-template
     */
    'prefer-template': 'off',

    /*
     * Запрещается javascript:void(0) и подобное.
     * https://eslint.org/docs/rules/no-script-url
     */
    'no-script-url': 'error',

    /*
     * Разрешается любой тип ошибки в 'catch' промиса.
     * https://eslint.org/docs/rules/prefer-promise-reject-errors
     */
    'prefer-promise-reject-errors': 'off',

    /*
     * Правила пустых строк после или до указанных команд.
     * Все правила сводятся к аккуратности итогового кода.
     * https://eslint.org/docs/rules/padding-line-between-statements
     */
    'padding-line-between-statements': [
        'error',
        // Пустая строка после импортов.
        { blankLine: 'always', prev: 'import', next: '*' },
        { blankLine: 'any', prev: 'import', next: 'import' },

        // Пустая строка перед return.
        { blankLine: 'always', prev: '*', next: 'return' },

        // Пустая строка после объявления переменных.
        { blankLine: 'always', prev: ['const', 'let', 'var'], next: '*' },
        {
            blankLine: 'any',
            prev: ['const', 'let', 'var'],
            next: ['const', 'let', 'var'],
        },
    ],

    /*
     * Управление стилем комментариев.
     * https://eslint.org/docs/rules/multiline-comment-style
     */
    'multiline-comment-style': 'off',

    /*
     * Разрешается не использовать 'default' конструкцию в 'switch'.
     * https://eslint.org/docs/rules/default-case
     */
    'default-case': 'off',

    /*
     * Деструктуризация в массивах и объектах необязательна.
     * Мы часто обращаемся к элементу массива по его индексу, а данное правило этому противоречит.
     * https://eslint.org/docs/rules/prefer-destructuring
     */
    'prefer-destructuring': 'off',

    /*
     * Запрещается отделять спред-оператор с переменной пробелом.
     * https://eslint.org/docs/rules/rest-spread-spacing
     */
    'rest-spread-spacing': ['error', 'never'],

    /*
     * Правила переноса скобок массива.
     * Скобки должны быть согласованы между собой и переноситься синхронизовано.
     * https://eslint.org/docs/rules/array-bracket-newline
     */
    'array-bracket-newline': ['error', 'consistent'],

    /*
     * Разрешаются неиспользуемые выражения.
     * Необходимо для выражений типа 'a && c || b()'.
     * https://eslint.org/docs/rules/no-unused-expressions
     */
    'no-unused-expressions': 'off',
};