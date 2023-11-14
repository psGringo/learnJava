import React from 'react';
import styles from './Styles.less';

export const NextQuestion: React.FC = () => {

    // const {nextState} = useSelector<IRootState, IAppState>((s) => s.app);
    //
    // const {t} = useTranslationTyped();
    //
    // const dispatch = useDispatch();
    //
    // const [value, setValue] = useState('');
    //
    // const handleStartClick = () => {
    //     AlienApiService.getNextState(ENTER_NAME_COMMAND, value).then((nextState) => {
    //         dispatch(setAppState(nextState));
    //     })
    // }
    //
    // const handleChange = (e) => {
    //     setValue(e.target.value);
    // }
    //
    // const hasStateMachineError: () => (boolean) = () => {
    //     if (isTypeIsNextState(nextState)) {
    //         const response = nextState.stateMachineResponse;
    //
    //         return (response.error != null);
    //     }
    //
    //     return false;
    // }
    //
    // const getLabel = () => {
    //     return  (hasStateMachineError() && t('App.enterName.errorLabel')) || '';
    // }

    return (<div className={styles.container}>
        hello

    </div>);
}
