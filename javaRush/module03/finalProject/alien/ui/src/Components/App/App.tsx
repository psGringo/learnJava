import {Button} from '@mui/material';
import React, {useEffect, useState} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import styles from './Styles.less'
import {EnterName} from '@/Components/EnterName/EnterName';
import {Start} from '@/Components/Start/Start';
import {ENTER_NAME_COMMAND, EXIT_COMMAND, NEXT_QUESTION_COMMAND, START_COMMAND} from '@/Const';
import {IAppState, IRootState} from '@/Types/StoreModel';
import {isTypeIsNextState, useTranslationTyped} from '@/Utils';
import {NextQuestion} from '@/Components/NextQuestion/NextQuestion';

export const getRenderStates = () => {
    const stateMap = new Map<string, React.ReactNode>();

    stateMap.set(START_COMMAND, <Start/>);
    stateMap.set(ENTER_NAME_COMMAND, <EnterName/>);
    stateMap.set(NEXT_QUESTION_COMMAND, <NextQuestion/>);
    stateMap.set(EXIT_COMMAND, <Start/>);

    return stateMap;
}


export const App: React.FC = () => {

    const {name, nextState} = useSelector<IRootState, IAppState>((s) => s.app);

    const {i18n} = useTranslationTyped();

    const dispatch = useDispatch();

    const [lang, setLang] = useState(i18n.language);

    useEffect(() => {

    }, [dispatch])

    const getLang = () => {
        return lang === 'ru' ? 'en' : 'ru';
    }

    const handleTranslationClick = () => {
        setLang(getLang());
        i18n.changeLanguage(getLang());
    }


    const getComponent = (): React.ReactNode | undefined => {
        if (isTypeIsNextState(nextState)) {
            const renderComponentName =
                nextState.stateMachineResponse?.renderComponentName;

            if (renderComponentName === undefined) {
                return undefined;
            }

            return getRenderStates().get(renderComponentName);
        }

        return undefined;
    }

    return (
        <div className={styles.app}>
            <div className={styles.topPanel}>
                <div className={styles.nameAndDescription}>
                    <div className={styles.name}>
                        {name}
                    </div>
                </div>

                <div className={styles.button}>
                    <Button
                        className={styles.button} color="primary" onClick={handleTranslationClick}
                        variant="contained">{getLang()}
                    </Button>
                </div>
            </div>
            {getComponent()}
        </div>)
}
