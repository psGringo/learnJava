import React from 'react';
import {useSelector} from 'react-redux';
import styles from './Styles.less';
import {IAppState, IRootState} from '@/Types/StoreModel';
import {isTypeIsNextState, useTranslationTyped} from '@/Utils';

export const GameResults: React.FC = () => {

    const p = new Promise((resolve) => {resolve(123)});

    const f = () => {};

    p.then((r) => {})
    
    
    const {t} = useTranslationTyped();

    const {nextState} = useSelector<IRootState, IAppState>((s) => s.app);

    const getResults = () => {
        if (isTypeIsNextState(nextState)) {

            const userNames = nextState.stateMachineResponse.gameResults?.map((gameResult, key) => {
                return (
                    <div className={styles.result} key={key}>
                        <div>
                            {gameResult.playerName}
                        </div>
                    </div>
                )
            })

            const gameNumbers = nextState.stateMachineResponse.gameResults?.map((gameResult, key) => {
                return (
                    <div className={styles.result} key={key}>
                        <div>
                            {gameResult.gameNumber}
                        </div>
                    </div>
                )
            })

            const victoryResults = nextState.stateMachineResponse.gameResults?.map((gameResult, key) => {
                return (
                    <div>
                        <div className={styles.result} key={key}>
                            <div>
                                {gameResult.isVictory ? t('App.results.victory') : t('App.results.failure')}
                            </div>
                        </div>
                    </div>

                )
            })

            return (<div>

                <div className={styles.title}>
                    <div>
                        {t('App.results.userName')}
                    </div>

                    <div>
                        {t('App.results.gameNumber')}
                    </div>

                    <div>
                        {t('App.results.result')}
                    </div>
                </div>

                <div className={styles.title}>
                    <div className={styles.results}>
                        {userNames}
                    </div>

                    <div className={styles.results}>
                        {gameNumbers}
                    </div>

                    <div className={styles.results}>
                        {victoryResults}
                    </div>
                </div>

            </div>);
        }

        return undefined;
    }


    return (<div className={styles.results}>
        {getResults()}
    </div>);
}
