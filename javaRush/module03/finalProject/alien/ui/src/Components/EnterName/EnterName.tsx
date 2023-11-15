import {TextField} from '@mui/material';
import React, {useState} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import styles from './Styles.less';
import {isTypeIsNextState, useTranslationTyped} from '@/Utils';
import {IAppState, IRootState} from '@/Types/StoreModel';
import {Buttons} from '@/Components/Buttons/Buttons';
import {setPayload} from '@/Store/ActionCreators';

export const EnterName: React.FC = () => {

    const {nextState} = useSelector<IRootState, IAppState>((s) => s.app);

    const {t} = useTranslationTyped();

    const [value, setValue] = useState('');

    const dispatch = useDispatch();
    
    const handleChange = (e) => {
        dispatch(setPayload(e.target.value)); // TODO Think how to optimize
        setValue(e.target.value);
    }

    const hasStateMachineError: () => (boolean) = () => {
        if (isTypeIsNextState(nextState)) {
            const response = nextState.stateMachineResponse;

            return (response.error != null);
        }

        return false;
    }

    const getLabel = () => {
        return (hasStateMachineError() && t('App.enterName.errorLabel')) || '';
    }

    return (<div className={styles.container}>

        <div className={styles.label}>
            <TextField
                error={hasStateMachineError()}
                id="standard-basic"
                label={getLabel()}
                onChange={handleChange}
                placeholder={t('name')}
                value={value}
                variant="standard"
                autoFocus
                fullWidth/>
        </div>

        <Buttons/>

        <div className={styles.block}>
            {hasStateMachineError() && (<div>
                {isTypeIsNextState(nextState) && nextState.stateMachineResponse?.error}
            </div>)}
        </div>

    </div>);
}
