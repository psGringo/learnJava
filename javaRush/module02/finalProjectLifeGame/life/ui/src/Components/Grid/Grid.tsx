import React from 'react';
import {useSelector} from 'react-redux';
import styles from './Styles.less'
import {SquareRow} from '@/Components/SquareRow/SquareRow';
import {IAppState, IRootState} from '@/Types/StoreModel';

export const Grid: React.FC = () => {

    const {gameState} = useSelector<IRootState, IAppState>((state) => state.app)

    const getSquareRows = () => {
        const res: React.ReactNode[] = [];

        for (let i = 0; i < gameState.gameMap.maxY; i += 1) {
            res.push(<SquareRow colCount={gameState.gameMap.maxX} rowIndex={i}/>);
        }

        return res;
    }

    const squareRoes = getSquareRows();


    return (
        <div className={styles.square}>
            {squareRoes}
        </div>
    )
}
