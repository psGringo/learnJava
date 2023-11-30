module.exports = {
    /*
     * Данные правила (jsx-a11y) пришли из стайлгайда airbnb.
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y
     */

    /*
     * Видимые элементы должны иметь заполненный атрибут alt
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y/blob/master/docs/rules/alt-text.md
     */
    'jsx-a11y/alt-text': ['error', { elements: ['img', 'object', 'area', 'input[type="image"]'] }],

    /*
     * Видимые элементы должны иметь заполненный атрибут alt
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y/blob/master/docs/rules/anchor-has-content.md
     */
    'jsx-a11y/anchor-has-content': 'warn',

    /*
     * Для композитных элементов, внутренний фокус должен быть равен нулю или быть положительным
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y/blob/master/docs/rules/aria-activedescendant-has-tabindex.md
     */
    'jsx-a11y/aria-activedescendant-has-tabindex': 'warn',

    /*
     * Корректные названия для aria-* атрибутов
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y/blob/master/docs/rules/aria-props.md
     */
    'jsx-a11y/aria-props': 'warn',

    /*
     * Валидные значения для aria элементов
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y/blob/master/docs/rules/aria-proptypes.md
     */
    'jsx-a11y/aria-proptypes': 'warn',

    /*
     * Валидные значения для aria ролей
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y/blob/master/docs/rules/aria-role.md
     * Список ролей https://www.w3.org/TR/wai-aria/#role_definitions
     */
    'jsx-a11y/aria-role': 'warn',

    /*
     * Не использовать элементы, не поддерживаемые aria
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y/blob/master/docs/rules/aria-unsupported-elements.md
     */
    'jsx-a11y/aria-unsupported-elements': 'warn',

    /*
     * Дублирование кликов мыши событиями на клавиатуре
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y/blob/master/docs/rules/click-events-have-key-events.md
     */
    'jsx-a11y/click-events-have-key-events': 'warn',

    /*
     * Заголовок дожен иметь контент и он не должен быть спрятан
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y/blob/master/docs/rules/heading-has-content.md
     */
    'jsx-a11y/heading-has-content': 'warn',

    /*
     * Атрибут html должен иметь тег lang
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y/blob/master/docs/rules/html-has-lang.md
     */
    'jsx-a11y/html-has-lang': 'warn',

    /*
     * Элементы <iframe> должны иметь уникальное имя
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y/blob/master/docs/rules/iframe-has-title.md
     */
    'jsx-a11y/iframe-has-title': 'warn',

    /*
     * Избыточное описание для тега img, скринридеры уже объявляют img элементы картинками
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y/blob/master/docs/rules/img-redundant-alt.md
     */
    'jsx-a11y/img-redundant-alt': ['warn', { elements: ['img'], words: ['Иконка', 'Картинка'] }],

    /*
     * Все активные элементы должны уметь получать фокус
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y/blob/master/docs/rules/interactive-supports-focus.md
     */
    'jsx-a11y/interactive-supports-focus': 'warn',

    /*
     * Медиа-теги audio, video и др. должны иметь специальный атрибут название для скринридера
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y/blob/master/docs/rules/media-has-caption.md
     */
    'jsx-a11y/media-has-caption': 'warn',

    /*
     * Дублирование событий мыши событиями на клавиатуре для пользователей только клавиатуры
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y/blob/master/docs/rules/mouse-events-have-key-events.md
     */
    'jsx-a11y/mouse-events-have-key-events': 'warn',

    /*
     * Запрет атрибута accessKey, чтобы не создавать путаницу скринридерам
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y/blob/master/docs/rules/no-access-key.md
     */
    'jsx-a11y/no-access-key': 'warn',

    /*
     * Запрет на использование тега autoFocus
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y/blob/master/docs/rules/no-autofocus.md
     */
    'jsx-a11y/no-autofocus': ['warn', { ignoreNonDOM: true }],

    /*
     * Запрет на использование элементов <marquee> и <blink>
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y/blob/master/docs/rules/no-distracting-elements.md
     */
    'jsx-a11y/no-distracting-elements': 'warn',

    /*
     * Не интерактивные aria роли не должны использоваться с интерактивными элементами, кроме <tr>
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y/blob/master/docs/rules/no-interactive-element-to-noninteractive-role.md
     */
    'jsx-a11y/no-interactive-element-to-noninteractive-role': ['warn', { tr: ['none', 'presentation'] }],

    /*
     * Не использовать обработчики в неинтерактивных элементах
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y/blob/master/docs/rules/no-noninteractive-element-interactions.md
     */
    'jsx-a11y/no-noninteractive-element-interactions': [
        'warn',
        {
            handlers: ['onClick', 'onMouseDown', 'onMouseUp', 'onKeyPress', 'onKeyDown', 'onKeyUp'],
        },
    ],

    /*
     * Не интерактивные элементы не должны использоваться с интерактивными ролями
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y/blob/master/docs/rules/no-static-element-interactions.md
     */
    'jsx-a11y/no-noninteractive-element-to-interactive-role': [
        'warn',
        {
            ul: ['listbox', 'menu', 'menubar', 'radiogroup', 'tablist', 'tree', 'treegrid'],
            ol: ['listbox', 'menu', 'menubar', 'radiogroup', 'tablist', 'tree', 'treegrid'],
            li: ['menuitem', 'option', 'row', 'tab', 'treeitem'],
            table: ['grid'],
            td: ['gridcell'],
        },
    ],

    /*
     * Предупреждать об элементах, которые не взаимодействуют с пользователем и
     * на них установлен tabindex
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y/blob/master/docs/rules/no-noninteractive-tabindex.md
     */
    'jsx-a11y/no-noninteractive-tabindex': [
        'warn',
        {
            tags: [],
            roles: ['tabpanel'],
        },
    ],

    /*
     * Избыточное описание ролей, для элементов, которые и так
     * семантически понятны, например <img role="img"> (.etc)
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y/blob/master/docs/rules/no-redundant-roles.md
     */
    'jsx-a11y/no-redundant-roles': 'warn',

    /*
     * Присваивание роли для статических элементов
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y/blob/master/docs/rules/no-static-element-interactions.md
     */
    'jsx-a11y/no-static-element-interactions': [
        'warn',
        {
            handlers: ['onClick', 'onMouseDown', 'onMouseUp', 'onKeyPress', 'onKeyDown', 'onKeyUp'],
        },
    ],

    /*
     * Соответствие aria атрибутов aria ролям
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y/blob/master/docs/rules/role-has-required-aria-props.md
     */
    'jsx-a11y/role-has-required-aria-props': 'warn',

    /*
     * Соответствие aria атрибутов aria ролям
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y/blob/master/docs/rules/role-supports-aria-props.md
     */
    'jsx-a11y/role-supports-aria-props': 'warn',

    /*
     * Атрибут scope должен использоваться только на элементе <th>
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y/blob/master/docs/rules/scope.md
     */
    'jsx-a11y/scope': 'warn',

    /*
     * Таб-индекс элементов равен 0 либо меньше 0, чтобы не нарушать скролл табом по странице
     * https://github.com/jsx-eslint/eslint-plugin-jsx-a11y/blob/master/docs/rules/tabindex-no-positive.md
     */
    'jsx-a11y/tabindex-no-positive': 'warn',
};