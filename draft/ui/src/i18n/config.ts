import i18next, {i18n} from 'i18next';
import {I18NEXT_NAMESPACE} from './Const';
import english from './en/english.json'
import russian from './ru/russian.json'

const resources = {
    ru: {
        translation: russian,
    },
    en: {
        translation: english
    }
}


export const i18Instance: i18n = i18next.createInstance();
i18Instance.init({
    returnNull: false,
    lng: 'en',
    ns: [I18NEXT_NAMESPACE],
    resources,
    fallbackLng: 'en',
    // initImmediate: true
}).then((t) => {
    console.log('here', t('App.description'));
})
