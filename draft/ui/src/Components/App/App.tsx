import {Button} from '@mui/material';
import React, {useEffect, useState} from 'react';

import {TFunction, useTranslation} from 'react-i18next';
import {useSelector} from 'react-redux';
import styles from './Styles.less'
import {GreetingsApiService} from '@/ApiService/GreetingsApiService';
import {i18Instance} from '@/i18n/config';
import {I18NEXT_NAMESPACE} from '@/i18n/Const';
import {setAppState} from '@/Store/ActionCreators';
import {appStore} from '@/Store/ConfigureStore';
import {IAppState, IRootState} from '@/Types/StoreModel';




const myUseTranslation = () => {
    const {t, i18n} = useTranslation(I18NEXT_NAMESPACE, {i18n: i18Instance});
    const myT = t<string>;

    return {t: myT, i18n};
}

export const App: React.FC = () => {


    const {t, i18n} = myUseTranslation();

    const {greeting} = useSelector<IRootState, IAppState>((state) => state.app)


    const [lang, setLang] = useState(i18n.language);

    useEffect(() => {
        GreetingsApiService.sayHello().then((greeting) => {
            appStore.dispatch(setAppState(greeting));
        })
    }, [])

    const getLang = () => {
        return lang === 'ru' ? 'en' : 'ru';
    }

    const handleButtonClick = () => {
        setLang(getLang());
        i18n.changeLanguage(getLang());
    }

    return (
        <div className={styles.app}>
            <div className={styles.topPanel}>
                <div className={styles.name}>
                    123456
                </div>
                <div className={styles.button}>
                    <Button className={styles.button} color="primary" onClick={handleButtonClick} variant="contained">{getLang()}</Button>
                </div>
            </div>
            <div className={styles.container}>
                <div className={styles.block}>
                    {t('App.description')}
                </div>
                <div className={styles.block}>
                    {greeting?.message || 'backend greeting expected...'}
                </div>
            </div>
        </div>)
}
