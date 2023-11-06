import React, {useEffect} from "react";
import styles from './Styles.less'
import {GreetingsApiService} from "@/ApiService/GreetingsApiService";
import {useSelector} from "react-redux";
import {IAppState, IRootState} from "@/Types/StoreModel";
import {appStore} from "@/Store/ConfigureStore";
import {setAppState} from "@/Store/ActionCreators";

export const App: React.FC = () => {

    const {name, greeting} = useSelector<IRootState, IAppState>((state) => state.app)

    useEffect(() => {
        GreetingsApiService.sayHello().then((greeting) => {
            appStore.dispatch(setAppState({name: 'draft app', greeting}))
        })
    }, [])

    return (
        <div className={styles.app}>
            <div>
                App name is: {name}
            </div>

            <div>
                {greeting?.message}
            </div>
        </div>)
}