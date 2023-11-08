import {Button, FormControl, FormControlLabel, FormLabel, Radio, RadioGroup} from '@mui/material';
import React, {useEffect, useState} from 'react';
import {useSelector} from 'react-redux';
import styles from './Styles.less'
import {GreetingsApiService} from '@/ApiService/GreetingsApiService';
import {setAppState} from '@/Store/ActionCreators';
import {appStore} from '@/Store/ConfigureStore';
import {IAppState, IRootState} from '@/Types/StoreModel';
import {i18Instance} from "@/i18n/config";
import {I18NEXT_NAMESPACE} from "@/i18n/Const";
import {useTranslation} from "react-i18next";

export const App: React.FC = () => {

    const [value, setValue] = React.useState('female');

    const {name, greeting} = useSelector<IRootState, IAppState>((state) => state.app)

    const {t, i18n} = useTranslation(I18NEXT_NAMESPACE, {i18n: i18Instance});
    const [lang, setLang] = useState(i18n.language);

    useEffect(() => {
        GreetingsApiService.sayHello().then((greeting) => {
            appStore.dispatch(setAppState({name: 'draft app', greeting}))
        })
    }, [])


    const handleChange = (event: React.ChangeEvent<HTMLInputElement>) => {

        setLang('en');

        setValue((event.target as HTMLInputElement).value);
    };

    const handleButtonClick = () => {
        if (i18n.language === 'ru') {
            setLang('en');
            i18n.changeLanguage('en');
        } else {
            setLang('en');
            i18n.changeLanguage('ru');
        }
    }

    return (
        <div className={styles.app}>
            <div className={styles.block}>
                App name is {name}
            </div>
            <div className={styles.block}>
                current language is {lang}
            </div>

            <div className={styles.block}>
                {t<string>('App.description')}
            </div>
            <div className={styles.block}>
                {greeting?.message}
            </div>
            <Button variant="contained" onClick={handleButtonClick}>Hello World</Button>
            <div className={styles.block}>
                <FormControl>
                    <FormLabel id="demo-controlled-radio-buttons-group">Gender</FormLabel>
                    <RadioGroup
                        aria-labelledby="demo-controlled-radio-buttons-group"
                        name="controlled-radio-buttons-group"
                        onChange={handleChange}
                        value={value}
                    >
                        <FormControlLabel control={<Radio/>} label="Female" value="female"/>
                        <FormControlLabel control={<Radio/>} label="Male" value="male"/>
                    </RadioGroup>
                </FormControl>
            </div>
        </div>)
}
