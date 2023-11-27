import {Button} from '@mui/material';
import React, {useEffect, useState} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import styles from './Styles.less'
import {GameApiService} from '@/ApiService/GameApiService';
import {GreetingsApiService} from '@/ApiService/GreetingsApiService';
import {Grid} from '@/Components/Grid/Grid';
import {Statistics} from '@/Components/Statistics/Statistics';
import {setGameState, setGreeting} from '@/Store/ActionCreators';
import {IAppState, IRootState} from '@/Types/StoreModel';
import {isTypeIsGameState, useTranslationTyped} from '@/Utils';

export const App: React.FC = () => {

    const {t, i18n} = useTranslationTyped();

    const {greeting} = useSelector<IRootState, IAppState>((state) => state.app)

    const dispatch = useDispatch();

    const [lang, setLang] = useState(i18n.language);

    useEffect(() => {
        GreetingsApiService.sayHello().then((greeting) => {
            dispatch(setGreeting(greeting));
        });
    }, [dispatch])


    useEffect(() => {

        let isValid = true;

        let intervalCall;

        const TWO_SECONDS = 5000;

        const getGameState = async () => {

            const gameState = await GameApiService.getGameState();

            if (isTypeIsGameState(gameState)) {
                dispatch(setGameState(gameState));
            }


            // after await if the component unmounts, and this scope
            // becomes stale, skip futher execution
            // so the interval wont be started, and wont break in dev mode where useEffect runs twice
            if (!isValid) {
                return;
            }

            // Gets new information every 2 seconds
            intervalCall = setInterval(async () => {
                const gameState = await GameApiService.getGameState();

                if (isTypeIsGameState(gameState)) {
                    dispatch(setGameState(gameState));
                }

                // might want to check valid scope inside
                // retrieveCurrencyConversionRateFunction
                // by passing it a flag to skip execution on a unmounted component scope

            }, TWO_SECONDS);


            // eslint-disable-next-line max-len
            // Have to wait until currencyConversionRateFunction 
            // is loaded before the map function is called on it in the render view otherwise will have error
            // setLoading(false);
        }

        getGameState();

        return () => {

            isValid = false

            // if interval was not stared, dont clear it
            if (intervalCall) {
                clearInterval(intervalCall);
            }
        };

    }, [dispatch]);


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
                    {t('App.name')}
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
            <Statistics/>
            <Grid/>
        </div>)
}
