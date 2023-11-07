import english from './en/english.json'
import russian from './ru/russian.json'
import i18next, {i18n} from "i18next";
import {I18NEXT_NAMESPACE} from "./Const";

const resources = {
    ru: {
        dictionary: russian,
    },
    en: {
        dictionary: english
    }
}

export const i18Object: i18n = i18next.createInstance();
i18Object.init({
    returnNull: false,
    lng: 'ru',
    ns: [I18NEXT_NAMESPACE],
    resources,
    fallbackLng: 'ru'
})