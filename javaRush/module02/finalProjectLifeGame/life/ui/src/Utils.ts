import {useTranslation} from 'react-i18next';
import {Error, GameState} from '../openapi-generated/lifegame';
import {i18Instance} from '@/i18n/config';
import {I18NEXT_NAMESPACE} from '@/i18n/Const';

export const useTranslationTyped = () => {
    const {t, i18n} = useTranslation(I18NEXT_NAMESPACE, {i18n: i18Instance});
    const myT = t<string>;

    return {t: myT, i18n};
}

export const isTypeIsGameState = (obj: GameState | Error): obj is GameState => {
    return 'gameMap' in obj;
}
