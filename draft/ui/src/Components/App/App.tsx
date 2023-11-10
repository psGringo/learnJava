import {Button} from '@mui/material';
import React, {useEffect, useState} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import styles from './Styles.less'
import {GreetingsApiService} from '@/ApiService/GreetingsApiService';
import {setAppState} from '@/Store/ActionCreators';
import {IAppState, IRootState} from '@/Types/StoreModel';
import {useTranslationTyped} from '@/Utils';

export const App: React.FC = () => {

    const {t, i18n} = useTranslationTyped();

    const {greeting} = useSelector<IRootState, IAppState>((state) => state.app)

    const dispatch = useDispatch();

    const [lang, setLang] = useState(i18n.language);

    useEffect(() => {
        GreetingsApiService.sayHello().then((greeting) => {
            dispatch(setAppState(greeting));
        })
    }, [dispatch])

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
                    <Button
                        className={styles.button} color="primary" onClick={handleButtonClick}
                        variant="contained">{getLang()}</Button>
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
