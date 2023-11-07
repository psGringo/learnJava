import React, {useEffect} from 'react';
import {useSelector} from 'react-redux';
import styles from './Styles.less'
import {GreetingsApiService} from '@/ApiService/GreetingsApiService';
import {setAppState} from '@/Store/ActionCreators';
import {appStore} from '@/Store/ConfigureStore';
import {IAppState, IRootState} from '@/Types/StoreModel';
import {useTranslation} from "react-i18next";
import {I18NEXT_NAMESPACE} from "../../../i18n/Const";
import {i18Object} from "../../../i18n/config";

export const App: React.FC = () => {

    const {greeting} = useSelector<IRootState, IAppState>((state) => state.app)

    const {t} = useTranslation(I18NEXT_NAMESPACE, {i18n: i18Object});

    useEffect(() => {
        GreetingsApiService.sayHello().then((greeting) => {
            appStore.dispatch(setAppState({name: 'draft app', greeting}))
        })
    }, [])

    return (
        <div className={styles.app}>
            <div>
                {t<string>('App.name')}
            </div>
            <div>
                {greeting?.message}
            </div>
        </div>)
}
