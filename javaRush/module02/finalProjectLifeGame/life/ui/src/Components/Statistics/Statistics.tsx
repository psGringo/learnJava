import React from 'react';
import {useSelector} from 'react-redux';
import styles from './Styles.less';
import {IAppState, IRootState} from '@/Types/StoreModel';


export const Statistics: React.FC = () => {
    const {gameState} = useSelector<IRootState, IAppState>((state) => state.app)

    return (
        <div className={styles.statistics}>
            <div className={styles.title}>Statistics</div>
            <div>{`totalGodParticles: ${gameState.statistics?.totalGodParticles}`}</div>
            <div>{`totalAliveGrass: ${gameState.statistics?.totalAliveGrass}`}</div>
            <div>{`totalAliveAnimals: ${gameState.statistics?.totalAliveAnimals}`}</div>
            <div>{`totalAliveHerbivorials: ${gameState.statistics?.totalAliveHerbivorials}`}</div>
            <div>{`totalAlivePredators: ${gameState.statistics?.totalAlivePredators}`}</div>
        </div>
    );
}
