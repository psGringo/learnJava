import React from 'react';
import {useSelector} from 'react-redux';
import styles from './Styles.less'
import animalsGroup from '@/Assets/icons/icons8-group-of-animals-48.png'
import sheep from '@/Assets/icons/icons8-sheep-100.png'
import wolf from '@/Assets/icons/icons8-wolf-50.png'
import {IAppState, IRootState} from '@/Types/StoreModel';

interface SquareProps {
    rowNumber: number;
    colNumber: number;
}

// site for downloadig icons
// https://icons8.com/icons/set/wolf

export const Square: React.FC<SquareProps> = ({rowNumber, colNumber}) => {

    const {gameState} = useSelector<IRootState, IAppState>((state) => state.app)

    const images = new Map<string, string>();

    images.set('wolf', wolf);
    images.set('sheep', sheep);
    images.set('animalsGroup', animalsGroup);

    const animals = gameState.animals.filter(
        (animal) => animal.positionX === colNumber && animal.positionY === rowNumber
    );

    const grassItem = gameState.grass.find(
        (grassItem) => grassItem.positionX === colNumber && grassItem.positionY === rowNumber
    );


    const getImage: (animals) => string | undefined = () => {

        if (animals.length > 1) {
            return animalsGroup;
        }

        if (animals.length === 1) {
            const animal = animals[0];

            if (animal?.name.includes('wolf')) {
                return images.get('wolf');
            }

            if (animal?.name.includes('sheep')) {
                return images.get('sheep');
            }
        }

        return undefined;
    }

    const image = getImage(animals);

    return (
        <div className={grassItem ? styles.squareGreen : styles.square}>
            {image && <img alt="pic" className={styles.pic} src={image}/>}
        </div>
    )
}
