import {Button} from '@mui/material';
import React from 'react';
import {useDispatch} from 'react-redux';
import styles from './Styles.less';
import {AlienApiService} from '@/ApiService/AlienApiService';
import {setAppState} from '@/Store/ActionCreators';
import {useTranslationTyped} from '@/Utils';
import {START_COMMAND} from '@/Const';

export const Start: React.FC = () => {

    const {t} = useTranslationTyped();

    const dispatch = useDispatch();

    const handleNext = () => {

        AlienApiService.getNextState(START_COMMAND).then((nextState) => {
            dispatch(setAppState(nextState));
        })
    }

    return (<div className={styles.container}>
        <Button
            color="primary" onClick={handleNext}
            variant="contained">
            {t('App.start')}
        </Button>
        <div className={styles.block}>
            {t('App.description')}
        </div>
        <div className={styles.block}>
            {t('App.legend')}
        </div>
    </div>);
}
