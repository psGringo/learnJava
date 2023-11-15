import {Button} from '@mui/material';
import React from 'react';
import {useDispatch, useSelector} from 'react-redux';
import {AlienApiService} from '@/ApiService/AlienApiService';
import styles from '@/Components/EnterName/Styles.less';
import {setAppState} from '@/Store/ActionCreators';
import {IAppState, IRootState} from '@/Types/StoreModel';
import {getButtonCaptions, isTypeIsNextState} from '@/Utils';

export const Buttons: React.FC = () => {

    const {nextState, payload} = useSelector<IRootState, IAppState>((s) => s.app);

    const dispatch = useDispatch();

    const getButtonHandler = (commandName: string, payload: string | undefined) => {
        const handler = () => {

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
                    /* eslint-disable-next-line max-len */
                    color="primary"
                    onClick={getButtonHandler(command.name, command.needPayload ? payload : undefined)}
                    size="large"
                    variant="contained">
                    {getButtonCaptions().get(command.name)}
                </Button>);
            });

            const node: React.ReactNode = <div className={styles.buttons}>{buttons}</div>;

            return node;
        }

        return null;
    }

    return (<div className={styles.buttons}>
        {getButtons()}
    </div>);
}
