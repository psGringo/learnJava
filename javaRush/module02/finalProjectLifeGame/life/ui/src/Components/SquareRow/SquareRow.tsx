import React from 'react';
import styles from './Styles.less'
import {Square} from '@/Components/Square/Square';


interface SquareRowProps {
    colCount: number;
    rowIndex: number;
}

export const SquareRow: React.FC<SquareRowProps> = ({rowIndex, colCount}) => {

    const getSquares = () => {
        const res: React.ReactNode[] = [];

        for (let i = 0; i < colCount; i+=1) {
            res.push(<Square colNumber={i} rowNumber={rowIndex}/>);
        }

        return res;
    }

    const squares = getSquares();

    return (
        <div className={styles.square}>
            {squares}
        </div>
    )
}
