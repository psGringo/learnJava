import {Button, TextField} from '@mui/material';
import React, {useState} from 'react';
import {useDispatch, useSelector} from 'react-redux';
import styles from './Styles.less';
import {AlienApiService} from '@/ApiService/AlienApiService';
import {setAppState} from '@/Store/ActionCreators';
import {getButtonCaptions, isTypeIsNextState, useTranslationTyped} from '@/Utils';
import {IAppState, IRootState} from '@/Types/StoreModel';

export const EnterName: React.FC = () => {

    const {nextState} = useSelector<IRootState, IAppState>((s) => s.app);

    const {t} = useTranslationTyped();

    const dispatch = useDispatch();

    const [value, setValue] = useState('');

    const handleChange = (e) => {
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

    const getButtonHandler = (commandName: string, needPayload: boolean) => {
        const handler = () => {
            const payload = needPayload ? value : undefined;

            AlienApiService.getNextState(commandName, payload).then((nextState) => {
                dispatch(setAppState(nextState));
            })
        }

        return handler;
    }


    const getButtons = () => {
        if (isTypeIsNextState(nextState)) {
            const response = nextState.stateMachineResponse;
            const buttons: React.ReactNode[] = [];

            response.nextCommands?.forEach((command) => {
                buttons.push(<Button
                    color="primary" onClick={getButtonHandler(command.name, command.needPayload)} size="large"
                    variant="contained">
                    {getButtonCaptions().get(command.name)}
                </Button>);
            });

            const node: React.ReactNode = <div className={styles.buttons}>{buttons}</div>;

            return node;
        }

        return null;
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


        {getButtons()}


        <div className={styles.block}>
            {hasStateMachineError() && (<div>
                {isTypeIsNextState(nextState) && nextState.stateMachineResponse?.error}
            </div>)}
        </div>

    </div>);
}
