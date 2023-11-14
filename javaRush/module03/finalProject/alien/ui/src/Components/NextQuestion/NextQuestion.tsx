import React from 'react';
import {useSelector} from 'react-redux';
import styles from './Styles.less';
import {IAppState, IRootState} from '@/Types/StoreModel';
import {isTypeIsNextState} from '@/Utils';

export const NextQuestion: React.FC = () => {

    const {nextState} = useSelector<IRootState, IAppState>((s) => s.app);


    const hasStateMachineError: () => (boolean) = () => {
        if (isTypeIsNextState(nextState)) {
            const response = nextState.stateMachineResponse;

            return (response.error != null);
        }

        return false;
    }


    const getQuestion = () => {
        if (isTypeIsNextState(nextState)) {
            return nextState.stateMachineResponse.question?.text
        }

        return undefined;
    }


    return (<div className={styles.container}>

        <div className={styles.label}>
            {getQuestion()}
        </div>


        <div className={styles.block}>
            {hasStateMachineError() && (<div>
                {isTypeIsNextState(nextState) && nextState.stateMachineResponse?.error}
            </div>)}
        </div>

    </div>);
}
